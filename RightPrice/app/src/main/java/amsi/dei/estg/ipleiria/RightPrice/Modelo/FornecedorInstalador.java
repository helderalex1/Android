package amsi.dei.estg.ipleiria.RightPrice.Modelo;

public class FornecedorInstalador {
    int id_fornecedor;
    int id_instalador;

    public int getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(int id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

    public int getId_instalador() {
        return id_instalador;
    }

    public void setId_instalador(int id_instalador) {
        this.id_instalador = id_instalador;
    }

    public FornecedorInstalador(int id_fornecedor, int id_instalador) {
        this.id_fornecedor = id_fornecedor;
        this.id_instalador = id_instalador;
    }
}