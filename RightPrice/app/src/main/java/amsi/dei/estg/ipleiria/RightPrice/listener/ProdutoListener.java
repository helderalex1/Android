package amsi.dei.estg.ipleiria.RightPrice.listener;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Produto;

//Listener para carregar os Produtos. Serve para o Fornecedor ver os seus produtos
public interface ProdutoListener {
    void onRefreshListaProdutos(ArrayList<Produto> listaProdutos);
    void onErroListaProfuto(String message);
}
