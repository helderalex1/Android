package amsi.dei.estg.ipleiria.RightPrice.Fornecedor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.R;
import amsi.dei.estg.ipleiria.RightPrice.listener.MainFornecedorFragmentListener;

//funcao main do Fornecedor
public class MainFragmentFornecedor extends Fragment implements MainFornecedorFragmentListener {
    private TextView quantidade_produtos_textview;
    private TextView quantidade_instaladores_text_View;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_main_fornecedor, container, false);
        quantidade_produtos_textview=view.findViewById(R.id.textViewTotalProdutos);
        quantidade_instaladores_text_View=view.findViewById(R.id.textViewTotalInstaladoresdoFornecedor);

        SingletonGerirOrcamentos.getInstance(getContext()).setFornecedorInstaladorListener(null);
        SingletonGerirOrcamentos.getInstance(getContext()).setMainFornecedorFragmentListener(this);
        SingletonGerirOrcamentos.getInstance(getContext()).getAllFornecedorInstaladorAPI(getContext(),2);
        SingletonGerirOrcamentos.getInstance(getContext()).getAllRelacionFornecedorInstaladorAPI(getContext());
        SingletonGerirOrcamentos.getInstance(getContext()).getAllProdutosAPI(getContext());
        SingletonGerirOrcamentos.getInstance(getContext()).getAllCategoriasAPI(getContext());
        return view;
    }

    //carrega os dados da estatistica
    @Override
    public void onRefreshdadoProdutoFornecedorMain(int valor) {
        quantidade_produtos_textview.setText(String.valueOf(valor));
    }

    @Override
    public void onRefreshdadoQunantidadesInstaladoresFOrnecedoresMain(int valor) {
        quantidade_instaladores_text_View.setText(String.valueOf(valor));
    }
}