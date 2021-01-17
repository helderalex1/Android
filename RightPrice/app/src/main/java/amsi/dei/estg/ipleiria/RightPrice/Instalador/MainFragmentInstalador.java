package amsi.dei.estg.ipleiria.RightPrice.Instalador;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.R;
import amsi.dei.estg.ipleiria.RightPrice.listener.MainInstaladorListener;


//Classe da main do Isntalador
public class MainFragmentInstalador extends Fragment implements MainInstaladorListener {
    private TextView quantidade_clientes;
    private TextView quantidade_fornecedores;
    private TextView quantidade_orcamentos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_main_instalador, container, false);
       quantidade_clientes=view.findViewById(R.id.textViewTotalClientes);
       quantidade_fornecedores=view.findViewById(R.id.textViewTotalSeusFornecedores);
       quantidade_orcamentos=view.findViewById(R.id.textViewTotalOrcamentos);


       SingletonGerirOrcamentos.getInstance(getContext()).setInstaladorFornecedorListener(null);
       SingletonGerirOrcamentos.getInstance(getContext()).setConhecerFornecedorListener(null);
        SingletonGerirOrcamentos.getInstance(getContext()).setDetalhesProdutoOrcamentoListener(null);
        SingletonGerirOrcamentos.getInstance(getContext()).setMainInstaladorListener(this);
        SingletonGerirOrcamentos.getInstance(getContext()).getAllCategoriasAPI(getContext());
        SingletonGerirOrcamentos.getInstance(getContext()).getAllFornecedorInstaladorAPI(getContext(),1);
        SingletonGerirOrcamentos.getInstance(getContext()).getAllRelacionInstaladorFornececorAPI(getContext());
        SingletonGerirOrcamentos.getInstance(getContext()).getAllClientesAPI(getContext());
        SingletonGerirOrcamentos.getInstance(getContext()).getAllOrcamentosAPI(getContext());
        SingletonGerirOrcamentos.getInstance(getContext()).getAllProdutosFornecedorAPI(getContext());
        SingletonGerirOrcamentos.getInstance(getContext()).getAllProdutosOrcamentoAPI(getContext());



        return view;
    }

    //carrega aestatistica
    @Override
    public void onRefreshQuantidadeCLientesMain(int valor) {
        quantidade_clientes.setText(String.valueOf(valor));
    }

    @Override
    public void onRefreshQuantidadeFornecedoresMain(int valor) {
        quantidade_fornecedores.setText(String.valueOf(valor));
    }

    @Override
    public void onRefreshQuantidadeOrcamentosMain(int valor) {
        quantidade_orcamentos.setText(String.valueOf(valor));
    }

    @Override
    public void onRefreshClientesAPI() {
        SingletonGerirOrcamentos.getInstance(getContext()).getAllOrcamentosAPI(getContext());
    }

    @Override
    public void onRefreshProdutosOrcamentoAPI() {
        SingletonGerirOrcamentos.getInstance(getContext()).getAllProdutosOrcamentoAPI(getContext());
    }
}