package amsi.dei.estg.ipleiria.RightPrice.listener;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Produto;

//Listener para carregar os produtos do orcamento.Serve para o instalador carregar os seus orcamentos e produtos
public interface ProdutosOrcamentoListener {
    void onRefreshListaProdutosOrcamentos(ArrayList<Produto> listaOrcamentos);
    void onErroprodutosOrcamentos(String message);
    void onSucssecAddOrcamento();
    void onErroorcamento(String message);
    void onSucssecProdutos();
}
