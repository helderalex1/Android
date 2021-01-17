package amsi.dei.estg.ipleiria.RightPrice.Instalador;

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
import amsi.dei.estg.ipleiria.RightPrice.listener.DetalhesFornecedordoInstaladorListener;

//classe detalhes do fornecedir
public class DetalhesFornecedor extends AppCompatActivity implements DetalhesFornecedordoInstaladorListener {
    public EditText nome,nome_empresa,email,telemovel,categoria;
    public Button ligar;
    public ImageView img;
    public static final String DETALHES_Fornecedor ="fornecedor";
    public static final int EDITAR = 3;
    private Utilizador utilizador;
    int id_utilizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_fornecedor);

        nome= findViewById(R.id.NomeDetalhesFornecedorInstalador);
        nome_empresa=findViewById(R.id.nome_empresaDetalhesFornecedorInstalador);
        email=findViewById(R.id.emailDetalhesFornecedorInstalador);
        telemovel=findViewById(R.id.telefoneDetalhesFornecedorInstalador);
        categoria=findViewById(R.id.categoriaDetalhesFornecedorInstalador);
        ligar=findViewById(R.id.Btn_ligar_numero_Instalador);
        img=findViewById(R.id.IMGDetalhesFornecedorInstalador);

        id_utilizador=getIntent().getIntExtra(DETALHES_Fornecedor,0);

        SingletonGerirOrcamentos.getInstance(getApplicationContext()).setDetalhesFornecedordoInstaladorListener(this);
        utilizador= SingletonGerirOrcamentos.getInstance(getApplicationContext()).getUtilizador(id_utilizador);

        //carrega os dados para a vista
        if(utilizador!=null) {
            setTitle("Detalhes do seu Fornecedor" + utilizador.getNome());
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
            } else {
                email.setText(utilizador.getEmail());
            }
            if (utilizador.getTelemovel() == 0) {
                telemovel.setText(R.string.sem_telemovel_atribuida);
            } else {
                telemovel.setText("" + utilizador.getTelemovel());
            }
            Categoria categoriaaux = SingletonGerirOrcamentos.getInstance(getBaseContext()).getCategoria(utilizador.getCategoria_id());
            categoria.setText(categoriaaux.getNome_categoria());
            Glide.with(getApplicationContext())
                    .load(SingletonGerirOrcamentos.mURLAPIOrcamentos + "/" + utilizador.getImagem())
                    .placeholder(R.drawable.ic_user)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img);


        }

        //funcao do botao ligar
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


    @Override
    public void onSucssecRemoveIFornecedor() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onErroDetalhesUtilizadores(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    private void DialogRemover(){
        AlertDialog.Builder builder;
        builder =new AlertDialog.Builder(this);
        builder.setTitle(R.string.remover_fornecedor)
                .setMessage(R.string.prertende_remover_o_fornecedor)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SingletonGerirOrcamentos.getInstance(getApplicationContext()).removerInstaladorFornecedorAPI(utilizador.getId(),getApplicationContext());
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