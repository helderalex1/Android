package amsi.dei.estg.ipleiria.RightPrice.Modelo;


//Modelo
public class Funcao {
    private String funcao;
    private String user_id;

//Construtor
    public Funcao(String funcao, String user_id) {
        this.funcao = funcao;
        this.user_id = user_id;
    }

    //FunÇões GET
    public String getFuncao() {
        return funcao;
    }

    public String getUser_id() {
        return user_id;
    }

    //Funções SET

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


}
