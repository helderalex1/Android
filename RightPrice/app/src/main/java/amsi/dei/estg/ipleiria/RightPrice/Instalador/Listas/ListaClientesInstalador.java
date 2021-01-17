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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Adaptadores.Instalador.ListaClientesInstaladorAdapter;
import amsi.dei.estg.ipleiria.RightPrice.Fornecedor.DetalhesProdutoFornecedor;
import amsi.dei.estg.ipleiria.RightPrice.Instalador.DetalhesCliente;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Cliente;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.R;
import amsi.dei.estg.ipleiria.RightPrice.listener.ClienteListener;

import static amsi.dei.estg.ipleiria.RightPrice.Instalador.DetalhesCliente.DETALHES_CLIENTE_INSTALADOR;

//classe de lista de clientes instalador
public class ListaClientesInstalador extends Fragment  implements SwipeRefreshLayout.OnRefreshListener, ClienteListener {

    private ListView lVListaClientesInstalador;
    private SearchView searchView;
    private SwipeRefreshLayout swipe;
    private FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_clientes_instalador, container, false);
        setHasOptionsMenu(true);

        lVListaClientesInstalador = view.findViewById(R.id.lvListaClientesinstalador);
        floatingActionButton = view.findViewById(R.id.fabaddClienteInstalador);
        swipe = view.findViewById(R.id.swiperefrechCleintesInstalador);

        //botao de clique da lista
        lVListaClientesInstalador.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente clientesInstalador = (Cliente) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), DetalhesCliente.class);
                intent.putExtra(DETALHES_CLIENTE_INSTALADOR, clientesInstalador.getId());
                startActivityForResult(intent, DetalhesCliente.EDITAR);
            }
        });

        //evento do botao de guardar
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetalhesCliente.class);
                startActivityForResult(intent, DetalhesCliente.ADICIONAR);
            }
        });

        swipe.setOnRefreshListener(this);

        SingletonGerirOrcamentos.getInstance(getContext()).setMainInstaladorListener(null);
        SingletonGerirOrcamentos.getInstance(getContext()).setDetalhesProdutoOrcamentoListener(null);
        SingletonGerirOrcamentos.getInstance(getContext()).setClienteListener(this);
        SingletonGerirOrcamentos.getInstance(getContext()).getAllClientesAPI(getContext());

        return view;
    }

    //funcao quando retorna da atividade anterior
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    SingletonGerirOrcamentos.getInstance(getContext()).getAllClientesAPI(getContext());
                }
            }, 1000);
            switch (requestCode) {
                case DetalhesProdutoFornecedor.ADICIONAR:
                    Snackbar.make(getView(), getString(R.string.cliente_adicionado_com_sucesso), Snackbar.LENGTH_LONG).show();
                    break;

                case DetalhesProdutoFornecedor.EDITAR:
                    Snackbar.make(getView(), getString(R.string.cliente_editado_com_sucesso), Snackbar.LENGTH_LONG).show();
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    //funcao de criaçao do menu
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
                ArrayList<Cliente> clientesInstaladorestemp = new ArrayList<>();
                for (Cliente clientesInstalador: SingletonGerirOrcamentos.getInstance(getContext()).getClienteDB()) {
                    if (clientesInstalador.getNome().toLowerCase().contains(s.toLowerCase())) {
                        clientesInstaladorestemp.add(clientesInstalador);
                    }
                }
                lVListaClientesInstalador.setAdapter(new ListaClientesInstaladorAdapter(getContext(),clientesInstaladorestemp));
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    //fucao de rtefresh
    @Override
    public void onRefresh() {
        SingletonGerirOrcamentos.getInstance(getContext()).getAllClientesAPI(getContext());
        swipe.setRefreshing(false);
    }

    //fucao de refresh da lista
    @Override
    public void onRefreshListaClientes(ArrayList<Cliente> listaclientes) {
        if(listaclientes!=null){
            lVListaClientesInstalador.setAdapter(new ListaClientesInstaladorAdapter(getContext(),listaclientes));
        }
    }

    //funcao de apresentaçao de erros
    @Override
    public void onErroListaCliente(String message) {
        Snackbar.make(getView(),R.string.erro_carregar_clientes,Snackbar.LENGTH_LONG).show();
    }
}