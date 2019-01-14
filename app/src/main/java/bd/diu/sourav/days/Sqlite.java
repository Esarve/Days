package bd.diu.sourav.days;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a class for handling all Database related shits
 */
public class Sqlite extends SQLiteOpenHelper {

    // General Shits about the database and Table
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "daysDB";
    private static final String TABLE = "def_table";
    private static final String COL_DATE = "date";
    private static final String COL_TIME = "time";
    private static final String COL_TEXT = "text";
    private static final String COL_ID = "id";
    // Constructor. u already know that.

    public Sqlite(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        initDB(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Creates the table

    private void initDB(SQLiteDatabase db){
        String createTable = "CREATE TABLE IF NOT EXISTS " +  TABLE + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL_DATE + " VARCHAR,"+
                COL_TEXT + " TEXT,"+
                COL_TIME + " VARCHAR"+
                ")";
        db.execSQL(createTable);
        setLog("Database Created");
    }

    // Method for adding data to the database. Havent tested yet  ¯\_(ツ)_/¯

    protected void addData(String date, String text, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        setLog("Ready to add values");
        values.put(COL_DATE, date);
        values.put(COL_TEXT, text);
        values.put(COL_TIME, time);
        db.insert(TABLE, null, values);
        db.close();
        setLog("values Added and shit closed");

    }

    // Fetches data. Should work. Kanged from my other project

    public List<Days> getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " +TABLE+ ";";

        List<Days> resultlist = new ArrayList<>();
        Cursor cursor = db.rawQuery(query,null);

        int dateIndex = cursor.getColumnIndex(COL_DATE);
        int textIndex = cursor.getColumnIndex(COL_TEXT);
        int timeIndex = cursor.getColumnIndex(COL_TIME);
        int idIndex = cursor.getColumnIndex(COL_ID);

        if (cursor.moveToFirst()){
            do {
                Days newContacts = new Days(cursor.getString(dateIndex),cursor.getString(textIndex),cursor.getString(timeIndex),cursor.getInt(idIndex));
                resultlist.add(newContacts);
            }while (cursor.moveToNext());
            setLog("Finised may be?");
        }

        db.close();
        setLog(Integer.toString(resultlist.size()));
        setLog("Result delivered");
        return resultlist;
    }

    public void remove(int pos){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.i("SwipeTest","Recived ID: " + pos);
        db.delete(TABLE, COL_ID + "=" + Integer.toString(pos),null);
        db.close();
    }


    // For logging and shits

    private void setLog(String message){
        Log.i("Database",message);
    }




}
