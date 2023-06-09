package com.example.smartcoffeecourt.Database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.smartcoffeecourt.Model.CartItem;
import com.example.smartcoffeecourt.Model.CartGroupItem;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Database extends SQLiteAssetHelper {
    static final String DB_NAME = "Order.db";
    static final int DB_VER = 1;
    public Database(Context context) {
        super(context, DB_NAME,null, DB_VER);
    }

    @SuppressLint("Range")
    public List<CartGroupItem> getCart() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"SupplierID", "Name", "Price", "Quantity", "Discount"};
        String sqlTable = "CartItem";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);

        List<CartGroupItem> result = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
                ListIterator<CartGroupItem> it = result.listIterator();
                int flag = 0;

                while(it.hasNext()){
                    CartGroupItem temp = it.next();
                    if(temp.getSupplierID().equals(c.getInt(c.getColumnIndex("SupplierID")))){
                        temp.addItem(new CartItem(c.getString(c.getColumnIndex("Name")),
                                c.getString(c.getColumnIndex("Price")),
                                c.getString(c.getColumnIndex("Quantity")),
                                c.getString(c.getColumnIndex("Discount"))
                              ));

                        it.set(temp);
                        flag = 1;
                        break;
                    }
                }
                if(flag == 0){
                    List<CartItem> t = new ArrayList<>();
                    t.add(new CartItem(c.getString(c.getColumnIndex("Name")),
                                    c.getString(c.getColumnIndex("Price")),
                                    c.getString(c.getColumnIndex("Quantity")),
                                    c.getString(c.getColumnIndex("Discount"))
                                    ));

                    result.add(new CartGroupItem(c.getInt(c.getColumnIndex("SupplierID")), t));
                }

            } while (c.moveToNext());
        }
        return result;
    }

    public void addToCart (CartItem cartItem, Integer supplierID) {
        SQLiteDatabase db = getReadableDatabase();
        @SuppressLint("DefaultLocale") String query = String.format("INSERT INTO CartItem(SupplierID, Name, Price, Quantity, Discount) VALUES('%d', '%s', '%s', '%s', '%s');",
                supplierID,
                cartItem.getName(),
                cartItem.getPrice(),
                cartItem.getQuantity(),
                cartItem.getDiscount());
        db.execSQL(query);
    }

    public void cleanCart () {
        SQLiteDatabase db = getReadableDatabase();
        String query = "DELETE FROM CartItem";
        db.execSQL(query);
    }
    public void deleteItem(Integer supplierID){
        SQLiteDatabase db = getReadableDatabase();
        @SuppressLint("DefaultLocale") String query = String.format("DELETE FROM CartItem WHERE SupplierID = %d", supplierID);
        db.execSQL(query);
    }
    public int getCountCart() {
        int count = 0;
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT COUNT(*) FROM CartItem";
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                count = cursor.getInt(0);
            }while (cursor.moveToNext());

        }
        return count;
    }

    public void changeQuantity(CartItem cartItem, int supplierID, int newQuantity) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("UPDATE CartItem SET Quantity = %d WHERE SupplierID = %d AND Name = '%s';",
                newQuantity,
                supplierID,
                cartItem.getName());
                db.execSQL(query);

    }
}
