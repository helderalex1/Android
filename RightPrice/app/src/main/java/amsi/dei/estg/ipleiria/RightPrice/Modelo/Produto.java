package amsi.dei.estg.ipleiria.RightPrice.Modelo;


//Modelo Produto
public class Produto {
   private int id;
   private int id_fornecedor;
   private String imagem;
   private String nome;
   private String referencia;
   private String descricao;
   private float preco;

   //Construtor


    public Produto(int id, int id_fornecedor, String imagem, String nome, String referencia, String descricao, float preco) {
        this.id = id;
        this.id_fornecedor = id_fornecedor;
        this.imagem = imagem;
        this.nome = nome;
        this.referencia = referencia;
        this.descricao = descricao;
        this.preco = preco;
    }

    //Funções GET


    public int getId() {
        return id;
    }

    public int getId_fornecedor() {
        return id_fornecedor;
    }

    public String getImagem() {
        return imagem;
    }

    public String getNome() {
        return nome;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getPreco() {
        return preco;
    }

    //Funções SET


    public void setId(int id) {
        this.id = id;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
}
