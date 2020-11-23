package amsi.dei.estg.ipleiria.RightPrice.Modelo;

import android.graphics.drawable.BitmapDrawable;

import java.util.function.BinaryOperator;

public class Utilizador {
    private int id;
    private String username;
    private int nif;
    private String nome_empresa;
    private int telemovel;
    private String email;
    private int imagem;
    private int categoria_id;
    private int status;
    private int user_id;

    public Utilizador(int id, String username, int nif, String nome_empresa, int telemovel, String email, int imagem, int categoria_id, int status, int user_id) {
        this.id = id;
        this.username = username;
        this.nif = nif;
        this.nome_empresa = nome_empresa;
        this.telemovel = telemovel;
        this.email = email;
        this.imagem = imagem;
        this.categoria_id = categoria_id;
        this.status = status;
        this.user_id = user_id;
    }


    public int getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public String getNome_empresa() {
        return nome_empresa;
    }

    public void setNome_empresa(String nome_empresa) {
        this.nome_empresa = nome_empresa;
    }

    public int getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public int getCategoria_id() {
        return categoria_id;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
