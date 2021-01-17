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
import amsi.dei.estg.ipleiria.RightPrice.Admin.DetalhesUtilizador;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;
import amsi.dei.estg.ipleiria.RightPrice.R;
import amsi.dei.estg.ipleiria.RightPrice.listener.UtilizadoresListListener;



//Lista que o admin vê os utilizadores do sistema

public class ListaUtilizadoresAdmin extends Fragment implements SwipeRefreshLayout.OnRefreshListener, UtilizadoresListListener {
    private ListView lVListautilizadores;
    private SearchView searchView;
    private SwipeRefreshLayout swipe;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_utilizadores_admin, container, false);
        setHasOptionsMenu(true);


        lVListautilizadores = view.findViewById(R.id.lvUtilizadoresAdmin);
        swipe= view.findViewById(R.id.swiperefrechUtilizadoresAdmin);

        //funcao de click da lista
         lVListautilizadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Utilizador temutilizador = (Utilizador) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), DetalhesUtilizador.class);
                intent.putExtra(DetalhesUtilizador.DETALHES_UTILIZADOR, temutilizador.getId());
                intent.putExtra(DetalhesUtilizador.Tipo_abrir_atividade, 2);
                startActivityForResult(intent, DetalhesUtilizador.Bloquear_Desbloquear);
            }
        });
        swipe.setOnRefreshListener(this);



        SingletonGerirOrcamentos.getInstance(getContext()).setutilizadoresListListener(this);
        SingletonGerirOrcamentos.getInstance(getContext()).getAllCategoriasAPI(getContext());
        SingletonGerirOrcamentos.getInstance(getContext()).getAllUtilizadoresAdminAPI(getContext());


        return view;
    }



    //funcao de retorno da atividade excuta por cima
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            SingletonGerirOrcamentos.getInstance(getContext()).getAllUtilizadoresAdminAPI(getContext());
            switch (SingletonGerirOrcamentos.getInstance(getContext()).getValor_send_message()){
                case 1:
                        Snackbar.make(getView(),"Utilizador aceite com sucesso",Snackbar.LENGTH_LONG).show();
                        break;
                case 2:
                        Snackbar.make(getView(),"Utilizador banido com sucesso",Snackbar.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    //funcao de criaçao do menu de pesquisa
    @Override
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
                    for (Utilizador utilizador : SingletonGerirOrcamentos.getInstance(getContext()).getUtilizadoresAceites()) {
                        if (utilizador.getUsername().toLowerCase().contains(s.toLowerCase())) {
                            temp_utilizador.add(utilizador);
                        }
                    }
                    lVListautilizadores.setAdapter(new ListaUtilizadoresAdapter(getContext(), temp_utilizador));
                    return true;
                }
            });
            super.onCreateOptionsMenu(menu, inflater);
        }


        //funcao de resreh da lista
    @Override
    public void onRefresh() {
        SingletonGerirOrcamentos.getInstance(getContext()).getAllUtilizadoresAdminAPI(getContext());
        swipe.setRefreshing(false);
    }


    //refresh da lista
    @Override
    public void onRefreshListaUtilizadores(ArrayList<Utilizador> listaUtilizadores) {
      if(listaUtilizadores!=null){
          ArrayList<Utilizador>  utilizadoresaceites=SingletonGerirOrcamentos.getInstance(getContext()).getUtilizadoresAceites();
          lVListautilizadores.setAdapter(new ListaUtilizadoresAdapter(getContext(),utilizadoresaceites));
      }
    }


    //erro do get de utilizadores
    @Override
    public void onErroListUtilizadores(String message) {
        Snackbar.make(getView(),message,Snackbar.LENGTH_LONG).show();
    }
}


