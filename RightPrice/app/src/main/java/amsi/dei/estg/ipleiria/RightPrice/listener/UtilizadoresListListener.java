package amsi.dei.estg.ipleiria.RightPrice.listener;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;

//Listener para a lista de utilizadores no admin
public interface UtilizadoresListListener {

    void onRefreshListaUtilizadores(ArrayList<Utilizador> listaUtilizadores);
    void onErroListUtilizadores(String message);

}
