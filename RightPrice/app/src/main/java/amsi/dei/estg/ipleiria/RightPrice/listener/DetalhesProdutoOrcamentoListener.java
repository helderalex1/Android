package amsi.dei.estg.ipleiria.RightPrice.listener;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.ProdutoOrcamento;

// Listener para os Detalhes dos Produtos do Orcamento
public interface DetalhesProdutoOrcamentoListener {
    void onSucssecProduto();
    void onSucssecEditValor();
    void onErroDetalhesprodutos(String message);
    void onRefreshProdutosOrcamento(ArrayList<ProdutoOrcamento> produtoOrcamentos);
}
