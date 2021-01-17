package amsi.dei.estg.ipleiria.RightPrice.listener;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Cliente;

//Listener para carregar os clientes
public interface ClienteListener {
    void onRefreshListaClientes(ArrayList<Cliente> listaclientes);
    void onErroListaCliente(String message);
}
