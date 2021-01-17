package amsi.dei.estg.ipleiria.RightPrice.Fornecedor.Listas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Adaptadores.Fornecedor.ListaProdutosFornecedorAdapter;
import amsi.dei.estg.ipleiria.RightPrice.Fornecedor.DetalhesProdutoFornecedor;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Produto;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.R;
import amsi.dei.estg.ipleiria.RightPrice.listener.ProdutoListener;

import static amsi.dei.estg.ipleiria.RightPrice.Fornecedor.DetalhesProdutoFornecedor.DETALHES_PRODUTO;


//class da lista de produtos do fornecedor
public class ListaProdutoFornecedor extends Fragment implements ProdutoListener,SwipeRefreshLayout.OnRefreshListener {


    private ListView lVprodutofornecedor;
    private SearchView searchView;
    private SwipeRefreshLayout swipe;
    private FloatingActionButton fabAddProdutoFornecedor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_produto_fornecedor, container, false);
        setHasOptionsMenu(true);

        fabAddProdutoFornecedor = view.findViewById(R.id.fabAddProdutoFornecedor);
        lVprodutofornecedor = view.findViewById(R.id.lV_produto_fornecedor);
        swipe= view.findViewById(R.id.swiperefrechprodutos);

        //fucnao de clique na lista
        lVprodutofornecedor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produtosFornecedor = (Produto) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), DetalhesProdutoFornecedor.class);
                intent.putExtra(DETALHES_PRODUTO, produtosFornecedor.getId());
                startActivityForResult(intent, DetalhesProdutoFornecedor.EDITAR);
            }
        });

        //funcao de botao de adicionar produto
        fabAddProdutoFornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getContext(),DetalhesProdutoFornecedor.class);
            startActivityForResult(intent, DetalhesProdutoFornecedor.ADICIONAR);
            }
        });

        swipe.setOnRefreshListener(this);

        SingletonGerirOrcamentos.getInstance(getContext()).setProdutoListener(this);
        SingletonGerirOrcamentos.getInstance(getContext()).getAllProdutosAPI(getContext());

        return view;
    }

    //funcao de retorno da antividade anterior
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode== Activity.RESULT_OK){
           Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    SingletonGerirOrcamentos.getInstance(getContext()).getAllProdutosAPI(getContext());
                }
            }, 1000);

            switch (requestCode){
                case DetalhesProdutoFornecedor.ADICIONAR:
                    Snackbar.make(getView(), getString(R.string.produto_adicionado_sucesso),Snackbar.LENGTH_LONG).show();
                    break;
                case DetalhesProdutoFornecedor.EDITAR:
                    Snackbar.make(getView(),getString(R.string.produto_editado_com_sucesso),Snackbar.LENGTH_LONG).show();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    //funcao de criaçao de opçao de pesquisa
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_pesquisa,menu);
        final MenuItem itemPesquisa = menu.findItem(R.id.item_pesquisa);
        searchView = (SearchView) itemPesquisa.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Produto> produtosFornecedorstemp = new ArrayList<>();
                for (Produto produtosFornecedores : SingletonGerirOrcamentos.getInstance(getContext()).getProdutoDB()) {
                    if (produtosFornecedores.getNome().toLowerCase().contains(s.toLowerCase())) {
                        produtosFornecedorstemp.add(produtosFornecedores);
                    }
                }
                    lVprodutofornecedor.setAdapter(new ListaProdutosFornecedorAdapter(getContext(), produtosFornecedorstemp));
                    return true;
            }

        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    //refresh da lista de produtos
    @Override
    public void onRefreshListaProdutos(ArrayList<Produto> listaProdutos) {
        if(listaProdutos!=null){
            System.out.println("-->"+listaProdutos);
            lVprodutofornecedor.setAdapter(new ListaProdutosFornecedorAdapter(getContext(),listaProdutos));
        }
    }


    //erro da lista de produtos
    @Override
    public void onErroListaProfuto(String message) {
        Snackbar.make(getView(),getString(R.string.erro_carregar_produtos),Snackbar.LENGTH_LONG).show();
    }


    //refresh dos dados
    @Override
    public void onRefresh() {
        SingletonGerirOrcamentos.getInstance(getContext()).getAllProdutosAPI(getContext());
        swipe.setRefreshing(false);
    }
}