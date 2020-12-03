package amsi.dei.estg.ipleiria.RightPrice.FragmentosAtividades;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador.Utilizador;
import amsi.dei.estg.ipleiria.RightPrice.R;

public class DetalhesUtilizador extends AppCompatActivity {
    public static final String DETALHES_UTILIZADOR ="utilizador";
    public static final String Tipo_abrir_atividade = "tipo";
    public static final int ACEITAR = 2;
    public static final int Meus_Intaladores = 3;
    private int Tipo_abrir ;
    private int id_utilizador;
    private Utilizador utilizador;
    private EditText nome, nome_empresa,telefone, email, categoria;
    private Button aceitar_utilizador,ligar,recusar,banir;
    private ImageView imagem_utilizador;



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
        imagem_utilizador = findViewById(R.id.img_cliente_acetiar_cliente);
        aceitar_utilizador= findViewById(R.id.btn_aceitar_utilizador_pendente);
        ligar= findViewById(R.id.Btn_ligar_numero);
        recusar= findViewById(R.id.btn_recusar_utilizador);
        banir = findViewById(R.id.btnBanirDesbanirAdmin);



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
            telefone.setText(""+utilizador.getTelemovel());
            email.setText(utilizador.getEmail());
            categoria.setText(""+utilizador.getCategoria_id());
            if(utilizador.getStatus()==10){
                banir.setText(getString(R.string.banir_utilizador));
            }else if (utilizador.getStatus()==0){
                banir.setText(getString(R.string.desbanir_utilizador));
            }
           // imagem_utilizador.setImageResource(utilizador.getImagem());
        }else{
            setTitle(R.string.Sem_utilizador_para_aceitar);
        }

        aceitar_utilizador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });


        ligar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = 0;
                number= utilizador.getTelemovel();
                Uri call = Uri.parse("tel:" + number);
                Intent surf = new Intent(Intent.ACTION_DIAL, call);
                startActivity(surf);

            }
        });

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

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (id_utilizador!=0 && Tipo_abrir==2){
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.menu_apagar,menu);
            return super.onCreateOptionsMenu(menu);
        }

        return false;


    }*/

}