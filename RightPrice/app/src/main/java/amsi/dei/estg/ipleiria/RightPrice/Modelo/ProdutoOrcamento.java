package amsi.dei.estg.ipleiria.RightPrice.Modelo;

//Modelo
public class ProdutoOrcamento {
    private int id_orcamento;
    private int id_produto;
    private int quantidade;

    //Construtor

    public ProdutoOrcamento(int id_orcamento, int id_produto, int quantidade) {
        this.id_orcamento = id_orcamento;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
    }

    //Funções GET

    public int getId_orcamento() {
        return id_orcamento;
    }

    public int getId_produto() {
        return id_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

   // Funções SET

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
