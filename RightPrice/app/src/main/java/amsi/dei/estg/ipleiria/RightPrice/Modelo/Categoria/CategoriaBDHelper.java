package amsi.dei.estg.ipleiria.RightPrice.Modelo.Categoria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class CategoriaBDHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION=495;
    private static final String DB_NAME = "RightPriceDB";
    private static final String TABLE_NAME = "categoria";

    private static final String ID_CATEGORIA = "id";
    private static final String NOME_CATEGORIA= "username";

    private final SQLiteDatabase sqLiteDatabase;

    public CategoriaBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCategoriaTable= "CREATE TABLE " + TABLE_NAME + " ( " + ID_CATEGORIA + " INTEGER PRIMARY KEY, " +
                NOME_CATEGORIA + " TEXT NOT NULL, " +
                " );";
        sqLiteDatabase.execSQL(createCategoriaTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    public Categoria adicionarCategoriaDB(Categoria categoria){
        ContentValues values = new ContentValues();
        values.put(ID_CATEGORIA, categoria.getId());
        values.put(NOME_CATEGORIA, categoria.getNome_categoria());

        long id = this.sqLiteDatabase.insert(TABLE_NAME, null, values);

       if(id > -1){
            categoria.setId((int) id);
            return categoria;
        }
        return null;
    }

    public boolean editarCategoriaDB(Categoria categoria){
        ContentValues values = new ContentValues();
        values.put(NOME_CATEGORIA, categoria.getNome_categoria());

        return this.sqLiteDatabase.update(TABLE_NAME,values,"id = ?", new String[]{String.valueOf(categoria.getId())}) == 1 ;
    }

    public boolean removerCategoriaDB(int idCategoria){
        return (this.sqLiteDatabase.delete(TABLE_NAME,"id = ?", new String[]{String.valueOf(idCategoria)})) == 1;
    }

    public void removerAllCategoriasDB(){
        this.sqLiteDatabase.delete(TABLE_NAME,null,null);
    }

    public ArrayList<Categoria> getAllCategoriasBD (){
        ArrayList<Categoria> categorias = new ArrayList<>();
        Cursor cursor = this.sqLiteDatabase.query(TABLE_NAME,new String[]{
                        ID_CATEGORIA,NOME_CATEGORIA},
                null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                Categoria categoria = new Categoria(cursor.getInt(0),cursor.getString(1));
                categorias.add(categoria);
            }while (cursor.moveToNext());
        }
        return categorias;
    }
}