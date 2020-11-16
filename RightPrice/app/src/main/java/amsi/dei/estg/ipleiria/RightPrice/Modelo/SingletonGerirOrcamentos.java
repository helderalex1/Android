package amsi.dei.estg.ipleiria.RightPrice.Modelo;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.R;

public class SingletonGerirOrcamentos {

    private ArrayList<ClientesInstalador> clientesInstaladores;
    private static SingletonGerirOrcamentos instance=null;



    public static synchronized SingletonGerirOrcamentos getInstance(){
        if (instance == null){
            instance = new SingletonGerirOrcamentos();
        }
        return instance;
    }

    private SingletonGerirOrcamentos() {
        gerarFakeData();
    }

    private void gerarFakeData() {
        clientesInstaladores = new ArrayList<ClientesInstalador>();
        clientesInstaladores.add(new ClientesInstalador(1,1,"A",12345,2020,"aa@a" ));
        clientesInstaladores.add(new ClientesInstalador(2,1,"B",54321,2020,"bb@b"));

    }

    public ArrayList<ClientesInstalador> getClientesInstaladores(){
        return new ArrayList<>(clientesInstaladores);
    }

    public ClientesInstalador getClienteInstalador(int id){
        for (ClientesInstalador clienteInstalador: clientesInstaladores) {
            if (clienteInstalador.getId()==id){
                return clienteInstalador;
            }
        }
        return null;
    }
}