package amsi.dei.estg.ipleiria.RightPrice.Modelo;


//Modelo Funcao
public class Funcao {
    private String funcao;
    private int user_id;

    //Construtor
    public Funcao(String funcao, int user_id) {
        this.funcao = funcao;
        this.user_id = user_id;
    }

    //Funções GET


    public String getFuncao() {
        return funcao;
    }

    public int getUser_id() {
        return user_id;
    }

    //Funções SET

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


}
