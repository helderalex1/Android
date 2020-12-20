package amsi.dei.estg.ipleiria.RightPrice.Modelo.FornecedorInstalador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class FornecedorInstaladorBDHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 495;
    private static final String DB_NAME = "RightPriceDB";
    private static final String TABLE_NAME = "FornecedorInstalador";

    private static final String ID_FORNECEDORINSTALADOR = "id";
    private static final String ID_Fornecedor = "id_fornecedor";
    private static final String ID_INSTALADOR = "id_instalador";

    private  final SQLiteDatabase sqLiteDatabase;

    public FornecedorInstaladorBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createFornecedorInstaladorTable= "CREATE TABLE " + TABLE_NAME + " ( " + ID_FORNECEDORINSTALADOR + " INTEGER PRIMARY KEY, " +
                ID_Fornecedor + " INTEGER NOT NULL, " +
                ID_INSTALADOR + " INTEGER NOT NULL " +
                " );";
        sqLiteDatabase.execSQL(createFornecedorInstaladorTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    public FornecedorInstalador adicionarFornecedorInstaladoroDB(FornecedorInstalador fornecedorInstalador){
        ContentValues values = new ContentValues();
        values.put(ID_FORNECEDORINSTALADOR, fornecedorInstalador.getId());
        values.put(ID_Fornecedor, fornecedorInstalador.getId_fornecedor());
        values.put(ID_INSTALADOR, fornecedorInstalador.getId_instalador());

        long id = this.sqLiteDatabase.insert(TABLE_NAME, null, values);

        if(id > -1){
            fornecedorInstalador.setId((int) id);
            return fornecedorInstalador;
        }
        return null;
    }

    public boolean editarFornecedorInstaladorDB(FornecedorInstalador fornecedorInstalador){
        ContentValues values = new ContentValues();
        values.put(ID_FORNECEDORINSTALADOR, fornecedorInstalador.getId());
        values.put(ID_Fornecedor, fornecedorInstalador.getId_fornecedor());
        values.put(ID_INSTALADOR, fornecedorInstalador.getId_instalador());

        return this.sqLiteDatabase.update(TABLE_NAME,values,"id = ?", new String[]{String.valueOf(fornecedorInstalador.getId())}) == 1 ;
    }

    public boolean removerFornecedorInstaladorDB(int idFornecedorInstalador){
        return (this.sqLiteDatabase.delete(TABLE_NAME,"id = ?", new String[]{String.valueOf(idFornecedorInstalador)})) == 1;
    }


    public void removerAllFornecedorInstaladorsDB(){
        this.sqLiteDatabase.delete(TABLE_NAME,null,null);
    }

    public ArrayList<FornecedorInstalador> getAllFornecedorInstaladorBD (){
        ArrayList<FornecedorInstalador> fornecedorInstaladores = new ArrayList<>();
        Cursor cursor = this.sqLiteDatabase.query(TABLE_NAME,new String[]{
                        ID_FORNECEDORINSTALADOR,ID_Fornecedor,ID_INSTALADOR},
                null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                FornecedorInstalador fornecedorInstalador = new FornecedorInstalador(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2));
                // fornecedorInstalador.setId(cursor.getInt(0));

                fornecedorInstaladores.add(fornecedorInstalador);
            }while (cursor.moveToNext());
        }
        return fornecedorInstaladores;
    }
}
