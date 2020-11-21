package amsi.dei.estg.ipleiria.RightPrice.Fornecedor.Vista_Item;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Fornecedor_instalador;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;
import amsi.dei.estg.ipleiria.RightPrice.R;

public class InstaladorFornecedorItem extends Fragment {
    private TextView nome, empresa, email, categoria;

    public InstaladorFornecedorItem() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_list_clientes_fornecedor, container, false);
        nome = view.findViewById(R.id.tVNomeClienteFornecedor);
        empresa = view.findViewById(R.id.tVEmpresaClienteFornecedor);
        email = view.findViewById(R.id.tvEmailClienteFornecedor);
        categoria = view.findViewById(R.id.tvCategoriaClienteFornecedor);
        carregarutilizador();

        return view;
    }

    private void carregarutilizador() {
        ArrayList<Utilizador> utilizadores = SingletonGerirOrcamentos.getInstance().getInstaladorFornecedor(1);
        if(utilizadores.size() >0){
            Utilizador utilizador = utilizadores.get(0);
            nome.setText(utilizador.getUsername());
            empresa.setText(utilizador.getNome_empresa());
            email.setText(utilizador.getEmail());
            categoria.setText(utilizador.getCategoria_id());
        }
    }
}
