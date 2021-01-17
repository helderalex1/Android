package amsi.dei.estg.ipleiria.RightPrice.Instalador.Listas;

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

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Adaptadores.Instalador.ListaConhecerFornecedoresAdapter;
import amsi.dei.estg.ipleiria.RightPrice.Instalador.DetalhesConhecerFornecedor;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Categoria;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.FornecedorInstalador;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;
import amsi.dei.estg.ipleiria.RightPrice.R;
import amsi.dei.estg.ipleiria.RightPrice.listener.ConhecerFornecedorListener;

//class lsita conhecer fornecedor instalador
public class ListaConhecerFornecedor extends Fragment implements SwipeRefreshLayout.OnRefreshListener , ConhecerFornecedorListener {

    private ListView lvListaconhecerFornecedor;
    private SearchView searchView;
    private SwipeRefreshLayout swipe;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_conhecer_fornecedor_instalador, container, false);
        setHasOptionsMenu(true);
        lvListaconhecerFornecedor = view.findViewById(R.id.lvListaconhecerFornecedor);
        swipe= view.findViewById(R.id.swiperefrechConhecerFornecedor);


        //funcao de clique da lista
        lvListaconhecerFornecedor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Utilizador utilizador = (Utilizador) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), DetalhesConhecerFornecedor.class);
                intent.putExtra(DetalhesConhecerFornecedor.DETALHES_Fornecedor, utilizador.getId());
                startActivityForResult(intent, DetalhesConhecerFornecedor.EDITAR);
            }
        });

        swipe.setOnRefreshListener(this);

        SingletonGerirOrcamentos.getInstance(getContext()).setInstaladorFornecedorListener(null);
        SingletonGerirOrcamentos.getInstance(getContext()).setDetalhesProdutoOrcamentoListener(null);
        SingletonGerirOrcamentos.getInstance(getContext()).setConhecerFornecedorListener(this);
        SingletonGerirOrcamentos.getInstance(getContext()).getAllCategoriasAPI(getContext());
        SingletonGerirOrcamentos.getInstance(getContext()).getAllRelacionInstaladorFornececorAPI(getContext());
        return view;
    }

    //funcao que excuta quando retorna da outra atividade
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    SingletonGerirOrcamentos.getInstance(getContext()).getAllRelacionInstaladorFornececorAPI(getContext());
                }
            }, 1000);
            switch (requestCode) {
                case DetalhesConhecerFornecedor.EDITAR:
                    Snackbar.make(getView(),getString(R.string.fornecedor_adicionado_sua_lista), Snackbar.LENGTH_LONG).show();
                    break;
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    //funcao de criação do menu de pesquisa
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
                ArrayList<Categoria> categorias = new ArrayList<>();
                for (Categoria categoria: SingletonGerirOrcamentos.getInstance(getContext()).getCategoriasDB()){
                    if(categoria.getNome_categoria().toLowerCase().contains(s.toLowerCase())){
                        categorias.add(categoria);
                    }
                }
                ArrayList<Utilizador> clienteIfornecedor = new ArrayList<>();
                ArrayList<Utilizador> utilizadorsapi=SingletonGerirOrcamentos.getInstance(getContext()).getConhecerFornecedor();
                for (Categoria categoria: categorias) {
                    for (Utilizador utilizador: utilizadorsapi) {
                        if(categoria.getId()==utilizador.getCategoria_id()){
                            clienteIfornecedor.add(utilizador);
                        }
                    }
                }
                lvListaconhecerFornecedor.setAdapter(new ListaConhecerFornecedoresAdapter(getContext(),clienteIfornecedor));
                return true;

            }

        });
        super.onCreateOptionsMenu(menu, inflater);
    }


    //funcao de refresh
    @Override
    public void onRefresh() {
        SingletonGerirOrcamentos.getInstance(getContext()).getAllRelacionInstaladorFornececorAPI(getContext());
        swipe.setRefreshing(false);
    }

    //funcao de refresh
    @Override
    public void onrefreshFornecedores(ArrayList<Utilizador> Fornecedor) {
        if(Fornecedor!=null){
            ArrayList<Utilizador>  FornecedorInstalador=SingletonGerirOrcamentos.getInstance(getContext()).getConhecerFornecedor();
            lvListaconhecerFornecedor.setAdapter(new ListaConhecerFornecedoresAdapter(getContext(),FornecedorInstalador));
        }
    }

    //funcao de erro
    @Override
    public void onRefreshFornecedoreInstalador(ArrayList<FornecedorInstalador> FornecedorInstalador) {
        if(FornecedorInstalador!=null){
            SingletonGerirOrcamentos.getInstance(getContext()).getAllFornecedorInstaladorAPI(getContext(),1);
        }
    }

    @Override
    public void onErroFornecedorInstalador(String message) {
        Snackbar.make(getView(),getString(R.string.erro_carregar_fornecedores),Snackbar.LENGTH_LONG).show();
    }
}