package amsi.dei.estg.ipleiria.RightPrice.listener;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Categoria;

//Listener para as categorias
public interface CategoriaListener {
    void onRefreshListaCategorias(ArrayList<Categoria> listacategorias);
}
