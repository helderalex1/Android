package amsi.dei.estg.ipleiria.RightPrice.Modelo;



//Modelo
public class Anexo {
    private int id;
    private int id_orçamento;
    private String nome;
    private String imagem;

    public Anexo(int id, int id_orçamento, String nome, String imagem) {
        this.id = id;
        this.id_orçamento = id_orçamento;
        this.nome = nome;
        this.imagem = imagem;
    }


    //Funções GET
    public int getId() {
        return id;
    }

    public int getId_orçamento() {
        return id_orçamento;
    }

    public String getNome() {
        return nome;
    }

    public String getImagem() {
        return imagem;
    }

}
