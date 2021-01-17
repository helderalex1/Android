package amsi.dei.estg.ipleiria.RightPrice.listener;


import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Funcao;

//Listener para a atividade de detalhes de utilizador
public interface DetalhesUtilizadorListener {
    void onRefreshDetalhesUtilizadores();
    void onErroDetalhesUtilizadores(String message);


    //para as funcoes
    void onRefreshListaFuncao(ArrayList<Funcao> listafuncoes);
    void onErroListaFuncao(String message);
}
