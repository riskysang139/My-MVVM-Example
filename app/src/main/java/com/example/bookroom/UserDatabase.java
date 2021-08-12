package com.example.bookroom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.bookroom.register.RegisterUser;

import java.util.ArrayList;

public class UserDatabase extends SQLiteOpenHelper {
    private static final String TAG ="UserDatabase";
    private static final String DATABASE_NAME = "USER_DB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE= "USER";
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "username";
    private static final String EMAIL_COLUMN = "email";
    private static final String PASSWORD_COLUMN = "password";
    private static UserDatabase sInstance;
    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + TABLE + " (" +
                    ID_COLUMN + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    NAME_COLUMN + " TEXT NOT NULL," +
                    EMAIL_COLUMN + " TEXT NOT NULL," +
                    PASSWORD_COLUMN + " TEXT NOT NULL" +
                    ")";
    public static UserDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new UserDatabase(context.getApplicationContext());
        }
        return sInstance;
    }
    public UserDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG,"on create");
        try {
            db.execSQL(CREATE_TABLE_SQL);
        }
        catch (Exception e)
        {
            Log.e(TAG,e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG, "onUpgrade: ");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public boolean insertUser(RegisterUser registerUser) {
        /**
         * long insert(String table, String nullColumnHack, ContentValues values)
         * chèn một bản ghi trên các cơ sở dữ liệu. Bảng chỉ định tên bảng,
         * nullColumnHack không cho phép các giá trị hoàn toàn vô giá trị.
         * Nếu số thứ hai là null, android sẽ lưu trữ các giá trị null nếu giá
         trị
         * là trống rỗng. Đối số thứ ba xác định các giá trị được lưu trữ.
         */
        Log.e(TAG, "onInsert: ");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COLUMN, registerUser.getStrUsername());
        values.put(EMAIL_COLUMN, registerUser.getStrEmailAddress());
        values.put(PASSWORD_COLUMN, registerUser.getStrPassword());

        long rowId = db.insert(TABLE, null, values);
        db.close();
        if (rowId != -1)
            return true;
        return false;
    }
    public ArrayList<RegisterUser> getAll() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<RegisterUser> users = new ArrayList<RegisterUser>();
        String sql = "SELECT * FROM " + TABLE;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                users.add(new
                        RegisterUser(cursor.getInt(cursor.getColumnIndex(ID_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(EMAIL_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(PASSWORD_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(NAME_COLUMN))
                ));

            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return users;
    }


}
