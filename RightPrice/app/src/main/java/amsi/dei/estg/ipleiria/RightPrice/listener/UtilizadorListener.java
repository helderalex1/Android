package amsi.dei.estg.ipleiria.RightPrice.listener;

//Listeener para carregar Utilizadores
public interface UtilizadorListener {
    void onUpdateUtilizador(String utilizador);
    void onErroUtilizador(String message);

}
