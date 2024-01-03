package com.example.sqldb.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqldb.MyDeatilModel;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DatabaseName = "MyDetail";
    private static final String TABLE_CONTACT = "MYDETAIL";

    private static final String KEY_ID = "id";

    private static final String KEY_FNAME = "firstname";
    private static final String KEY_LNAME = "lastname";

    public MyDatabase(Context context) {
        super(context, DatabaseName, null, DATABASE_VERSION);
        context.openOrCreateDatabase(DatabaseName, context.MODE_PRIVATE, null);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACT + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," + KEY_FNAME + " TEXT," +
                KEY_LNAME + " TEXT" + ")";



        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);

        onCreate(sqLiteDatabase);
    }
    public void deleteContact(int contactId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACT, KEY_ID + " = ?", new String[]{String.valueOf(contactId)});
        db.close();
    }
    public void addContact(MyDeatilModel myDeatilModel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, myDeatilModel.get_fname());
        values.put(KEY_LNAME, myDeatilModel.get_lname());

        db.insert(TABLE_CONTACT, null, values);
        db.close();
    }


    public List<MyDeatilModel> getAllDetail() {
        List<MyDeatilModel> myDeatilModelList = new ArrayList<>();
        String selectquery = "SELECT * FROM " + TABLE_CONTACT;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectquery, null);
        if (cursor.moveToFirst()) {
            do {
                MyDeatilModel myDeatilModel = new MyDeatilModel();
                myDeatilModel.set_id(cursor.getInt(0));
                myDeatilModel.set_fname(cursor.getString(1));
                myDeatilModel.set_lname(cursor.getString(2));

                myDeatilModelList.add(myDeatilModel);
            } while (cursor.moveToNext());
        }
        return myDeatilModelList;
    }
}
