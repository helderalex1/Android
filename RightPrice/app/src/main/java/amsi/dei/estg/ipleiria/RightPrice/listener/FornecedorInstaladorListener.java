package amsi.dei.estg.ipleiria.RightPrice.listener;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.FornecedorInstalador;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;

//Listener para carregar as relações Fornecedor Instalador
public interface FornecedorInstaladorListener {
    void onrefreshInstaladores(ArrayList<Utilizador> Instalador);
    void onRefreshFornecedoreInstalador(ArrayList<FornecedorInstalador> FornecedorInstalador);
    void onErroFornecedorInstalador(String message);
}
