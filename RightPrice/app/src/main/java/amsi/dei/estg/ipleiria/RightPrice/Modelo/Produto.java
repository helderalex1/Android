package amsi.dei.estg.ipleiria.RightPrice.Modelo;

public class ProdutosFornecedor {
    int id;
    String Nome;
    int Referência;
    String Descrição;
    int preço;
    int fornecedor_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public int getReferência() {
        return Referência;
    }

    public void setReferência(int referência) {
        Referência = referência;
    }

    public String getDescrição() {
        return Descrição;
    }

    public void setDescrição(String descrição) {
        Descrição = descrição;
    }

    public int getPreço() {
        return preço;
    }

    public void setPreço(int preço) {
        this.preço = preço;
    }

    public int getFornecedor_id() {
        return fornecedor_id;
    }

    public void setFornecedor_id(int fornecedor_id) {
        this.fornecedor_id = fornecedor_id;
    }

    public ProdutosFornecedor(int id, String nome, int referência, String descrição, int preço, int fornecedor_id) {
        this.id = id;
        Nome = nome;
        Referência = referência;
        Descrição = descrição;
        this.preço = preço;
        this.fornecedor_id = fornecedor_id;
    }
}
