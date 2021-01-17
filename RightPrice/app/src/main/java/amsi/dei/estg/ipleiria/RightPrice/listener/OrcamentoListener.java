package amsi.dei.estg.ipleiria.RightPrice.listener;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Orcamento;

//Listener para carregar os Orçamentos dos clientes
public interface OrcamentoListener {
    void onRefreshListaOrcamentos(ArrayList<Orcamento> listaOrcamentos);
    void onErroOrcamentos(String message);
    void onSucssecAddCliente();
    void onErroDetalhesUtilizadores(String message);
}
