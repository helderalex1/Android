package amsi.dei.estg.ipleiria.RightPrice.Instalador;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Adaptadores.Instalador.ListaOrcamentoAdapter;
import amsi.dei.estg.ipleiria.RightPrice.MenuMainActivity;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Cliente;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Orcamento;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.R;
import amsi.dei.estg.ipleiria.RightPrice.listener.OrcamentoListener;

//classe de detalhes do cliente
public class DetalhesCliente extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, OrcamentoListener {
    private EditText nome,telefone,email,nif;
    private Button btn_ligar,btn_eviar_email;
    private ListView listViewOrcamentos;
    private SwipeRefreshLayout swipe;
    private SearchView searchView;
    private FloatingActionButton floatingActionButtonsaveCliente, floatingActionButtonAdicionarPorduto;
    public static final String DETALHES_CLIENTE_INSTALADOR ="orcamento";
    public static final int ADICIONAR = 1;
    public static final int EDITAR = 3;
    public int valor_save=0;
    private Cliente cliente;
    int user_id;



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitleColor(R.color.black);
        setContentView(R.layout.activity_detalhes_cliente_instalador);

        nome = findViewById(R.id.edtNomeClienteInstalador);
        telefone = findViewById(R.id.edtTelClienteInstalador);
        email = findViewById(R.id.edtEmailClienteInstalador);
        nif = findViewById(R.id.edtNifClienteInstalador);
        btn_ligar = findViewById(R.id.Btn_ligar_numero_Cliente);
        swipe = findViewById(R.id.swiperefrechOrcamentosCLiente);
        listViewOrcamentos=findViewById(R.id.lvListaorcamentosCliente);
        floatingActionButtonsaveCliente = findViewById(R.id.fab_adicionarcliente);
        floatingActionButtonAdicionarPorduto = findViewById(R.id.fab_adicionarorcamento);
        btn_eviar_email = findViewById(R.id.Btn_enviar_email_cliente);


        user_id = getIntent().getIntExtra(DETALHES_CLIENTE_INSTALADOR, 0);
        cliente = SingletonGerirOrcamentos.getInstance(getApplicationContext()).getCliente(user_id);
        SingletonGerirOrcamentos.getInstance(getApplicationContext()).setMainInstaladorListener(null);
        SingletonGerirOrcamentos.getInstance(getApplicationContext()).setDetalhesProdutoOrcamentoListener(null);
        SingletonGerirOrcamentos.getInstance(getApplicationContext()).setOrcamentoListener(this);
        SingletonGerirOrcamentos.getInstance(getApplicationContext()).getAllOrcamentosAPI(getApplicationContext());

//carrega os dados para a vista
        if (cliente != null) {
            setTitle(getString(R.string.detalhes_cliente) + cliente.getNome());
        if (cliente.getNome().equals("") || cliente.getNome().equals(" ") || cliente.getNome().equals("null")) {
            nome.setText(R.string.sem_nome);
        } else {
            nome.setText(cliente.getNome());
        }
        if (cliente.getTelemovel() == 0) {
            telefone.setText(R.string.sem_telemovel_atribuida);
        } else {
            telefone.setText(""+cliente.getTelemovel());
        }
        if (cliente.getEmail().equals("") || cliente.getEmail().equals(" ") || cliente.getEmail().equals("null")) {
            email.setText(R.string.sem_email);
        } else {
            email.setText(cliente.getEmail());
        }
        if (cliente.getNif() == 0) {
            nif.setText(R.string.sem_nif);
        } else {
            nif.setText(""+cliente.getNif());
        }
        }else{
            setTitle(getString(R.string.adicionar_cliente));
        }

