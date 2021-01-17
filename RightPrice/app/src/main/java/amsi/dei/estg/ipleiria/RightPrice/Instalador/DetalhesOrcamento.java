package amsi.dei.estg.ipleiria.RightPrice.Instalador;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import amsi.dei.estg.ipleiria.RightPrice.Adaptadores.Instalador.ListaProdutosOrcamentoAdapter;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Orcamento;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Produto;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.R;
import amsi.dei.estg.ipleiria.RightPrice.listener.ProdutosOrcamentoListener;


//classe de detalhes do orcamento
public class DetalhesOrcamento extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, ProdutosOrcamentoListener {

    private EditText nome,margem,total,Data;
    private Button btn_data;
    private ListView listView;
    private SwipeRefreshLayout swipe;
    private SearchView searchView;
    private FloatingActionButton floatingActionButtonsaveorcamento, floatingActionButtonadicionarproduto;

    public static final String DETALHES_Orcamento ="produto";
    public static final String VALOR_ABRIR="valor";
    public static final String ID_CLIENTE="cliente";
    public static final int ADICIONAR = 1;
    public static final int EDITAR = 3;

    public int valor_save=0;
    private Orcamento orcamento;
    int orcamento_id;
    int cliente_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitleColor(R.color.black);
        setContentView(R.layout.activity_detalhes_orcamento_instalador);

        nome=findViewById(R.id.nome_orcamento);
        margem=findViewById(R.id.margem_orcamento);
        total=findViewById(R.id.total_orcamento);
        Data=findViewById(R.id.data_orcamento);
        btn_data=findViewById(R.id.Btn_data_Atual);
        swipe=findViewById(R.id.swiperefrechProdutodorcamento);
        listView=findViewById(R.id.lvprodutosOrcamento);
        floatingActionButtonsaveorcamento=findViewById(R.id.fab_save_orcamento);
        floatingActionButtonadicionarproduto=findViewById(R.id.fab_addprodutos);

        orcamento_id= getIntent().getIntExtra(DETALHES_Orcamento, 0);
        cliente_id = getIntent().getIntExtra(ID_CLIENTE,0);
        orcamento= SingletonGerirOrcamentos.getInstance(getApplicationContext()).getOrcamento(orcamento_id);
        SingletonGerirOrcamentos.getInstance(getApplicationContext()).setConhecerFornecedorListener(null);
        SingletonGerirOrcamentos.getInstance(getApplicationContext()).setFornecedorInstaladorListener(null);
        SingletonGerirOrcamentos.getInstance(getApplicationContext()).setDetalhesProdutoOrcamentoListener(null);
        SingletonGerirOrcamentos.getInstance(getApplicationContext()).setProdutoOrcamentoListener(this);
        SingletonGerirOrcamentos.getInstance(getApplicationContext()).getAllRelacionInstaladorFornececorAPI(getApplicationContext());
        SingletonGerirOrcamentos.getInstance(getApplicationContext()).getAllProdutosOrcamentoAPI(getApplicationContext());

        if(getIntent().getIntExtra(VALOR_ABRIR,0)==1){
            floatingActionButtonadicionarproduto.setVisibility(View.INVISIBLE);
        }else if(getIntent().getIntExtra(VALOR_ABRIR,0)==2){
            floatingActionButtonadicionarproduto.setVisibility(View.VISIBLE);
        }
//carrega os dados para a vista
        if(orcamento!=null){
            setTitle(getString(R.string.detalhes_orcamento)+orcamento.getNome());
            if(orcamento.getNome().equals("")||orcamento.getNome().equals(" ")||orcamento.getNome().equals("null")){
                nome.setText(R.string.sem_nome);
            }else{
                nome.setText(orcamento.getNome());
            }
            if(orcamento.getData_orcamento().equals("")||orcamento.getData_orcamento().equals(" ")||orcamento.getData_orcamento().equals("null")){
                Data.setText(R.string.sem_data);
            }else{
                Data.setText(orcamento.getData_orcamento());
            }
            margem.setText(String.valueOf(orcamento.getMargem()));
            total.setText(String.valueOf(orcamento.getTotal()));
        }else{
            setTitle(getString(R.string.adicionar_orcamento));
        }

