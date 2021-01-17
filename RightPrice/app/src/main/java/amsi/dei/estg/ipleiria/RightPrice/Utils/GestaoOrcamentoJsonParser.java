package amsi.dei.estg.ipleiria.RightPrice.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Categoria;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Cliente;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.FornecedorInstalador;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Funcao;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Orcamento;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Produto;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.ProdutoOrcamento;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;


//Classe que faz a descodificação do json
public class GestaoOrcamentoJsonParser {


    //funcao de descodificar todoas as categorias
    public static ArrayList<Categoria> parserJsonCategorias(JSONArray response){
        ArrayList<Categoria> listaCategorias = new ArrayList<>();

        try {
            for (int i = 0; i < response.length(); i++){
                JSONObject categoria = (JSONObject) response.get(i);
                int idCategoria = categoria.getInt("id");
                String nomeCategoria= categoria.getString("nome_Categoria");
                Categoria auxcategoria= new Categoria(idCategoria,nomeCategoria);
                listaCategorias.add(auxcategoria);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
        return listaCategorias;
    }

    //funcao de descodificar todos os clientes
    public static ArrayList<Cliente> parserJsonClientes(JSONArray response){
        ArrayList<Cliente> listaClientes = new ArrayList<>();

        try {
            for (int i = 0; i< response.length(); i++){
                JSONObject cliente = (JSONObject) response.get(i);
                int idcliente=cliente.getInt("id");
                int idUser= cliente.getInt("user_id");
                String nomeCliente = cliente.getString("nome");
                int TelemovelCliente = cliente.getInt("Telemovel");
                String emailCLiente= cliente.getString("Email");
               int NifCliente = cliente.getInt("Nif");

               Cliente auxcliente= new Cliente(idcliente,idUser,nomeCliente,TelemovelCliente,NifCliente,emailCLiente);
               listaClientes.add(auxcliente);

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return listaClientes;
    }

    //funcao de descodificar todos os FornecedorInstalador
    public static ArrayList<FornecedorInstalador> parserJsonFornecedoresInstaladores(JSONArray response){
        ArrayList<FornecedorInstalador> listaFornecedoresInstaladores = new ArrayList<>();

        try {
            for (int i = 0; i< response.length(); i++){
                JSONObject fornecedorInstalador = (JSONObject) response.get(i);
                int fornecedorID=fornecedorInstalador.getInt("fornecedor_id");
                int instaladorID= fornecedorInstalador.getInt("instalador_id");

                FornecedorInstalador auxfornecedorInstalador= new FornecedorInstalador(fornecedorID,instaladorID);
                listaFornecedoresInstaladores.add(auxfornecedorInstalador);

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return listaFornecedoresInstaladores;
    }

    //funcao de descodificar todas as funçoes
    public static ArrayList<Funcao> parserJsonFuncoes(JSONArray response){
        ArrayList<Funcao> listaFuncoes = new ArrayList<>();

        try {
            for (int i = 0; i< response.length(); i++){
                JSONObject funcoes = (JSONObject) response.get(i);
                String itemName=funcoes.getString("item_name");
                int userID= funcoes.getInt("user_id");

                Funcao auxfuncao= new Funcao(itemName,userID);
                listaFuncoes.add(auxfuncao);

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return listaFuncoes;
    }

    //funcao de descodificar todos os orcamentos
    public static ArrayList<Orcamento> parserJsonorcamentos(JSONArray response){
        ArrayList<Orcamento> listaorcamentos = new ArrayList<>();
        try {
                    for (int i = 0; i < response.length(); i++) {
                    JSONObject orcamanetos = (JSONObject) response.get(i);
                    int id = Integer.parseInt(orcamanetos.getString("id"));
                    int clienteID = Integer.parseInt(orcamanetos.getString("cliente_id"));
                    String DataOrcamento = orcamanetos.getString("data_orcamento");
                    int Margem = Integer.parseInt(orcamanetos.getString("margem"));
                    if (orcamanetos.getString("total").equals("") || orcamanetos.getString("total").equals(" ") || orcamanetos.getString("total").equals("null")) {
                        Float totalorcamento = 0.0f;
                    }
                    Float totalorcamento = Float.parseFloat(orcamanetos.getString("total"));
                    String nomeorcamento = orcamanetos.getString("nome");
                    Orcamento auxorcamento = new Orcamento(id, clienteID, DataOrcamento, Margem, totalorcamento, nomeorcamento);
                    listaorcamentos.add(auxorcamento);
                }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return listaorcamentos;
    }

    //funcao de descodificar todos os produtos
    public static ArrayList<Produto> parserJsonProdutos(JSONArray response){
        ArrayList<Produto> listaprodutos = new ArrayList<>();

        try {
            for (int i = 0; i< response.length(); i++){
                JSONObject produto = (JSONObject) response.get(i);
                int id = produto.getInt("id");
                int id_fornecedor = produto.getInt("fornecedor_id");
                String imagem= produto.getString("imagem");
                String nome = produto.getString("nome");
                String referencia = produto.getString("referencia");
                String descricao= produto.getString("descricao");
                String preco = produto.getString("preco");

                Produto auxproduto= new Produto(id,id_fornecedor,imagem,nome,referencia,descricao,Float.parseFloat(preco));
                listaprodutos.add(auxproduto);

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return listaprodutos;
    }

    //funcao que descodifica todos os produtos dos orcamaentos
    public static ArrayList<ProdutoOrcamento> parserJsonProdutosorcamentos(JSONArray response){
        ArrayList<ProdutoOrcamento> listaprodutosorcamentos = new ArrayList<>();

        try {
            for (int i = 0; i< response.length(); i++){
                JSONObject produtosorcmaentos = (JSONObject) response.get(i);
                int OrcamentoID = produtosorcmaentos.getInt("orcamento_id");
                int ProdutoID = produtosorcmaentos.getInt("produto_id");
                int quantidade= produtosorcmaentos.getInt("quantidade");


                ProdutoOrcamento auxprodutoorcamento= new ProdutoOrcamento(OrcamentoID,ProdutoID,quantidade);
                listaprodutosorcamentos.add(auxprodutoorcamento);

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return listaprodutosorcamentos;
    }

    //funcao que descodifica todos os produtos dos orcamaentos
    public static ArrayList<Utilizador> parserJsonUtilizadores(JSONArray response){
        int telemovelaux;
        ArrayList<Utilizador> listautilizadores = new ArrayList<>();

        try {
            for (int i = 0; i< response.length(); i++){
                JSONObject utilizador = (JSONObject) response.get(i);
                int idutilizador = Integer.parseInt(utilizador.getString("id"));
                String username = utilizador.getString("username");
                String nome = utilizador.getString("nome");
                String nomeEmpresa = utilizador.getString("nome_empresa");
                String telemovel = utilizador.getString("telemovel");
                if (telemovel.equals("null")){
                    telemovelaux=0;
                }else{
                    telemovelaux= Integer.parseInt(telemovel);
                }
                String email = utilizador.getString("email");
                String imagem = utilizador.getString("imagem");
                int categoria = Integer.parseInt(utilizador.getString("categoria_id"));
                int status = Integer.parseInt(utilizador.getString("status"));
                    Utilizador auxUtilizador= new Utilizador(idutilizador,username,nome,nomeEmpresa,telemovelaux,email,imagem,categoria,status);
                    listautilizadores.add(auxUtilizador);
                    
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return listautilizadores;
    }

    //funcao de descoficar outilizador
    public static Utilizador parserJsonUtilizador(String response) {
        Utilizador auxUtilizador= null;
        try {
            JSONObject utilizador= new JSONObject(response);
            int idutilizador = utilizador.getInt("id");
            String username = utilizador.getString("username");
            String nome = utilizador.getString("nome");
            String nomeEmpresa = utilizador.getString("nome_empresa");
            int telemovel = utilizador.getInt("telemovel");
            String email = utilizador.getString("email");
            String imagem = utilizador.getString("imagem");
            int categoria = utilizador.getInt("categoria_id");
            int status = utilizador.getInt("status");

            auxUtilizador = new Utilizador(idutilizador,username,nome,nomeEmpresa,telemovel,email,imagem,categoria,status);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return auxUtilizador;
    }

}