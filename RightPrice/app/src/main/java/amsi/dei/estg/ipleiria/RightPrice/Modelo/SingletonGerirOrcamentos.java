package amsi.dei.estg.ipleiria.RightPrice.Modelo;

import java.util.ArrayList;

public class SingletonGerirOrcamentos {

    private ArrayList<ClientesInstalador> clientesInstaladores;
    private ArrayList<Utilizador> utilizadores_array;
    private ArrayList<Fornecedor_instalador> fornecedor_instaladores;
<<<<<<< HEAD
=======
    private ArrayList<ProdutosFornecedor> produtos_fornecedor;
>>>>>>> PWSAP-128-cdigo-para-abrir-os-clientes-d
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
        utilizadores_array=new ArrayList<Utilizador>();
        utilizadores_array.add(new Utilizador(1, "Manuel", "Continente", 919564869, "a@a.pt", 0, 1, 9));
<<<<<<< HEAD
        utilizadores_array.add(new Utilizador(2, "Rui", "Captemp", 919705797, "a@ab.pt", 0, 1, 9));
        fornecedor_instaladores = new ArrayList<Fornecedor_instalador>();
        fornecedor_instaladores.add(new Fornecedor_instalador(1,2));

=======
        utilizadores_array.add(new Utilizador(2, "Rui12", "Captemp", 919705797, "a@ab.pt", 0, 1, 9));
        utilizadores_array.add(new Utilizador(3, "Rui122", "Captemp", 919705797, "a@ab.pt", 0, 1, 9));
        fornecedor_instaladores = new ArrayList<Fornecedor_instalador>();
        fornecedor_instaladores.add(new Fornecedor_instalador(1,2));
        fornecedor_instaladores.add(new Fornecedor_instalador(1,3));
        produtos_fornecedor = new ArrayList<ProdutosFornecedor>();
        produtos_fornecedor.add(new ProdutosFornecedor(1,"asASd",12,"sa",10,1));
        produtos_fornecedor.add(new ProdutosFornecedor(2,"bcs",12,"s2a",13,1));
>>>>>>> PWSAP-128-cdigo-para-abrir-os-clientes-d
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

    public ArrayList<Utilizador> getUtilizadores_array() {
        return new ArrayList<>(utilizadores_array);
    }

    public Utilizador getUtilizador(int id) {
        for (Utilizador utilizador : utilizadores_array) {
            if (utilizador.getId() == id) {
                return utilizador;
            }
        }
        return null;
    }
<<<<<<< HEAD
=======

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

>>>>>>> PWSAP-128-cdigo-para-abrir-os-clientes-d
    public ArrayList<Utilizador> getInstaladorFornecedor(int id_fornecedor){
        ArrayList<Utilizador> utilizadorestemp;
        utilizadorestemp = new ArrayList<Utilizador>();
        for (Fornecedor_instalador fornecedor_instalador:fornecedor_instaladores) {
            if(fornecedor_instalador.getId_fornecedor() == id_fornecedor){
                for (Utilizador utilizador : utilizadores_array) {
                    if (utilizador.getId()==fornecedor_instalador.getId_instalador()){
                        utilizadorestemp.add(new Utilizador(utilizador.getId(),utilizador.getUsername(),utilizador.getNome_empresa(),utilizador.getTelemovel(),utilizador.getEmail(),utilizador.getImagem(),utilizador.getCategoria_id(),utilizador.getStatus()));
                    }
                }
            }
        }
        return utilizadorestemp;
    }
<<<<<<< HEAD
=======

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
>>>>>>> PWSAP-128-cdigo-para-abrir-os-clientes-d
}

