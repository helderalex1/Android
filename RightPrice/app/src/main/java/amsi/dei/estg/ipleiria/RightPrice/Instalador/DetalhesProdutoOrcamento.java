package amsi.dei.estg.ipleiria.RightPrice.Instalador;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Adaptadores.Instalador.ListaProdutosAdicionarOrcamentoAdpater;
import amsi.dei.estg.ipleiria.RightPrice.Adaptadores.Instalador.ListaProdutosOrcamentoAdapter;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Orcamento;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Produto;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.ProdutoOrcamento;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.R;
import amsi.dei.estg.ipleiria.RightPrice.listener.DetalhesProdutoOrcamentoListener;


//clasee de detalhes do produto do orcamento
public class DetalhesProdutoOrcamento extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, DetalhesProdutoOrcamentoListener {
    private EditText nome,referencia,preco,quantidade;
    TextView descricao;
    private ListView listView;
    private SwipeRefreshLayout swipe;
    private SearchView searchView;
    private FloatingActionButton floatingActionButton;


    public static final String DETALHES_produto ="produto";
    public static final String VALOR_ABRIR="valor";
    public static final String ID_ORCAMENTO="id_orcamento";
    public static final int ADICIONAR = 1;
    public static final int EDITAR = 3;
    public static final int REMOVER = 2;

    public int valor_save=0;
    private Produto produto;
    int produto_id;
    int orcamento_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitleColor(R.color.black);
        setContentView(R.layout.activity_detalhes_produto_orcamento_instalador);

        nome=findViewById(R.id.NomedoProdutoDetalhes);
        referencia=findViewById(R.id.referencia_produto_Detalhes);
        preco=findViewById(R.id.preco_produto_detalhes);
        quantidade=findViewById(R.id.quantidade_produto_detalhes);
        descricao=findViewById(R.id.descricao_produto_detalhes);
        listView=findViewById(R.id.lvAdicionarProduto);
        swipe=findViewById(R.id.swiperefrechprodutosAdicionarDetalhes);
        floatingActionButton=findViewById(R.id.fab_guardar_Produto);

        orcamento_id = getIntent().getIntExtra(ID_ORCAMENTO,0);
        produto_id= getIntent().getIntExtra(DETALHES_produto,0);
        produto = SingletonGerirOrcamentos.getInstance(getApplicationContext()).getProduto(produto_id);
        SingletonGerirOrcamentos.getInstance(getApplicationContext()).setDetalhesProdutoOrcamentoListener(this);
        SingletonGerirOrcamentos.getInstance(getApplicationContext()).getAllRelacionInstaladorFornececorAPI(getApplicationContext());
        SingletonGerirOrcamentos.getInstance(getApplicationContext()).getAllProdutosFornecedorAPI(getApplicationContext());
        SingletonGerirOrcamentos.getInstance(getApplicationContext()).getAllProdutosOrcamentoAPI(getApplicationContext());

//carrega os dados para a vista
        if(getIntent().getIntExtra(VALOR_ABRIR,0)==1){
            listView.setVisibility(View.INVISIBLE);
        }else if(getIntent().getIntExtra(VALOR_ABRIR,0)==2){
            listView.setVisibility(View.VISIBLE);
        }

        if(produto!=null){
            setTitle("Detalhes Produto: "+produto.getNome());
            if(produto.getNome().equals("")||produto.getNome().equals(" ")||produto.getNome().equals("null")){
                nome.setText(R.string.sem_nome);
            }else{
                nome.setText(produto.getNome());
            }

            if(produto.getReferencia().equals("")||produto.getReferencia().equals(" ")||produto.getReferencia().equals("null")){
                referencia.setText(R.string.sem_referencia);
            }else{
                referencia.setText(produto.getReferencia());
            }

            if(produto.getPreco()==0){
                preco.setText(R.string.sem_preco);
            }else{
                preco.setText(String.valueOf(produto.getPreco()));
            }
            if(produto.getDescricao().equals("")||produto.getDescricao().equals(" ")||produto.getDescricao().equals("null")){
                descricao.setText(R.string.sem_descricao);
            }else{
                descricao.setText(produto.getDescricao());
            }
        }else{
            setTitle(getString(R.string.adicionar_produto));
        }

