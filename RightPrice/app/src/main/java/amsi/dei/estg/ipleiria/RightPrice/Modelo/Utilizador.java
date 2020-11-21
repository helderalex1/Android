package amsi.dei.estg.ipleiria.RightPrice.Modelo;

import android.graphics.drawable.BitmapDrawable;

import java.util.function.BinaryOperator;

public class Utilizador {
    private int id;
    private String username;
    private String nome_empresa;
    private int telemovel;
    private String email;
    private int imagem;
    private int categoria_id;
    private int status;

    public Utilizador(int id, String username, String nome_empresa, int telemovel, String email, int imagem, int categoria_id, int status) {
        this.id = id;
        this.username = username;
        this.nome_empresa = nome_empresa;
        this.telemovel = telemovel;
        this.email = email;
        this.imagem = imagem;
        this.categoria_id = categoria_id;
        this.status = status;
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
}
