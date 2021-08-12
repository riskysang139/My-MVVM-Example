package com.example.bookroom.history;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.bookroom.Common.Social;

import java.util.ArrayList;

public class HistoryDatabase extends SQLiteOpenHelper {
    private static final String TAG ="HistoryDatabase";
    private static final String DATABASE_NAME = "HISTORY_DB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE= "HISTORY";
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String ICON_COLUMN = "icon";
    private static final String LINK_COLUMN = "link";
    private static final String FLAG_COLUMN = "flag";
    private static HistoryDatabase sInstance;
    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + TABLE + " (" +
                    ID_COLUMN + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    NAME_COLUMN + " TEXT NOT NULL," +
                    ICON_COLUMN + " TEXT NOT NULL," +
                    LINK_COLUMN + " TEXT NOT NULL," +
                    FLAG_COLUMN + " INTEGER NOT NULL" +
                    ")";
    public static HistoryDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new HistoryDatabase(context.getApplicationContext());
        }
        return sInstance;
    }
    public HistoryDatabase(@Nullable Context context) {
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

    public boolean insertSocial(Social social) {
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
        values.put(NAME_COLUMN, social.getName());
        values.put(ICON_COLUMN, social.getIcon());
        values.put(LINK_COLUMN, social.getLink());
        values.put(FLAG_COLUMN, social.getFlag());
        long rowId = db.insert(TABLE, null, values);
        db.close();
        if (rowId != -1)
            return true;
        return false;
    }
    public ArrayList<Social> getAll() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Social> socials = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                socials.add(new
                        Social(cursor.getInt(cursor.getColumnIndex(ID_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(NAME_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(ICON_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(LINK_COLUMN)),
                        cursor.getInt(cursor.getColumnIndex(FLAG_COLUMN))

                ));

            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return socials;
    }
}
