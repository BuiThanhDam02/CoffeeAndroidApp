package com.example.smartcoffeecourt.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Cart";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng CartItem với cột mới "CoffeeId"
        String createTableQuery = "CREATE TABLE CartItem (" +
                "ID INTEGER PRIMARY KEY," +
                "SupplierID INTEGER," +
                "Name TEXT," +
                "Price TEXT," +
                "Quantity TEXT," +
                "Discount TEXT," +
                "CoffeeId TEXT)";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng cũ
        db.execSQL("DROP TABLE IF EXISTS CartItem");

        // Tạo lại bảng mới với cột "CoffeeId"
        onCreate(db);
    }
}

