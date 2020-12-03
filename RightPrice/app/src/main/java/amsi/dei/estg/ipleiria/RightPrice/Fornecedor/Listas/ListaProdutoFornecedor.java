package amsi.dei.estg.ipleiria.RightPrice.Fornecedor.Listas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Adaptadores.Fornecedor.ListaProdutosFornecedor;
import amsi.dei.estg.ipleiria.RightPrice.Fornecedor.Atividades.DetalhesProdutoFornecedor;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Produto;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.R;

import static amsi.dei.estg.ipleiria.RightPrice.Fornecedor.Atividades.DetalhesProdutoFornecedor.DETALHES_PRODUTO;


public class ListaProdutoFornecedor extends Fragment {


    private ArrayList<Produto> produtosFornecedores;
    private ListView lVprodutofornecedor;
    private SearchView searchView;
    private FloatingActionButton fabAddProdutoFornecedor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_produto_fornecedor, container, false);
        setHasOptionsMenu(true);
        fabAddProdutoFornecedor = view.findViewById(R.id.fabAddProdutoFornecedor);
      //  produtosFornecedores = SingletonGerirOrcamentos.getInstance().getProdutos_fornecedor(1);
        lVprodutofornecedor = view.findViewById(R.id.lV_produto_fornecedor);
        lVprodutofornecedor.setAdapter(new ListaProdutosFornecedor(getContext(), produtosFornecedores));
        lVprodutofornecedor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produtosFornecedor = (Produto) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), DetalhesProdutoFornecedor.class);
                intent.putExtra(DETALHES_PRODUTO, produtosFornecedor.getId());
                startActivityForResult(intent, DetalhesProdutoFornecedor.ACEITAR);
            }
        });

        fabAddProdutoFornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getContext(),DetalhesProdutoFornecedor.class);
            startActivityForResult(intent, DetalhesProdutoFornecedor.ADICIONAR);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode== Activity.RESULT_OK){
        //    produtosFornecedores = SingletonGerirOrcamentos.getInstance().getProdutos_fornecedor_array();
            lVprodutofornecedor.setAdapter(new ListaProdutosFornecedor(getContext(),produtosFornecedores));
            switch (requestCode){
                case DetalhesProdutoFornecedor.ADICIONAR:
                    Snackbar.make(getView(), "Livro_adicionado_com_sucesso",Snackbar.LENGTH_LONG).show();
                    break;

                case DetalhesProdutoFornecedor.EDITAR:
                    Snackbar.make(getView(), "livro_editado_com_sucesso",Snackbar.LENGTH_LONG).show();
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


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
              //  for (Produto produtosFornecedores: SingletonGerirOrcamentos.getInstance().getProdutos_fornecedor(1)) {
                  /*  if (produtosFornecedores.getNome().toLowerCase().contains(s.toLowerCase())) {
                        produtosFornecedorstemp.add(produtosFornecedores);
                    }*/

                lVprodutofornecedor.setAdapter(new ListaProdutosFornecedor(getContext(),produtosFornecedorstemp));
                return true;

            }

        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}