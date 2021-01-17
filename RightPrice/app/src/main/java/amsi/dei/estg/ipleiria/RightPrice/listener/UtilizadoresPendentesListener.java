package amsi.dei.estg.ipleiria.RightPrice.listener;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;

//Listener para a lista de utilizadores pendentes.Serve para o Admin ver os utilizadores pendentes
public interface UtilizadoresPendentesListener {
    void onRefreshListaUtilizadorespendentes(ArrayList<Utilizador> listaUtilizadores);
    void onErroListUtilizadores(String message);
}
