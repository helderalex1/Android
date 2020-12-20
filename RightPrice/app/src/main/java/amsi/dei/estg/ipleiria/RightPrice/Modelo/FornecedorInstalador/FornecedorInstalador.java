package amsi.dei.estg.ipleiria.RightPrice.Modelo.FornecedorInstalador;

//Modelo
public class FornecedorInstalador {
    int id;
    int id_fornecedor;
    int id_instalador;

    //Construtor
    public FornecedorInstalador(int id, int id_fornecedor, int id_instalador) {
        this.id = id;
        this.id_fornecedor = id_fornecedor;
        this.id_instalador = id_instalador;
    }

    //Funções GET

    public int getId() {
        return id;
    }

    public int getId_fornecedor() {
        return id_fornecedor;
    }

    public int getId_instalador() {
        return id_instalador;
    }
    //Funções SET

    public void setId(int id) {
        this.id = id;
    }

    public void setId_fornecedor(int id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

    public void setId_instalador(int id_instalador) {
        this.id_instalador = id_instalador;
    }
}