package amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;

import android.graphics.drawable.BitmapDrawable;

import java.util.function.BinaryOperator;

//Modelo
public class Utilizador {
    private int id;
    private String username;
    private String nome;
    private String nome_empresa;
    private int telemovel;
    private String email;
    private String imagem;
    private int categoria_id;
    private int status;

    //construtor
    public Utilizador( String username,String nome, String nome_empresa, int telemovel, String email, String imagem, int categoria_id, int status) {
        this.username = username;
        this.nome = nome;
        this.nome_empresa = nome_empresa;
        this.telemovel = telemovel;
        this.email = email;
        this.imagem = imagem;
        this.categoria_id = categoria_id;
        this.status = status;
    }


    //Funções Get

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNome (){
        return nome;
    }

    public String getNome_empresa() {
        return nome_empresa;
    }

    public int getTelemovel() {
        return telemovel;
    }

    public String getEmail() {
        return email;
    }

    public String getImagem() {
        return imagem;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public int getStatus() {
        return status;
    }


    //Funções SET

    public void setId(int id){
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setNome_empresa(String nome_empresa) {
        this.nome_empresa = nome_empresa;
    }

    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
