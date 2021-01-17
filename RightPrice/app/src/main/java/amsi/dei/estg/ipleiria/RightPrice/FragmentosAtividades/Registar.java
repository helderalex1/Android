package amsi.dei.estg.ipleiria.RightPrice.FragmentosAtividades;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Categoria;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.R;
import amsi.dei.estg.ipleiria.RightPrice.listener.CategoriaListener;


//classe registar
//carrega o fornulario de registar utilizador
public class Registar extends Fragment implements CategoriaListener {
    //declaração dos objetos
    private EditText edusername;
    private EditText ednome;
    private EditText edemail;
    private EditText edpassword;
    private EditText edpassword2;
    private Spinner spfuncao;
    private Spinner spcategoria;
    private Button btregistar;
    private ImageView imageViewpass1;
    private ImageView imageViewpass2;


    //declaração do array de categorias
    ArrayList<String> arrayListCategorias = new ArrayList<>();

    //declaração de variaveis auxiliares
    private String nome=null,email=null,password=null,password2=null,funcao=null,categoria=null,username=null;
    private int passwordvisivel1=0, passwordvisivel2=0;
    private String messageErroRegistar;

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registar, container, false);

        edusername = view.findViewById(R.id.editusernameRegistar);
        ednome =view.findViewById(R.id.editNomeRegistar);
        edemail = view.findViewById(R.id.editEmailRegistar);
        edpassword= view.findViewById(R.id.editPasswordRegistar1);
        edpassword2=view.findViewById(R.id.editPassworRegistar2);
        spfuncao=view.findViewById(R.id.spinnerfuncaoRegistar);
        spcategoria=view.findViewById(R.id.spCategoriaRegistar);
        btregistar= view.findViewById(R.id.btnRegistar);
        imageViewpass1=view.findViewById(R.id.imageViewPassword1);
        imageViewpass2=view.findViewById(R.id.imageViewPassword2);


        btregistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!SingletonGerirOrcamentos.isConnectionInternet(getContext())){
                    messageErroRegistar=getContext().getString(R.string.sem_ligacao_internet)+". Não será possivel Registar";
                    SingletonGerirOrcamentos.getInstance(getContext()).onErroLoginlistener(messageErroRegistar);
                    return;
                }
                username=edusername.getText().toString();
                nome=ednome.getText().toString();
                email=edemail.getText().toString();
                password=edpassword.getText().toString();
                password2=edpassword2.getText().toString();
                funcao=spfuncao.getSelectedItem().toString();
              try{
                  categoria=spcategoria.getSelectedItem().toString();
              }catch (Exception exception){

              }
              if(nome.equals("")||nome.equals(" ")||email.equals("")||password.equals("")||password2.equals("")||funcao.equals("")||funcao.equals(" ")||categoria.equals("")||categoria.equals(" ")||username.equals("")) {
                  Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                  return;
              } else if(!password.equals(password2)) {
                  edpassword2.setError("Passwords Incoerretas");
                  //Toast.makeText(getContext(), "Erro. Passwords Incoerretas", Toast.LENGTH_SHORT).show();
                  return;
              }else {
                  if (!isEmailValido(email)){
                      edemail.setError("Email Inválido");
                      return;
                  }else if (!isPasswordValida(password)){
                      edpassword.setError("Password Inválida. Minimo de 8 caracteres");
                      return;
                  }else{
                      SingletonGerirOrcamentos.getInstance(getContext()).registarAPI(nome,email,password,funcao,categoria,username,getContext());
                  }
              }
            }
        });

        imageViewpass1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(passwordvisivel1==0){
                   edpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                   passwordvisivel1=1;
                   return;
               }else {
                   edpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                   passwordvisivel1=0;
               }
            }
        });

        imageViewpass2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passwordvisivel2==0){
                    edpassword2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordvisivel2=1;
                    return;
                }else {
                    edpassword2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordvisivel2=0;
                }
            }
        });

        //Criar o spiner das FUnções
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.funcao, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spfuncao.setAdapter(adapter);

        SingletonGerirOrcamentos.getInstance(getContext()).setCategoriaListener(this);

        SingletonGerirOrcamentos.getInstance(getContext()).getAllCategoriasConlistenerAPI(getContext());

        return view;
    }

    //verificar se email é valido
    private boolean isEmailValido(String email){
        if (email==null){
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    //verifica se a password tem no minimo 8 caracteres
    private boolean isPasswordValida(String password){
        if (password==null){
            return false;
        }
        return password.length()>=8;
    }


    @Override
    public void onRefreshListaCategorias(ArrayList<Categoria> listacategorias) {
        if(listacategorias!=null){
            for (Categoria categoria: listacategorias) {
                arrayListCategorias.add(categoria.getNome_categoria());
            }
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,arrayListCategorias);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spcategoria.setAdapter(adapter1);
        }
    }
}