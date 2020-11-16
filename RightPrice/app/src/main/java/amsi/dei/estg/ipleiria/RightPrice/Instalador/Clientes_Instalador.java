package amsi.dei.estg.ipleiria.RightPrice.Instalador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Adaptadores.ListaClientesInstaladorAdapter;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.ClientesInstalador;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.R;

public class Clientes_Instalador extends Fragment {
    private ListView lVListaClientesInstalador;
    private ArrayList<ClientesInstalador> clientesInstaladores;
    private FloatingActionButton floatingActionButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clientes_instalador, container, false);
        setHasOptionsMenu(true);
        clientesInstaladores = SingletonGerirOrcamentos.getInstance().getClientesInstaladores();
        lVListaClientesInstalador = view.findViewById(R.id.lvListaClientesinstalador);
        floatingActionButton = view.findViewById(R.id.fabaddClienteInstalador);
        lVListaClientesInstalador.setAdapter(new ListaClientesInstaladorAdapter(getContext(),clientesInstaladores));

        //Inflate the layout for this fragment1
        return view;
    }
}