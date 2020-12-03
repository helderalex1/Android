package amsi.dei.estg.ipleiria.RightPrice.Instalador.Atividades;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.widget.EditText;

import amsi.dei.estg.ipleiria.RightPrice.FullScreen;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.R;

public class AdicionarCliente extends FullScreen {
    public static final String DETALHES_CLIENTE_INSTALADOR ="cliente";
    public static final int ADICIONAR = 1;
    public static final int ACEITAR = 2;
    public static final int EDITAR = 3;
    int user_id;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editar_cliente_instalador);
        EditText nome = findViewById(R.id.edtNomeClienteInstalador);
        EditText telefone = findViewById(R.id.edtTelClienteInstalador);
        EditText email = findViewById(R.id.edtEmailClienteInstalador);
        EditText nif = findViewById(R.id.edtNifClienteInstalador);

        user_id = getIntent().getIntExtra(DETALHES_CLIENTE_INSTALADOR,0);
     //   ClientesInstalador clientesInstalador = SingletonGerirOrcamentos.getInstance().getClienteInstalador(user_id);
        /*if (clientesInstalador !=null){
            setTitle(getString(R.string.detalhes_utilizador_com_dois_pontos)+ clientesInstalador.getNome());
            nome.setText(clientesInstalador.getNome());
            telefone.setText(String.valueOf(clientesInstalador.getTelefone()));
            email.setText(String.valueOf(clientesInstalador.getEmail()));
            nif.setText(String.valueOf(clientesInstalador.getNif()));
        }else{
            setTitle("Adicionar produto");
        }*/
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