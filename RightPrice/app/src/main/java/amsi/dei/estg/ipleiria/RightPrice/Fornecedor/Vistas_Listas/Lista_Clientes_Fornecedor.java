package amsi.dei.estg.ipleiria.RightPrice.Fornecedor.Vistas_Listas;

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

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Adaptadores.UtilizadoresAceitarAdapter;
import amsi.dei.estg.ipleiria.RightPrice.Admin.Detalhes_aceitar_utilizador_admin;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;
import amsi.dei.estg.ipleiria.RightPrice.R;
import static amsi.dei.estg.ipleiria.RightPrice.Admin.Detalhes_aceitar_utilizador_admin.DETALHES_UTILIZADOR;

public class Lista_Clientes_Fornecedor extends Fragment {

    private ListView lvListaClienteconhecerFornecedor;
    private ArrayList<Utilizador> utilizador;
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_clientes_fornecedor, container, false);
        setHasOptionsMenu(true);
        utilizador = SingletonGerirOrcamentos.getInstance().getInstaladorFornecedor(1);
        lvListaClienteconhecerFornecedor = view.findViewById(R.id.lvListaClienteconhecerFornecedor);
        lvListaClienteconhecerFornecedor.setAdapter(new UtilizadoresAceitarAdapter(getContext(),utilizador));

       lvListaClienteconhecerFornecedor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Utilizador utilizador = (Utilizador) parent.getItemAtPosition(position);
               Intent intent = new Intent(getContext(), Detalhes_aceitar_utilizador_admin.class);
               intent.putExtra(DETALHES_UTILIZADOR, utilizador.getId());
               startActivityForResult(intent, Detalhes_aceitar_utilizador_admin.ACEITAR);
           }
       });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode== Activity.RESULT_OK){

        }else if (resultCode == Activity.RESULT_CANCELED){

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
                ArrayList<Utilizador> temp_utilizador = new ArrayList<>();
                for (Utilizador utilizador: SingletonGerirOrcamentos.getInstance().getInstaladorFornecedor(1)) {
                    if (utilizador.getUsername().toLowerCase().contains(s.toLowerCase())) {
                        temp_utilizador.add(utilizador);
                    }
                }
                lvListaClienteconhecerFornecedor.setAdapter(new UtilizadoresAceitarAdapter(getContext(),temp_utilizador));
                return true;

            }

        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}
