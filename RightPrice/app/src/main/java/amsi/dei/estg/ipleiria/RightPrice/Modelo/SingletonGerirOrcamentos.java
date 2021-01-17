package amsi.dei.estg.ipleiria.RightPrice.Modelo;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import amsi.dei.estg.ipleiria.RightPrice.MenuMainActivity;
import amsi.dei.estg.ipleiria.RightPrice.R;
import amsi.dei.estg.ipleiria.RightPrice.Utils.GestaoOrcamentoBDHelper;
import amsi.dei.estg.ipleiria.RightPrice.Utils.GestaoOrcamentoJsonParser;
import amsi.dei.estg.ipleiria.RightPrice.listener.AtualizaCabecalhoListener;
import amsi.dei.estg.ipleiria.RightPrice.listener.CategoriaListener;
import amsi.dei.estg.ipleiria.RightPrice.listener.ClienteListener;
import amsi.dei.estg.ipleiria.RightPrice.listener.ConhecerFornecedorListener;
import amsi.dei.estg.ipleiria.RightPrice.listener.DetalhesConhecerFornecedorListener;
import amsi.dei.estg.ipleiria.RightPrice.listener.DetalhesFornecedordoInstaladorListener;
import amsi.dei.estg.ipleiria.RightPrice.listener.DetalhesInstaladordoFornecedorListener;
import amsi.dei.estg.ipleiria.RightPrice.listener.DetalhesProdutoListener;
import amsi.dei.estg.ipleiria.RightPrice.listener.DetalhesProdutoOrcamentoListener;
import amsi.dei.estg.ipleiria.RightPrice.listener.DetalhesUtilizadorListener;
import amsi.dei.estg.ipleiria.RightPrice.listener.FornecedorInstaladorListener;
import amsi.dei.estg.ipleiria.RightPrice.listener.InstaladorFornecedorListener;
import amsi.dei.estg.ipleiria.RightPrice.listener.LoginListener;
import amsi.dei.estg.ipleiria.RightPrice.listener.MainAdminFragmentListener;
import amsi.dei.estg.ipleiria.RightPrice.listener.MainFornecedorFragmentListener;
import amsi.dei.estg.ipleiria.RightPrice.listener.MainInstaladorListener;
import amsi.dei.estg.ipleiria.RightPrice.listener.OrcamentoListener;
import amsi.dei.estg.ipleiria.RightPrice.listener.ProdutoListener;
import amsi.dei.estg.ipleiria.RightPrice.listener.ProdutosOrcamentoListener;
import amsi.dei.estg.ipleiria.RightPrice.listener.UtilizadorListener;
import amsi.dei.estg.ipleiria.RightPrice.listener.UtilizadoresListListener;
import amsi.dei.estg.ipleiria.RightPrice.listener.UtilizadoresPendentesListener;

public class SingletonGerirOrcamentos {

    //declaraçao dos listener
    private LoginListener loginListener;
    private CategoriaListener categoriaListener;
    private ClienteListener clienteListener;
    private FornecedorInstaladorListener fornecedorInstaladorListener;
    private InstaladorFornecedorListener instaladorFornecedorListener;
    private OrcamentoListener orcamentoListener;
    private ProdutoListener produtoListener;
    private ProdutosOrcamentoListener produtoOrcamentoListener;
    private UtilizadorListener utilizadorListener;
    private AtualizaCabecalhoListener atulizaCabecalhoListener;
    private UtilizadoresListListener utilizadoresListListener;
    private UtilizadoresPendentesListener utilizadoresPendentesListener;
    private MainAdminFragmentListener mainAdminFragmentListener;
    private DetalhesUtilizadorListener detalhesUtilizadorListener;
    private MainFornecedorFragmentListener mainFornecedorFragmentListener;
    private DetalhesProdutoListener detalhesProdutoListener;
    private DetalhesInstaladordoFornecedorListener detalhesinstaladordofornecedorListener;
    private MainInstaladorListener mainInstaladorListener;
    private DetalhesFornecedordoInstaladorListener detalhesFornecedordoInstaladorListener;
    private ConhecerFornecedorListener conhecerFornecedorListener;
    private DetalhesConhecerFornecedorListener detalhesConhecerFornecedorListener;
    private DetalhesProdutoOrcamentoListener detalhesProdutoOrcamentoListener;



    //dados Shared Preference do IP
    public static final String PREF_IP = "IP_SAVE";
    public static final String IP = "ID";

    //declaraçao do URL da API
    public static String mURLAPIOrcamentos= "http://192.168.1.12";
    private static final String URLCategoria="/api/categoria";
    private static final String URLClientesInstalador= "/api/cliente/clientes-instalador";
    private static final String URLCLiente = "/api/cliente";
    private static final String URLFornecedorMeusInstaladore= "/api/fornecedor-instalador/fornecedor-meus-instaladores";
    private static final String URLInstaladorMeusFornecedores= "/api/fornecedor-instalador/instalador-meus-fornecedores";
    private static final String URLPUTFornecedorInstalador="/api/fornecedor-instalador/put-fornecedor-instalador";
    private static final String URLFornecedorInstaladorADD="/api/fornecedor-instalador";
    private static final String URLOrcamentos ="/api/orcamento/orcamentos";
    private static final String URLCRUDOrcamentos="/api/orcamento";
    private static final String URLprodutosFornecedor = "/api/produto/produtos-fornecedor";
    private static final String URLProduto ="/api/produto";
    private static final String URLprodutosOrcamento= "/api/produto-orcamento/produtos-orcamentos";
    private static final String URLalterrodutosOrcamento= "/api/produto-orcamento/put-produtosorcamento";
    private static final String URLCRULprodutosOrcamentos= "/api/produto-orcamento";
    private static final String URLUtilizdorLogin= "/api/utilizador/login";
    private static final String URLUtilizadorRegistar= "/api/utilizador/registar";
    private static final String URLUtilizadorToken = "/api/utilizador-token";
    private static final String URLUtilizadorTokenUser= "/api/utilizador-token/user";
    private static final String URLUtilizadorTokenInstaladores= "/api/utilizador-token/user-instalador";
    private static final String URLUtilizadorTokenFornecedores= "/api/utilizador-token/user-fornecedor";
    private static final String URLFuncao ="/api/auth-assigment/auth";

    //declaraçao da string de imagem
    public String NOIMAGEUser = "/uploads/default.png";


    //declaração do valor para definir o tipo de mensagem
    private int valor_send_message=0;


    //declaraçao dos array para o sistema
    private ArrayList<Categoria> categoriaArray;
    private ArrayList<Cliente> clienteArray;
    private ArrayList<FornecedorInstalador> fornecedorInstaladorArray;
    private ArrayList<Funcao> funcaoArray;
    private ArrayList<Orcamento> orcamentoArray;
    private ArrayList<Produto> produtoArray;
    private ArrayList<ProdutoOrcamento> produtoOrcamentoArray;
    private ArrayList<Utilizador> utilizadorArray;


    //Declaraçao dos ficheiros de base de dados do android
    private GestaoOrcamentoBDHelper gestaoOrcamentoBDHelper = null;

    //declaraçao do volley
    private static RequestQueue volleyQueue = null;

    //declaraçao da SharedPreference
    private SharedPreferences sharedPreferences;

    //declaraçao da instacia
    private static SingletonGerirOrcamentos instance = null;

    //variaveis do programa
    private String messageErroLogin= null;
    private String messageErroRegistar=null;


