package amsi.dei.estg.ipleiria.RightPrice.Admin.Vistas_Listas;

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

import amsi.dei.estg.ipleiria.RightPrice.Adaptadores.ListaUtilizadoresAdapter;
import amsi.dei.estg.ipleiria.RightPrice.Adaptadores.Utilizadores_aceitar_Adapter;
import amsi.dei.estg.ipleiria.RightPrice.Detalhes_aceitar_utilizador_admin;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;
import amsi.dei.estg.ipleiria.RightPrice.R;

import static amsi.dei.estg.ipleiria.RightPrice.Detalhes_aceitar_utilizador_admin.DETALHES_UTILIZADOR;
import static amsi.dei.estg.ipleiria.RightPrice.Detalhes_aceitar_utilizador_admin.Tipo_abrir_atividade;


public class Lista_utilizadores_admin extends Fragment {
    private ListView lVListautilizadores;
    private ArrayList<Utilizador> utilizadores;
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_utilizadores_admin, container, false);
        setHasOptionsMenu(true);
        utilizadores = SingletonGerirOrcamentos.getInstance().getUtilizadores_array();
        lVListautilizadores = view.findViewById(R.id.lvUtilizadoresAdmin);
        lVListautilizadores.setAdapter(new ListaUtilizadoresAdapter(getContext(), utilizadores));

        return view;
    }

    /*

        lVListautilizadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Utilizador temutilizador = (Utilizador) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), Detalhes_aceitar_utilizador_admin.class);
                intent.putExtra(DETALHES_UTILIZADOR, temutilizador.getId());
                intent.putExtra(Tipo_abrir_atividade, 1);
                startActivityForResult(intent, Detalhes_aceitar_utilizador_admin.ACEITAR);

            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {

        } else if (resultCode == Activity.RESULT_CANCELED) {

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

  /*  @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_pesquisa, menu);
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
                for (Utilizador utilizador : SingletonGerirOrcamentos.getInstance().getUtilizadores_pendentes_array()) {
                    if (utilizador.getUsername().toLowerCase().contains(s.toLowerCase())) {
                        temp_utilizador.add(utilizador);
                    }
                }
                lVListautilizadorespendentes.setAdapter(new Utilizadores_aceitar_Adapter(getContext(), temp_utilizador));
                return true;

            }

        });


        super.onCreateOptionsMenu(menu, inflater);
    }*/

}


