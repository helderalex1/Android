package amsi.dei.estg.ipleiria.RightPrice.listener;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.FornecedorInstalador;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;


//Listener para carregar os Fornecedores e para mensagem de erro
public interface ConhecerFornecedorListener {

    void onrefreshFornecedores(ArrayList<Utilizador> Fornecedor);
    void onRefreshFornecedoreInstalador(ArrayList<FornecedorInstalador> FornecedorInstalador);
    void onErroFornecedorInstalador(String message);

}
