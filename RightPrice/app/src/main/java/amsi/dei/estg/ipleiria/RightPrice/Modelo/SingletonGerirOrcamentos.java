package amsi.dei.estg.ipleiria.RightPrice.Modelo;

import android.content.Context;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador.Utilizador;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador.UtilizadorBDHelper;

public class SingletonGerirOrcamentos {

    private static SingletonGerirOrcamentos instance = null;

    //declaração da Base de dados de cada array
    private static UtilizadorBDHelper utilizadorBDHelper = null;

    //array a utilizar com os valores da base de dados
    private ArrayList<Utilizador> utilizadores_array;



    private ArrayList<Cliente> clientesInstaladores;
    private ArrayList<FornecedorInstalador> fornecedor_instaladores;
    private ArrayList<Produto> produtos_fornecedor;



    public static synchronized SingletonGerirOrcamentos getInstance(Context context) {
        if (instance == null) {
            instance = new SingletonGerirOrcamentos(context);
        }
        return instance;
    }

    private SingletonGerirOrcamentos(Context context) {
       utilizadores_array = new ArrayList<Utilizador>();
       utilizadorBDHelper = new UtilizadorBDHelper(context);
       getUtilizadoresDB();

        //gerarFakeData();
    }


    //funçoes para os Utilizadores
    public ArrayList<Utilizador> getUtilizadoresDB(){
        utilizadores_array = utilizadorBDHelper.getAllUserDB();
        return new ArrayList<>(utilizadores_array);
    }

    public Utilizador getUtilizador(int id_utilizador){
        for (Utilizador utilizador : utilizadores_array){
            if (utilizador.getId()==id_utilizador){
                return utilizador;
            }
        }
        return null;
    }

    public ArrayList<Utilizador> getUtilizadorstatus9 (){
        ArrayList<Utilizador> utilizadorespendentes;
        utilizadorespendentes = new ArrayList<Utilizador>();
        for (Utilizador utilizador: utilizadores_array) {
            if (utilizador.getStatus()==9){
                utilizadorespendentes.add(new Utilizador(utilizador.getUsername(),utilizador.getNome(),utilizador.getNome_empresa(),utilizador.getTelemovel(),utilizador.getEmail(),utilizador.getImagem(),utilizador.getCategoria_id(),utilizador.getStatus()));
            }
        }
        return utilizadorespendentes;
    }

    public ArrayList<Utilizador> getUtilizadoresAceites(){
        ArrayList<Utilizador> utilizadoresaceites;
        utilizadoresaceites = new ArrayList<Utilizador>();
        for (Utilizador utilizador: utilizadores_array){
            if (utilizador.getStatus()==10 || utilizador.getStatus()==0){
                utilizadoresaceites.add(new Utilizador(utilizador.getUsername(),utilizador.getNome(),utilizador.getNome_empresa(),utilizador.getTelemovel(),utilizador.getEmail(),utilizador.getImagem(),utilizador.getCategoria_id(),utilizador.getStatus()));
            }
        }
        return utilizadoresaceites;
    }



    public void adicionarUtilizadorDB(Utilizador utilizador){
        Utilizador auxutilizador = utilizadorBDHelper.adicionarUserDB(utilizador);
        if(auxutilizador!=null){
            utilizadores_array.add(utilizador);
        }
    }

    public void removerUtilizadorDB(int id){
        Utilizador u = getUtilizador(id);
        if(u != null){
            if (utilizadorBDHelper.removerUserDB(u.getId())){
                utilizadores_array.remove(u);
                System.out.println("--> Livro removido com sucesso da DB");
            }
        }
    }

    public void editarUtilizadorDB(Utilizador utilizador){
        if (!utilizadores_array.contains(utilizador)){
            return;
        }
        Utilizador u = getUtilizador(utilizador.getId());
        u.setUsername(utilizador.getUsername());
        u.setNome(utilizador.getNome());
        u.setNome_empresa(utilizador.getNome_empresa());
        u.setEmail(utilizador.getEmail());
        u.setImagem(utilizador.getImagem());
        u.setStatus(utilizador.getStatus());
        u.setTelemovel(utilizador.getTelemovel());
    }




    private void gerarFakeData() {
        clientesInstaladores = new ArrayList<Cliente>();
        clientesInstaladores.add(new Cliente(1, 1, "A", 12345, 2020, "aa@a"));
        clientesInstaladores.add(new Cliente(2,2, "B", 54321, 2020, "bb@b"));
        clientesInstaladores.add(new Cliente(3,2, "C", 54321, 2020, "bb@b"));

        fornecedor_instaladores = new ArrayList<FornecedorInstalador>();
        fornecedor_instaladores.add(new FornecedorInstalador(1,2));
        fornecedor_instaladores.add(new FornecedorInstalador(1,3));
        produtos_fornecedor = new ArrayList<Produto>();
        produtos_fornecedor.add(new Produto(1,2,"","sa","10","1",1));
        produtos_fornecedor.add(new Produto(2,2,"","s2a","13","1",1));
    }

