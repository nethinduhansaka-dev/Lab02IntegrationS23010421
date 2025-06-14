package com.s23010421.vpnhansaka;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Information
    private static final String DATABASE_NAME = "StudentDB.db";
    private static final int DATABASE_VERSION = 1;

    // Table Information
    private static final String TABLE_NAME = "students_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "STUDENT_ID";
    private static final String COL_3 = "USERNAME";
    private static final String COL_4 = "PASSWORD";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table with SQL query
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_2 + " TEXT, " +
                COL_3 + " TEXT, " +
                COL_4 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop table if exists and recreate
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert data method - only method needed as per requirements
    public boolean insertData(String studentId, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, studentId);
        contentValues.put(COL_3, username);
        contentValues.put(COL_4, password);

        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        // If result is -1, insertion failed
        return result != -1;
    }
}