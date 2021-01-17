package amsi.dei.estg.ipleiria.RightPrice.FragmentosAtividades;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import amsi.dei.estg.ipleiria.RightPrice.MenuMainActivity;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.R;
import amsi.dei.estg.ipleiria.RightPrice.listener.UtilizadorListener;

//Class de Perfil Utilizador. Comum com todos os utilizadores
public class PerfilUtilizador extends Fragment implements UtilizadorListener {
    private Button btnEscolherImagem;
    private Button btnGuardarDados;
    private EditText editTextNome;
    private EditText editTextEmail;
    private EditText editTextNomeEmpresa;
    private EditText editTextTelefone;
    private ConstraintLayout constraint_detalhes;

    private SharedPreferences sharedPreferences;


    private String nome,email,nomeempresa,caminhoimagem,telefone;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_perfil_utilizador, container, false);
        btnEscolherImagem= view.findViewById(R.id.buttonEscolherImagem);
        btnGuardarDados = view.findViewById(R.id.buttonGuardarDadosUtilizador);
        editTextNome= view.findViewById(R.id.editTextNomeEditar);
        editTextEmail=view.findViewById(R.id.editTextEmailEditar);
        editTextNomeEmpresa=view.findViewById(R.id.editTextTextNomeEmpresaEditar);
        editTextTelefone=view.findViewById(R.id.editTextTelefone);
        constraint_detalhes=view.findViewById(R.id.constraint_detalhes);


        //chamar o singleton dos utilizadores
        SingletonGerirOrcamentos.getInstance(getContext()).setUtilizadorListener(this);

        sharedPreferences= this.getActivity().getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        nome=sharedPreferences.getString(MenuMainActivity.NOME, String.valueOf(R.string.sem_nome));
        email=sharedPreferences.getString(MenuMainActivity.EMAIL, String.valueOf(R.string.sem_funcao_atribuida));
        nomeempresa= sharedPreferences.getString(MenuMainActivity.NOME_EMPRESA, String.valueOf(R.string.sem_empresa_Atribuida));
        caminhoimagem=sharedPreferences.getString(MenuMainActivity.IMAGEM, String.valueOf(R.string.sem_imagem_atribuida));
        telefone=sharedPreferences.getString(MenuMainActivity.TELEMOVEL, String.valueOf(R.string.sem_telemovel_atribuida));

//carrega os dados para as vistas
        if(nome.equals("null")){
            editTextNome.setText("");
        }else{
            editTextNome.setText(nome);
        }
        if(email.equals("null")){
            editTextEmail.setText("");
        }else{
            editTextEmail.setText(email);
        }
        if(nomeempresa.equals("null")){
            editTextNomeEmpresa.setText("");
        }else{
            editTextNomeEmpresa.setText(nomeempresa);
        }
        if(caminhoimagem.equals("null")){
            btnEscolherImagem.setText(R.string.escolher);
        }else{
            btnEscolherImagem.setText(R.string.alterar);
        }
        if(telefone.equals("null")){
            editTextTelefone.setText("");
        }else{
            editTextTelefone.setText(telefone);
        }

        //quando clique fora da caixa de texto fechar o teclado
        constraint_detalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                }
        });

        //botao de escolher imagem do utilizador
        btnEscolherImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getView(),"Sistema de Carregar imagens nao implementado na 1ª versão",Snackbar.LENGTH_LONG).show();
            }
        });

        //funcao de guardar dados
        btnGuardarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telefone=editTextTelefone.getText().toString();
                nome=editTextNome.getText().toString();
                nomeempresa= editTextNomeEmpresa.getText().toString();
            if( telefone.length()==9){
                SingletonGerirOrcamentos.getInstance(getContext()).editarUtilizadorAPI(nome,nomeempresa,telefone,getContext());
                return;
            }
                editTextTelefone.setError(getString(R.string.maxixo_numeros_telefone));
                return;
            }
        });
        return view;
    }

    //funcao que atualizao os dados
    @Override
    public void onUpdateUtilizador(String utilizador) {
        Snackbar.make(getView(),utilizador,Snackbar.LENGTH_LONG).show();

    }

    @Override
    public void onErroUtilizador(String message) {
       Snackbar.make(getView(),message,Snackbar.LENGTH_LONG).show();
    }

}