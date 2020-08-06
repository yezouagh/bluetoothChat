package com.smp.bluetoothchat;

/**
 * Created by Younes on 19-Mar-17.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;

public class MessageSqlite extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "MessagesDB",
            TABLE_Messages = "Messages", Me_Tag="Me", Msg_Tag="Msg",Recu_Tag="Recu",Adresse_Tag="Adresse",
            CREATE_Messages_TABLE = "CREATE TABLE "+ TABLE_Messages + " ( " +
                    Recu_Tag+" INTEGER PRIMARY KEY,"+Msg_Tag+" TEXT, " +Me_Tag+" INTEGER," +Adresse_Tag+" TEXT)";
    private Context c;

    public MessageSqlite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);//the query for creating the Messages DATABASE for the first time
        c=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_Messages_TABLE);//the query for creating the Messages TABLE
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Messages);
        this.onCreate(db);
    }

    public void addMessage(cMessage Message){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Recu_Tag, Message.getRecu());
        values.put(Msg_Tag, Message.getMsg());
        values.put(Me_Tag, Message.isMe());
        values.put(Adresse_Tag, Message.getAdresse());
        db.insert(TABLE_Messages, null, values);
        db.close();
    }
    public void updateMessage(cMessage Message){
        long date=Message.getRecu();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Recu_Tag, Message.getRecu());
        values.put(Msg_Tag, Message.getMsg());
        values.put(Me_Tag, Message.isMe());
        values.put(Adresse_Tag, Message.getAdresse());
        db.update(TABLE_Messages, values,Recu_Tag+" = ? and "+Adresse_Tag+" = ?", new String[]{String.valueOf(date),Message.getAdresse()});
        db.close();
    }
    public cMessage getMessage(long date,String adresse){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_Messages,
                new String[]{Recu_Tag, Msg_Tag, Me_Tag,Adresse_Tag},
                Recu_Tag+" = ? and "+Adresse_Tag+" = ?", new String[]{String.valueOf(date),adresse},
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        else
            return null;
        return new cMessage(cursor.getLong(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3));
    }
    public ArrayList<cMessage> getMessages(String adresse) { // get Messages concerning a specific address
        ArrayList<cMessage> CaracterizedMessages = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_Messages,
                new String[]{Recu_Tag, Msg_Tag, Me_Tag,Adresse_Tag},
                Adresse_Tag+" = ?", new String[]{ adresse },
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst())
            do{
                CaracterizedMessages.add(new cMessage(cursor.getLong(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3)));
            }while (cursor.moveToNext());
        db.close();
        return CaracterizedMessages;
    }
}

