package amsi.dei.estg.ipleiria.RightPrice.Modelo.Cliente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ClienteBDHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 495;
    private static final String DB_NAME = "RightPriceDB";
    private static final String TABLE_NAME = "Cliente";

    private static final String ID_CLIENTE = "id";
    private static final String USER_ID = "user_id";
    private static final String NOME = "nome";
    private static final String TELEMOVEL = "telemovel";
    private static final String NIF = "nif";
    private static final String EMAIL = "email";

    private final SQLiteDatabase sqLiteDatabase;

    public ClienteBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.sqLiteDatabase = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createClienteTable = "CREATE TABLE " + TABLE_NAME + " ( " + ID_CLIENTE + " INTEGER PRIMARY KEY, " +
                USER_ID + " INTEGER NOT NULL, " +
                NOME + " TEXT NOT NULL, " +
                TELEMOVEL + " INTEGER NOT NULL, " +
                NIF + " INTEGER NOT NULL, " +
                EMAIL + " TEXT NOT NULL" +
                " );";
        sqLiteDatabase.execSQL(createClienteTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    public Cliente adicionarClienteDB(Cliente cliente) {
        ContentValues values = new ContentValues();
        values.put(ID_CLIENTE, cliente.getId());
        values.put(USER_ID, cliente.getUser_id());
        values.put(NOME, cliente.getNome());
        values.put(TELEMOVEL, cliente.getTelemovel());
        values.put(NIF, cliente.getNif());
        values.put(EMAIL, cliente.getEmail());

        long id = this.sqLiteDatabase.insert(TABLE_NAME, null, values);

        if(id > -1){
            cliente.setId((int) id);
            return cliente;
        }
        return null;
    }

    public boolean editarClienteDB(Cliente cliente) {
        ContentValues values = new ContentValues();
        values.put(ID_CLIENTE, cliente.getId());
        values.put(USER_ID, cliente.getUser_id());
        values.put(NOME, cliente.getNome());
        values.put(TELEMOVEL, cliente.getTelemovel());
        values.put(NIF, cliente.getNif());
        values.put(EMAIL, cliente.getEmail());

        return this.sqLiteDatabase.update(TABLE_NAME, values, "id = ?", new String[]{String.valueOf(cliente.getId())}) == 1;
    }

    public boolean removerClienteDB(int idCliente) {
        return (this.sqLiteDatabase.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(idCliente)})) == 1;
    }


    public void removerAllClientesDB() {
        this.sqLiteDatabase.delete(TABLE_NAME, null, null);
    }

    public ArrayList<Cliente> getAllClientesBD() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        Cursor cursor = this.sqLiteDatabase.query(TABLE_NAME, new String[]{
                        ID_CLIENTE, USER_ID, NOME, TELEMOVEL, NIF, EMAIL},
                null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Cliente cliente = new Cliente(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5));

                // cliente.setId(cursor.getInt(0));

                clientes.add(cliente);
            } while (cursor.moveToNext());
        }
        return clientes;
    }
}
