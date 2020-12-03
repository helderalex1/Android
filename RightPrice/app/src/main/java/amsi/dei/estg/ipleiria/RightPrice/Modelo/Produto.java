package amsi.dei.estg.ipleiria.RightPrice.Modelo;

public class Produto {
   private int id;
   private int id_fornecedor;
   private String imagem;
   private String nome;
   private String referencia;
   private String descrição;
   private int preco;

   //Construtor


    public Produto(int id, int id_fornecedor, String imagem, String nome, String referencia, String descrição, int preco) {
        this.id = id;
        this.id_fornecedor = id_fornecedor;
        this.imagem = imagem;
        this.nome = nome;
        this.referencia = referencia;
        this.descrição = descrição;
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

    public String getDescrição() {
        return descrição;
    }

    public int getPreco() {
        return preco;
    }

    //Funções SET

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }
}
