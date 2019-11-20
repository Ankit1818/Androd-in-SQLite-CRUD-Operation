package com.example.tops.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper
{
    private static final int DB_VERSION=1;
    private static final String DB_NAME="user.db";
    private static final String TABLE_NAME="abc";
    private static final String ID="id";
    private static final String NAME="name";
    private static final String PHN="phn";

    public DBHandler(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query= "create table " + TABLE_NAME + " (" +ID +
                " integer primary key autoincrement, " +NAME+" text, "+PHN+ " text )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String upquery= "drop table if exist" +TABLE_NAME;
        db.execSQL(upquery);
        onCreate(db);
    }

    public long insert(User user)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(NAME,user.getName());
        values.put(PHN,user.getPhn());
        long id=db.insert(TABLE_NAME,ID,values);
        db.close();
        return id;
    }
    public List<User>view()
    {
        ArrayList<User> arrayList =new ArrayList();
        SQLiteDatabase db=getReadableDatabase();
        String col[]={ID,NAME,PHN};


        Cursor cursor=db.query(TABLE_NAME,col,null,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String phn=cursor.getString(2);

            User user=new User();
            user.setId(id);
            user.setName(name);
            user.setPhn(phn);

            arrayList.add(user);
        }

        return arrayList;
    }

    public void delete(int id) {

        SQLiteDatabase db=getWritableDatabase();
        String deletequry=ID +"="+id;
        db.delete(TABLE_NAME,deletequry,null);

    }

    public void update(User user) {

        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(NAME,user.getName());
        values.put(PHN,user.getPhn());
        String whereqry=ID + "="+ user.getId();
        db.update(TABLE_NAME,values,whereqry,null);
    }
}
