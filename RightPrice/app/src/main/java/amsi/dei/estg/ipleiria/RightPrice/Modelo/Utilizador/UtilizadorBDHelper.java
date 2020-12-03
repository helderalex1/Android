package amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Currency;
import java.util.function.BinaryOperator;

public class UtilizadorBDHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION=495;
    private static final String DB_NAME = "RightPriceDB";
    private static final String TABLE_NAME = "user";

    private static final String ID_USER = "id";
    private static final String USERNAME= "username";
    private static final String NOME="nome";
    private static final String NOME_EMPRESA ="nome_empresa";
    private static final String TELEMOVEL= "telemovel";
    private static final String EMAIL= "email";
    private static final String IMAGEM="imagem";
    private static final String CATEGORIA_ID="categoria_id";
    private static final String STATUS="status";

    private final SQLiteDatabase sqLiteDatabase;

    public UtilizadorBDHelper (Context context){
        super(context,DB_NAME,null, DB_VERSION);
        this.sqLiteDatabase = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUserTable= "CREATE TABLE " + TABLE_NAME+ " ( " + ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERNAME + " TEXT NOT NULL, " +
                NOME + " TEXT, " +
                NOME_EMPRESA + " TEXT, " +
                TELEMOVEL + " INTEGER, " +
                EMAIL + " TEXT, " +
                IMAGEM + " BLOB, " +
                CATEGORIA_ID + " INTEGER, " +
                STATUS + " INTEGER " +
                " );";
        sqLiteDatabase.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    public Utilizador adicionarUserDB (Utilizador utilizador){
        ContentValues values = new ContentValues();
        values.put(USERNAME, utilizador.getUsername());
        values.put(NOME, utilizador.getNome());
        values.put(NOME_EMPRESA,utilizador.getNome_empresa());
        values.put(TELEMOVEL,utilizador.getTelemovel());
        values.put(EMAIL,utilizador.getEmail());
        values.put(IMAGEM, utilizador.getImagem());
        values.put(CATEGORIA_ID, utilizador.getCategoria_id());
        values.put(STATUS,utilizador.getStatus());

        long id = this.sqLiteDatabase.insert(TABLE_NAME, null, values);

        if (id> -1){
            utilizador.setId((int)id);
            return utilizador;
        }
        return null;
    }

    public boolean editarUserDB(Utilizador utilizador){
        ContentValues values = new ContentValues();
        values.put(USERNAME, utilizador.getUsername());
        values.put(NOME, utilizador.getNome());
        values.put(NOME_EMPRESA,utilizador.getNome_empresa());
        values.put(TELEMOVEL,utilizador.getTelemovel());
        values.put(EMAIL,utilizador.getEmail());
        values.put(IMAGEM, utilizador.getImagem());
        values.put(CATEGORIA_ID, utilizador.getCategoria_id());
        values.put(STATUS,utilizador.getStatus());

        return this.sqLiteDatabase.update(TABLE_NAME,values,"id = ?",new String[]{String.valueOf(utilizador.getId())})==1;
    }

    public boolean removerUserDB(int idUser){
        return (this.sqLiteDatabase.delete(TABLE_NAME,"id= ?", new String[]{String.valueOf(idUser)}))==1;
    }

    public ArrayList<Utilizador> getAllUserDB(){
        ArrayList<Utilizador> utilizadores = new ArrayList<>();
        Cursor cursor = this.sqLiteDatabase.query(TABLE_NAME,new String[]{
                ID_USER,USERNAME,NOME,NOME_EMPRESA,TELEMOVEL,EMAIL,IMAGEM,CATEGORIA_ID,STATUS},
                null,null,null,null,null,null);

        if(cursor.moveToFirst()){
            do {
                Utilizador utilizador= new Utilizador(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getInt(8));
                utilizador.setId(cursor.getInt(0));
                utilizadores.add(utilizador);
            }while (cursor.moveToNext());
        }
        return utilizadores;
    }

}
