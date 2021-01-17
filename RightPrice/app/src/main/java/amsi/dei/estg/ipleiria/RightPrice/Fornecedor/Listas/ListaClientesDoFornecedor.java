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

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Adaptadores.Fornecedor.ListaInstaladoresDoFornecedorAdapter;
import amsi.dei.estg.ipleiria.RightPrice.Fornecedor.DetalhesClienteFornecedor;
import amsi.dei.estg.ipleiria.RightPrice.Fornecedor.DetalhesProdutoFornecedor;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.FornecedorInstalador;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;
import amsi.dei.estg.ipleiria.RightPrice.R;
import amsi.dei.estg.ipleiria.RightPrice.listener.FornecedorInstaladorListener;


//funcao de lista de clientes do Fornecedor
public class ListaClientesDoFornecedor extends Fragment implements SwipeRefreshLayout.OnRefreshListener, FornecedorInstaladorListener {

    private ListView lvListaClienteFornecedor;
    private SearchView searchView;
    private SwipeRefreshLayout swipe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_clientes_fornecedor, container, false);
        setHasOptionsMenu(true);


        lvListaClienteFornecedor = view.findViewById(R.id.lvListaClientesFornecedor);
        swipe=view.findViewById(R.id.swiperefrechClientesFornecedor);

        //fucnao de botao da lista
       lvListaClienteFornecedor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Utilizador utilizador = (Utilizador) parent.getItemAtPosition(position);
               Intent intent = new Intent(getContext(), DetalhesClienteFornecedor.class);
               intent.putExtra(DetalhesClienteFornecedor.DETALHES_Cliente, utilizador.getId());
               startActivityForResult(intent, DetalhesClienteFornecedor.EDITAR);
           }
       });

        swipe.setOnRefreshListener(this);

        SingletonGerirOrcamentos.getInstance(getContext()).setFornecedorInstaladorListener(this);
        SingletonGerirOrcamentos.getInstance(getContext()).getAllCategoriasAPI(getContext());
        SingletonGerirOrcamentos.getInstance(getContext()).getAllRelacionFornecedorInstaladorAPI(getContext());
        return view;
    }

    //funcao de retorna da atividade anterior
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    SingletonGerirOrcamentos.getInstance(getContext()).getAllRelacionFornecedorInstaladorAPI(getContext());
                }
            }, 1000);
            switch (requestCode) {
                case DetalhesProdutoFornecedor.EDITAR:
                    Snackbar.make(getView(), getString(R.string.instalador_removido_com_sucesso), Snackbar.LENGTH_LONG).show();
                    break;
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    //criaçao das opçoes de menu. Neste case de pesquisa
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
                ArrayList<Utilizador> clienteInstalador = new ArrayList<>();
                for (Utilizador utilizador: SingletonGerirOrcamentos.getInstance(getContext()).getInstaladoresdoFornecedor()) {
                    if (utilizador.getUsername().toLowerCase().contains(s.toLowerCase())) {
                        clienteInstalador.add(utilizador);
                    }
                }
                lvListaClienteFornecedor.setAdapter(new ListaInstaladoresDoFornecedorAdapter(getContext(),clienteInstalador));
                return true;

            }

        });
        super.onCreateOptionsMenu(menu, inflater);
    }


    //funcao de refresh
    @Override
    public void onRefresh() {
        SingletonGerirOrcamentos.getInstance(getContext()).getAllRelacionFornecedorInstaladorAPI(getContext());
        swipe.setRefreshing(false);
    }

    //funcao de atualizar os dados
    @Override
    public void onrefreshInstaladores(ArrayList<Utilizador> Instalador) {
        if(Instalador!=null){
                ArrayList<Utilizador>  clientesFornecedor=SingletonGerirOrcamentos.getInstance(getContext()).getInstaladoresdoFornecedor();
                lvListaClienteFornecedor.setAdapter(new ListaInstaladoresDoFornecedorAdapter(getContext(),clientesFornecedor));
        }
    }

    //funcao de refresh de fornecedoresInstaladores
    @Override
    public void onRefreshFornecedoreInstalador(ArrayList<FornecedorInstalador> FornecedorInstalador) {
        if(FornecedorInstalador!=null){
            SingletonGerirOrcamentos.getInstance(getContext()).getAllFornecedorInstaladorAPI(getContext(),2);
        }
    }

//Funcaode erro carregar dados
    @Override
    public void onErroFornecedorInstalador(String message) {
        Snackbar.make(getView(),getString(R.string.Erro_carregar_instaladores),Snackbar.LENGTH_LONG).show();
    }
}
