package amsi.dei.estg.ipleiria.RightPrice.Admin.Listas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

import amsi.dei.estg.ipleiria.RightPrice.Adaptadores.Admin.ListaUtilizadoresAdapter;
import amsi.dei.estg.ipleiria.RightPrice.Adaptadores.Admin.UtilizadoresAceitarAdapter;
import amsi.dei.estg.ipleiria.RightPrice.Admin.DetalhesUtilizador;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;
import amsi.dei.estg.ipleiria.RightPrice.R;
import amsi.dei.estg.ipleiria.RightPrice.listener.UtilizadoresPendentesListener;

import static amsi.dei.estg.ipleiria.RightPrice.Admin.DetalhesUtilizador.DETALHES_UTILIZADOR;
import static amsi.dei.estg.ipleiria.RightPrice.Admin.DetalhesUtilizador.Tipo_abrir_atividade;


//Lista de aceitar os utilizadores
//é onde o administrador vê as pessoas pendentes
public class ListaAceitarUtilizadoresAdmin extends Fragment implements SwipeRefreshLayout.OnRefreshListener, UtilizadoresPendentesListener {
    private ListView lVListautilizadorespendentes;
    private SearchView searchView;
    private SwipeRefreshLayout swipe;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_aceitar_utilizador_admin, container, false);
        setHasOptionsMenu(true);


        lVListautilizadorespendentes= view.findViewById(R.id.lvUtilizadoresPendentes);
        swipe=view.findViewById(R.id.swiperefrechUtilizadoresAceitarAdmin);
        
        //funcao click quando é clicado na lista
        lVListautilizadorespendentes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Utilizador temutilizador =(Utilizador) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), DetalhesUtilizador.class);
                intent.putExtra(DETALHES_UTILIZADOR,temutilizador.getId());
                intent.putExtra(Tipo_abrir_atividade,1);
                startActivityForResult(intent, DetalhesUtilizador.ACEITAR);

            }
        });
        swipe.setOnRefreshListener( this);

        SingletonGerirOrcamentos.getInstance(getContext()).setutilizadoresPendentesListener(this);
        SingletonGerirOrcamentos.getInstance(getContext()).getAllCategoriasAPI(getContext());
        SingletonGerirOrcamentos.getInstance(getContext()).getAllUtilizadoresAdminAPI(getContext());
        return view;
    }

    //funcao que excuta quando o atividade inicianda em cima é fechada
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            SingletonGerirOrcamentos.getInstance(getContext()).getAllUtilizadoresAdminAPI(getContext());
            switch (SingletonGerirOrcamentos.getInstance(getContext()).getValor_send_message()){
                case 3:
                    Snackbar.make(getView(),getString(R.string.utilizador_aceite_com_sucesso),Snackbar.LENGTH_LONG).show();
                    break;
                case 4:
                    Snackbar.make(getView(),getString(R.string.utilizador_recusado_com_sucesso),Snackbar.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //funcao que cria as opçoes no menu. Neste caso a opçao de pesquisa
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
                for (Utilizador utilizador: SingletonGerirOrcamentos.getInstance(getContext()).getUtilizadorstatus9()) {
                    if (utilizador.getUsername().toLowerCase().contains(s.toLowerCase())) {
                        temp_utilizador.add(utilizador);
                    }
                }
                lVListautilizadorespendentes.setAdapter(new UtilizadoresAceitarAdapter(getContext(),temp_utilizador));
                return true;

            }

        });


        super.onCreateOptionsMenu(menu, inflater);
    }


    //funcao de refresh da lista
    @Override
    public void onRefresh() {
        SingletonGerirOrcamentos.getInstance(getContext()).getAllUtilizadoresAdminAPI(getContext());
        swipe.setRefreshing(false);
    }

    //funcao do listener para utilizadores pendentes
    //Carrega os utilizadores pendetes
    @Override
    public void onRefreshListaUtilizadorespendentes(ArrayList<Utilizador> listaUtilizadores) {
        if(listaUtilizadores!=null){
            ArrayList<Utilizador>  utilizadorespendentes=SingletonGerirOrcamentos.getInstance(getContext()).getUtilizadorstatus9();
            lVListautilizadorespendentes.setAdapter(new ListaUtilizadoresAdapter(getContext(),utilizadorespendentes));
        }
    }


    //funcao de erro de get valores 
    @Override
    public void onErroListUtilizadores(String message) {
        Snackbar.make(getView(),message,Snackbar.LENGTH_LONG).show();
    }

}