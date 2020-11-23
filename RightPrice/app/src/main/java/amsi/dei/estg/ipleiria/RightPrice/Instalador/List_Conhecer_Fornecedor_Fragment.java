package amsi.dei.estg.ipleiria.RightPrice.Instalador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Adaptadores.ListaFornecedores;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;
import amsi.dei.estg.ipleiria.RightPrice.R;

public class List_Conhecer_Fornecedor_Fragment extends Fragment {

    private ArrayList<Utilizador> fornecedorInstaladores;
    private FloatingActionButton floatingActionButton;
    private SearchView searchView;
    private ListView lVFornecedoresInstaladores;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_conhecer_fornecedor, container, false);
        fornecedorInstaladores = SingletonGerirOrcamentos.getInstance().getFornecedores(1);
        lVFornecedoresInstaladores = view.findViewById(R.id.lvListaClienteconhecerFornecedor);
        lVFornecedoresInstaladores.setAdapter(new ListaFornecedores(getContext(),fornecedorInstaladores));
       /* lVListaClientesInstalador.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClientesInstalador clientesInstalador = (ClientesInstalador) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), Adicionar_Cliente_Instalador.class);
                intent.putExtra(DETALHES_CLIENTE_INSTALADOR, clientesInstalador.getId());
                startActivityForResult(intent, DetalhesProdutoFornecedor.ACEITAR);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),Adicionar_Cliente_Instalador.class);
                startActivityForResult(intent, Adicionar_Cliente_Instalador.EDITAR);
            }
        });*/
        return view;
    }

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode== Activity.RESULT_OK){
            clientesInstaladores = SingletonGerirOrcamentos.getInstance().getClientesInstaladores();
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
                ArrayList<ClientesInstalador> clientesInstaladorestemp = new ArrayList<>();
                for (ClientesInstalador clientesInstalador: SingletonGerirOrcamentos.getInstance().getClientesInstalador(1)) {
                    if (clientesInstalador.getNome().toLowerCase().contains(s.toLowerCase())) {
                        clientesInstaladorestemp.add(clientesInstalador);
                    }
                }
                lVListaClientesInstalador.setAdapter(new ListaClientesInstaladorAdapter(getContext(),clientesInstaladorestemp));
                return true;

            }

        });
        super.onCreateOptionsMenu(menu, inflater);
    }*/
}