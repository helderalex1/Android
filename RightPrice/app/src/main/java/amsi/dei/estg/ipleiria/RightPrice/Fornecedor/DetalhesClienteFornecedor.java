package amsi.dei.estg.ipleiria.RightPrice.Fornecedor;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.snackbar.Snackbar;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Categoria;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;
import amsi.dei.estg.ipleiria.RightPrice.R;
import amsi.dei.estg.ipleiria.RightPrice.listener.DetalhesInstaladordoFornecedorListener;

//classe de detalhes do fornecedor
public class DetalhesClienteFornecedor extends AppCompatActivity implements DetalhesInstaladordoFornecedorListener {
    public EditText nome,nome_empresa,email,telemovel,categoria;
    public Button ligar;
    public ImageView img;
    public static final String DETALHES_Cliente ="cliente";
    public static final int EDITAR = 3;
    private Utilizador utilizador;
    int id_utilizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitleColor(R.color.black);
        setContentView(R.layout.activity_detalhes_cliente_fornecedor);

        nome= findViewById(R.id.NomeDetalhesInstaladorFOrnecedor);
        nome_empresa=findViewById(R.id.nome_empresaDetalhesInstaladorFOrnecedor);
        email=findViewById(R.id.emailDetalhesInstaladorFOrnecedor);
        telemovel=findViewById(R.id.telefoneDetalhesInstaladorFOrnecedor);
        categoria=findViewById(R.id.categoriaDetalhesInstaladorFOrnecedor);
        ligar=findViewById(R.id.Btn_ligar_numero_Fornecedor);
        img=findViewById(R.id.IMGDetalhesInstaladorFOrnecedor);

        id_utilizador=getIntent().getIntExtra(DETALHES_Cliente,0);

        SingletonGerirOrcamentos.getInstance(getBaseContext()).setDetalhes_instalador_do_fornecedorListener(this);
        utilizador = SingletonGerirOrcamentos.getInstance(getBaseContext()).getUtilizador(id_utilizador);

        //carregar os dados para a vista
        if(utilizador!=null){
            setTitle("Detalhes do seu Instalador"+ utilizador.getNome());
            if (utilizador.getNome().equals("") || utilizador.getNome().equals(" ")||utilizador.getNome().equals("null")) {
                nome.setText(R.string.sem_nome);
            } else {
                nome.setText(utilizador.getNome());
            }
            if (utilizador.getNome_empresa().equals("") || utilizador.getNome_empresa().equals(" ")||utilizador.getNome_empresa().equals("null")) {
                nome_empresa.setText(R.string.sem_empresa_Atribuida);
            } else {
                nome_empresa.setText(utilizador.getNome_empresa());
            }
            if (utilizador.getEmail().equals("") || utilizador.getEmail().equals(" ")||utilizador.getEmail().equals("null")) {
                email.setText(R.string.sem_email);
            }else{
                email.setText(utilizador.getEmail());
            }
            if(utilizador.getTelemovel()==0){
                telemovel.setText(R.string.sem_telemovel_atribuida);
            }else{
                telemovel.setText(""+utilizador.getTelemovel());
            }
            Categoria categoriaaux= SingletonGerirOrcamentos.getInstance(getBaseContext()).getCategoria(utilizador.getCategoria_id());
            categoria.setText(categoriaaux.getNome_categoria());
            Glide.with(getApplicationContext())
                    .load(SingletonGerirOrcamentos.mURLAPIOrcamentos+"/"+utilizador.getImagem())
                    .placeholder(R.drawable.ic_user)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img);

        }

        //clique do botao de ligar
        ligar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = 0;
                number = utilizador.getTelemovel();
                if (number == 0) {
                    Snackbar.make(view, getString(R.string.sem_numero_para_ligar), Snackbar.LENGTH_LONG).show();
                } else {
                    Uri call = Uri.parse("tel:" + number);
                    Intent surf = new Intent(Intent.ACTION_DIAL, call);
                    startActivity(surf);
                }
            }
        });

    }

    //criação da opçao de apagar no menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (id_utilizador!=0){
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.menu_apagar,menu);
            return super.onCreateOptionsMenu(menu);
        }

        return false;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.item_apagar:
                DialogRemover();
                break;
        }
        return true;
    }


    //fucao excuta quando instalador é removido com sucesso
    @Override
    public void onSucssecRemoveInstalador() {
        setResult(RESULT_OK);
        finish();
    }

    //funcao de erro detalhes utilizador
    @Override
    public void onErroDetalhesUtilizadores(String message) {
        Toast.makeText(this, R.string.erro_apagar_instalador, Toast.LENGTH_SHORT).show();
    }



// funcao de criação de um Dialog
    private void DialogRemover(){
        AlertDialog.Builder builder;
        builder =new AlertDialog.Builder(this);
        builder.setTitle(R.string.remover_instalador)
                .setMessage(R.string.pretende_remover_o_instalador)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SingletonGerirOrcamentos.getInstance(getApplicationContext()).removerFornecedorInstaladorAPI(utilizador.getId(),getApplicationContext());
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_delete)
                .show();
    }

}