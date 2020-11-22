package amsi.dei.estg.ipleiria.RightPrice.Fornecedor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.ProdutosFornecedor;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.R;

public class Adicionar_Produto_Fornecedor_Fragment extends Fragment {

    private EditText nome_produto, referência, descrição, preço;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.item_list_produto_fornecedor, container, false);
        nome_produto = view.findViewById(R.id.edtNomeClienteInstalador);
        referência = view.findViewById(R.id.edtReferênciaProduto);
        descrição = view.findViewById(R.id.edtDescrição);
        preço = view.findViewById(R.id.edtPreçoProduto);
        carregarprodutosfornecedor();
        //Inflate the layout for this fragment
        return view;
    }

    private void carregarprodutosfornecedor() {
        ArrayList<ProdutosFornecedor> produtosFornecedors = SingletonGerirOrcamentos.getInstance().getProdutos_fornecedor_array();
        if (produtosFornecedors.size() > 0) {
            ProdutosFornecedor produtosFornecedor = produtosFornecedors.get(0);
            nome_produto.setText(produtosFornecedor.getNome());
            referência.setText(produtosFornecedor.getReferência());
            descrição.setText(produtosFornecedor.getDescrição());
            preço.setText(produtosFornecedor.getPreço());
        }
    }
}