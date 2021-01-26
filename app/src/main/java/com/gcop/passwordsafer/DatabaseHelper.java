package com.gcop.passwordsafer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    static  String name = "passwordSafer";
    static int version=1;

    String createTaleUser="CREATE TABLE if not exists`user` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`title`\tTEXT,\n" +
           "\t`image`\tBLOB,\n" +
            "\t`userId`\tTEXT,\n" +
            "\t`password`\tTEXT,\n" +
            "\t`websites`\tTEXT\n" +
            ")";



    public DatabaseHelper(Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createTaleUser);
    }

    public void insertUser(ContentValues contentValues){
        getWritableDatabase().insert("user", "", contentValues);
        Log.i("user", "user inserted");

    }

    public void updateUser(String id, ContentValues contentValues){
//        getWritableDatabase().update("user", contentValues, "id="+id, null);
        getWritableDatabase().update("user", contentValues, "id=?", new String[]{id});

    }

    public void deleteUser(String id){
        getWritableDatabase().delete("user", "id="+id, null);
    }

    public ArrayList<UserInfo> getUserList(){
        String sql = "select * from user";
        Cursor cursor= getReadableDatabase().rawQuery(sql,null);
        ArrayList<UserInfo>list= new ArrayList<>();
        while (cursor.moveToNext()){
            UserInfo info= new UserInfo();
            info.id= cursor.getString(cursor.getColumnIndex("id"));
            info.title= cursor.getString(cursor.getColumnIndex("title"));
            info.userId=cursor.getString(cursor.getColumnIndex("userId"));
            info.password=cursor.getString(cursor.getColumnIndex("password"));
            info.websites=cursor.getString(cursor.getColumnIndex("websites"));
            info.image=cursor.getBlob(cursor.getColumnIndex("image"));

            list.add(info);
        }
        cursor.close();
        return list;
    }



   /* public ArrayList<String> getUserTitleList(){
        String sql = "select * from user";
        Cursor cursor= getReadableDatabase().rawQuery(sql,null);
        ArrayList<String>list= new ArrayList<>();
        while (cursor.moveToNext()){


            list.add(cursor.getString(cursor.getColumnIndex("title")));
        }
        cursor.close();
        return list;
    }*/

    public UserInfo getUserInfo(String id){
        String sql = "select * from user where id ="+id;
        Cursor cursor= getReadableDatabase().rawQuery(sql,null);
        UserInfo info= new UserInfo();
        while (cursor.moveToNext()){
           info.id= cursor.getString(cursor.getColumnIndex("id"));
            info.title= cursor.getString(cursor.getColumnIndex("title"));
            info.userId=cursor.getString(cursor.getColumnIndex("userId"));
            info.password=cursor.getString(cursor.getColumnIndex("password"));
            info.websites=cursor.getString(cursor.getColumnIndex("websites"));
            info.image=cursor.getBlob(cursor.getColumnIndex("image"));

        }
        cursor.close();
        return info;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
