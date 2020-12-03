package amsi.dei.estg.ipleiria.RightPrice.Modelo;

import java.util.Date;

//Modelo
public class Orçamento {
    private int id;
    private int id_cliente;
    private Date data_orçemento;
    private int margem;
    private float total;
    private String Nome;

    //Construtor
    public Orçamento(int id, int id_cliente, Date data_orçemento, int margem, float total, String nome) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.data_orçemento = data_orçemento;
        this.margem = margem;
        this.total = total;
        Nome = nome;
    }

    //Funções GET


    public int getId() {
        return id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public Date getData_orçemento() {
        return data_orçemento;
    }

    public int getMargem() {
        return margem;
    }

    public float getTotal() {
        return total;
    }

    public String getNome() {
        return Nome;
    }


    //Funções SET

    public void setData_orçemento(Date data_orçemento) {
        this.data_orçemento = data_orçemento;
    }

    public void setMargem(int margem) {
        this.margem = margem;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setNome(String nome) {
        Nome = nome;
    }
}