    public static synchronized SingletonGerirOrcamentos getInstance(Context context) {
        if (instance == null) {
            instance = new SingletonGerirOrcamentos(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return instance;
    }


    private SingletonGerirOrcamentos(Context context) {
       //inicialização dos array
       categoriaArray = new ArrayList<Categoria>();
       clienteArray = new ArrayList<Cliente>();
       fornecedorInstaladorArray= new ArrayList<FornecedorInstalador>();
       funcaoArray = new ArrayList<Funcao>();
       orcamentoArray = new ArrayList<Orcamento>();
       produtoArray = new ArrayList<Produto>();
       produtoOrcamentoArray = new ArrayList<ProdutoOrcamento>();
       utilizadorArray = new ArrayList<Utilizador>();
       gestaoOrcamentoBDHelper = new GestaoOrcamentoBDHelper(context);
      }


      //valor para dizer qual a snacbar a apresentar
    public int getValor_send_message() {
        return valor_send_message;
    }

    public void setValor_send_message(int valor_send_message) {
        this.valor_send_message = valor_send_message;
    }


    //Quando erro de login
    public void onErroLoginlistener(String erro){
        loginListener.onErroRegistar(erro);
    }



    //atribuiçao de todos os listener
    public void setLoginListener(LoginListener loginListener){
       this.loginListener = loginListener;
    }

    public void setCategoriaListener(CategoriaListener categoriaListener){
        this.categoriaListener = categoriaListener;
    }

    public void setClienteListener(ClienteListener clienteListener){
        this.clienteListener= clienteListener;
    }

    public void setFornecedorInstaladorListener (FornecedorInstaladorListener fornecedorInstaladorListener){
        this.fornecedorInstaladorListener = fornecedorInstaladorListener;
    }

    public void setInstaladorFornecedorListener (InstaladorFornecedorListener instaladorFornecedorListener){
        this.instaladorFornecedorListener=instaladorFornecedorListener;
    }

    public void setOrcamentoListener (OrcamentoListener orcamentoListener){
        this.orcamentoListener = orcamentoListener;
    }

    public void setProdutoListener (ProdutoListener produtoListener){
        this.produtoListener= produtoListener;
    }

    public void setProdutoOrcamentoListener (ProdutosOrcamentoListener produtoOrcamentoListener){
        this.produtoOrcamentoListener = produtoOrcamentoListener;
    }

    public void setAtualizaCabecalhoListener(AtualizaCabecalhoListener atulizaCabecalhoListener){
        this.atulizaCabecalhoListener = atulizaCabecalhoListener;
    }

    public void setUtilizadorListener (UtilizadorListener utilizadorListener){
        this.utilizadorListener= utilizadorListener;
    }

    public void setutilizadoresListListener(UtilizadoresListListener utilizadoresListListener){
        this.utilizadoresListListener=utilizadoresListListener;
    }

    public void setutilizadoresPendentesListener(UtilizadoresPendentesListener utilizadoresPendentesListener){
        this.utilizadoresPendentesListener=utilizadoresPendentesListener;
    }

    public void setmainAdminFragmentListener(MainAdminFragmentListener mainAdminFragmentListener){
        this.mainAdminFragmentListener=mainAdminFragmentListener;
    }

    public void setDetalhesUtilizadorListener(DetalhesUtilizadorListener detalhesUtilizadorListener){
        this.detalhesUtilizadorListener=detalhesUtilizadorListener;
    }

    public void setMainFornecedorFragmentListener (MainFornecedorFragmentListener mainFornecedorFragmentListener){
        this.mainFornecedorFragmentListener=mainFornecedorFragmentListener;
    }

    public void setDesalhesProdutoListener(DetalhesProdutoListener detalhesProdutoListener){
        this.detalhesProdutoListener=detalhesProdutoListener;
    }

    public void setDetalhes_instalador_do_fornecedorListener(DetalhesInstaladordoFornecedorListener detalhes_instalador_do_fornecedorListener){
        this.detalhesinstaladordofornecedorListener=detalhes_instalador_do_fornecedorListener;
    }

    public void setMainInstaladorListener (MainInstaladorListener mainInstaladorListener){
        this.mainInstaladorListener=mainInstaladorListener;
    }

    public void setDetalhesFornecedordoInstaladorListener(DetalhesFornecedordoInstaladorListener detalhesFornecedordoInstaladorListener){
        this.detalhesFornecedordoInstaladorListener=detalhesFornecedordoInstaladorListener;
    }

    public void setConhecerFornecedorListener (ConhecerFornecedorListener conhecerFornecedorListener){
        this.conhecerFornecedorListener=conhecerFornecedorListener;
    }

    public void setDetalhesConhecerFornecedorListener (DetalhesConhecerFornecedorListener detalhesConhecerFornecedorListener){
        this.detalhesConhecerFornecedorListener=detalhesConhecerFornecedorListener;
    }

    public void setDetalhesProdutoOrcamentoListener(DetalhesProdutoOrcamentoListener detalhesProdutoOrcamentoListener){
        this.detalhesProdutoOrcamentoListener=detalhesProdutoOrcamentoListener;
    }



    //funçoes de buscar os dados todos na BD
    public ArrayList<Categoria>getCategoriasDB(){
        categoriaArray= gestaoOrcamentoBDHelper.getAllCategoriasBD();
        System.out.println("-->"+categoriaArray );
        return new ArrayList<>(categoriaArray);
    }

    public ArrayList<Cliente>getClienteDB(){
        clienteArray = gestaoOrcamentoBDHelper.getAllClientesBD();
        return new ArrayList<>(clienteArray);
    }

    public ArrayList<FornecedorInstalador> getFornecedorInstaladorDB(){
        fornecedorInstaladorArray= gestaoOrcamentoBDHelper.getAllFornecedorInstaladorBD();
        return new ArrayList<>(fornecedorInstaladorArray);
    }

    public ArrayList<Orcamento>getOrcamentoDB(){
        orcamentoArray= gestaoOrcamentoBDHelper.getAllOrcamentossBD();
        return new ArrayList<>(orcamentoArray);
    }

    public ArrayList<Produto> getProdutoDB(){
        produtoArray= gestaoOrcamentoBDHelper.getAllProdutosBD();
        return new ArrayList<>(produtoArray);
    }

    public ArrayList<ProdutoOrcamento> getProdutoOrcamentoDB(){
        produtoOrcamentoArray= gestaoOrcamentoBDHelper.getAllProdutoOrcamentosBD();
        return new ArrayList<>(produtoOrcamentoArray);
    }

    public ArrayList<Utilizador>getUtilizadorDB(){
        utilizadorArray= gestaoOrcamentoBDHelper.getAllUsersDB();
        return new ArrayList<>(utilizadorArray);
    }



    //só ir buscar um campo em cada array
    public Categoria getCategoria(int id_categoria){
        for (Categoria categoria: categoriaArray) {
            if(categoria.getId()==id_categoria)
            {
                return categoria;
            }
        }
        return null;
    }

    public Cliente getCliente(int id_cliente){
        for (Cliente cliente: clienteArray) {
            if (cliente.getId()==id_cliente){
                return cliente;
            }
        }
        return null;
    }

    public Orcamento getOrcamento(int id_orcamento){
        for (Orcamento orcamento: orcamentoArray) {
            if (orcamento.getId()==id_orcamento){
                return orcamento;
            }
        }
        return null;
    }

    public Produto getProduto(int id_produto){
        for (Produto produto: produtoArray) {
            if (produto.getId()==id_produto){
                return produto;
            }
        }
        return null;
    }

    public Utilizador getUtilizador (int id_utilizador){
        for (Utilizador utilizador: utilizadorArray) {
            if(utilizador.getId()==id_utilizador){
                return utilizador;
            }
        }
        return null;
    }


    //funçoes que adicionam na base de dados interna
    //adicionar uma valor
    public void adicionarCategoriaDB(Categoria categoria){
        gestaoOrcamentoBDHelper.adicionarCategoriaDB(categoria);
    }

    public void adicionarClienteDB(Cliente cliente){
        gestaoOrcamentoBDHelper.adicionarClienteDB(cliente);
    }

    public void adicionarFornecedorInstaladorDB(FornecedorInstalador fornecedorInstalador){
        gestaoOrcamentoBDHelper.adicionarFornecedorInstaladoroDB(fornecedorInstalador);
    }

    public void adicionarFuncaoDB(Funcao funcao){
        gestaoOrcamentoBDHelper.adicionarFuncaoDB(funcao);
    }

    public void adicionarOrcamentoDB (Orcamento orcamento){
        gestaoOrcamentoBDHelper.adicionarOrcamentoDB(orcamento);
    }

    public void adicionarProdutoDB (Produto produto){
        gestaoOrcamentoBDHelper.adicionarProdutoDB(produto);
    }

    public void adicionarProdutoOrcamentoDB (ProdutoOrcamento produtoOrcamento){
        gestaoOrcamentoBDHelper.adicionarProdutoOrcamentoDB(produtoOrcamento);
    }

    public void adicionarUtilizadorDB(Utilizador utilizador){
        gestaoOrcamentoBDHelper.adicionarUserDB(utilizador);
    }



    //adiconar uma lista de valores na DataBase
    public void adicionarCategoriasDB(ArrayList<Categoria> categorias){
        gestaoOrcamentoBDHelper.removerAllCategoriasDB();
        for (Categoria categoria: categorias) {
            adicionarCategoriaDB(categoria);
        }
    }

    public void adicionarClientesDB(ArrayList<Cliente> clientes){
        gestaoOrcamentoBDHelper.removerAllClientesDB();
        for (Cliente cliente: clientes) {
            adicionarClienteDB(cliente);
        }
    }

    public void adicionarFornecedoresInstaladoresDB(ArrayList<FornecedorInstalador> fornecedoresInstaladores){
        gestaoOrcamentoBDHelper.removerAllFornecedorInstaladorsDB();
        for (FornecedorInstalador forenecedorinstalador: fornecedoresInstaladores) {
            adicionarFornecedorInstaladorDB(forenecedorinstalador);
        }
    }

    public void adicionarFuncoesDB(ArrayList<Funcao> funcoes){
        gestaoOrcamentoBDHelper.removerAllFuncoesDB();
        for (Funcao funcao: funcoes) {
            adicionarFuncaoDB(funcao);
        }
    }

    public void adicionarOrcamentosDB(ArrayList<Orcamento> orcamentos){
        for (Orcamento orcamento: orcamentos) {
            adicionarOrcamentoDB(orcamento);
        }
    }

    public void adiconarProdutosDB (ArrayList<Produto> produtos){
        for (Produto produto: produtos) {
            adicionarProdutoDB(produto);
        }
    }

    public void adicionarProdutosOrcamentosDB (ArrayList<ProdutoOrcamento> produtosOrcamentos){
        for (ProdutoOrcamento produtoOrcamento: produtosOrcamentos) {
            adicionarProdutoOrcamentoDB(produtoOrcamento);
        }
    }

    public void adicionarUtilizadoresDB(ArrayList<Utilizador> utilizadores){
        gestaoOrcamentoBDHelper.removerAllUtilizadoresDB();
        for (Utilizador utilizador : utilizadores){
            adicionarUtilizadorDB(utilizador);
        }
    }




    // funçao que vao comunicar com a api para eviar valores e buscar valoes na base de dados do servidor

    //funçao de login do sistema. Comunica com a api
    public void loginAPI (String username,  String password, int TipoDeVerificacao, Context context){

        if (!isConnectionInternet(context)){
            messageErroLogin = context.getString(R.string.sem_ligacao_internet);
            loginListener.onErroLogin(messageErroLogin);
            return;
        }

        System.out.println("--> "+mURLAPIOrcamentos + URLUtilizdorLogin + "?username=" + username + "&password=" + password);
        StringRequest request = new StringRequest(Request.Method.GET, mURLAPIOrcamentos + URLUtilizdorLogin + "?username=" + username + "&password=" + password, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int valor = parserJsonLOGIN(response,password,context);
                if (valor==1){
                    sharedPreferences= context.getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
                    String token=sharedPreferences.getString(MenuMainActivity.TOKEN,null);
                    loginListener.onValidateLogin(token,username,TipoDeVerificacao);

                }else if (valor==0){
                    System.out.println("-->"+ messageErroLogin);
                    loginListener.onErroLogin(messageErroLogin);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("--> "+error);
                loginListener.onErroLogin(context.getString(R.string.erro_login_servidor));
            }
        });
        volleyQueue.add(request);
    }

    //funcao de registar o utilizador na API
    public void registarAPI(String nome, String email, String password, String funcao, String  categoria, String username, Context context){
        if(!isConnectionInternet(context)){
            messageErroRegistar=context.getString(R.string.sem_ligacao_internet);
            loginListener.onErroRegistar(messageErroRegistar);
            return;
        }
        System.out.println("-->"+mURLAPIOrcamentos+URLUtilizadorRegistar);
        StringRequest request = new StringRequest(Request.Method.POST, mURLAPIOrcamentos + URLUtilizadorRegistar, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int valor= parserJsonRegistar(response,context);
                if(valor==1){
                    loginListener.onValidateRegistar(messageErroRegistar);
                }else if(valor==0){
                    loginListener.onErroRegistar(messageErroRegistar);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("-->"+ error);
                loginListener.onErroRegistar(context.getString(R.string.erro_registar));
            }
        }){
            @Override
            protected Map<String,String>getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("nome",nome);
                params.put("email",email);
                params.put("password",password);
                params.put("funcao",funcao.toLowerCase());
                params.put("categoria",categoria);
                params.put("username",username);
                return params;
            }
        };
        volleyQueue.add(request);
    }



    //geral
    //funçoes que vao buscar os dados todos na api
   public void getAllCategoriasConlistenerAPI(final Context context){
        if(!isConnectionInternet(context)){
          if(categoriaListener!=null){

              categoriaListener.onRefreshListaCategorias(gestaoOrcamentoBDHelper.getAllCategoriasBD());
          }
        }else{
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, mURLAPIOrcamentos + URLCategoria, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    categoriaArray = GestaoOrcamentoJsonParser.parserJsonCategorias(response);
                    adicionarCategoriasDB(categoriaArray);
                    if (categoriaListener != null) {
                        categoriaListener.onRefreshListaCategorias(categoriaArray);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context,context.getString(R.string.erro_carrregar_categorias), Toast.LENGTH_LONG).show();
                    categoriaListener.onRefreshListaCategorias(gestaoOrcamentoBDHelper.getAllCategoriasBD());
                }
            });
            volleyQueue.add(request);
        }
   }

   //vai buscar as categorias a API
    public void getAllCategoriasAPI(final Context context){
        if(!isConnectionInternet(context)){
             categoriaArray=gestaoOrcamentoBDHelper.getAllCategoriasBD();
             if(mainAdminFragmentListener!=null){
                 mainAdminFragmentListener.onRefreshdadocategoriaAdminMain(categoriaArray.size());
             }

        }else {
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, mURLAPIOrcamentos + URLCategoria, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    categoriaArray = GestaoOrcamentoJsonParser.parserJsonCategorias(response);
                    adicionarCategoriasDB(categoriaArray);
                    if(mainAdminFragmentListener!=null){
                        mainAdminFragmentListener.onRefreshdadocategoriaAdminMain(categoriaArray.size());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(mainAdminFragmentListener!=null){
                        mainAdminFragmentListener.onRefreshdadocategoriaAdminMain(categoriaArray.size());
                    }
                }
            });
            volleyQueue.add(request);
        }
    }



    //admin
   //funcao que vai buscar as funçoes
   public void getAllFuncoesAdminAPI(final Context context){
       if(!isConnectionInternet(context)){
           funcaoArray=gestaoOrcamentoBDHelper.getAllFuncoesBD();
           if(detalhesUtilizadorListener!=null){
               detalhesUtilizadorListener.onRefreshListaFuncao(funcaoArray);
           }

       }else {

           sharedPreferences= context.getSharedPreferences(MenuMainActivity.PREF_USER,Context.MODE_PRIVATE);
           final String token = sharedPreferences.getString(MenuMainActivity.TOKEN,null);


           JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, mURLAPIOrcamentos + URLFuncao+ "?auth_key=" + token, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   funcaoArray = GestaoOrcamentoJsonParser.parserJsonFuncoes(response);
                   adicionarFuncoesDB(funcaoArray);
                   if(detalhesUtilizadorListener!=null){
                       detalhesUtilizadorListener.onRefreshListaFuncao(funcaoArray);
                   }
               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   if(detalhesUtilizadorListener!=null){
                       detalhesUtilizadorListener.onErroListaFuncao(context.getString(R.string.erro_carregar_função));
                       detalhesUtilizadorListener.onRefreshListaFuncao(funcaoArray);
                   }
               }
           });
           volleyQueue.add(request);
       }
   }

    //vai buscar os utilizadores todos da api
    public void getAllUtilizadoresAdminAPI(final Context context){
        if(!isConnectionInternet(context)) {
            utilizadorArray=gestaoOrcamentoBDHelper.getAllUsersDB();
            for (Utilizador utilizador: utilizadorArray) {
                if(utilizador.getNome_empresa().equals("null")||utilizador.getNome_empresa().equals("")||utilizador.getNome_empresa().equals(" ")){
                    utilizador.setNome_empresa("Sem Empresa");
                }

            }
            if (utilizadoresListListener != null) {
                utilizadoresListListener.onRefreshListaUtilizadores(utilizadorArray);
            }
            if(utilizadoresPendentesListener!=null){
                utilizadoresPendentesListener.onRefreshListaUtilizadorespendentes(utilizadorArray);
            }
            if(mainAdminFragmentListener!=null){
                int aux_count=0;
                for (Utilizador utilizador: utilizadorArray) {
                    if(utilizador.getStatus()==10||utilizador.getStatus()==0){
                        aux_count++;
                    }
                }
                mainAdminFragmentListener.onRefreshdadoutilizadorAdminMain(aux_count);
                int aux_count1=0;
                for (Utilizador utilizador: utilizadorArray) {
                    if(utilizador.getStatus()==9){
                        aux_count1++;
                    }
                }
                mainAdminFragmentListener.onRefreshdadopendentesAdminMain(aux_count1);
            }
        }else{
            sharedPreferences= context.getSharedPreferences(MenuMainActivity.PREF_USER,Context.MODE_PRIVATE);
            final String token = sharedPreferences.getString(MenuMainActivity.TOKEN,null);

            System.out.println("-->" +mURLAPIOrcamentos+URLUtilizadorTokenUser+"?auth_key="+token);
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, mURLAPIOrcamentos + URLUtilizadorTokenUser + "?auth_key=" + token, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    utilizadorArray=GestaoOrcamentoJsonParser.parserJsonUtilizadores(response);
                    adicionarUtilizadoresDB(utilizadorArray);
                    for (Utilizador utilizador: utilizadorArray) {
                        if(utilizador.getNome_empresa().equals("null")||utilizador.getNome_empresa().equals("")||utilizador.getNome_empresa().equals(" ")){
                            utilizador.setNome_empresa("Sem Empresa");
                        }

                    }
                    if (utilizadoresListListener != null) {
                        utilizadoresListListener.onRefreshListaUtilizadores(utilizadorArray);
                    }
                    if(utilizadoresPendentesListener!=null){
                        utilizadoresPendentesListener.onRefreshListaUtilizadorespendentes(utilizadorArray);
                    }
                    if(mainAdminFragmentListener!=null){
                        int aux_count=0;
                        for (Utilizador utilizador: utilizadorArray) {
                            if(utilizador.getStatus()==10||utilizador.getStatus()==0){
                                aux_count++;
                            }
                        }
                        mainAdminFragmentListener.onRefreshdadoutilizadorAdminMain(aux_count);
                        int aux_count1=0;
                        for (Utilizador utilizador: utilizadorArray) {
                            if(utilizador.getStatus()==9){
                                aux_count1++;
                            }
                        }
                        mainAdminFragmentListener.onRefreshdadopendentesAdminMain(aux_count1);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    utilizadorArray = gestaoOrcamentoBDHelper.getAllUsersDB();
                    for (Utilizador utilizador : utilizadorArray) {
                        if (utilizador.getNome_empresa().equals("null") || utilizador.getNome_empresa().equals("") || utilizador.getNome_empresa().equals(" ")) {
                            utilizador.setNome_empresa("Sem Empresa");
                        }

                    }
                    if (utilizadoresListListener != null) {
                        utilizadoresListListener.onErroListUtilizadores("Erro a carregar os utilizadores da base de dados");
                        utilizadoresListListener.onRefreshListaUtilizadores(utilizadorArray);
                    }
                    if (utilizadoresPendentesListener != null) {
                        utilizadoresPendentesListener.onErroListUtilizadores("Erro a carregar os utilizadores da base de dados");
                        utilizadoresPendentesListener.onRefreshListaUtilizadorespendentes(utilizadorArray);
                    }
                    if (mainAdminFragmentListener != null) {
                        int aux_count = 0;
                        for (Utilizador utilizador : utilizadorArray) {
                            if (utilizador.getStatus() == 10 || utilizador.getStatus() == 0) {
                                aux_count++;
                            }
                        }
                        mainAdminFragmentListener.onRefreshdadoutilizadorAdminMain(aux_count);
                        int aux_count1 = 0;
                        for (Utilizador utilizador : utilizadorArray) {
                            if (utilizador.getStatus() == 9) {
                                aux_count1++;
                            }
                        }
                        mainAdminFragmentListener.onRefreshdadopendentesAdminMain(aux_count1);
                    }
                }
            });
            volleyQueue.add(request);
        }
    }

    public void getAllProdutosAPI(final Context context){
        if(!isConnectionInternet(context)){
            produtoArray=gestaoOrcamentoBDHelper.getAllProdutosBD();
            if(mainFornecedorFragmentListener!=null){
                mainFornecedorFragmentListener.onRefreshdadoProdutoFornecedorMain(produtoArray.size());
            }
            if(produtoListener!=null){
                produtoListener.onRefreshListaProdutos(produtoArray);
            }

        }else {

            sharedPreferences= context.getSharedPreferences(MenuMainActivity.PREF_USER,Context.MODE_PRIVATE);
            final String token = sharedPreferences.getString(MenuMainActivity.TOKEN,null);
            final int id=Integer.parseInt(sharedPreferences.getString(MenuMainActivity.ID,null));


            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, mURLAPIOrcamentos + URLprodutosFornecedor+ "?id_fornecedor="+id+"&auth_key=" + token, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    produtoArray = GestaoOrcamentoJsonParser.parserJsonProdutos(response);
                    gestaoOrcamentoBDHelper.removerAllProdutosDB();
                   adiconarProdutosDB(produtoArray);
                    if(produtoListener!=null){
                        produtoListener.onRefreshListaProdutos(produtoArray);
                    }
                    if (mainFornecedorFragmentListener!=null){
                        mainFornecedorFragmentListener.onRefreshdadoProdutoFornecedorMain(produtoArray.size());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    produtoArray=gestaoOrcamentoBDHelper.getAllProdutosBD();
                    if(produtoListener!=null) {
                        produtoListener.onErroListaProfuto(context.getString(R.string.erro_carregar_produtos));
                        produtoListener.onRefreshListaProdutos(produtoArray);
                    }
                    if(mainFornecedorFragmentListener!=null){
                        mainFornecedorFragmentListener.onRefreshdadoProdutoFornecedorMain(produtoArray.size());
                    }
                }
            });
            volleyQueue.add(request);
        }
    }

    //se o valor for 1 vai buscar os fornecedores, se for 2 vai buscar os instaladores
    public void getAllFornecedorInstaladorAPI(final Context context, int valor_tipo_buscar_api){
        if(!isConnectionInternet(context)) {
            utilizadorArray=gestaoOrcamentoBDHelper.getAllUsersDB();
            for (Utilizador utilizador: utilizadorArray) {
                if(utilizador.getNome_empresa().equals("null")||utilizador.getNome_empresa().equals("")||utilizador.getNome_empresa().equals(" ")){
                    utilizador.setNome_empresa("Sem Empresa");
                }

            }
            if (fornecedorInstaladorListener != null) {
                fornecedorInstaladorListener.onrefreshInstaladores(utilizadorArray);
            }
            if(instaladorFornecedorListener!=null) {
                instaladorFornecedorListener.onrefreshFornecedores(utilizadorArray);
            }
            if(conhecerFornecedorListener!=null){
                conhecerFornecedorListener.onrefreshFornecedores(utilizadorArray);
            }
        }else{
            sharedPreferences= context.getSharedPreferences(MenuMainActivity.PREF_USER,Context.MODE_PRIVATE);
            final String token = sharedPreferences.getString(MenuMainActivity.TOKEN,null);
            String url=null;
            if (valor_tipo_buscar_api==1){
                url=mURLAPIOrcamentos + URLUtilizadorTokenFornecedores + "?auth_key=" + token;
            }else if(valor_tipo_buscar_api==2){
                url=mURLAPIOrcamentos + URLUtilizadorTokenInstaladores + "?auth_key=" + token;
            }
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    utilizadorArray=GestaoOrcamentoJsonParser.parserJsonUtilizadores(response);
                    adicionarUtilizadoresDB(utilizadorArray);
                    for (Utilizador utilizador: utilizadorArray) {
                        if(utilizador.getNome_empresa().equals("null")||utilizador.getNome_empresa().equals("")||utilizador.getNome_empresa().equals(" ")){
                            utilizador.setNome_empresa("Sem Empresa");
                        }

                    }
                    if (fornecedorInstaladorListener != null) {
                        fornecedorInstaladorListener.onrefreshInstaladores(utilizadorArray);
                    }
                    if(instaladorFornecedorListener!=null) {
                        instaladorFornecedorListener.onrefreshFornecedores(utilizadorArray);
                    }
                    if(conhecerFornecedorListener!=null){
                        conhecerFornecedorListener.onrefreshFornecedores(utilizadorArray);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    utilizadorArray=gestaoOrcamentoBDHelper.getAllUsersDB();
                    for (Utilizador utilizador: utilizadorArray) {
                        if(utilizador.getNome_empresa().equals("null")||utilizador.getNome_empresa().equals("")||utilizador.getNome_empresa().equals(" ")){
                            utilizador.setNome_empresa("Sem Empresa");
                        }

                    }
                    if (fornecedorInstaladorListener != null) {
                        fornecedorInstaladorListener.onErroFornecedorInstalador(context.getString(R.string.erro_carregar_seus_instaladores));
                        fornecedorInstaladorListener.onrefreshInstaladores(utilizadorArray);
                    }
                    if(instaladorFornecedorListener!=null) {
                        instaladorFornecedorListener.onErroFornecedorInstalador(context.getString(R.string.erro_carregar_seus_fornecedores));
                        instaladorFornecedorListener.onrefreshFornecedores(utilizadorArray);
                    }
                    if(conhecerFornecedorListener!=null){
                        conhecerFornecedorListener.onErroFornecedorInstalador(context.getString(R.string.erro_carregar_novos_fornecedores));
                        conhecerFornecedorListener.onrefreshFornecedores(utilizadorArray);
                    }
                }
            });
            volleyQueue.add(request);
        }
    }

    public void getAllRelacionFornecedorInstaladorAPI(final Context context){
           if (!isConnectionInternet(context)) {
               fornecedorInstaladorArray = gestaoOrcamentoBDHelper.getAllFornecedorInstaladorBD();
               if (mainFornecedorFragmentListener != null) {
                   mainFornecedorFragmentListener.onRefreshdadoQunantidadesInstaladoresFOrnecedoresMain(fornecedorInstaladorArray.size());
               }
               if (fornecedorInstaladorListener != null) {
                   fornecedorInstaladorListener.onRefreshFornecedoreInstalador(fornecedorInstaladorArray);
               }
           } else {

               sharedPreferences = context.getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
               final String token = sharedPreferences.getString(MenuMainActivity.TOKEN, null);
               final int id = Integer.parseInt(sharedPreferences.getString(MenuMainActivity.ID, null));


               System.out.println("-->" + mURLAPIOrcamentos + URLFornecedorMeusInstaladore + "?id_fornecedor=" + id + "&auth_key=" + token);
               JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, mURLAPIOrcamentos + URLFornecedorMeusInstaladore + "?id_fornecedor=" + id + "&auth_key=" + token, null, new Response.Listener<JSONArray>() {
                   @Override
                   public void onResponse(JSONArray response) {
                       fornecedorInstaladorArray = GestaoOrcamentoJsonParser.parserJsonFornecedoresInstaladores(response);
                       adicionarFornecedoresInstaladoresDB(fornecedorInstaladorArray);
                       System.out.println("-->" + fornecedorInstaladorArray.size());
                       if (mainFornecedorFragmentListener != null) {
                           mainFornecedorFragmentListener.onRefreshdadoQunantidadesInstaladoresFOrnecedoresMain(fornecedorInstaladorArray.size());
                       }
                       if (fornecedorInstaladorListener != null) {
                           fornecedorInstaladorListener.onRefreshFornecedoreInstalador(fornecedorInstaladorArray);
                       }
                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       fornecedorInstaladorArray = gestaoOrcamentoBDHelper.getAllFornecedorInstaladorBD();
                       if (fornecedorInstaladorListener != null) {
                           fornecedorInstaladorListener.onErroFornecedorInstalador(context.getString(R.string.erro_carregar_seus_instaladores));
                           fornecedorInstaladorListener.onRefreshFornecedoreInstalador(fornecedorInstaladorArray);
                       }
                       if (mainFornecedorFragmentListener != null) {
                           mainFornecedorFragmentListener.onRefreshdadoQunantidadesInstaladoresFOrnecedoresMain(fornecedorInstaladorArray.size());
                       }
                   }
               });
               volleyQueue.add(request);
           }
    }

    public void getAllRelacionInstaladorFornececorAPI(final Context context){
        if (!isConnectionInternet(context)) {
            fornecedorInstaladorArray = gestaoOrcamentoBDHelper.getAllFornecedorInstaladorBD();
            if (mainInstaladorListener != null) {
                mainInstaladorListener.onRefreshQuantidadeFornecedoresMain(fornecedorInstaladorArray.size());
            }
            if(detalhesProdutoOrcamentoListener!=null){
                detalhesProdutoOrcamentoListener.onSucssecProduto();
            }
            if (instaladorFornecedorListener != null) {
                instaladorFornecedorListener.onRefreshFornecedoreInstalador(fornecedorInstaladorArray);
            }
            if(conhecerFornecedorListener!=null){
                conhecerFornecedorListener.onRefreshFornecedoreInstalador(fornecedorInstaladorArray);
            }
        } else {

            sharedPreferences = context.getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
            final String token = sharedPreferences.getString(MenuMainActivity.TOKEN, null);
            final int id = Integer.parseInt(sharedPreferences.getString(MenuMainActivity.ID, null));


            System.out.println("-->" + mURLAPIOrcamentos + URLInstaladorMeusFornecedores + "?id_instalador=" + id + "&auth_key=" + token);
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, mURLAPIOrcamentos + URLInstaladorMeusFornecedores + "?id_instalador=" + id + "&auth_key=" + token, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    fornecedorInstaladorArray = GestaoOrcamentoJsonParser.parserJsonFornecedoresInstaladores(response);
                    adicionarFornecedoresInstaladoresDB(fornecedorInstaladorArray);
                    if (mainInstaladorListener != null) {
                        mainInstaladorListener.onRefreshQuantidadeFornecedoresMain(fornecedorInstaladorArray.size());
                    }
                    if(detalhesProdutoOrcamentoListener!=null){
                        detalhesProdutoOrcamentoListener.onSucssecProduto();
                    }
                    if (instaladorFornecedorListener != null) {
                        instaladorFornecedorListener.onRefreshFornecedoreInstalador(fornecedorInstaladorArray);
                    }
                    if(conhecerFornecedorListener!=null){
                        conhecerFornecedorListener.onRefreshFornecedoreInstalador(fornecedorInstaladorArray);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    fornecedorInstaladorArray = gestaoOrcamentoBDHelper.getAllFornecedorInstaladorBD();
                    if (mainInstaladorListener != null) {
                        mainInstaladorListener.onRefreshQuantidadeFornecedoresMain(fornecedorInstaladorArray.size());
                    }
                    if(detalhesProdutoOrcamentoListener!=null){
                        detalhesProdutoOrcamentoListener.onSucssecProduto();
                    }
                    if (instaladorFornecedorListener != null) {
                        instaladorFornecedorListener.onErroFornecedorInstalador(context.getString(R.string.erro_carregar_seus_fornecedores));
                        instaladorFornecedorListener.onRefreshFornecedoreInstalador(fornecedorInstaladorArray);
                    }
                    if(conhecerFornecedorListener!=null){
                        conhecerFornecedorListener.onErroFornecedorInstalador(context.getString(R.string.erro_carregar_novos_fornecedores));
                        conhecerFornecedorListener.onRefreshFornecedoreInstalador(fornecedorInstaladorArray);
                    }
                }
            });
            volleyQueue.add(request);
        }
    }

    public void getAllClientesAPI(final Context context){
        if(!isConnectionInternet(context)){
            clienteArray=gestaoOrcamentoBDHelper.getAllClientesBD();
            if(mainInstaladorListener!=null){
                mainInstaladorListener.onRefreshQuantidadeCLientesMain(clienteArray.size());
                mainInstaladorListener.onRefreshClientesAPI();
            }
            if(clienteListener!=null){
                clienteListener.onRefreshListaClientes(clienteArray);
            }

        }else {

            sharedPreferences= context.getSharedPreferences(MenuMainActivity.PREF_USER,Context.MODE_PRIVATE);
            final String token = sharedPreferences.getString(MenuMainActivity.TOKEN,null);
            final int id=Integer.parseInt(sharedPreferences.getString(MenuMainActivity.ID,null));

            System.out.println("-->"+mURLAPIOrcamentos + URLClientesInstalador+ "?id_instalador="+id+"&auth_key=" + token );
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, mURLAPIOrcamentos + URLClientesInstalador+ "?id_instalador="+id+"&auth_key=" + token, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    clienteArray = GestaoOrcamentoJsonParser.parserJsonClientes(response);
                    adicionarClientesDB(clienteArray);
                    if(clienteListener!=null){
                        clienteListener.onRefreshListaClientes(clienteArray);
                    }
                    if (mainInstaladorListener!=null){
                        mainInstaladorListener.onRefreshQuantidadeCLientesMain(clienteArray.size());
                        mainInstaladorListener.onRefreshClientesAPI();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    clienteArray=gestaoOrcamentoBDHelper.getAllClientesBD();
                    if(clienteListener!=null) {
                        clienteListener.onErroListaCliente(context.getString(R.string.erro_carregar_clientes));
                        clienteListener.onRefreshListaClientes(clienteArray);
                    }
                }
            });
            volleyQueue.add(request);
        }
    }

    public void getAllOrcamentosAPI(final Context context){
        if(!isConnectionInternet(context)){
            orcamentoArray=gestaoOrcamentoBDHelper.getAllOrcamentossBD();
            if(mainInstaladorListener!=null){
                mainInstaladorListener.onRefreshQuantidadeOrcamentosMain(orcamentoArray.size());
                mainInstaladorListener.onRefreshProdutosOrcamentoAPI();
            }
            if(orcamentoListener!=null){
                orcamentoListener.onRefreshListaOrcamentos(orcamentoArray);
            }

        }else {

            sharedPreferences= context.getSharedPreferences(MenuMainActivity.PREF_USER,Context.MODE_PRIVATE);
            final String token = sharedPreferences.getString(MenuMainActivity.TOKEN,null);

            gestaoOrcamentoBDHelper.removerAllOrcamentosDB();

            for (Cliente cliente: clienteArray) {
                System.out.println("-->"+mURLAPIOrcamentos + URLOrcamentos+ "?cliente_id="+cliente.getId()+"&auth_key=" + token );
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, mURLAPIOrcamentos + URLOrcamentos+ "?cliente_id="+cliente.getId()+"&auth_key=" + token, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        orcamentoArray = GestaoOrcamentoJsonParser.parserJsonorcamentos(response);
                        adicionarOrcamentosDB(orcamentoArray);
                        orcamentoArray=gestaoOrcamentoBDHelper.getAllOrcamentossBD();
                        if(orcamentoListener!=null){
                            orcamentoListener.onRefreshListaOrcamentos(orcamentoArray);
                        }
                        if (mainInstaladorListener!=null){
                            mainInstaladorListener.onRefreshQuantidadeOrcamentosMain(orcamentoArray.size());
                            mainInstaladorListener.onRefreshProdutosOrcamentoAPI();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        orcamentoArray=gestaoOrcamentoBDHelper.getAllOrcamentossBD();
                        if(orcamentoListener!=null) {
                            orcamentoListener.onErroOrcamentos(context.getString(R.string.erro_carregar_orcmaentos));
                            orcamentoListener.onRefreshListaOrcamentos(orcamentoArray);
                        }
                        return;
                    }
                });
                volleyQueue.add(request);
            }

        }
    }

    public void getAllProdutosOrcamentoAPI(final Context context){
        if(!isConnectionInternet(context)){
            produtoOrcamentoArray=gestaoOrcamentoBDHelper.getAllProdutoOrcamentosBD();
            if(produtoOrcamentoListener!=null){
                produtoOrcamentoListener.onSucssecProdutos();
            }
            if(detalhesProdutoOrcamentoListener!=null){
                detalhesProdutoOrcamentoListener.onRefreshProdutosOrcamento(produtoOrcamentoArray);
            }
        }else {

            sharedPreferences= context.getSharedPreferences(MenuMainActivity.PREF_USER,Context.MODE_PRIVATE);
            final String token = sharedPreferences.getString(MenuMainActivity.TOKEN,null);


            gestaoOrcamentoBDHelper.removerAllProdutoOrcamentosDB();
            produtoOrcamentoArray=null;
            orcamentoArray=null;
            orcamentoArray=getOrcamentoDB();
            for (Orcamento orcamento:orcamentoArray) {
                System.out.println("-->"+mURLAPIOrcamentos + URLprodutosOrcamento+ "?id_orcamento="+ orcamento.getId()+"&auth_key=" + token );
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, mURLAPIOrcamentos + URLprodutosOrcamento+ "?id_orcamento="+orcamento.getId()+"&auth_key=" + token, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<ProdutoOrcamento>  produtoOrcamentoArraytem =new ArrayList<ProdutoOrcamento>();
                        produtoOrcamentoArraytem = GestaoOrcamentoJsonParser.parserJsonProdutosorcamentos(response);
                        adicionarProdutosOrcamentosDB(produtoOrcamentoArraytem);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                produtoOrcamentoArray=gestaoOrcamentoBDHelper.getAllProdutoOrcamentosBD();
                                if(produtoOrcamentoListener!=null){
                                    produtoOrcamentoListener.onSucssecProdutos();
                                }
                                if(detalhesProdutoOrcamentoListener!=null){
                                    detalhesProdutoOrcamentoListener.onRefreshProdutosOrcamento(produtoOrcamentoArray);
                                }
                            }
                        }, 5000);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        produtoOrcamentoArray=gestaoOrcamentoBDHelper.getAllProdutoOrcamentosBD();
                        if(produtoOrcamentoListener!=null) {
                            produtoOrcamentoListener.onErroprodutosOrcamentos("Erro a carregar os produtos do orcamento");
                            produtoOrcamentoListener.onSucssecProdutos();
                        }
                        if(detalhesProdutoOrcamentoListener!=null){
                            detalhesProdutoOrcamentoListener.onErroDetalhesprodutos("Erro a carregar os produtos já utilizados");
                            detalhesProdutoOrcamentoListener.onRefreshProdutosOrcamento(produtoOrcamentoArray);
                        }
                        return;
                    }
                });
                    volleyQueue.add(request);
            }



        }

    }

    public void getAllProdutosFornecedorAPI(final Context context){
        if(!isConnectionInternet(context)){
            produtoArray=gestaoOrcamentoBDHelper.getAllProdutosBD();
            if(produtoOrcamentoListener!=null){
                produtoOrcamentoListener.onRefreshListaProdutosOrcamentos(produtoArray);
            }
        }else {

            sharedPreferences= context.getSharedPreferences(MenuMainActivity.PREF_USER,Context.MODE_PRIVATE);
            final String token = sharedPreferences.getString(MenuMainActivity.TOKEN,null);

            gestaoOrcamentoBDHelper.removerAllProdutosDB();
            for (Utilizador utilizador: utilizadorArray){
                System.out.println("-->"+mURLAPIOrcamentos + URLprodutosFornecedor+ "?id_fornecedor="+utilizador.getId()+"&auth_key=" + token );
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, mURLAPIOrcamentos + URLprodutosFornecedor+ "?id_fornecedor="+utilizador.getId()+"&auth_key=" + token, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        produtoArray = GestaoOrcamentoJsonParser.parserJsonProdutos(response);
                        adiconarProdutosDB(produtoArray);
                        produtoArray=gestaoOrcamentoBDHelper.getAllProdutosBD();
                        if(produtoOrcamentoListener!=null){
                            produtoOrcamentoListener.onRefreshListaProdutosOrcamentos(produtoArray);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        produtoArray=gestaoOrcamentoBDHelper.getAllProdutosBD();
                        if(produtoOrcamentoListener!=null){
                            produtoOrcamentoListener.onRefreshListaProdutosOrcamentos(produtoArray);
                        }
                        return;
                    }
                });
                volleyQueue.add(request);
            }
        }
    }



   //funcoes que vão editar os dados na api

    public void editarUtilizadorAPI(final String nome,  String nomeEmpresa,  String Telefone /*, String imagemPerfil*/,Context context ) {
        String message= null;
        if (!isConnectionInternet(context)){
            message = context.getString(R.string.sem_ligacao_internet)+". Certifique que está conectado na internet";
            utilizadorListener.onErroUtilizador(message);
            return;
        }

        sharedPreferences= context.getSharedPreferences(MenuMainActivity.PREF_USER,Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString(MenuMainActivity.TOKEN,null);
        final String id= sharedPreferences.getString(MenuMainActivity.ID,null);


        StringRequest request = new StringRequest(Request.Method.PUT, mURLAPIOrcamentos + URLUtilizadorToken +"/"+id+"?auth_key="+token, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              Utilizador utilizadoraux =GestaoOrcamentoJsonParser.parserJsonUtilizador(response);

                SharedPreferences sharedPreferences= context.getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                String nome = utilizadoraux.getNome();
                String nome_empresa  = utilizadoraux.getNome_empresa();
                String telefone =String.valueOf(utilizadoraux.getTelemovel());

                editor.putString(MenuMainActivity.NOME,nome);
                editor.putString(MenuMainActivity.NOME_EMPRESA,nome_empresa);
                editor.putString(MenuMainActivity.TELEMOVEL,telefone);
                editor.apply();

                utilizadorListener.onUpdateUtilizador(context.getString(R.string.dados_atualizados_com_sucesso));
                atulizaCabecalhoListener.onAtualizaCabecalho();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                utilizadorListener.onErroUtilizador(context.getString(R.string.erro_atualizar_seus_dados));
            }
        }){
            @Override
            protected Map<String, String> getParams() {
               String nome_editar=nome;
               String empresa_editar= nomeEmpresa;
               String telefoneEditar= Telefone;
             //  String imagem= imagemPerfil;
                if(nome_editar.equals("")||nome_editar.equals(" ")){
                    nome_editar="null";
                }
                if(empresa_editar.equals("") || empresa_editar.equals(" ")){
                    empresa_editar="null";
                }
                if (telefoneEditar.equals("")){
                    telefoneEditar="null";
                }
                Map<String, String> params = new HashMap<>();
                params.put("nome", nome_editar);
                params.put("nome_empresa",empresa_editar);
                params.put("telemovel", telefoneEditar);
             //   params.put("imagem", imagem);
                return params;
            }
        };
        volleyQueue.add(request);
    }

    //altera o status do utilizador na api
    public void alterarstatusAPI(Utilizador utilizador,Context context){
        String message= null;
        if (!isConnectionInternet(context)){
            message=context.getString(R.string.verifique_coneccao_servidor);
           detalhesUtilizadorListener.onErroDetalhesUtilizadores(message);
            return;
        }

        sharedPreferences= context.getSharedPreferences(MenuMainActivity.PREF_USER,Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString(MenuMainActivity.TOKEN,null);

        StringRequest request = new StringRequest(Request.Method.PUT, mURLAPIOrcamentos + URLUtilizadorToken +"/"+utilizador.getId()+"?auth_key="+token, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               detalhesUtilizadorListener.onRefreshDetalhesUtilizadores();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
             String message=context.getString(R.string.erro_alterar_dados_servidor);
                detalhesUtilizadorListener.onErroDetalhesUtilizadores(message);
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                String status=String.valueOf(utilizador.getStatus());
                Map<String, String> params = new HashMap<>();
                params.put("status", status);
                return params;
            }
        };
        volleyQueue.add(request);
    }

    //altera o produto
    public void alteraProdutoAPI(Produto produto, Context context){
        String message=null;
        if(!isConnectionInternet(context)){
            message=context.getString(R.string.verifique_coneccao_servidor);
            detalhesProdutoListener.onErroDetalhesprodutos(message);
            return;
        }

        sharedPreferences=context.getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString(MenuMainActivity.TOKEN,null);

        StringRequest request = new StringRequest(Request.Method.PUT, mURLAPIOrcamentos + URLProduto +"/"+produto.getId()+"?auth_key="+token, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                detalhesProdutoListener.onSucssecProduto();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message=context.getString(R.string.erro_alterar_dados_servidor);
                detalhesProdutoListener.onErroDetalhesprodutos(message);
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                String nome=produto.getNome();
                String referencia = produto.getReferencia();
                String descricao = produto.getDescricao();
                String preco =String.valueOf(produto.getPreco());
                Map<String, String> params = new HashMap<>();
                params.put("nome",nome);
                params.put("referencia",referencia);
                params.put("descricao",descricao);
                params.put("preco",preco);
                return params;
            }
        };
        volleyQueue.add(request);
    }

    //altera o cliente
    public void alteraClienteAPI(Cliente cliente,Context context){
        String message=null;
        if(!isConnectionInternet(context)){
            message=context.getString(R.string.verifique_coneccao_servidor);
            if(orcamentoListener!=null){
                orcamentoListener.onErroDetalhesUtilizadores(message);
            }
            return;
        }

        sharedPreferences=context.getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString(MenuMainActivity.TOKEN,null);

        System.out.println("-->"+mURLAPIOrcamentos + URLCLiente +"/"+cliente.getId()+"?auth_key="+token);
        StringRequest request = new StringRequest(Request.Method.PUT, mURLAPIOrcamentos + URLCLiente +"/"+cliente.getId()+"?auth_key="+token, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(orcamentoListener!=null){
                    orcamentoListener.onSucssecAddCliente();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message=context.getString(R.string.erro_alterar_dados_servidor);
                if(orcamentoListener!=null)
                {
                    orcamentoListener.onErroDetalhesUtilizadores(message);
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                String nome=cliente.getNome();
                String email = cliente.getEmail();
                String nif =String.valueOf(cliente.getNif());
                String telefone =String.valueOf(cliente.getTelemovel());
                Map<String, String> params = new HashMap<>();
                params.put("nome",nome);
                params.put("Telemovel",telefone);
                params.put("Nif",nif);
                params.put("Email",email);
                return params;
            }
        };
        volleyQueue.add(request);
    }

    public void alteraOrcamentoAPI(Orcamento orcamento, Context context){
        String message=null;
        if(!isConnectionInternet(context)){
            message=context.getString(R.string.verifique_coneccao_servidor);
            if(produtoOrcamentoListener!=null){
                produtoOrcamentoListener.onErroprodutosOrcamentos(message);
            }
            return;
        }

        sharedPreferences=context.getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString(MenuMainActivity.TOKEN,null);

        System.out.println("-->"+mURLAPIOrcamentos + URLCRUDOrcamentos +"/"+orcamento.getId()+"?auth_key="+token);
        StringRequest request = new StringRequest(Request.Method.PUT, mURLAPIOrcamentos + URLCRUDOrcamentos +"/"+orcamento.getId()+"?auth_key="+token, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(produtoOrcamentoListener!=null){
                    produtoOrcamentoListener.onSucssecAddOrcamento();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message=context.getString(R.string.erro_alterar_dados_servidor);
                if(produtoOrcamentoListener!=null)
                {
                    produtoOrcamentoListener.onErroorcamento(message);
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                String nome=orcamento.getNome();
                String margem = String.valueOf(orcamento.getMargem());
                String data =orcamento.getData_orcamento();
                String cliente_id=String.valueOf(orcamento.getId_cliente());
                String Total = String.valueOf(orcamento.getTotal());
                Map<String, String> params = new HashMap<>();
                params.put("nome",nome);
                params.put("margem",margem);
               params.put("data_orcamento",data);
                params.put("cliente_id",cliente_id);
                return params;
            }
        };
        volleyQueue.add(request);
    }

    public void alterarquantidadeProdutoAPI(int novo_valor,int id_orcamento, int id_produto,Context context){
        String message=null;
        if(!isConnectionInternet(context)){
            message=context.getString(R.string.verifique_coneccao_servidor);
            if(detalhesProdutoOrcamentoListener!=null){
                detalhesProdutoOrcamentoListener.onErroDetalhesprodutos(message);
            }
            return;
        }

        sharedPreferences=context.getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString(MenuMainActivity.TOKEN,null);

        System.out.println("-->"+mURLAPIOrcamentos + URLalterrodutosOrcamento +"?id_orcamento="+id_orcamento+"&id_produto="+id_produto+"&novo_valor="+novo_valor+"&auth_key="+token);
        StringRequest request = new StringRequest(Request.Method.PUT, mURLAPIOrcamentos + URLalterrodutosOrcamento +"?id_orcamento="+id_orcamento+"&id_produto="+id_produto+"&novo_valor="+novo_valor+"&auth_key="+token, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(detalhesProdutoOrcamentoListener!=null){
                    detalhesProdutoOrcamentoListener.onSucssecEditValor();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message=context.getString(R.string.erro_alterar_dados_servidor);
                if(detalhesProdutoOrcamentoListener!=null)
                {
                    detalhesProdutoOrcamentoListener.onErroDetalhesprodutos(message);
                }
            }});
        volleyQueue.add(request);
    }

    public void alterarTotalOrcamentoAPI(Orcamento orcamento ,Context context){
        produtoOrcamentoArray=getProdutoOrcamentoDB();
        produtoArray=getProdutoDB();
        String message=null;
        Float total=0.0f;
        if(!isConnectionInternet(context)){
            message=context.getString(R.string.verifique_coneccao_servidor);
            if(produtoOrcamentoListener!=null){
                produtoOrcamentoListener.onErroprodutosOrcamentos(message);
            }
            return;
        }
        for (ProdutoOrcamento produtoOrcamento: produtoOrcamentoArray) {
            if(produtoOrcamento.getId_orcamento()==orcamento.getId()){
                for (Produto produto: produtoArray) {
                    if(produtoOrcamento.getId_produto()==produto.getId()){
                        total=total+(produtoOrcamento.getQuantidade()*produto.getPreco());
                    }
                }
            }
        }
        total=total+total*(orcamento.getMargem()/100);
        System.out.println("-->"+total);

        sharedPreferences=context.getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString(MenuMainActivity.TOKEN,null);

        System.out.println("-->"+mURLAPIOrcamentos + URLCRUDOrcamentos +"/"+orcamento.getId()+"?auth_key="+token);
        Float finalTotal = total;
        StringRequest request = new StringRequest(Request.Method.PUT, mURLAPIOrcamentos + URLCRUDOrcamentos +"/"+orcamento.getId()+"?auth_key="+token, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(produtoOrcamentoListener!=null){
                    produtoOrcamentoListener.onSucssecAddOrcamento();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message=context.getString(R.string.erro_alterar_dados_servidor);
                if(produtoOrcamentoListener!=null)
                {
                    produtoOrcamentoListener.onErroorcamento(message);
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                String total_enviar = String.valueOf(finalTotal);
                Map<String, String> params = new HashMap<>();
                params.put("total",total_enviar);
                return params;
            }
        };
        volleyQueue.add(request);
    }




    //funçoes que vao adicionar na api
    public void adicionarProdutoAPI(Produto produto, Context context){
        String message=null;
        if(!isConnectionInternet(context)){
            message=context.getString(R.string.sem_ligacao_internet);
            detalhesProdutoListener.onErroDetalhesprodutos(message);
            return;
        }

        sharedPreferences=context.getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString(MenuMainActivity.TOKEN,null);
        final String id= sharedPreferences.getString(MenuMainActivity.ID,null);

        StringRequest request = new StringRequest(Request.Method.POST, mURLAPIOrcamentos + URLProduto+"?auth_key="+token, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                detalhesProdutoListener.onSucssecProduto();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message=context.getString(R.string.erro_guardar_dados_produto);
                detalhesProdutoListener.onErroDetalhesprodutos(message);
            }
        }){
            @Override
            protected Map<String,String>getParams(){
                String nome=produto.getNome();
                String referencia = produto.getReferencia();
                String descricao = produto.getDescricao();
                String preco =String.valueOf(produto.getPreco());

                Map<String,String> params = new HashMap<>();
                params.put("nome",nome);
                params.put("fornecedor_id",id);
                params.put("referencia",referencia);
                params.put("descricao",descricao);
                params.put("preco",preco);
                return params;
            }
        };
        volleyQueue.add(request);
    }

    public void adicionarFornecedorInstaladorAPI(int id_fornecedor, Context context){
        String message=null;
        if(!isConnectionInternet(context)){
            message=context.getString(R.string.sem_ligacao_internet);
            if(detalhesConhecerFornecedorListener!=null){
                detalhesConhecerFornecedorListener.onErroDetalhesUtilizadores(message);
            }

            return;
        }

        sharedPreferences=context.getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString(MenuMainActivity.TOKEN,null);
        final String id= sharedPreferences.getString(MenuMainActivity.ID,null);

        StringRequest request = new StringRequest(Request.Method.POST, mURLAPIOrcamentos + URLFornecedorInstaladorADD+"?auth_key="+token, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                detalhesConhecerFornecedorListener.onSucssecAddFornecedor();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message=context.getString(R.string.erro_guardar_dados_produto);
                if(detalhesConhecerFornecedorListener!=null){
                    detalhesConhecerFornecedorListener.onErroDetalhesUtilizadores(message);
                }
            }
        }){
            @Override
            protected Map<String,String>getParams(){
                sharedPreferences=context.getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
                final String id= sharedPreferences.getString(MenuMainActivity.ID,null);
                 Map<String,String> params = new HashMap<>();
                params.put("fornecedor_id",String.valueOf(id_fornecedor));
                params.put("instalador_id",id);
                return params;
            }
        };
        volleyQueue.add(request);
    }

    public void adicionarCLienteAPI(Cliente cliente, Context context){
        String message=null;
        if(!isConnectionInternet(context)){
            message=context.getString(R.string.sem_ligacao_internet);
            if(orcamentoListener!=null)
            {
                orcamentoListener.onErroDetalhesUtilizadores(message);
            }
            return;
        }

        sharedPreferences=context.getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString(MenuMainActivity.TOKEN,null);
        final String id= sharedPreferences.getString(MenuMainActivity.ID,null);

        System.out.println("-->"+mURLAPIOrcamentos + URLCLiente+"?auth_key="+token);
        StringRequest request = new StringRequest(Request.Method.POST, mURLAPIOrcamentos + URLCLiente+"?auth_key="+token, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(orcamentoListener!=null){
                    orcamentoListener.onSucssecAddCliente();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message=context.getString(R.string.erro_adicionar_na_base_de_dados);
                if(orcamentoListener!=null)
                {
                    orcamentoListener.onErroDetalhesUtilizadores(message);
                }
            }
        }){
            @Override
            protected Map<String,String>getParams(){
                String nome=cliente.getNome();
                String email = cliente.getEmail();
                String nif =String.valueOf(cliente.getNif());
                String telemovel =String.valueOf(cliente.getTelemovel());

                Map<String,String> params = new HashMap<>();
                params.put("nome",nome);
                params.put("user_id",id);
                params.put("Telemovel",telemovel);
                params.put("Nif",nif);
                params.put("Email",email);
                return params;
            }
        };
        volleyQueue.add(request);
    }

    public void adicionarOrcamentoAPI(Orcamento orcamento,Context context){
        String message=null;
        if(!isConnectionInternet(context)){
            message=context.getString(R.string.sem_ligacao_internet);
            if(produtoOrcamentoListener!=null)
            {
                produtoOrcamentoListener.onErroorcamento(message);
            }
            return;
        }

        sharedPreferences=context.getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString(MenuMainActivity.TOKEN,null);

        System.out.println("-->"+mURLAPIOrcamentos + URLCRUDOrcamentos+"?auth_key="+token);
        StringRequest request = new StringRequest(Request.Method.POST, mURLAPIOrcamentos + URLCRUDOrcamentos+"?auth_key="+token, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(produtoOrcamentoListener!=null){
                    produtoOrcamentoListener.onSucssecAddOrcamento();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message=context.getString(R.string.erro_adicionar_na_base_de_dados);
                if(produtoOrcamentoListener!=null)
                {
                    produtoOrcamentoListener.onErroorcamento(message);
                }
            }
        }){
            @Override
            protected Map<String,String>getParams(){
                String nome=orcamento.getNome();
                String total = String.valueOf(orcamento.getTotal());
                String margem =String.valueOf(orcamento.getMargem());
                String data_orcamento =orcamento.getData_orcamento();
                String cliente_id =String.valueOf(orcamento.getId_cliente());

                Map<String,String> params = new HashMap<>();
                params.put("nome",nome);
                params.put("total",total);
                params.put("margem",margem);
                params.put("data_orcamento",data_orcamento);
                params.put("cliente_id",cliente_id);
                return params;
            }
        };
        volleyQueue.add(request);
    }

    public void adicionarProdutoOrcamentoAPI(ProdutoOrcamento produtoOrcamento, Context context){
        String message=null;
        if(!isConnectionInternet(context)){
            message=context.getString(R.string.sem_ligacao_internet);
            if(detalhesProdutoOrcamentoListener !=null)
            {
                detalhesProdutoOrcamentoListener.onErroDetalhesprodutos(message);
            }
            return;
        }

        sharedPreferences=context.getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString(MenuMainActivity.TOKEN,null);

        System.out.println("-->"+mURLAPIOrcamentos + URLCRULprodutosOrcamentos+"?auth_key="+token);
        StringRequest request = new StringRequest(Request.Method.POST, mURLAPIOrcamentos + URLCRULprodutosOrcamentos+"?auth_key="+token, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(detalhesProdutoOrcamentoListener!=null){
                    detalhesProdutoOrcamentoListener.onSucssecEditValor();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message=context.getString(R.string.erro_adicionar_na_base_de_dados);
                if(detalhesProdutoOrcamentoListener!=null)
                {
                    detalhesProdutoOrcamentoListener.onErroDetalhesprodutos(message);
                }
            }
        }){
            @Override
            protected Map<String,String>getParams(){
                String orcamento_id=String.valueOf(produtoOrcamento.getId_orcamento());
                String produto_id = String.valueOf(produtoOrcamento.getId_produto());
                String quantidade =String.valueOf(produtoOrcamento.getQuantidade());

                Map<String,String> params = new HashMap<>();
                params.put("orcamento_id",orcamento_id);
                params.put("produto_id",produto_id);
                params.put("quantidade",quantidade);
                return params;
            }
        };
        volleyQueue.add(request);
    }




    //funcoes que vao apagar na api
    public void removerFornecedorInstaladorAPI(int id_instalador ,Context context){
        String message=null;
        if(!isConnectionInternet(context)){
            message=context.getString(R.string.verifique_coneccao_servidor);
            detalhesinstaladordofornecedorListener.onErroDetalhesUtilizadores(message);
            return;
        }

        sharedPreferences=context.getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString(MenuMainActivity.TOKEN,null);
        final String id = sharedPreferences.getString(MenuMainActivity.ID, null);
        System.out.println("-->"+ mURLAPIOrcamentos + URLPUTFornecedorInstalador +"?id_fornecedor="+id+"&id_instalador="+id_instalador+"&auth_key="+token);
        StringRequest request = new StringRequest(Request.Method.DELETE, mURLAPIOrcamentos + URLPUTFornecedorInstalador +"?id_fornecedor="+id+"&id_instalador="+id_instalador+"&auth_key="+token, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                detalhesinstaladordofornecedorListener.onSucssecRemoveInstalador();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message=context.getString(R.string.erro_alterar_dados_servidor);
                detalhesinstaladordofornecedorListener.onErroDetalhesUtilizadores(message);
            }
        });
        volleyQueue.add(request);
    }

    public void removerInstaladorFornecedorAPI(int id_fornecedor, Context context){
        String message=null;
        if(!isConnectionInternet(context)){
            message=context.getString(R.string.verifique_coneccao_servidor);
            detalhesFornecedordoInstaladorListener.onErroDetalhesUtilizadores(message);
            return;
        }

        sharedPreferences=context.getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString(MenuMainActivity.TOKEN,null);
        final String id = sharedPreferences.getString(MenuMainActivity.ID, null);
        System.out.println("-->"+ mURLAPIOrcamentos + URLPUTFornecedorInstalador +"?id_fornecedor="+id_fornecedor+"&id_instalador="+id+"&auth_key="+token);
        StringRequest request = new StringRequest(Request.Method.DELETE, mURLAPIOrcamentos + URLPUTFornecedorInstalador +"?id_fornecedor="+id_fornecedor+"&id_instalador="+id+"&auth_key="+token, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                detalhesFornecedordoInstaladorListener.onSucssecRemoveIFornecedor();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message=context.getString(R.string.erro_alterar_dados_servidor);
                detalhesFornecedordoInstaladorListener.onErroDetalhesUtilizadores(message);
            }
        });
        volleyQueue.add(request);
    }



    //retorna os utilizadores que já têm a sua conta aceite
    public ArrayList<Utilizador> getUtilizadoresAceites(){
        utilizadorArray=getUtilizadorDB();
        ArrayList<Utilizador> utilizadoresaceites;
        utilizadoresaceites = new ArrayList<Utilizador>();
        for (Utilizador utilizador: utilizadorArray){
            if (utilizador.getStatus()==10 || utilizador.getStatus()==0){
                utilizadoresaceites.add(new Utilizador(utilizador.getId(),utilizador.getUsername(),utilizador.getNome(),utilizador.getNome_empresa(),utilizador.getTelemovel(),utilizador.getEmail(),utilizador.getImagem(),utilizador.getCategoria_id(),utilizador.getStatus()));
            }
        }
        return utilizadoresaceites;
    }

    //retorne os utilizadores que estão pendentes
    public ArrayList<Utilizador> getUtilizadorstatus9 (){
        utilizadorArray=getUtilizadorDB();
        ArrayList<Utilizador> utilizadorespendentes;
        utilizadorespendentes = new ArrayList<Utilizador>();
        for (Utilizador utilizador: utilizadorArray) {
            if (utilizador.getStatus()==9){
                utilizadorespendentes.add(new Utilizador(utilizador.getId(),utilizador.getUsername(),utilizador.getNome(),utilizador.getNome_empresa(),utilizador.getTelemovel(),utilizador.getEmail(),utilizador.getImagem(),utilizador.getCategoria_id(),utilizador.getStatus()));
            }
        }
        return utilizadorespendentes;
    }


    //retorna os isntaladores do fornecedor
    public ArrayList<Utilizador> getInstaladoresdoFornecedor(){
        utilizadorArray=getUtilizadorDB();
        fornecedorInstaladorArray=getFornecedorInstaladorDB();
        ArrayList<Utilizador> utilizadoresdoFornecedoraux;
        utilizadoresdoFornecedoraux= new ArrayList<Utilizador>();
        for (Utilizador utilizador : utilizadorArray) {
            for (FornecedorInstalador fornecedorInstalador: fornecedorInstaladorArray){
                if(fornecedorInstalador.getId_instalador()==utilizador.getId()){
                    utilizadoresdoFornecedoraux.add(new Utilizador(utilizador.getId(),utilizador.getUsername(),utilizador.getNome(),utilizador.getNome_empresa(),utilizador.getTelemovel(),utilizador.getEmail(),utilizador.getImagem(),utilizador.getCategoria_id(),utilizador.getStatus()));
                }
            }
        }
        return utilizadoresdoFornecedoraux;
    }

    //retorna os Fornecedores do Instalador
    public ArrayList<Utilizador> getFornecedoresdoInstalador(){
        utilizadorArray=getUtilizadorDB();
        fornecedorInstaladorArray=getFornecedorInstaladorDB();
        ArrayList<Utilizador> utilizadoresdoFornecedoraux;
        utilizadoresdoFornecedoraux= new ArrayList<Utilizador>();
        for (FornecedorInstalador fornecedorInstalador:fornecedorInstaladorArray) {
            for (Utilizador utilizador: utilizadorArray) {
                if(utilizador.getId()==fornecedorInstalador.getId_fornecedor()){
                    utilizadoresdoFornecedoraux.add(new Utilizador(utilizador.getId(),utilizador.getUsername(),utilizador.getNome(),utilizador.getNome_empresa(),utilizador.getTelemovel(),utilizador.getEmail(),utilizador.getImagem(),utilizador.getCategoria_id(),utilizador.getStatus()));
                }
            }
        }
        return utilizadoresdoFornecedoraux;
    }

    //retorna os utilizadors a conhecer
    public ArrayList<Utilizador> getConhecerFornecedor(){
        utilizadorArray=getUtilizadorDB();
        fornecedorInstaladorArray=getFornecedorInstaladorDB();
        ArrayList<Utilizador> utilizadoresdoFornecedoraux;
        utilizadoresdoFornecedoraux= new ArrayList<Utilizador>();

        Boolean adicionar = true;

        for (Utilizador utilizador: utilizadorArray) {
            for (FornecedorInstalador fornecedorInstalador:fornecedorInstaladorArray) {
                if(utilizador.getId()==fornecedorInstalador.getId_fornecedor()){
                    adicionar=false;
                }
            }
            if(adicionar==true){
                utilizadoresdoFornecedoraux.add(new Utilizador(utilizador.getId(),utilizador.getUsername(),utilizador.getNome(),utilizador.getNome_empresa(),utilizador.getTelemovel(),utilizador.getEmail(),utilizador.getImagem(),utilizador.getCategoria_id(),utilizador.getStatus()));
            }else{
                adicionar=true;
            }

        }
        return utilizadoresdoFornecedoraux;
    }

    //retorna os orcamentos
    public ArrayList<Orcamento> getOrcamentoArray(int id_cliente){
      ArrayList<Orcamento> orcamentos;
      orcamentos = new ArrayList<Orcamento>();
      for(Orcamento orcamento: orcamentoArray){

          if(orcamento.getId_cliente()==id_cliente){
              orcamentos.add(new Orcamento(orcamento.getId(),orcamento.getId_cliente(),orcamento.getData_orcamento(),orcamento.getMargem(),orcamento.getTotal(),orcamento.getNome()));
          }
      }
      return orcamentos;
    }

    //retorna os produtos do orçamento
    public ArrayList<Produto>getProdutosOrcamentoArray(int id_orcamento){
        produtoOrcamentoArray=getProdutoOrcamentoDB();
        produtoArray=getProdutoDB();

        ArrayList<Produto> produtosOrcamento;
        produtosOrcamento = new ArrayList<Produto>();
        for(ProdutoOrcamento produtoOrcamento: produtoOrcamentoArray){
            for (Produto produto: produtoArray) {
                if(produtoOrcamento.getId_produto()==produto.getId()&&produtoOrcamento.getId_orcamento()==id_orcamento){
                    produtosOrcamento.add(new Produto(produto.getId(),produto.getId_fornecedor(),produto.getImagem(),produto.getNome(),produto.getReferencia(),produto.getDescricao(),produto.getPreco()));
                }
            }
        }
        return produtosOrcamento;
    }

    public ArrayList<Produto> getProdutosAddOrcamento(int id_orcamento){
        ArrayList<Produto> produtosaux ;
        produtoArray=getProdutoDB();
        fornecedorInstaladorArray=getFornecedorInstaladorDB();
        produtoOrcamentoArray=getProdutoOrcamentoDB();
        produtosaux= new ArrayList<Produto>();

        Boolean exite=false;

        for (Produto produto: produtoArray) {
            for (FornecedorInstalador fornecedorInstalador: fornecedorInstaladorArray) {
                if(fornecedorInstalador.getId_fornecedor()==produto.getId_fornecedor()){
                    exite=false;
                }
            }
            for (ProdutoOrcamento produtoOrcamento: produtoOrcamentoArray) {
                if(produtoOrcamento.getId_produto()==produto.getId()&&produtoOrcamento.getId_orcamento()==id_orcamento){
                    exite=true;
                }
            }

            if(exite==false){
                produtosaux.add(new Produto(produto.getId(),produto.getId_fornecedor(),produto.getImagem(),produto.getNome(),produto.getReferencia(),produto.getDescricao(),produto.getPreco()));
            }
        }

        return produtosaux;
    }

    // Função Json Parse Login
    public int parserJsonLOGIN(String response,String password,final Context context){
        int id , categoria;
        String sucesso = "";
        String nome = null ,nome_empresa = null, email= null, imagem = null, authkey = null , funcao= null,telemovel=null, username=null;

        try {
            JSONObject login= new JSONObject(response);
            System.out.println("--> "+login);

            if (login.getString("sucesso").equals("true")){
                sharedPreferences= context.getSharedPreferences(MenuMainActivity.PREF_USER,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                //vai buscar os valores do return da api
                id = login.getInt("id");
                nome= login.getString("nome");
                nome_empresa = login.getString("nome_empresa");
                telemovel = login.getString("telemovel");
                email= login.getString("email");
                imagem = login.getString("imagem");
                categoria= login.getInt("categoria_id");
                authkey = login.getString("auth_key");
                funcao = login.getString("funcao");
                username = login.getString("username");
                //guarda na sharedpreference
                editor.clear();
                editor.putString(MenuMainActivity.ID,Integer.toString(id));
                editor.putString(MenuMainActivity.NOME, nome);
                editor.putString(MenuMainActivity.NOME_EMPRESA, nome_empresa);
                editor.putString(MenuMainActivity.TELEMOVEL, telemovel);
                editor.putString(MenuMainActivity.EMAIL,email);
                editor.putString(MenuMainActivity.IMAGEM,imagem);
                editor.putString(MenuMainActivity.CATEFORIA, Integer.toString(categoria));
                editor.putString(MenuMainActivity.TOKEN,authkey);
                editor.putString(MenuMainActivity.FUNCAO,funcao.toLowerCase());
                editor.putString(MenuMainActivity.PASSWORD,password);
                editor.putString(MenuMainActivity.USERNAME, username);
                editor.apply();
                return 1;
            }else{
                messageErroLogin= login.getString("texto");
                return 0 ;
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
        return 0;
    }

    //Função de JSON Parse Registar
    public int parserJsonRegistar(String response, final Context context) {
        String sucesso = "";
        try {
            JSONObject registar = new JSONObject(response);
            System.out.println("--> " + registar);

            if (registar.getString("sucesso").equals("true")) {
               messageErroRegistar = registar.getString("texto") + ". Será necessário agora os administradores aceitarem o seu pedido.";
               return 1;
            }else{
                messageErroRegistar= registar.getString("texto");
                return 0;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //funcao que veririfica se esta conectado a internet
    public static boolean isConnectionInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo!=null && networkInfo.isConnected();
    }


    //funçoes externas

    //funcao retirada de https://pt.stackoverflow.com/questions/237535/colocar-a-primeira-letra-de-cada-palavra-em-mai%C3%BAscula
    public String toTitledCase(String str){
        String[] words = str.split("\\s");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < words.length; i++){
            sb.append(words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase());
            sb.append(" ");
        }
        return sb.toString();
    }

}
