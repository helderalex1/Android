package amsi.dei.estg.ipleiria.RightPrice.Modelo.Funcao;


//Modelo
public class Funcao {
    private int id;
    private String funcao;
    private int user_id;

    //Construtor
    public Funcao(int id, String funcao, int user_id) {
        this.id = id;
        this.funcao = funcao;
        this.user_id = user_id;
    }

    //Funções GET

    public int getId() {
        return id;
    }

    public String getFuncao() {
        return funcao;
    }

    public int getUser_id() {
        return user_id;
    }

    //Funções SET

    public void setId(int id) {
        this.id = id;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


}
