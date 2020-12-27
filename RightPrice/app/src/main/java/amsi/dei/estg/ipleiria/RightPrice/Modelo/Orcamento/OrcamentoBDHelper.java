package amsi.dei.estg.ipleiria.RightPrice.Modelo.Orcamento;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class OrcamentoBDHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 495;
    private static final String DB_NAME = "RightPriceDB";
    private static final String TABLE_NAME = "Livro";

    private static final String ID_ORCAMENTO = "id";
    private static final String ID_CLIENTE = "id_cliente";
    private static final String DATA_ORCAMENTO = "data_orcamento";
    private static final String MARGEM = "margem";
    private static final String TOTAL = "total";
    private static final String NOME = "nome";

    private  final SQLiteDatabase sqLiteDatabase;

    public OrcamentoBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createLivroTable= "CREATE TABLE " + TABLE_NAME + " ( " + ID_ORCAMENTO + " INTEGER PRIMARY KEY, " +
                ID_CLIENTE + " INTEGER NOT NULL, " +
               /* DATA_ORCAMENTO + " DATETIME NOT NULL, " +*/
                MARGEM + " INTEGER NOT NULL, " +
                TOTAL + " INTEGER NOT NULL, " +
                NOME + " TEXT NOT NULL " +
                " );";
        sqLiteDatabase.execSQL(createLivroTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    public Orcamento adicionarOrcamentoDB(Orcamento orcamento){
        ContentValues values = new ContentValues();
        values.put(ID_ORCAMENTO, orcamento.getId());
        values.put(ID_CLIENTE, orcamento.getId_cliente());
       // values.put(DATA_ORCAMENTO, orcamento.getData_orcamento());
        values.put(MARGEM, orcamento.getMargem());
        values.put(TOTAL, orcamento.getTotal());
        values.put(NOME, orcamento.getNome());

        long id = this.sqLiteDatabase.insert(TABLE_NAME, null, values);

        if(id > -1){
            orcamento.setId((int) id);
            return orcamento;
        }
        return null;
    }

    public boolean editarOrcamentoDB(Orcamento orcamento){
        ContentValues values = new ContentValues();
        values.put(ID_ORCAMENTO, orcamento.getId());
        values.put(ID_CLIENTE, orcamento.getId_cliente());
        //values.put(DATA_ORCAMENTO, orcamento.getData_orcamento());
        values.put(MARGEM, orcamento.getMargem());
        values.put(TOTAL, orcamento.getTotal());
        values.put(NOME, orcamento.getNome());

        return this.sqLiteDatabase.update(TABLE_NAME,values,"id = ?", new String[]{String.valueOf(orcamento.getId())}) == 1 ;
    }

    public boolean removerOrcamentoDB(int idOrcamento){
        return (this.sqLiteDatabase.delete(TABLE_NAME,"id = ?", new String[]{String.valueOf(idOrcamento)})) == 1;
    }


    public void removerAllOrcamentosDB(){
        this.sqLiteDatabase.delete(TABLE_NAME,null,null);
    }

    public ArrayList<Orcamento> getAllOrcamentossBD (){
        ArrayList<Orcamento> orcamentos = new ArrayList<>();
        Cursor cursor = this.sqLiteDatabase.query(TABLE_NAME,new String[]{
                        ID_ORCAMENTO,ID_CLIENTE,DATA_ORCAMENTO,MARGEM,TOTAL,NOME},
                null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                Orcamento orcamento = new Orcamento(cursor.getInt(0),cursor.getInt(1),/*cursor.get(2),*/cursor.getInt(2),cursor.getInt(3),cursor.getString(4));

                orcamentos.add(orcamento);
            }while (cursor.moveToNext());
        }
        return orcamentos;
    }


}
