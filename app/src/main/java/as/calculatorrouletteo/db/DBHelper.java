package as.calculatorrouletteo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DBHelper extends SQLiteOpenHelper {

    // Don`t use, but if will need save data, will be
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "roulette";
    // Table Names
    private static final String TABLE_MAINR = "main_screen";
    // Common column names
    private static final String ID = "id";
    private static final String FIELDR = "field_r";
    private static final String RATER = "rate_r";


    /**
     * Create a database.
     */
    private static final String CREATE_TABLE_MAINR = "CREATE TABLE " + TABLE_MAINR +
            "(" + ID + " INTEGER PRIMARY KEY," + FIELDR + " flag INTEGER" + "," + RATER + " flag INTEGER " + ")";

    private static DBHelper instance;

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * The implementation of the method singleton.
     */
    public static DBHelper getInstance(Context context) {
        if (instance == null) instance = new DBHelper(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MAINR);
    }


    /**
     * Upgrade a database.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_MAINR);
        onCreate(db);
    }

    public void insertQuote(SQLiteDatabase db, JSONArray mainScreen) throws Exception {
        for (int i = 0; i < mainScreen.length(); i++) {
            JSONObject c = mainScreen.getJSONObject(i);
            setNewItem(db, c);
        }
    }


    public Cursor selectData(SQLiteDatabase db) {
        return db.query(TABLE_MAINR, null, null, null, null, null, null, null);
    }

    private void setNewItem(SQLiteDatabase db, JSONObject c){
        String field;
        try {
            field = c.getString("field");
            String rate = c.getString("rate");
            ContentValues cv = new ContentValues();
            cv.put("field_r", field);
            cv.put("rate_r", rate);
            db.insert(TABLE_MAINR, null, cv);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}