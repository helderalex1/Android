package amsi.dei.estg.ipleiria.RightPrice.Modelo;

//Modelo Orcamento
public class Orcamento {
    private int id;
    private int id_cliente;
    private String data_orcamento;
    private int margem;
    private float total;
    private String nome;

    //Construtor
    public Orcamento(int id, int id_cliente, String data_orcamento, int margem, float total, String nome) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.data_orcamento = data_orcamento;
        this.margem = margem;
        this.total = total;
        this.nome = nome;
    }

    //Funções GET


    public int getId() {
        return id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public String getData_orcamento() { return data_orcamento; }

    public int getMargem() {
        return margem;
    }

    public float getTotal() {
        return total;
    }

    public String getNome() {
        return nome;
    }


    //Funções SET

    public void setId(int id) {
        this.id = id;
    }

    public void setData_orçemento(String data_orcamento) { this.data_orcamento = data_orcamento; }

    public void setMargem(int margem) {
        this.margem = margem;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
