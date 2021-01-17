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

import amsi.dei.estg.ipleiria.RightPrice.Adaptadores.Instalador.ListaFornecedoresAdpater;
import amsi.dei.estg.ipleiria.RightPrice.Fornecedor.DetalhesProdutoFornecedor;
import amsi.dei.estg.ipleiria.RightPrice.Instalador.DetalhesFornecedor;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.FornecedorInstalador;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;
import amsi.dei.estg.ipleiria.RightPrice.R;
import amsi.dei.estg.ipleiria.RightPrice.listener.InstaladorFornecedorListener;

//classe de lista dos meus fornecedores
public class ListaMeusFornecedores extends Fragment implements SwipeRefreshLayout.OnRefreshListener, InstaladorFornecedorListener {

    private ListView lvListaFornecedores;
    private SearchView searchView;
    private SwipeRefreshLayout swipe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_lista_meus_fornecedores_instalador, container, false);
        setHasOptionsMenu(true);

        lvListaFornecedores= view.findViewById(R.id.lVListaForneceddoresInstalador);
        swipe=view.findViewById(R.id.swiperefrechFornecedoresdoInstalador);

        //funcao de clique na lista
        lvListaFornecedores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Utilizador utilizador = (Utilizador) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), DetalhesFornecedor.class);
                intent.putExtra(DetalhesFornecedor.DETALHES_Fornecedor, utilizador.getId());
                startActivityForResult(intent, DetalhesFornecedor.EDITAR);
            }
        });

        swipe.setOnRefreshListener(this);

        SingletonGerirOrcamentos.getInstance(getContext()).setConhecerFornecedorListener(null);
        SingletonGerirOrcamentos.getInstance(getContext()).setDetalhesProdutoOrcamentoListener(null);
        SingletonGerirOrcamentos.getInstance(getContext()).setInstaladorFornecedorListener(this);
        SingletonGerirOrcamentos.getInstance(getContext()).getAllCategoriasAPI(getContext());
        SingletonGerirOrcamentos.getInstance(getContext()).getAllRelacionInstaladorFornececorAPI(getContext());
        return view;
    }


    //funcao que retorna quando a outra atividade fecha
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
                case DetalhesProdutoFornecedor.EDITAR:
                    Snackbar.make(getView(),getString(R.string.fornecedor_removido_com_sucesso), Snackbar.LENGTH_LONG).show();
                    break;
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    //fucao de criaçao do menu de pesquisa
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
                ArrayList<Utilizador> clienteIfornecedor = new ArrayList<>();
                for (Utilizador utilizador: SingletonGerirOrcamentos.getInstance(getContext()).getFornecedoresdoInstalador()) {
                    if (utilizador.getUsername().toLowerCase().contains(s.toLowerCase())) {
                        clienteIfornecedor.add(utilizador);
                    }
                }
                lvListaFornecedores.setAdapter(new ListaFornecedoresAdpater(getContext(),clienteIfornecedor));
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

    //funcao de refresf de fornecedores
    @Override
    public void onrefreshFornecedores(ArrayList<Utilizador> Fornecedor) {
        if(Fornecedor!=null){
            ArrayList<Utilizador>  FornecedorInstalador=SingletonGerirOrcamentos.getInstance(getContext()).getFornecedoresdoInstalador();
            lvListaFornecedores.setAdapter(new ListaFornecedoresAdpater(getContext(),FornecedorInstalador));
        }
    }

    //funcao de refresf de fornecedores
    @Override
    public void onRefreshFornecedoreInstalador(ArrayList<FornecedorInstalador> FornecedorInstalador) {
        if(FornecedorInstalador!=null){
            SingletonGerirOrcamentos.getInstance(getContext()).getAllFornecedorInstaladorAPI(getContext(),1);
        }
    }

    //funcao de erros
    @Override
    public void onErroFornecedorInstalador(String message) {
        Snackbar.make(getView(),getString(R.string.erro_carregar_fornecedores),Snackbar.LENGTH_LONG).show();
    }
}