        //funcao do botao
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produtoa =(Produto) parent.getItemAtPosition(position);
                nome.setText(produtoa.getNome());
                referencia.setText(produtoa.getReferencia());
                preco.setText(String.valueOf(produtoa.getPreco()));
                descricao.setText(produtoa.getDescricao());
                quantidade.setText(String.valueOf(0));
                produto_id=produtoa.getId();

            }
        });

        //funcao do botao
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(produto!=null){
                    valor_save=1;
                    SingletonGerirOrcamentos.getInstance(getApplicationContext()).alterarquantidadeProdutoAPI(Integer.parseInt(quantidade.getText().toString()),orcamento_id,produto_id, getApplicationContext());

                }else{
                    if(quantidade.getText().equals("")||quantidade.getText().equals(" ")||quantidade.getText().equals("0")){
                        Snackbar.make(view,getString(R.string.introduza_uma_quantidade),Snackbar.LENGTH_LONG).show();
                        return;
                    }
                    ProdutoOrcamento produtoOrcamento =new ProdutoOrcamento(orcamento_id,produto_id,Integer.parseInt(quantidade.getText().toString()));
                    valor_save=2;
                    SingletonGerirOrcamentos.getInstance(getApplicationContext()).adicionarProdutoOrcamentoAPI(produtoOrcamento,getApplicationContext());
                    return;
                }
            }
        });

        swipe.setOnRefreshListener(this);
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pesquisa, menu);
        final MenuItem itemPesquisa = menu.findItem(R.id.item_pesquisa);
        searchView = (android.widget.SearchView) itemPesquisa.getActionView();
        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Produto> produtos = new ArrayList<>();
                for (Produto produto: SingletonGerirOrcamentos.getInstance(getApplicationContext()).getProdutosAddOrcamento(orcamento_id)) {
                    if (produto.getNome().toLowerCase().contains(s.toLowerCase())) {
                        produtos.add(produto);
                    }
                }
                listView.setAdapter(new ListaProdutosAdicionarOrcamentoAdpater(getApplicationContext(),produtos));
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
    public void onSucssecProduto() {
        listView.setAdapter(new ListaProdutosOrcamentoAdapter(getApplicationContext(),SingletonGerirOrcamentos.getInstance(getApplicationContext()).getProdutosAddOrcamento(orcamento_id)));
    }

    @Override
    public void onSucssecEditValor() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Orcamento orcamento= SingletonGerirOrcamentos.getInstance(getApplicationContext()).getOrcamento(orcamento_id);
                SingletonGerirOrcamentos.getInstance(getApplicationContext()).alterarTotalOrcamentoAPI(orcamento,getApplicationContext());
                setResult(RESULT_OK);
                finish();
            }
        }, 2000);


    }

    @Override
    public void onErroDetalhesprodutos(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefreshProdutosOrcamento(ArrayList<ProdutoOrcamento> produtoOrcamentos)
    {
        for (ProdutoOrcamento produtoOrcamento: produtoOrcamentos) {
            if(produtoOrcamento.getId_orcamento()==orcamento_id&&produtoOrcamento.getId_produto()==produto_id){
                quantidade.setText(String.valueOf(produtoOrcamento.getQuantidade()));
            }
        }
        SingletonGerirOrcamentos.getInstance(getApplicationContext()).getAllRelacionInstaladorFornececorAPI(getApplicationContext());
    }

    @Override
    public void onRefresh() {
        SingletonGerirOrcamentos.getInstance(getApplicationContext()).getProdutosAddOrcamento(orcamento_id);
        swipe.setRefreshing(false);
    }
}