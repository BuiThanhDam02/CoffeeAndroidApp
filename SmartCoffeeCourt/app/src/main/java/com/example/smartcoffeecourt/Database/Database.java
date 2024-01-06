package com.example.smartcoffeecourt.Database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.smartcoffeecourt.Model.CartItem;
import com.example.smartcoffeecourt.Model.CartGroupItem;
import com.example.smartcoffeecourt.Model.Coffee;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

//public class Database extends SQLiteAssetHelper {
public class Database {

//    static final String DB_NAME = "Order.db";
//    static final int DB_VER = 1;
//    public Database(Context context) {
//        super(context, DB_NAME,null, DB_VER);
//    }

    private DatabaseHelper databaseHelper;

    public Database(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    @SuppressLint("Range")
    public List<CartGroupItem> getCart() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"SupplierID", "Name", "Price", "Quantity", "Discount", "CoffeeId"};
        String sqlTable = "CartItem";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);

        List<CartGroupItem> result = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
                ListIterator<CartGroupItem> it = result.listIterator();
                int flag = 0;

                while (it.hasNext()) {
                    CartGroupItem temp = it.next();
                    if (temp.getSupplierID().equals(c.getInt(c.getColumnIndex("SupplierID")))) {
                        temp.addItem(new CartItem(
                                c.getLong(c.getColumnIndex("CoffeeId")),
                                c.getString(c.getColumnIndex("Name")),
                                c.getString(c.getColumnIndex("Price")),
                                c.getString(c.getColumnIndex("Quantity")),
                                c.getString(c.getColumnIndex("Discount"))
                        ));

                        it.set(temp);
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    List<CartItem> t = new ArrayList<>();
                    t.add(new CartItem(
                            c.getLong(c.getColumnIndex("CoffeeId")),
                            c.getString(c.getColumnIndex("Name")),
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
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM CartItem WHERE CoffeeId = ?",
                new String[]{String.valueOf(cartItem.getCoffeeId())});
        if(c.getCount() > 0) {
            c.moveToFirst();
            @SuppressLint("Range") int currentQuantity = c.getInt(c.getColumnIndex("Quantity"));
            int newQuantity = currentQuantity + Integer.parseInt(cartItem.getQuantity());

            updateCartItemQuantity(db, cartItem, newQuantity);
        } else {
            insertCartItem(db, supplierID, cartItem);
        }

        c.close();
    }

    public void cleanCart () {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String query = "DELETE FROM CartItem";
        db.execSQL(query);
    }
    public void deleteItem(Integer supplierID){
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        @SuppressLint("DefaultLocale") String query = String.format("DELETE FROM CartItem;", supplierID);
        db.execSQL(query);
    }
    public int getCountCart() {
        int count = 0;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String query = "SELECT COUNT(*) FROM CartItem";
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                count = cursor.getInt(0);
            }while (cursor.moveToNext());

        }
        return count;
    }

    public void changeQuantity(CartItem cartItem, int newQuantity) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        updateCartItemQuantity(db, cartItem, newQuantity);

    }

    private void updateCartItemQuantity(SQLiteDatabase db, CartItem cartItem, int newQuantity) {
        String query = "UPDATE CartItem SET Quantity = ? WHERE CoffeeId = ?";
        db.execSQL(query, new Object[]{newQuantity, cartItem.getCoffeeId()});
    }

    private void insertCartItem(SQLiteDatabase db, Integer supplierId, CartItem cartItem) {
        String query = "INSERT INTO CartItem(SupplierID, CoffeeId, Name, Price, Quantity, Discount) VALUES(?, ?, ?, ?, ?, ?)";
        db.execSQL(query, new Object[]{supplierId, cartItem.getCoffeeId(), cartItem.getName(), cartItem.getPrice(), cartItem.getQuantity(), cartItem.getDiscount()});
    }


}
