package amsi.dei.estg.ipleiria.RightPrice.listener;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.FornecedorInstalador;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;

//Listener para a Lista dos meus Fornecedores
//pertence ao instalador
public interface InstaladorFornecedorListener {
    void onrefreshFornecedores(ArrayList<Utilizador> Forncedor);
    void onRefreshFornecedoreInstalador(ArrayList<FornecedorInstalador> FornecedorInstalador);
    void onErroFornecedorInstalador(String message);
}
