package amsi.dei.estg.ipleiria.RightPrice.Admin;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.function.BinaryOperator;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizadores;
import amsi.dei.estg.ipleiria.RightPrice.R;

public class Detalhes_aceitar_utilizador_admin extends AppCompatActivity {
    public static final String DETALHES_UTILIZADOR ="utilizador";
    public static final int ACEITAR = 2;
    private int id_utilizador;
    private Utilizadores utilizador;
    private EditText nome, nome_empresa,telefone, email, categoria;
    private Button aceitar_utilizador,ligar;
    private ImageView imagem_utilizador;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalhes_aceitar_utilizador);
        id_utilizador = getIntent().getIntExtra(DETALHES_UTILIZADOR, 0);
        utilizador = SingletonGerirOrcamentos.getInstance().getUtilizador(id_utilizador);
        nome = findViewById(R.id.Nome_utilizador_pendente_detalhes);
        nome_empresa = findViewById(R.id.nome_empresa_utilizador_pendente);
        telefone = findViewById(R.id.telefone_utilizador_pendente);
        email = findViewById(R.id.email_utilizador_pendente);
        categoria = findViewById(R.id.categoria_utilizador_pendente);
        imagem_utilizador = findViewById(R.id.img_cliente_acetiar_cliente);
        aceitar_utilizador= findViewById(R.id.button_aceitar_utilizador_pendente);
        ligar= findViewById(R.id.Btn_ligar_numero);


        if (utilizador!=null){
            setTitle(getString(R.string.detalhes_utilizador_com_dois_pontos)+ utilizador.getUsername());
            nome.setText(utilizador.getUsername());
            nome_empresa.setText(utilizador.getNome_empresa());
            telefone.setText(""+utilizador.getTelemovel());
            email.setText(utilizador.getEmail());
            categoria.setText(""+utilizador.getCategoria_id());
           // imagem_utilizador.setImageResource(utilizador.getImagem());
        }else{
            setTitle(getString(R.string.Sem_utilizador_para_aceitar));
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
}