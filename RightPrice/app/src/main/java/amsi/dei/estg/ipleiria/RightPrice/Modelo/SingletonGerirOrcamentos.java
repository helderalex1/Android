package amsi.dei.estg.ipleiria.RightPrice.Modelo;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.R;

public class SingletonGerirOrcamentos {

    private ArrayList<ClientesInstalador> clientesInstaladores;
    private ArrayList<Utilizadores> utilizadores_array;
    private static SingletonGerirOrcamentos instance = null;


    public static synchronized SingletonGerirOrcamentos getInstance() {
        if (instance == null) {
            instance = new SingletonGerirOrcamentos();
        }
        return instance;
    }

    private SingletonGerirOrcamentos() {
        gerarFakeData();
    }

    private void gerarFakeData() {
        clientesInstaladores = new ArrayList<ClientesInstalador>();
        clientesInstaladores.add(new ClientesInstalador(1, 1, "A", 12345, 2020, "aa@a"));
        clientesInstaladores.add(new ClientesInstalador(2, 1, "B", 54321, 2020, "bb@b"));
        utilizadores_array=new ArrayList<Utilizadores>();
        utilizadores_array.add(new Utilizadores(1, "Manuel", "Continente", 919564869, "a@a.pt", 0, 1, 9));
        utilizadores_array.add(new Utilizadores(2, "Rui", "Captemp", 919705797, "a@ab.pt", 0, 1, 9));
    }

    public ArrayList<ClientesInstalador> getClientesInstaladores() {
        return new ArrayList<>(clientesInstaladores);
    }

    public ClientesInstalador getClienteInstalador(int id) {
        for (ClientesInstalador clienteInstalador : clientesInstaladores) {
            if (clienteInstalador.getId() == id) {
                return clienteInstalador;
            }
        }
        return null;
    }

    public ArrayList<Utilizadores> getUtilizadores_array() {
        return new ArrayList<>(utilizadores_array);
    }

    public Utilizadores getUtilizador(int id) {
        for (Utilizadores utilizador : utilizadores_array) {
            if (utilizador.getId() == id) {
                return utilizador;
            }
        }
        return null;
    }
}