        //funcao do botao
        btn_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date data = new Date();
                String dataFormatada = formataData.format(data);
                Data.setText(dataFormatada);
            }
        });
        //funcao do botao
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produto =(Produto) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(),DetalhesProdutoOrcamento.class);
                intent.putExtra(DetalhesProdutoOrcamento.DETALHES_produto, produto.getId());
                intent.putExtra(DetalhesProdutoOrcamento.ID_ORCAMENTO,orcamento.getId());
                intent.putExtra(DetalhesProdutoOrcamento.VALOR_ABRIR,1);
                startActivityForResult(intent,DetalhesProdutoOrcamento.EDITAR);
            }
        });
        //funcao do botao
        floatingActionButtonadicionarproduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DetalhesProdutoOrcamento.class);
                intent.putExtra(DetalhesProdutoOrcamento.VALOR_ABRIR,2);
                intent.putExtra(DetalhesProdutoOrcamento.ID_ORCAMENTO,orcamento.getId());
                startActivityForResult(intent,DetalhesProdutoOrcamento.ADICIONAR);
            }
        });
        //funcao do botao
        floatingActionButtonsaveorcamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(orcamento !=null){
                    orcamento.setNome(nome.getText().toString());
                    orcamento.setMargem(Integer.parseInt(margem.getText().toString()));
                    orcamento.setData_orçemento(Data.getText().toString());
                    valor_save=1;
                    SingletonGerirOrcamentos.getInstance(getApplicationContext()).alteraOrcamentoAPI(orcamento,getApplicationContext());
                }else{
                    if(nome.getText().toString().equals("")||nome.getText().toString().equals(" ")||margem.getText().toString().equals("")||margem.getText().toString().equals(" ")||Data.getText().toString().equals("")||Data.getText().toString().equals(" ")){
                        Snackbar.make(view,getString(R.string.preencha_todos_os_campos),Snackbar.LENGTH_LONG).show();
                        return;
                    }
                            Orcamento orcamento = new Orcamento(0,cliente_id,Data.getText().toString(),Integer.parseInt(margem.getText().toString()),0,nome.getText().toString());
                            valor_save=2;
                            SingletonGerirOrcamentos.getInstance(getApplicationContext()).adicionarOrcamentoAPI(orcamento,getApplicationContext());
                            return;
                        }

                    }
                });

        swipe.setOnRefreshListener(this);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    SingletonGerirOrcamentos.getInstance(getApplicationContext()).getAllProdutosOrcamentoAPI(getApplicationContext());
                }
            }, 100);

            switch (requestCode) {
                case DetalhesOrcamento.ADICIONAR:
                    Toast.makeText(this,getString(R.string.orcamento_adicionado_com_sucesso), Toast.LENGTH_SHORT).show();
                    break;

                case DetalhesOrcamento.EDITAR:
                    Toast.makeText(this,getString(R.string.orcamento_editado_com_sucesso), Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

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
                ArrayList<Produto> produtos = new ArrayList<>();
                for (Produto produto: SingletonGerirOrcamentos.getInstance(getApplicationContext()).getProdutosOrcamentoArray(orcamento.getId())) {
                    if (produto.getNome().toLowerCase().contains(s.toLowerCase())) {
                        produtos.add(produto);
                    }
                }
                listView.setAdapter(new ListaProdutosOrcamentoAdapter(getApplicationContext(),produtos));
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

    private void DialogCancelar(){
        AlertDialog.Builder builder;
        builder =new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.cancelar_orcamento))
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



    @Override
    public void onRefresh() {
        SingletonGerirOrcamentos.getInstance(getApplicationContext()).getAllProdutosOrcamentoAPI(getApplicationContext());
        swipe.setRefreshing(false);
    }

    @Override
    public void onRefreshListaProdutosOrcamentos(ArrayList<Produto> listaproduto) {
        ArrayList<Produto> produtos;
        produtos=new ArrayList<Produto>();
        if(listaproduto!=null &&orcamento!=null) {
            listView.setAdapter(new ListaProdutosOrcamentoAdapter(getApplicationContext(), SingletonGerirOrcamentos.getInstance(getApplicationContext()).getProdutosOrcamentoArray(orcamento.getId())));
            return;
        }
    }

    @Override
    public void onErroprodutosOrcamentos(String message) {
        Toast.makeText(this,getString(R.string.erro_carregar_produtos_orcamento), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSucssecAddOrcamento() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onErroorcamento(String message) {
        if(valor_save==2){
            Toast.makeText(this,getString(R.string.erro_adicionar_orcamento), Toast.LENGTH_SHORT).show();
        }else if(valor_save==1){
            Toast.makeText(this,getString(R.string.Erro_editar_orcamento), Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onSucssecProdutos() {
        SingletonGerirOrcamentos.getInstance(getApplicationContext()).getAllProdutosFornecedorAPI(getApplicationContext());

    }
}