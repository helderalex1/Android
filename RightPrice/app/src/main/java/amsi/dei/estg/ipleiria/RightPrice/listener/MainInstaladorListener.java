package amsi.dei.estg.ipleiria.RightPrice.listener;


//Listener par a Main do Instalador. Serve para carregar a estatistica do instalador
public interface MainInstaladorListener {
    void onRefreshQuantidadeCLientesMain(int valor);
    void onRefreshQuantidadeFornecedoresMain(int valor);
    void onRefreshQuantidadeOrcamentosMain(int valor);

    void onRefreshClientesAPI();
    void onRefreshProdutosOrcamentoAPI();
}
