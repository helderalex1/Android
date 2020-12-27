package amsi.dei.estg.ipleiria.RightPrice.Instalador.Listas;

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

import amsi.dei.estg.ipleiria.RightPrice.Adaptadores.Instalador.ListaClientesInstaladorAdapter;
import amsi.dei.estg.ipleiria.RightPrice.Fornecedor.Atividades.DetalhesProdutoFornecedor;
import amsi.dei.estg.ipleiria.RightPrice.Instalador.Atividades.AdicionarCliente;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Cliente.Cliente;
import amsi.dei.estg.ipleiria.RightPrice.R;

import static amsi.dei.estg.ipleiria.RightPrice.Instalador.Atividades.AdicionarCliente.DETALHES_CLIENTE_INSTALADOR;

public class ListaClientesInstalador extends Fragment {
    private ListView lVListaClientesInstalador;
    private ArrayList<Cliente> clientesInstaladores;
    private ArrayList<Cliente> fornecedores;
    private FloatingActionButton floatingActionButton;
    private SearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_clientes_instalador, container, false);
        setHasOptionsMenu(true);
        //clientesInstaladores = SingletonGerirOrcamentos.getInstance(getContext()).getClientesInstalador(1);
        lVListaClientesInstalador = view.findViewById(R.id.lvListaClientesinstalador);
        floatingActionButton = view.findViewById(R.id.fabaddClienteInstalador);
        lVListaClientesInstalador.setAdapter(new ListaClientesInstaladorAdapter(getContext(),clientesInstaladores));
        lVListaClientesInstalador.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente clientesInstalador = (Cliente) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), AdicionarCliente.class);
                intent.putExtra(DETALHES_CLIENTE_INSTALADOR, clientesInstalador.getId());
                startActivityForResult(intent, DetalhesProdutoFornecedor.ACEITAR);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AdicionarCliente.class);
                startActivityForResult(intent, AdicionarCliente.EDITAR);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode== Activity.RESULT_OK){
           // clientesInstaladores = SingletonGerirOrcamentos.getInstance().getClientesInstaladores();
            lVListaClientesInstalador.setAdapter(new ListaClientesInstaladorAdapter(getContext(),clientesInstaladores));
            switch (requestCode){
                case DetalhesProdutoFornecedor.ADICIONAR:
                    Snackbar.make(getView(), "Cliente_adicionado_com_sucesso",Snackbar.LENGTH_LONG).show();
                    break;

                case DetalhesProdutoFornecedor.EDITAR:
                    Snackbar.make(getView(), "Cliente editado_com_sucesso",Snackbar.LENGTH_LONG).show();
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
                ArrayList<Cliente> clientesInstaladorestemp = new ArrayList<>();
               /* for (Cliente clientesInstalador: Singl) {
                    if (clientesInstalador.getNome().toLowerCase().contains(s.toLowerCase())) {
                        clientesInstaladorestemp.add(clientesInstalador);
                    }
                }*/
                lVListaClientesInstalador.setAdapter(new ListaClientesInstaladorAdapter(getContext(),clientesInstaladorestemp));
                return true;

            }

        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}