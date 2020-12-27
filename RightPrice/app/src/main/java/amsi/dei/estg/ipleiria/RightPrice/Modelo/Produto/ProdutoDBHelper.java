package amsi.dei.estg.ipleiria.RightPrice.Modelo.Produto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ProdutoDBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 495;
    private static final String DB_NAME = "RightPriceDB";
    private static final String TABLE_NAME = "Produto";

    private static final String ID_PRODUTO = "id";
    private static final String ID_FORNECEDOR = "id_fornecedor";
    private static final String IMAGEM = "imagem";
    private static final String NOME = "nome";
    private static final String REFERENCIA = "referencia";
    private static final String DESCRICAO = "descricao";
    private static final String PRECO = "preco";

    private  final SQLiteDatabase sqLiteDatabase;

    public ProdutoDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createProdutoTable= "CREATE TABLE " + TABLE_NAME + " ( " + ID_PRODUTO + " INTEGER PRIMARY KEY, " +
                ID_FORNECEDOR + " TEXT NOT NULL, " +
                IMAGEM + " TEXT , " +
                NOME + " TEXT NOT NULL, " +
                REFERENCIA + " INTEGER NOT NULL, " +
                DESCRICAO + " TEXT, " +
                PRECO + " INTEGER NOT NULL " +
                " );";
        sqLiteDatabase.execSQL(createProdutoTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    public Produto adicionarProdutoDB(Produto produto){
        ContentValues values = new ContentValues();
        values.put(ID_PRODUTO, produto.getId());
        values.put(ID_FORNECEDOR, produto.getId_fornecedor());
        values.put(IMAGEM, produto.getImagem());
        values.put(NOME, produto.getNome());
        values.put(REFERENCIA, produto.getReferencia());
        values.put(DESCRICAO, produto.getDescricao());
        values.put(PRECO, produto.getPreco());

        long id = this.sqLiteDatabase.insert(TABLE_NAME, null, values);

        if(id > -1){
            produto.setId((int) id);
            return produto;
        }
        return null;
    }

    public boolean editarProdutoDB(Produto produto){
        ContentValues values = new ContentValues();
        values.put(ID_PRODUTO, produto.getId());
        values.put(ID_FORNECEDOR, produto.getId_fornecedor());
        values.put(IMAGEM, produto.getImagem());
        values.put(NOME, produto.getNome());
        values.put(REFERENCIA, produto.getReferencia());
        values.put(DESCRICAO, produto.getDescricao());
        values.put(PRECO, produto.getPreco());

        return this.sqLiteDatabase.update(TABLE_NAME,values,"id = ?", new String[]{String.valueOf(produto.getId())}) == 1 ;
    }

    public boolean removerProdutoDB(int idProduto){
        return (this.sqLiteDatabase.delete(TABLE_NAME,"id = ?", new String[]{String.valueOf(idProduto)})) == 1;
    }


    public void removerAllProdutosDB(){
        this.sqLiteDatabase.delete(TABLE_NAME,null,null);
    }

    public ArrayList<Produto> getAllProdutosBD (){
        ArrayList<Produto> produtos = new ArrayList<>();
        Cursor cursor = this.sqLiteDatabase.query(TABLE_NAME,new String[]{
                        ID_PRODUTO,ID_FORNECEDOR,IMAGEM,NOME,REFERENCIA,DESCRICAO,PRECO},
                null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                Produto produto = new Produto(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6));
                produtos.add(produto);
            }while (cursor.moveToNext());
        }
        return produtos;
    }

}
