package amsi.dei.estg.ipleiria.RightPrice.Instalador;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;
import amsi.dei.estg.ipleiria.RightPrice.R;

public class ItemConhecerFornecedor extends Fragment {

    private TextView nome, empresa, email, categoria;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        View view = inflater.inflate(R.layout.item_list_clientes_fornecedor, container, false);

        nome = view.findViewById(R.id.tVNomeClienteFornecedor);
        empresa = view.findViewById(R.id.tVEmpresaClienteFornecedor);
        email = view.findViewById(R.id.tvEmailClienteFornecedor);
        categoria = view.findViewById(R.id.tvCategoriaClienteFornecedor);
        carregarClientes();
        //Inflate the layout for this fragment
        return view;
    }

    private void carregarClientes() {
        ArrayList<Utilizador> clientesInstaladores = SingletonGerirOrcamentos.getInstance().getUtilizadores_array();
        if(clientesInstaladores.size() > 0){
            Utilizador clientesInstalador = clientesInstaladores.get(0);
            nome.setText(clientesInstalador.getUsername());
            empresa.setText(clientesInstalador.getTelemovel());
            email.setText(clientesInstalador.getEmail());
            categoria.setText(""+clientesInstalador.getCategoria_id());
        }
    }
}