package amsi.dei.estg.ipleiria.RightPrice.Admin;

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

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Categoria;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Funcao;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;
import amsi.dei.estg.ipleiria.RightPrice.R;
import amsi.dei.estg.ipleiria.RightPrice.listener.DetalhesUtilizadorListener;

public class DetalhesUtilizador extends AppCompatActivity implements DetalhesUtilizadorListener {
    public static final String DETALHES_UTILIZADOR ="utilizador";
    public static final String Tipo_abrir_atividade = "tipo";
    public static final int ACEITAR = 2;
    public static final int Bloquear_Desbloquear=1;
    private int Tipo_abrir ;
    private int id_utilizador;
    private Utilizador utilizador;
    private EditText nome, nome_empresa,telefone, email, categoria,funcaoedit;
    private Button aceitar_utilizador,ligar,recusar,banir;
    private ImageView imagem_utilizador;
    private   int valor_send_Message=1;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitleColor(R.color.black);
        setContentView(R.layout.activity_detalhes_utilizador);
        id_utilizador = getIntent().getIntExtra(DETALHES_UTILIZADOR, 0);
        Tipo_abrir = getIntent().getIntExtra(Tipo_abrir_atividade,0);
        utilizador = SingletonGerirOrcamentos.getInstance(getBaseContext()).getUtilizador(id_utilizador);
        nome = findViewById(R.id.Nome_utilizador_pendente_detalhes);
        nome_empresa = findViewById(R.id.nome_empresa_utilizador_pendente);
        telefone = findViewById(R.id.telefone_utilizador_pendente);
        email = findViewById(R.id.email_utilizador_pendente);
        categoria = findViewById(R.id.categoria_utilizador_pendente);
        imagem_utilizador = findViewById(R.id.IMGDetalhesUtilizador);
        aceitar_utilizador= findViewById(R.id.btn_aceitar_utilizador_pendente);
        funcaoedit=findViewById(R.id.Funcao_utilizadore_pendente);
        ligar= findViewById(R.id.Btn_ligar_numero);
        recusar= findViewById(R.id.btn_recusar_utilizador);
        banir = findViewById(R.id.btnBanirDesbanirAdmin);

        SingletonGerirOrcamentos.getInstance(getBaseContext()).setDetalhesUtilizadorListener(this);
        SingletonGerirOrcamentos.getInstance(getBaseContext()).getAllFuncoesAdminAPI(getBaseContext());

        //abre a atividade consoante o tipo. Se é para aceitar ou para banir/desbanir
        if (Tipo_abrir==0){
            setResult(RESULT_CANCELED);
            finish();
        }else if (Tipo_abrir==1){
            aceitar_utilizador.setVisibility(View.VISIBLE);
            recusar.setVisibility(View.VISIBLE);
            banir.setVisibility(View.INVISIBLE);

        }else if (Tipo_abrir==2){
            aceitar_utilizador.setVisibility(View.INVISIBLE);
            recusar.setVisibility(View.INVISIBLE);
            banir.setVisibility(View.VISIBLE);
        }

        if (utilizador!=null){
            setTitle(getString(R.string.detalhes_utilizador_com_dois_pontos)+ utilizador.getUsername());
            nome.setText(utilizador.getUsername());
            nome_empresa.setText(utilizador.getNome_empresa());
            if(utilizador.getTelemovel()==0){
                telefone.setText("Sem Numero");
            }else{
                telefone.setText(""+utilizador.getTelemovel());
            }
            email.setText(utilizador.getEmail());
           Categoria categoriaaux= SingletonGerirOrcamentos.getInstance(getBaseContext()).getCategoria(utilizador.getCategoria_id());
            categoria.setText(categoriaaux.getNome_categoria());
            if(utilizador.getStatus()==10){
                banir.setText(getString(R.string.banir_utilizador));
            }else if (utilizador.getStatus()==0){
                banir.setText(getString(R.string.desbanir_utilizador));
            }
            Glide.with(getApplicationContext())
                    .load(utilizador.getImagem())
                    .placeholder(R.drawable.ic_user)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imagem_utilizador);
        }else{
            setTitle(R.string.Sem_utilizador_para_aceitar);
        }

        //funcao do botao de aceitar utilizadoor
        aceitar_utilizador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(utilizador!=null){
                    utilizador.setStatus(10);
                    valor_send_Message=3;
                    SingletonGerirOrcamentos.getInstance(getApplicationContext()).alterarstatusAPI(utilizador,getApplicationContext());
                }
                setResult(RESULT_OK);
            }
        });

        //funcao de recusar utilizador
        recusar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(utilizador!=null){
                    utilizador.setStatus(0);
                    valor_send_Message=4;
                    SingletonGerirOrcamentos.getInstance(getApplicationContext()).alterarstatusAPI(utilizador,getApplicationContext());
                }
                setResult(RESULT_OK);
            }
        });

        //funcao de banir utilizador
        banir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              
                if (utilizador!=null){
                    if (utilizador.getStatus()==0){
                        utilizador.setStatus(10);
                        valor_send_Message=1;
                    }else {
                        utilizador.setStatus(0);
                        valor_send_Message=2;
                    }
                    SingletonGerirOrcamentos.getInstance(getApplicationContext()).alterarstatusAPI(utilizador,getApplicationContext());
                }
                setResult(RESULT_OK);
            }
        });


        //fucao de ligar para o numero
        ligar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = 0;
                number= utilizador.getTelemovel();
                if(number==0){
                    Snackbar.make(view,getString(R.string.sem_numero_para_ligar),Snackbar.LENGTH_LONG).show();
                }else{
                    Uri call = Uri.parse("tel:" + number);
                    Intent surf = new Intent(Intent.ACTION_DIAL, call);
                    startActivity(surf);
                }
            }
        });

    }

    // funcao que escuta quando um clique na barra status
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


    //refresh dos utilizadores
    @Override
    public void onRefreshDetalhesUtilizadores() {
        SingletonGerirOrcamentos.getInstance(getApplicationContext()).setValor_send_message(valor_send_Message);
        finish();
    }

    //erro detalhes utilizadores
    @Override
    public void onErroDetalhesUtilizadores(String message) {
        Toast.makeText(this, "Erro a alterar a permissão do utilizador. "+message, Toast.LENGTH_LONG).show();
    }

    //refresh lista funcao
    @Override
    public void onRefreshListaFuncao(ArrayList<Funcao> listafuncoes) {
        for (Funcao funcao: listafuncoes) {
            if(funcao.getUser_id()==utilizador.getId())
            {
                funcaoedit.setText(SingletonGerirOrcamentos.getInstance(getApplicationContext()).toTitledCase(funcao.getFuncao()));
                return;
            }
            funcaoedit.setText(R.string.funcao_nao_encontrada);
        }
    }

    //erro carregar lista funcao
    @Override
    public void onErroListaFuncao(String message) {
        Toast.makeText(this, "Erro a carregar a funcao do utilizador", Toast.LENGTH_LONG).show();
        funcaoedit.setText(" ");
    }
}