        //funco de click do botao de ligar
        btn_ligar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = 0;
                number = cliente.getTelemovel();
                if (number == 0) {
                    Snackbar.make(view, getString(R.string.sem_numero_para_ligar), Snackbar.LENGTH_LONG).show();
                } else {
                    Uri call = Uri.parse("tel:" + number);
                    Intent surf = new Intent(Intent.ACTION_DIAL, call);
                    startActivity(surf);
                }
            }
        });

        //botao de enviar email
        btn_eviar_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "Right Price";
                String message = "Excelentissimo Senhor. "+cliente.getNome();
                Uri uri = Uri.parse("mailto:")
                        .buildUpon()
                        .appendQueryParameter("to", cliente.getEmail())
                        .appendQueryParameter("subject", subject)
                        .appendQueryParameter("body", message)
                        .build();
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        //funcao de clique da lista
        listViewOrcamentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Orcamento orcamento= (Orcamento) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(),DetalhesOrcamento.class);
                intent.putExtra(DetalhesOrcamento.DETALHES_Orcamento,orcamento.getId());
                intent.putExtra(DetalhesOrcamento.ID_CLIENTE,cliente.getId());
                intent.putExtra(DetalhesOrcamento.VALOR_ABRIR,2);
                startActivityForResult(intent,DetalhesOrcamento.EDITAR);
            }
        });



        //funcao do floatingactionbuttton

        floatingActionButtonAdicionarPorduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DetalhesOrcamento.class);
                intent.putExtra(DetalhesOrcamento.VALOR_ABRIR,1);
                intent.putExtra(DetalhesOrcamento.ID_CLIENTE,cliente.getId());
                startActivityForResult(intent,DetalhesOrcamento.ADICIONAR);
            }
        });

        //funcao do floatingactionbuttton
        floatingActionButtonsaveCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferenceuser = getSharedPreferences(MenuMainActivity.PREF_USER, MODE_PRIVATE);
                String id = sharedPreferenceuser.getString(MenuMainActivity.ID, null);

                if(cliente !=null){
                    cliente.setNome(nome.getText().toString());
                    cliente.setEmail(email.getText().toString());
                    cliente.setNif(Integer.parseInt(nif.getText().toString()));
                    cliente.setTelemovel(Integer.parseInt(telefone.getText().toString()));
                    valor_save=1;
                    SingletonGerirOrcamentos.getInstance(getApplicationContext()).alteraClienteAPI(cliente,getApplicationContext());
                }else{
                    if(nome.getText().toString().equals("")||nome.getText().toString().equals(" ")||email.getText().toString().equals("")||email.getText().toString().equals(" ")||nif.getText().toString().equals("")||nif.getText().toString().equals(" ")||telefone.getText().toString().equals("")||telefone.getText().toString().equals(" ")){
                        Snackbar.make(view,getString(R.string.preencha_todos_os_campos),Snackbar.LENGTH_LONG).show();
                        return;
                    }
                    if( telefone.length()==9) {
                        if(nif.length()==9){
                            Cliente auxcliente = new Cliente(0,Integer.parseInt(id),nome.getText().toString(),Integer.parseInt(telefone.getText().toString()),Integer.parseInt(nif.getText().toString()),email.getText().toString());
                            valor_save=2;
                            SingletonGerirOrcamentos.getInstance(getApplicationContext()).adicionarCLienteAPI(auxcliente,getApplicationContext());
                            return;
                        }
                        nif.setError(getString(R.string.maxixo_numeros_telefone));
                    }
                    telefone.setError(getString(R.string.maxixo_numeros_telefone));
                    return;
                }
            }
        });

        swipe.setOnRefreshListener(this);


    }


    //funcao de resutl da atividade anterior
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    SingletonGerirOrcamentos.getInstance(getApplicationContext()).getAllOrcamentosAPI(getApplicationContext());
                }
            }, 1000);
            switch (requestCode) {
                case DetalhesCliente.ADICIONAR:
                    Toast.makeText(this,getString(R.string.orcamento_adicionado_com_sucesso), Toast.LENGTH_SHORT).show();
                    break;

                case DetalhesCliente.EDITAR:
                    Toast.makeText(this,getString(R.string.orcamento_editado_com_sucesso), Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    //funcao de criar o menu de pesquisa
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pesquisa, menu);
        final MenuItem itemPesquisa = menu.findItem(R.id.item_pesquisa);
        searchView = (SearchView) itemPesquisa.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Orcamento> orcamentos = new ArrayList<>();
                for (Orcamento orcamento: SingletonGerirOrcamentos.getInstance(getApplicationContext()).getOrcamentoArray(cliente.getId())) {
                    if (orcamento.getNome().toLowerCase().contains(s.toLowerCase())) {
                        orcamentos.add(orcamento);
                    }
                }
                listViewOrcamentos.setAdapter(new ListaOrcamentoAdapter(getApplicationContext(),orcamentos));
                return true;
            }

        });
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                DialogCancelar();
                break;
        }
        return true;
    }

//criaçao da DIALOG
    private void DialogCancelar(){
        AlertDialog.Builder builder;
        builder =new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.cancelar_cliente))
                .setMessage(R.string.pretende_sair_sem_guardar_os_dados)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setResult(RESULT_CANCELED);
                        finish();
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


    //funcao de refresh
    @Override
    public void onRefresh() {
        SingletonGerirOrcamentos.getInstance(getApplicationContext()).getAllOrcamentosAPI(getApplicationContext());
        swipe.setRefreshing(false);
    }

    //funcao de refresh
    @Override
    public void onRefreshListaOrcamentos(ArrayList<Orcamento> listaOrcamentos) {
        if(listaOrcamentos!=null &&cliente!=null){
            listViewOrcamentos.setAdapter(new ListaOrcamentoAdapter(getBaseContext(),SingletonGerirOrcamentos.getInstance(getBaseContext()).getOrcamentoArray(cliente.getId())));
        }
    }

    //funcao de erro
    @Override
    public void onErroOrcamentos(String message) {
        Toast.makeText(this, getString(R.string.erro_a_carregar_os_orçamentos), Toast.LENGTH_SHORT).show();
    }

    //funcao de refresh
    @Override
    public void onSucssecAddCliente() {
        setResult(RESULT_OK);
        finish();
    }

    //funcao de erro
    @Override
    public void onErroDetalhesUtilizadores(String message) {
        if(valor_save==2){
            Toast.makeText(this, getString(R.string.erro_adicionar_cliente), Toast.LENGTH_SHORT).show();
        }else if(valor_save==1){
            Toast.makeText(this,getString(R.string.erro_editar_cliente), Toast.LENGTH_SHORT).show();
        }

    }

}