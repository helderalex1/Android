package amsi.dei.estg.ipleiria.RightPrice.Admin.Vistas_Listas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Adaptadores.Utilizadores_aceitar_Adapter;
import amsi.dei.estg.ipleiria.RightPrice.Admin.Detalhes_aceitar_utilizador_admin;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizadores;
import amsi.dei.estg.ipleiria.RightPrice.R;
import static amsi.dei.estg.ipleiria.RightPrice.Admin.Detalhes_aceitar_utilizador_admin.DETALHES_UTILIZADOR;

public class Lista_Aceitar_Cliente_admin_Fragment extends Fragment {
    private ListView lVListautilizadorespendentes;
    private ArrayList<Utilizadores> utilizadores;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_aceitar_cliente_admin, container, false);
        setHasOptionsMenu(true);
        utilizadores=SingletonGerirOrcamentos.getInstance().getUtilizadores_array();
        lVListautilizadorespendentes= view.findViewById(R.id.lvUtilizadoresPendentes);
        lVListautilizadorespendentes.setAdapter(new Utilizadores_aceitar_Adapter(getContext(),utilizadores));
        // Inflate the layout for this fragment



        lVListautilizadorespendentes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Utilizadores temutilizador =(Utilizadores) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), Detalhes_aceitar_utilizador_admin.class);
                intent.putExtra(DETALHES_UTILIZADOR,temutilizador.getId());
                startActivityForResult(intent,Detalhes_aceitar_utilizador_admin.ACEITAR);
            }
        });


        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode== Activity.RESULT_OK){

        }else if (resultCode== Activity.RESULT_CANCELED){
            Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}