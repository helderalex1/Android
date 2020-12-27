package amsi.dei.estg.ipleiria.RightPrice.Modelo.Funcao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class FuncaoBDHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 495;
    private static final String DB_NAME = "RightPriceDB";
    private static final String TABLE_NAME = "Funcao";

    private static final String ID_FUNCAO = "id";
    private static final String FUNCAO = "funcao";
    private static final String USER_ID = "user_id";
    private  final SQLiteDatabase sqLiteDatabase;

    public FuncaoBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createFuncaoTable= "CREATE TABLE " + TABLE_NAME + " ( " + ID_FUNCAO + " INTEGER PRIMARY KEY, " +
                FUNCAO + " TEXT NOT NULL, " +
                USER_ID + " INTEGER NOT NULL " +
                " );";
        sqLiteDatabase.execSQL(createFuncaoTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    public Funcao adicionarFuncaoDB(Funcao funcao){
        ContentValues values = new ContentValues();
        values.put(ID_FUNCAO, funcao.getId());
        values.put(FUNCAO, funcao.getFuncao());
        values.put(USER_ID, funcao.getUser_id());

        long id = this.sqLiteDatabase.insert(TABLE_NAME, null, values);

        if(id > -1){
            funcao.setId((int) id);
            return funcao;
        }
        return null;
    }

    public boolean editarFuncaoDB(Funcao funcao){
        ContentValues values = new ContentValues();
        values.put(ID_FUNCAO, funcao.getId());
        values.put(FUNCAO, funcao.getFuncao());
        values.put(USER_ID, funcao.getUser_id());

        return this.sqLiteDatabase.update(TABLE_NAME,values,"id = ?", new String[]{String.valueOf(funcao.getId())}) == 1 ;
    }

    public boolean removerFuncaoDB(int idFuncao){
        return (this.sqLiteDatabase.delete(TABLE_NAME,"id = ?", new String[]{String.valueOf(idFuncao)})) == 1;
    }


    public void removerAllFuncoesDB(){
        this.sqLiteDatabase.delete(TABLE_NAME,null,null);
    }

    public ArrayList<Funcao> getAllFuncoesBD (){
        ArrayList<Funcao> funcoes = new ArrayList<>();
        Cursor cursor = this.sqLiteDatabase.query(TABLE_NAME,new String[]{
                        ID_FUNCAO,FUNCAO,USER_ID},
                null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                Funcao funcao = new Funcao(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));

                // funcao.setId(cursor.getInt(0));

                funcoes.add(funcao);
            }while (cursor.moveToNext());
        }
        return funcoes;
    }
}
