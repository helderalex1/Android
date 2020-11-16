package amsi.dei.estg.ipleiria.RightPrice.Modelo;

public class ClientesInstalador {
    private int id;
    private int user_id;
    private String nome;
    private int telemovel;
    private int nif;
    private String email;

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClientesInstalador(int id, int user_id, String nome, int telemovel, int nif, String email) {
        this.id = id;
        this.user_id = user_id;
        this.nome = nome;
        this.telemovel = telemovel;
        this.nif = nif;
        this.email = email;
    }
}