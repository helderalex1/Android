package amsi.dei.estg.ipleiria.RightPrice.Instalador.vistas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.ClientesInstalador;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.R;

public class ClienteItem extends Fragment {

    private TextView nome, telemovel, nif, email;

    public ClienteItem(){};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        View view = inflater.inflate(R.layout.item_list_clientes_instalador, container, false);

        nome = view.findViewById(R.id.tVNomeClienteInstalador);
        telemovel = view.findViewById(R.id.tVTelClienteInstalador);
        nif = view.findViewById(R.id.tVNifClienteInstalador);
        email = view.findViewById(R.id.tVEmailClienteInstalador);
        carregarClientes();
        //Inflate the layout for this fragment
        return view;
    }

    private void carregarClientes() {
        ArrayList<ClientesInstalador> clientesInstaladores = SingletonGerirOrcamentos.getInstance().getClientesInstaladores();
        if(clientesInstaladores.size() > 0){
            ClientesInstalador clientesInstalador = clientesInstaladores.get(0);
            nome.setText(clientesInstalador.getNome());
            telemovel.setText(""+clientesInstalador.getTelemovel());
            nif.setText(""+clientesInstalador.getNif());
            email.setText(clientesInstalador.getEmail());
        }
    }
}