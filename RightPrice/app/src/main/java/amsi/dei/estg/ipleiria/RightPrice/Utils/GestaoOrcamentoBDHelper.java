package amsi.dei.estg.ipleiria.RightPrice.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Categoria;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Cliente;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.FornecedorInstalador;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Funcao;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Orcamento;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Produto;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.ProdutoOrcamento;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;


//classe de base de dados android
public class GestaoOrcamentoBDHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION=495;
    //nome da base de dados
    private static final String DB_NAME = "RightPriceDB";
    //nomes das tabelas
    private static final String TABLE_NAMEUser = "User";
    private static final String TABLE_NAMECategoria = "Categoria";
    private static final String TABLE_NAMECliente = "Cliente";
    private static final String TABLE_NAMEFornecedorInstalador = "fornecedor_instalador";
    private static final String TABLE_NAMEFuncao = "Funcao";
    private static final String TABLE_NAMEOrcamento = "orcamento";
    private static final String TABLE_NAMEProduto = "Produto";
    private static final String TABLE_NAMEProdutoOrcamentos = "orcamento_produto";


    //parametros da tabela user
    private static final String ID_USER_User = "id";
    private static final String USERNAME_User= "username";
    private static final String NOME_User="nome";
    private static final String NOME_EMPRESA_User ="nome_empresa";
    private static final String TELEMOVEL_User= "telemovel";
    private static final String EMAIL_User= "email";
    private static final String IMAGEM_User="imagem";
    private static final String CATEGORIA_ID_User="categoria_id";
    private static final String STATUS_User="status";

    //parametros da tabela categoria
    private static final String ID_CATEGORIA_Categoria = "id";
    private static final String NOME_CATEGORIA_Categoria= "nome_Categoria";


    //parametros da tabela clietes
    private static final String ID_CLIENTE_Cliente = "id";
    private static final String USER_ID_Cliente = "user_id";
    private static final String NOME_Cliente = "nome";
    private static final String TELEMOVEL_Cliente = "Telemovel";
    private static final String NIF_Cliente = "Nif";
    private static final String EMAIL_Cliente = "Email";


    //parametros da tabela Fonecedor Instalador
    private static final String ID_Fornecedor_fornecedor_instalado = "fornecedor_id";
    private static final String ID_INSTALADOR_fornecedor_instalador = "instalador_id";


    //parametros da tabela Funcao
    private static final String FUNCAO_Funcao = "item_name";
    private static final String USER_ID_Funcao = "user_id";


    //parametros da tabela orcamento
    private static final String ID_ORCAMENTO_Orcamento = "id";
    private static final String ID_CLIENTE_Orcamento = "cliente_id";
    private static final String DATA_ORCAMENTO_Orcamento = "data_orcamento";
    private static final String MARGEM_Orcamento = "margem";
    private static final String TOTAL_Orcamento = "total";
    private static final String NOME_Orcamento = "nome";

    //parametros da tabela produtos
    private static final String ID_PRODUTO_Produto = "id";
    private static final String ID_FORNECEDOR_Produto = "fornecedor_id";
    private static final String IMAGEM_Produto = "imagem";
    private static final String NOME_Produto = "nome";
    private static final String REFERENCIA_Produto = "referencia";
    private static final String DESCRICAO_Produto = "descricao";
    private static final String PRECO_Produto = "preco";

    //parametros da tabela produtos orcamentos
    private static final String ID_ORCAMENTO_produto_orcamento = "orcamento_id";
    private static final String ID_PRODUTO_produto_orcamento = "produto_id";
    private static final String QUANTIDADE_produto_orcamento = "quantidade";


    private final SQLiteDatabase sqLiteDatabase;


    public GestaoOrcamentoBDHelper (Context context){
        super(context,DB_NAME,null, DB_VERSION);
        this.sqLiteDatabase = this.getWritableDatabase();
    }




    //funcao que cria as tabelas na base de dados
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createCategoriaTable);
        sqLiteDatabase.execSQL(createClienteTable);
        sqLiteDatabase.execSQL(createFornecedorInstaladorTable);
        sqLiteDatabase.execSQL(createFuncaoTable);
        sqLiteDatabase.execSQL(createOrcamentoTable);
        sqLiteDatabase.execSQL(createProdutoTable);
        sqLiteDatabase.execSQL(createProdutoOrcamentoTable);
        sqLiteDatabase.execSQL(createUserTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMECategoria);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMECliente);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMEFornecedorInstalador);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMEFuncao);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMEOrcamento);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMEProduto);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMEProdutoOrcamentos);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMEUser);
        this.onCreate(sqLiteDatabase);
    }


    //codigo para criar as tabelas

    //criação da tabela Utilizador
    private static final String createUserTable= "CREATE TABLE " + TABLE_NAMEUser+ " ( " + ID_USER_User + " INTEGER PRIMARY KEY, " +
            USERNAME_User + " TEXT NOT NULL, " +
            NOME_User + " TEXT, " +
            NOME_EMPRESA_User + " TEXT, " +
            TELEMOVEL_User + " INTEGER, " +
            EMAIL_User + " TEXT, " +
            IMAGEM_User + " TEXT, " +
            CATEGORIA_ID_User + " INTEGER, " +
            STATUS_User + " INTEGER " +
            " );";


    //codigo para criar a tabela categorias
    private static final String createCategoriaTable= "CREATE TABLE " + TABLE_NAMECategoria + " ( " + ID_CATEGORIA_Categoria + " INTEGER PRIMARY KEY, " + NOME_CATEGORIA_Categoria + " TEXT NOT NULL " + " );";

    //codigo para criar a tabela clientes
    private static final  String createClienteTable = "CREATE TABLE " + TABLE_NAMECliente + " ( " + ID_CLIENTE_Cliente + " INTEGER PRIMARY KEY, " +
            USER_ID_Cliente + " INTEGER NOT NULL, " +
            NOME_Cliente + " TEXT NOT NULL, " +
            TELEMOVEL_Cliente + " INTEGER NOT NULL, " +
            NIF_Cliente + " INTEGER NOT NULL, " +
            EMAIL_Cliente + " TEXT NOT NULL" +
            " );";

    //codigo para criar a tabela FornecedorInstalador
    private static final String createFornecedorInstaladorTable= "CREATE TABLE " + TABLE_NAMEFornecedorInstalador + " (" + ID_Fornecedor_fornecedor_instalado + " INTEGER NOT NULL, " +
            ID_INSTALADOR_fornecedor_instalador + " INTEGER NOT NULL " +
            " );";

    //codigo para criar a tabela Funcao
    private static final String createFuncaoTable= "CREATE TABLE " + TABLE_NAMEFuncao + " ( "+FUNCAO_Funcao + " TEXT NOT NULL, " +
            USER_ID_Funcao + " INTEGER NOT NULL " +
            " );";

    //codigo para criar a tabela Orcamentos
    private static final String createOrcamentoTable= "CREATE TABLE " + TABLE_NAMEOrcamento + " ( " + ID_ORCAMENTO_Orcamento + " INTEGER PRIMARY KEY, " +
            ID_CLIENTE_Orcamento + " INTEGER NOT NULL, " +
            DATA_ORCAMENTO_Orcamento + " TEXT NOT NULL, " +
            MARGEM_Orcamento + " INTEGER NOT NULL, " +
            TOTAL_Orcamento + " FLOAT NOT NULL, " +
            NOME_Orcamento + " TEXT NOT NULL " +
            " );";

    //codigo para criar a tabela Produtos
    private static final String createProdutoTable= "CREATE TABLE " + TABLE_NAMEProduto + " ( " + ID_PRODUTO_Produto + " INTEGER PRIMARY KEY, " +
            ID_FORNECEDOR_Produto + " INTEGER NOT NULL, " +
            IMAGEM_Produto + " TEXT , " +
            NOME_Produto + " TEXT NOT NULL, " +
            REFERENCIA_Produto + " TEXT NOT NULL, " +
            DESCRICAO_Produto + " TEXT, " +
            PRECO_Produto + " FLOAT NOT NULL " +
            " );";

    //codigo para criar a tabela ProdutoOrcamento
    private static final String createProdutoOrcamentoTable= "CREATE TABLE " + TABLE_NAMEProdutoOrcamentos + " ( " + ID_ORCAMENTO_produto_orcamento + " INTEGER, " +
            ID_PRODUTO_produto_orcamento + " INTEGER NOT NULL, " +
            QUANTIDADE_produto_orcamento + " INTEGER NOT NULL " +
            " );";



    //Cetegorias

    //adicionar Categoria
    public Categoria adicionarCategoriaDB(Categoria categoria){
        ContentValues values = new ContentValues();
        values.put(ID_CATEGORIA_Categoria, categoria.getId());
        values.put(NOME_CATEGORIA_Categoria, categoria.getNome_categoria());

        this.sqLiteDatabase.insert(TABLE_NAMECategoria, null, values);
        return null;
    }
    //remover todas as categorias
    public void removerAllCategoriasDB(){
        this.sqLiteDatabase.delete(TABLE_NAMECategoria,null,null);
    }
    //get todas as categorias
    public ArrayList<Categoria> getAllCategoriasBD (){
        ArrayList<Categoria> categorias = new ArrayList<>();
        Cursor cursor = this.sqLiteDatabase.query(TABLE_NAMECategoria,new String[]{
                        ID_CATEGORIA_Categoria,NOME_CATEGORIA_Categoria},
                null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                Categoria categoria = new Categoria(cursor.getInt(0),cursor.getString(1));
                categorias.add(categoria);
            }while (cursor.moveToNext());
        }
        return categorias;
    }


    //Clientes

    //adiciona Cliente
    public Cliente adicionarClienteDB(Cliente cliente) {
        ContentValues values = new ContentValues();
        values.put(ID_CLIENTE_Cliente, cliente.getId());
        values.put(USER_ID_Cliente, cliente.getUser_id());
        values.put(NOME_Cliente, cliente.getNome());
        values.put(TELEMOVEL_Cliente, cliente.getTelemovel());
        values.put(NIF_Cliente, cliente.getNif());
        values.put(EMAIL_Cliente, cliente.getEmail());

        this.sqLiteDatabase.insert(TABLE_NAMECliente, null, values);
        return null;
    }
    //remover todos os clientes
    public void removerAllClientesDB() {
        this.sqLiteDatabase.delete(TABLE_NAMECliente, null, null);
    }
    //get todos os clientes
    public ArrayList<Cliente> getAllClientesBD() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        Cursor cursor = this.sqLiteDatabase.query(TABLE_NAMECliente, new String[]{
                        ID_CLIENTE_Cliente, USER_ID_Cliente, NOME_Cliente, TELEMOVEL_Cliente, NIF_Cliente, EMAIL_Cliente},
                null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Cliente cliente = new Cliente(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5));
                clientes.add(cliente);
            } while (cursor.moveToNext());
        }
        return clientes;
    }


    //Fornecedores Instaladores

    //adiciona o FornecedorInstalador
    public FornecedorInstalador adicionarFornecedorInstaladoroDB(FornecedorInstalador fornecedorInstalador){
        ContentValues values = new ContentValues();
        values.put(ID_Fornecedor_fornecedor_instalado, fornecedorInstalador.getId_fornecedor());
        values.put(ID_INSTALADOR_fornecedor_instalador, fornecedorInstalador.getId_instalador());

        this.sqLiteDatabase.insert(TABLE_NAMEFornecedorInstalador, null, values);
        return null;
    }
    //Removert todos os Fornecedores Instaladores
    public void removerAllFornecedorInstaladorsDB(){
        this.sqLiteDatabase.delete(TABLE_NAMEFornecedorInstalador,null,null);
    }
    //Get todos os FOrnecedores Insrtaladores
    public ArrayList<FornecedorInstalador> getAllFornecedorInstaladorBD (){
        ArrayList<FornecedorInstalador> fornecedorInstaladores = new ArrayList<>();
        Cursor cursor = this.sqLiteDatabase.query(TABLE_NAMEFornecedorInstalador,new String[]{
                        ID_Fornecedor_fornecedor_instalado,ID_INSTALADOR_fornecedor_instalador},
                null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                FornecedorInstalador fornecedorInstalador = new FornecedorInstalador(cursor.getInt(0),cursor.getInt(1));
                fornecedorInstaladores.add(fornecedorInstalador);
            }while (cursor.moveToNext());
        }
        return fornecedorInstaladores;
    }


    //Funcao

    //Adiciona funcao
    public Funcao adicionarFuncaoDB(Funcao funcao){
        ContentValues values = new ContentValues();
        values.put(FUNCAO_Funcao, funcao.getFuncao());
        values.put(USER_ID_Funcao, funcao.getUser_id());

        if(this.sqLiteDatabase.insert(TABLE_NAMEFuncao, null, values)==1){
            return funcao;
        }
        return null;
    }
    //Remover todas as funcoes
    public void removerAllFuncoesDB(){
        this.sqLiteDatabase.delete(TABLE_NAMEFuncao,null,null);
    }
    //Get todas as Funcoes
    public ArrayList<Funcao> getAllFuncoesBD (){
        ArrayList<Funcao> funcoes = new ArrayList<>();
        Cursor cursor = this.sqLiteDatabase.query(TABLE_NAMEFuncao,new String[]{
                        FUNCAO_Funcao,USER_ID_Funcao},
                null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                Funcao funcao = new Funcao(cursor.getString(0),cursor.getInt(1));
                funcoes.add(funcao);
            }while (cursor.moveToNext());
        }
        return funcoes;
    }


    //orcamento

    //Adicionar Orcamento
    public Orcamento adicionarOrcamentoDB(Orcamento orcamento){
        ContentValues values = new ContentValues();
        values.put(ID_ORCAMENTO_Orcamento, orcamento.getId());
        values.put(ID_CLIENTE_Orcamento, orcamento.getId_cliente());
        values.put(DATA_ORCAMENTO_Orcamento,orcamento.getData_orcamento());
        values.put(MARGEM_Orcamento, orcamento.getMargem());
        values.put(TOTAL_Orcamento, orcamento.getTotal());
        values.put(NOME_Orcamento, orcamento.getNome());
        this.sqLiteDatabase.insert(TABLE_NAMEOrcamento, null, values);
        return null;
    }
    //remover todos os Orcamentos
    public void removerAllOrcamentosDB(){
        this.sqLiteDatabase.delete(TABLE_NAMEOrcamento,null,null);
    }
    //get todos os Orcamentos
    public ArrayList<Orcamento> getAllOrcamentossBD (){
        ArrayList<Orcamento> orcamentos = new ArrayList<>();
        Cursor cursor = this.sqLiteDatabase.query(TABLE_NAMEOrcamento,new String[]{
                        ID_ORCAMENTO_Orcamento,ID_CLIENTE_Orcamento,DATA_ORCAMENTO_Orcamento,MARGEM_Orcamento,TOTAL_Orcamento,NOME_Orcamento},
                null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                Orcamento orcamento = new Orcamento(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4),cursor.getString(5));
                orcamentos.add(orcamento);
            }while (cursor.moveToNext());
        }
        return orcamentos;
    }


    //produto

    //Adiciona um produto
    public Produto adicionarProdutoDB(Produto produto){
        ContentValues values = new ContentValues();
        values.put(ID_PRODUTO_Produto, produto.getId());
        values.put(ID_FORNECEDOR_Produto, produto.getId_fornecedor());
        values.put(IMAGEM_Produto, produto.getImagem());
        values.put(NOME_Produto, produto.getNome());
        values.put(REFERENCIA_Produto, produto.getReferencia());
        values.put(DESCRICAO_Produto, produto.getDescricao());
        values.put(PRECO_Produto, produto.getPreco());


        this.sqLiteDatabase.insert(TABLE_NAMEProduto, null, values);
        return null;
    }
    //remover todos os Produtos
    public void removerAllProdutosDB(){
        this.sqLiteDatabase.delete(TABLE_NAMEProduto,null,null);
    }
    //get todos os Produtos
    public ArrayList<Produto> getAllProdutosBD (){
        ArrayList<Produto> produtos = new ArrayList<>();
        Cursor cursor = this.sqLiteDatabase.query(TABLE_NAMEProduto,new String[]{
                        ID_PRODUTO_Produto,ID_FORNECEDOR_Produto,IMAGEM_Produto,NOME_Produto,REFERENCIA_Produto,DESCRICAO_Produto,PRECO_Produto},
                null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                Produto produto = new Produto(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6));
                produtos.add(produto);
            }while (cursor.moveToNext());
        }
        return produtos;
    }


    //ProdutoOrcamento

    //Adicionar um produto ao Orcamento
    public ProdutoOrcamento adicionarProdutoOrcamentoDB(ProdutoOrcamento produtoOrcamento){
        ContentValues values = new ContentValues();
        values.put(ID_ORCAMENTO_produto_orcamento, produtoOrcamento.getId_orcamento());
        values.put(ID_PRODUTO_produto_orcamento, produtoOrcamento.getId_produto());
        values.put(QUANTIDADE_produto_orcamento, produtoOrcamento.getQuantidade());

        this.sqLiteDatabase.insert(TABLE_NAMEProdutoOrcamentos, null, values);
        return null;
    }
    //remover todos os Produtos
    public void removerAllProdutoOrcamentosDB(){
        this.sqLiteDatabase.delete(TABLE_NAMEProdutoOrcamentos,null,null);
    }
    //get todos os Produtos do Orcamento
    public ArrayList<ProdutoOrcamento> getAllProdutoOrcamentosBD (){
        ArrayList<ProdutoOrcamento> produtoOrcamentos = new ArrayList<>();
        Cursor cursor = this.sqLiteDatabase.query(TABLE_NAMEProdutoOrcamentos,new String[]{
                        ID_ORCAMENTO_produto_orcamento,ID_PRODUTO_produto_orcamento,QUANTIDADE_produto_orcamento},
                null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                ProdutoOrcamento produtoOrcamento = new ProdutoOrcamento(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2));
                produtoOrcamentos.add(produtoOrcamento);
            }while (cursor.moveToNext());
        }
        return produtoOrcamentos;
    }


    //Username

    //adiciona o utilizador
    public Utilizador adicionarUserDB (Utilizador utilizador){
        ContentValues values = new ContentValues();
        values.put(ID_USER_User,utilizador.getId());
        values.put(USERNAME_User, utilizador.getUsername());
        values.put(NOME_User, utilizador.getNome());
        values.put(NOME_EMPRESA_User,utilizador.getNome_empresa());
        values.put(TELEMOVEL_User,utilizador.getTelemovel());
        values.put(EMAIL_User,utilizador.getEmail());
        values.put(IMAGEM_User, utilizador.getImagem());
        values.put(CATEGORIA_ID_User, utilizador.getCategoria_id());
        values.put(STATUS_User,utilizador.getStatus());

        this.sqLiteDatabase.insert(TABLE_NAMEUser, null, values);
        return null;
    }
    //remove todos os Utilizadores
    public void removerAllUtilizadoresDB(){
           this.sqLiteDatabase.delete(TABLE_NAMEUser,null,null);
    }
   //Get todos os Utilizadores
    public ArrayList<Utilizador> getAllUsersDB(){
        ArrayList<Utilizador> utilizadores = new ArrayList<>();
        Cursor cursor = this.sqLiteDatabase.query(TABLE_NAMEUser,new String[]{
                        ID_USER_User,USERNAME_User,NOME_User,NOME_EMPRESA_User,TELEMOVEL_User,EMAIL_User,IMAGEM_User,CATEGORIA_ID_User,STATUS_User},
                null,null,null,null,null,null);

        if(cursor.moveToFirst()){
            do {
                Utilizador utilizador= new Utilizador(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getInt(8));
                utilizadores.add(utilizador);
            }while (cursor.moveToNext());
        }
        return utilizadores;
    }


}
