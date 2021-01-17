package amsi.dei.estg.ipleiria.RightPrice.Instalador;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.snackbar.Snackbar;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Categoria;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;
import amsi.dei.estg.ipleiria.RightPrice.R;
import amsi.dei.estg.ipleiria.RightPrice.listener.DetalhesConhecerFornecedorListener;

//classe de detalhes de conhecer fornecedor
public class DetalhesConhecerFornecedor extends AppCompatActivity implements DetalhesConhecerFornecedorListener {

    public EditText nome,nome_empresa,email,telemovel,categoria;
    public Button ligar,btn_aceitar;
    public ImageView img;
    public static final String DETALHES_Fornecedor ="fornecedor";
    public static final int EDITAR = 3;
    private Utilizador utilizador;
    int id_utilizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_conhecer_fornecedor);

        nome = findViewById(R.id.NomeDetalhesConhecerFornecedor);
        nome_empresa = findViewById(R.id.nome_empresaDetalhesConhecerFornecedor);
        email = findViewById(R.id.emailDetalhesConhecerFornecedor);
        telemovel = findViewById(R.id.telefoneDetalhesConhecerFornecedor);
        categoria = findViewById(R.id.categoriaDetalhesConhecerFornecedor);
        ligar = findViewById(R.id.Btn_ligar_numero_Conhecer_Fornecedor);
        img = findViewById(R.id.IMGDetalhesConhecerFornecedor);
        btn_aceitar = findViewById(R.id.btn_acetiar_fornecedor);

        id_utilizador = getIntent().getIntExtra(DETALHES_Fornecedor, 0);

        SingletonGerirOrcamentos.getInstance(getApplicationContext()).setDetalhesConhecerFornecedorListener(this);
        utilizador = SingletonGerirOrcamentos.getInstance(getApplicationContext()).getUtilizador(id_utilizador);
//carrega os dados para a lista
        if (utilizador != null) {
            setTitle("Detalhes do Fornecedor" + utilizador.getNome());
            if (utilizador.getNome().equals("") || utilizador.getNome().equals(" ") || utilizador.getNome().equals("null")) {
                nome.setText(R.string.sem_nome);
            } else {
                nome.setText(utilizador.getNome());
            }
            if (utilizador.getNome_empresa().equals("") || utilizador.getNome_empresa().equals(" ") || utilizador.getNome_empresa().equals("null")) {
                nome_empresa.setText(R.string.sem_empresa_Atribuida);
            } else {
                nome_empresa.setText(utilizador.getNome_empresa());
            }
            if (utilizador.getEmail().equals("") || utilizador.getEmail().equals(" ") || utilizador.getEmail().equals("null")) {
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

//funcao de ligar
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

            //funcao de aceitar
            btn_aceitar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SingletonGerirOrcamentos.getInstance(getApplicationContext()).adicionarFornecedorInstaladorAPI(utilizador.getId(),getApplicationContext());
                }
            });
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onSucssecAddFornecedor() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onErroDetalhesUtilizadores(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}