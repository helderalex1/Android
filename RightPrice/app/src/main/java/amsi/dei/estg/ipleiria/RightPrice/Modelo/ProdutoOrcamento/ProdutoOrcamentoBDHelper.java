package amsi.dei.estg.ipleiria.RightPrice.Modelo.ProdutoOrcamento;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ProdutoOrcamentoBDHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 495;
    private static final String DB_NAME = "RightPriceDB";
    private static final String TABLE_NAME = "ProdutoOrcamento";

    private static final String ID_ORCAMENTO = "id";
    private static final String ID_PRODUTO = "id_produto";
    private static final String QUANTIDADE = "quantidade";

    private  final SQLiteDatabase sqLiteDatabase;

    public ProdutoOrcamentoBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createProdutoOrcamentoTable= "CREATE TABLE " + TABLE_NAME + " ( " + ID_ORCAMENTO + " INTEGER PRIMARY KEY, " +
                ID_PRODUTO + " INTEGER NOT NULL, " +
                QUANTIDADE + " TEXT NOT NULL " +
                " );";
        sqLiteDatabase.execSQL(createProdutoOrcamentoTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    public ProdutoOrcamento adicionarProdutoOrcamentoDB(ProdutoOrcamento produtoOrcamento){
        ContentValues values = new ContentValues();
        values.put(ID_ORCAMENTO, produtoOrcamento.getId_orcamento());
        values.put(ID_PRODUTO, produtoOrcamento.getId_produto());
        values.put(QUANTIDADE, produtoOrcamento.getQuantidade());

        long id = this.sqLiteDatabase.insert(TABLE_NAME, null, values);

        if(id > -1){
            produtoOrcamento.setId_orcamento((int) id);
            return produtoOrcamento;
        }
        return null;
    }

    public boolean editarProdutoOrcamentoDB(ProdutoOrcamento produtoOrcamento){
        ContentValues values = new ContentValues();
        values.put(ID_ORCAMENTO, produtoOrcamento.getId_orcamento());
        values.put(ID_PRODUTO, produtoOrcamento.getId_produto());
        values.put(QUANTIDADE, produtoOrcamento.getQuantidade());

        return this.sqLiteDatabase.update(TABLE_NAME,values,"id = ?", new String[]{String.valueOf(produtoOrcamento.getId_orcamento())}) == 1 ;
    }

    public boolean removerProdutoOrcamentoDB(int idProdutoOrcamento){
        return (this.sqLiteDatabase.delete(TABLE_NAME,"id = ?", new String[]{String.valueOf(idProdutoOrcamento)})) == 1;
    }


    public void removerAllProdutoOrcamentosDB(){
        this.sqLiteDatabase.delete(TABLE_NAME,null,null);
    }

    public ArrayList<ProdutoOrcamento> getAllProdutoOrcamentosBD (){
        ArrayList<ProdutoOrcamento> produtoOrcamentos = new ArrayList<>();
        Cursor cursor = this.sqLiteDatabase.query(TABLE_NAME,new String[]{
                        ID_ORCAMENTO,ID_PRODUTO,QUANTIDADE},
                null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                ProdutoOrcamento produtoOrcamento = new ProdutoOrcamento(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2));
                produtoOrcamentos.add(produtoOrcamento);
            }while (cursor.moveToNext());
        }
        return produtoOrcamentos;
    }

}
