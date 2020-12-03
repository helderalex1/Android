package amsi.dei.estg.ipleiria.RightPrice.Modelo;


//Modelo
public class Categoria {
    private int id;
    private String nome_categoria;

    //Construtor
    public Categoria(int id, String nome_categoria) {
        this.id = id;
        this.nome_categoria = nome_categoria;
    }

    //Funções GET

    public int getId() {
        return id;
    }

    public String getNome_categoria() {
        return nome_categoria;
    }

    //Funções SET

    public void setId(int id) {
        this.id = id;
    }

    public void setNome_categoria(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }
}
