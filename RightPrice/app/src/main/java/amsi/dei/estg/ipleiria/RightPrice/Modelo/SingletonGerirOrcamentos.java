package amsi.dei.estg.ipleiria.RightPrice.Modelo;

import java.util.ArrayList;

public class SingletonGerirOrcamentos {

    private ArrayList<ClientesInstalador> clientesInstaladores;
    private ArrayList<Utilizador> utilizadores_array;
    private ArrayList<FornecedorInstalador> fornecedor_instaladores;
    private ArrayList<ProdutosFornecedor> produtos_fornecedor;
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
        clientesInstaladores.add(new ClientesInstalador(2,2, "B", 54321, 2020, "bb@b"));
        clientesInstaladores.add(new ClientesInstalador(3,2, "C", 54321, 2020, "bb@b"));
        utilizadores_array=new ArrayList<Utilizador>();
        utilizadores_array.add(new Utilizador(1, "Manuel","Continente", 919564869, "a@a.pt", 0, 1, 0,1));
        utilizadores_array.add(new Utilizador(2, "Rui12", "Captemp", 919705797, "a@ab.pt", 0, 1, 9,2));
        utilizadores_array.add(new Utilizador(3, "Rui122", "Captemp", 919705797, "a@ab.pt", 0, 1, 10,2));
        utilizadores_array.add(new Utilizador(4, "Ruhrthrr323i122", "Captemp", 919705797, "a@ab.pt", 0, 1, 10,2));
        utilizadores_array.add(new Utilizador(5, "Rufsdfshrghi122", "Captemp", 919705797, "a@ab.pt", 0, 1, 10,2));
        fornecedor_instaladores = new ArrayList<FornecedorInstalador>();
        fornecedor_instaladores.add(new FornecedorInstalador(1,2));
        fornecedor_instaladores.add(new FornecedorInstalador(1,3));
        produtos_fornecedor = new ArrayList<ProdutosFornecedor>();
        produtos_fornecedor.add(new ProdutosFornecedor(1,"asASd",12,"sa",10,1));
        produtos_fornecedor.add(new ProdutosFornecedor(2,"bcs",12,"s2a",13,1));
    }

    public ArrayList<FornecedorInstalador> getFornecedoress() {
        return new ArrayList<>(fornecedor_instaladores);
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

    public ArrayList<Utilizador> getUtilizadores_pendentes_array() {
        ArrayList<Utilizador> utilizadorespendentes;
        utilizadorespendentes = new ArrayList<Utilizador>();
        for (Utilizador utilizador: utilizadores_array) {
            if (utilizador.getStatus()==9){
                utilizadorespendentes.add(new Utilizador(utilizador.getId(),utilizador.getUsername(),utilizador.getNome_empresa(),utilizador.getTelemovel(),utilizador.getEmail(),utilizador.getImagem(),utilizador.getCategoria_id(),utilizador.getStatus(),utilizador.getUser_id()));
            }
        }
        return utilizadorespendentes;
    }

    public Utilizador getUtilizador(int id) {
        for (Utilizador utilizador : utilizadores_array) {
            if (utilizador.getId() == id) {
                return utilizador;
            }
        }
        return null;
    }
    public ArrayList<Utilizador> getUtilizadores_array(){
       ArrayList<Utilizador> utilizadoresaceites;
       utilizadoresaceites = new ArrayList<Utilizador>();
       for (Utilizador utilizador: utilizadores_array){
           if (utilizador.getStatus()==10 || utilizador.getStatus()==0){
               utilizadoresaceites.add(new Utilizador(utilizador.getId(),utilizador.getUsername(),utilizador.getNome_empresa(),utilizador.getTelemovel(),utilizador.getEmail(),utilizador.getImagem(),utilizador.getCategoria_id(),utilizador.getStatus(),utilizador.getUser_id()));
           }
       }
       return utilizadoresaceites;
    }

    public ProdutosFornecedor getProduto(int id){
        for(ProdutosFornecedor produtosFornecedor :produtos_fornecedor){
            if(produtosFornecedor.getId() == id){
                return produtosFornecedor;
            }
        }
        return null;
    }

    public ArrayList<ProdutosFornecedor> getProdutos_fornecedor_array() {
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

    public ArrayList<ClientesInstalador> getClientesInstalador(int id_user){
        ArrayList<ClientesInstalador> clientesInstaladorestemp;
        clientesInstaladorestemp = new ArrayList<ClientesInstalador>();
       for (ClientesInstalador clientesInstalador:clientesInstaladores) {
           if (clientesInstalador.getUser_id() == id_user) {
               for (Utilizador utilizador : utilizadores_array) {
                   if (utilizador.getUser_id() == clientesInstalador.getUser_id()) {
                       clientesInstaladorestemp.add(new ClientesInstalador(clientesInstalador.getId(), clientesInstalador.getUser_id(), clientesInstalador.getNome(), clientesInstalador.getTelefone(), clientesInstalador.getNif(), clientesInstalador.getEmail()));
                   }
               }
           }
       }
        return clientesInstaladorestemp;
    }

    public ArrayList<ProdutosFornecedor> getProdutos_fornecedor (int id_fornecedor){
        ArrayList<ProdutosFornecedor> produtosFornecedortemp;
        produtosFornecedortemp = new ArrayList<ProdutosFornecedor>();
        for(ProdutosFornecedor produtoFornecedor: produtos_fornecedor){
            if(produtoFornecedor.getFornecedor_id() == id_fornecedor){
                produtosFornecedortemp.add(new ProdutosFornecedor(produtoFornecedor.getId(),produtoFornecedor.getNome(),produtoFornecedor.getReferência(),produtoFornecedor.getDescrição(),produtoFornecedor.getPreço(),produtoFornecedor.getFornecedor_id()));
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
    }
}
