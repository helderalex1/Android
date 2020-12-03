package amsi.dei.estg.ipleiria.RightPrice.Modelo;

//Modelo
public class FornecedorInstalador {
    int id_fornecedor;
    int id_instalador;

    //Construtor
    public FornecedorInstalador(int id_fornecedor, int id_instalador) {
        this.id_fornecedor = id_fornecedor;
        this.id_instalador = id_instalador;
    }

    //Funções GET
    public int getId_fornecedor() {
        return id_fornecedor;
    }

    public int getId_instalador() {
        return id_instalador;
    }
    //Funções SET

}