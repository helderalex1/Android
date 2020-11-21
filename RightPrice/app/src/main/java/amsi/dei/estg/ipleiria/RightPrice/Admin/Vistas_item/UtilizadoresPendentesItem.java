package amsi.dei.estg.ipleiria.RightPrice.Admin.Vistas_item;

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

public class UtilizadoresPendentesItem extends Fragment {


    private  TextView nome, empresa, email,categoria;

    public UtilizadoresPendentesItem(){};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        View view = inflater.inflate(R.layout.item_list_aceitar_cliente_admin, container, false);

        nome = view.findViewById(R.id.tVNomeAceitarUtilizador);
        empresa = view.findViewById(R.id.tvEmpresaAceitarUtilizador);
        email = view.findViewById(R.id.tvEmailAceitarUtilizador);
        categoria = view.findViewById(R.id.tvCategoriaAceitarUtilizador);
        carregarutilizadores();
        //Inflate the layout for this fragment
        return view;
    }

    private void carregarutilizadores() {
        ArrayList<Utilizador> utilizadors = SingletonGerirOrcamentos.getInstance().getUtilizadores_array();
        if(utilizadors.size() > 0){
            Utilizador utilizador = utilizadors.get(0);
            nome.setText(utilizador.getUsername());
            empresa.setText(utilizador.getNome_empresa());
            email.setText(utilizador.getEmail());
            categoria.setText(utilizador.getCategoria_id());
        }
    }
}