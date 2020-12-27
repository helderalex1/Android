package amsi.dei.estg.ipleiria.RightPrice.Modelo.Cliente;


//Modelo
public class Cliente {
    private int id;
    //utilizador a quem está associado o cliente
    private int user_id;
    private String nome;
    private int telemovel;
    private int nif;
    private String email;

    //construtor
    public Cliente(int id, int user_id, String nome, int telemovel, int nif, String email) {
        this.id = id;
        this.user_id = user_id;
        this.nome = nome;
        this.telemovel = telemovel;
        this.nif = nif;
        this.email = email;
    }

    //Funções GET

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getNome() {
        return nome;
    }

    public int getTelemovel() {
        return telemovel;
    }

    public int getNif() {
        return nif;
    }

    public String getEmail() {
        return email;
    }

    //Funções SET


    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
