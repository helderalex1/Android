package amsi.dei.estg.ipleiria.RightPrice.Instalador;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.ClientesInstalador;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.R;

public class Adicionar_Cliente_Instalador extends AppCompatActivity {
    private EditText nome,telefone,email,nif;
    public static final String DETALHES_CLIENTE_INSTALADOR ="cliente";
    public static final int ADICIONAR = 1;
    public static final int ACEITAR = 2;
    public static final int EDITAR = 3;
    private ClientesInstalador clientesInstalador;
    int user_id;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_adicionar__cliente__instalador_);
        nome = findViewById(R.id.edtNomeClienteInstalador);
        telefone = findViewById(R.id.edtTelClienteInstalador);
        email = findViewById(R.id.edtEmailClienteInstalador);
        nif = findViewById(R.id.edtNifClienteInstalador);

        user_id = getIntent().getIntExtra(DETALHES_CLIENTE_INSTALADOR,0);
        clientesInstalador = SingletonGerirOrcamentos.getInstance().getClienteInstalador(user_id);
        if (clientesInstalador!=null){
            setTitle(getString(R.string.detalhes_utilizador_com_dois_pontos)+ clientesInstalador.getNome());
            nome.setText(clientesInstalador.getNome());
            telefone.setText(""+clientesInstalador.getTelefone());
            email.setText(clientesInstalador.getEmail());
            nif.setText(""+clientesInstalador.getNif());
        }else{
            setTitle("Adicionar produto");
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
}