    public ArrayList<FornecedorInstalador> getFornecedoress() {
        return new ArrayList<>(fornecedor_instaladores);
    }

    public ArrayList<Cliente> getClientesInstaladores() {
        return new ArrayList<>(clientesInstaladores);
    }

    public Cliente getClienteInstalador(int id) {
        for (Cliente clienteInstalador : clientesInstaladores) {
            if (clienteInstalador.getId() == id) {
                return clienteInstalador;
            }
        }
        return null;
    }





  /*


    public Produto getProduto(int id){
        for(Produto produtosFornecedor :produtos_fornecedor){
            if(produtosFornecedor.getId() == id){
                return produtosFornecedor;
            }
        }
        return null;
    }

    public ArrayList<Produto> getProdutos_fornecedor_array() {
        return new ArrayList<>(produtos_fornecedor);
    }

    public ArrayList<Utilizador> getInstaladorFornecedor(int id_fornecedor){
        ArrayList<Utilizador> utilizadorestemp;
        utilizadorestemp = new ArrayList<Utilizador>();
        for (FornecedorInstalador fornecedor_instalador:fornecedor_instaladores) {
            if(fornecedor_instalador.getId_fornecedor() == id_fornecedor){
                for (Utilizador utilizador : utilizadores_array) {
                    if (utilizador.getId()==fornecedor_instalador.getId_instalador()){
                        utilizadorestemp.add(new Utilizador(utilizador.getId(),utilizador.getUsername(),utilizador.getNome_empresa(),utilizador.getTelemovel(),utilizador.getEmail(),utilizador.getImagem(),utilizador.getCategoria_id(),utilizador.getStatus(),utilizador.getUser_id()));
                    }
                }
            }
        }
        return utilizadorestemp;
    }

    public ArrayList<Cliente> getClientesInstalador(int id_user){
        ArrayList<Cliente> clientesInstaladorestemp;
        clientesInstaladorestemp = new ArrayList<Cliente>();
       for (Cliente clientesInstalador:clientesInstaladores) {
           if (clientesInstalador.getUser_id() == id_user) {
               for (Utilizador utilizador : utilizadores_array) {
                   if (utilizador.getId() == clientesInstalador.getUser_id()) {
                       clientesInstaladorestemp.add(new Cliente(clientesInstalador.getId(), clientesInstalador.getUser_id(), clientesInstalador.getNome(), clientesInstalador.getTelemovel(), clientesInstalador.getNif(), clientesInstalador.getEmail()));
                   }
               }
           }
       }
        return clientesInstaladorestemp;
    }

    public ArrayList<Produto> getProdutos_fornecedor (int id_fornecedor){
        ArrayList<Produto> produtosFornecedortemp;
        produtosFornecedortemp = new ArrayList<Produto>();
        for(Produto produtoFornecedor: produtos_fornecedor){
            if(produtoFornecedor.getFornecedor_id() == id_fornecedor){
                produtosFornecedortemp.add(new Produto(produtoFornecedor.getId(),produtoFornecedor.getNome(),produtoFornecedor.getReferência(),produtoFornecedor.getDescrição(),produtoFornecedor.getPreço(),produtoFornecedor.getFornecedor_id()));
            }
        }
        return produtosFornecedortemp;
    }

    public ArrayList<Utilizador> getFornecedores(int id){
        int id_categoriainstalador = 0;
        boolean existe = false;
        ArrayList<Utilizador> fornecedorestemp;
        fornecedorestemp = new ArrayList<Utilizador>();
        for(Utilizador utilizador : utilizadores_array){
            if(utilizador.getId()== id){
                id_categoriainstalador= utilizador.getCategoria_id();

            }
        }
        if(id_categoriainstalador != 0) {
            for (Utilizador utilizador : utilizadores_array) {
                if (utilizador.getCategoria_id() == id_categoriainstalador && utilizador.getUser_id() == 2) {
                    for(FornecedorInstalador fornecedorInstalador:fornecedor_instaladores){
                        if(fornecedorInstalador.getId_instalador() != id && fornecedorInstalador.getId_fornecedor() == utilizador.getId()){
                            existe = true;
                        }
                        break;
                    }
                    if(!existe){
                        fornecedorestemp.add(new Utilizador(utilizador.getId(), utilizador.getUsername(), utilizador.getNome_empresa(), utilizador.getTelemovel(), utilizador.getEmail(), utilizador.getImagem(), utilizador.getCategoria_id(), utilizador.getStatus(), utilizador.getUser_id()));
                    }
                }
                existe=false;
            }
        }
        return fornecedorestemp;
    }*/
}
