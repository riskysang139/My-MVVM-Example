package com.example.bookroom.home;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.bookroom.Common.Social;

import java.util.ArrayList;

public class SocialDatabase extends SQLiteOpenHelper {
    private static final String TAG ="SocialDatabase";
    private static final String DATABASE_NAME = "SOCIAL_DB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE= "SOCIAL";
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String ICON_COLUMN = "icon";
    private static final String LINK_COLUMN = "link";
    private static final String FLAG_COLUMN = "flag";
    private static SocialDatabase sInstance;
    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + TABLE + " (" +
                    ID_COLUMN + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    NAME_COLUMN + " TEXT NOT NULL," +
                    ICON_COLUMN + " TEXT NOT NULL," +
                    LINK_COLUMN + " TEXT NOT NULL," +
                    FLAG_COLUMN + " INTEGER NOT NULL" +
                    ")";
    public static SocialDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SocialDatabase(context.getApplicationContext());
        }
        return sInstance;
    }
    public SocialDatabase(@Nullable Context context) {
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
         * ch??n m???t b???n ghi tr??n c??c c?? s??? d??? li???u. B???ng ch??? ?????nh t??n b???ng,
         * nullColumnHack kh??ng cho ph??p c??c gi?? tr??? ho??n to??n v?? gi?? tr???.
         * N???u s??? th??? hai l?? null, android s??? l??u tr??? c??c gi?? tr??? null n???u gi??
         tr???
         * l?? tr???ng r???ng. ?????i s??? th??? ba x??c ?????nh c??c gi?? tr??? ???????c l??u tr???.
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
    public int updateData(Social social,int type) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FLAG_COLUMN,type);
        int rowEffect = db.update(TABLE, values, ID_COLUMN + " = ?",
                new String[]{String.valueOf(social.getID())});
        db.close();
        return rowEffect;
    }
    public ArrayList<Social> getWithID() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Social> socials = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE +" WHERE "+ FLAG_COLUMN +" = 1 ";
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
