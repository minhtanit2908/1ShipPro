package com.android.test.a1shippro.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.android.test.a1shippro.Model.Login.LoginState;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by NamNgo on 25/05/2016.
 */
public class Database extends SQLiteOpenHelper {

    SQLiteDatabase db;
    private static String DB_PATH = "/data/data/com.s4you.FlyBeau/databases/";
    private static String DB_NAME = "db_FlyBeau";
    private final Context myContext;
    String tag = "loi";

    public Database(Context context) {
        super(context, DB_NAME, null, 1);
        // TODO Auto-generated constructor stub
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    // Tao data trong asset --->>>
    public void openDataBase() throws SQLException {

        // Open the database
        String myPath = DB_PATH + DB_NAME;
        db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    public synchronized void close() {

        if (db != null)
            db.close();

        super.close();
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            // database chua ton tai
        }

        if (checkDB != null)
            checkDB.close();

        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {

        // mo db trong thu muc assets nhu mot input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // duong dan den db se tao
        String outFileName = DB_PATH + DB_NAME;

        // mo db giong nhu mot output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // truyen du lieu tu inputfile sang outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Dong luon
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    // ----------Kiểm tra có data chưa, chưa có thì copy file lưu trong asset để
    // tạo
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase(); // kiem tra db

        if (dbExist) {
            // khong lam gi ca, database da co roi
            Log.d(tag, "Co data");
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase(); // chep du lieu
                Log.d(tag, "createDataBase(): copyDataBase() Thành công");
            } catch (IOException e) {
                throw new Error("createDataBase(): Error copying database");
            }
        }
    }

    public void DeleteDataBase() throws IOException {
        boolean dbExist = checkDataBase(); // kiem tra db

        if (dbExist) {
            this.getReadableDatabase();
            try {
                copyDataBase(); // chep du lieu
                Log.d(tag, "DeleteDataBase(): copyDataBase() Thành công");
            } catch (IOException e) {
                throw new Error("DeleteDataBase(): Error copying database");
            }
        }
    }

    // ---------------------------------------------------------------------------------------------
    public void insertOrUpdate_Object(Object object, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Cursor c = null;
        try {
            switch (tableName) {
                case "tbLogin":
                    LoginState loginState = (LoginState) object;
                    values.put("username", loginState.getUsername());
                    values.put("password", loginState.getPassword());
                    values.put("state", loginState.getState());
                    c = db.query("tbLogin", null, "username=?", new String[]{loginState.getUsername()}, null, null, null);

                    if (c.moveToFirst()) {
                        // Log.d(tag, "UPDATE");
                        String _id = c.getString(0);
                        db.update("tbLogin", values, "_id=?", new String[] { _id });
                    } else {
                        // Log.d(tag, "INSERT");
                        db.insert("tbLogin", null, values);
                    }
                    c.close();
                    break;

//                case "class_DMNhanVien":
//                    class_DMNhanVien themSua2 = (class_DMNhanVien) object;
//                    values.put("IdNhanVien", themSua2.IdNhanVien);
//                    values.put("IdKho", themSua2.IdKho);
//                    values.put("IdCongTy", themSua2.IdCongTy);
//                    values.put("MaNhanVien", themSua2.MaNhanVien);
//                    values.put("TenNhanVien", themSua2.TenNhanVien);
//                    c = db.query("tbDMNhanVien", null, null, null, null, null, null);
//
//                    if (c.moveToFirst()) {
//                        String _id = c.getString(0);
//                        db.update("tbDMNhanVien", values, "_id=?", new String[] { _id });
//                    } else
//                        db.insert("tbDMNhanVien", null, values);
//                    c.close();
//                    break;

//                case "class_DMKhachHang":
//                    class_DMKhachHang themSua3 = (class_DMKhachHang) object;
//                    values.put("IdCongTy", themSua3.IdCongTy + "");
//
//                    db.insert("tbDMKhachHang", null, values);
//                    break;
            }
        } catch (Exception e) {
            Log.d(tag, "ERROR: Database insertOrUpdate_Object() " + tableName + " - " + e);
        }
    }

    // INSERT 2: insert list
    public void InsertOrUpdate_List(Object object, String className) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
//            switch (className) {
//
//                case "class_GoogleToaDoKhachHang":
//                    try {
//                        db.beginTransaction();
//                        for (class_GoogleToaDoKhachHang g : Login_Activity.lstGoogleToaDoKH_Login) {
//                            if (g != null) {
//                                InsertOrUpdate_Object(g, "class_GoogleToaDoKhachHang");
//                            }
//
//                        }
//                        db.setTransactionSuccessful();
//                        Log.d(tag, "InsertOrUpdate_List " + className + " - Xong");
//                    } catch (SQLException e) {
//                        Log.d(tag, "ERROR: InsertOrUpdate_List " + className + "-" + e);
//                    } finally {
//                        db.endTransaction();
//                    }
//
//                    break;
//
//            }
        } catch (Exception e) {
            Log.d(tag, "ERROR: Database InsertOrUpdate_List() " + className + " - " + e);
        }

    }

    // END insert 2

    //Update all row of column
    public void updateAllRowOfColumn(String tableName, String column, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        try {
            switch (tableName) {
                case "tbLogin":
                    String where = "UPDATE "+ tableName + " SET " + column + " = " + value;
                    c = db.rawQuery(where, null);
                    c.moveToFirst();
                    c.close();
                    break;

            }
        } catch (Exception e) {
            Log.d(tag, "ERROR: Database InsertOrUpdate() " + tableName + " - " + e);
        }
    }
    //END Update all row of column

    // Kiểm tra tồn tại
    public Boolean KiemTraTonTai(String className) {
        Boolean kq = false;
        Cursor c;
        switch (className) {
            case "class_DMKhachHang":
                try {
                    c = GetDataFromTable("tbDMKhachHang", new String[] { "count(*)" }, null, null, null, null, null);
                    c.moveToFirst();
                    Log.d(tag, "tbDMKhachHang: " + c.getString(0));
                    if (!c.getString(0).equals("0")) {

                        c.close();
                        return true;
                    }
                } catch (Exception e) {
                    Log.d(tag, "KiemTraTonTai tbDMKhachHang" + e);
                }
                break;


        }

        return kq;
    }

    // Lấy tất cả dữ liệu 1 bảng từ sql trả về cursor
    public Cursor GetAllDataFromTable(String tableName) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        try {
            c = db.query(tableName, null, null, null, null, null, null);
        } catch (Exception e) {
            Log.d(tag, "ERROR: GetAllDataFromSQL(" + tableName + "): " + e);
        }
        return c;
    }

    // Lấy dữ liệu từ sql trả về cursor
    public Cursor GetDataFromTable(String tableName, String arrColumn[], String whereClause,
                                   String arrGiaTriWhereClause[], String groupBy, String having, String orderBy) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        try {
            c = db.query(tableName, // Tên bảng
                    arrColumn, // Cột lấy
                    whereClause, // Mệnh đề where
                    arrGiaTriWhereClause, groupBy, having, orderBy);
        } catch (Exception e) {
            Log.d(tag, "ERROR: GetDataFromSQL(" + tableName + "): " + e);
        }
        return c;
//		Cursor c = db.GetDataFromTable("tbDMKho", new String[] { "TenKho" }, "IdKho=?",
//				new String[] { idkho }, null, null, null);
    }

    // Xóa dữ liệu một bảng
    public void DeleteDataOfTable(String tableName, String columnName, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (columnName != null && value != null)
                db.delete(tableName, columnName + "=?", new String[] { String.valueOf(value) });
            else {
                db.delete(tableName, null, null);
                db.delete("sqlite_sequence", "name=?", new String[] { tableName });
            }
        } catch (Exception e) {
            Log.d(tag, "ERROR: DeleteDataOfTable" + e);
        }
    }

}// END
