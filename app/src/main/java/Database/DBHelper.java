package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.usb.UsbRequest;

import java.util.ArrayList;

import Model.Users;

public class DBHelper extends SQLiteOpenHelper {
    public static final String Database = "user.db";

    public DBHelper(Context context){
        super(context,Database,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlEntry = "create table " + UserMaster.User.TABLE + " (" +
                UserMaster.User._ID + " integer primary key," +
                UserMaster.User.COLUsername + " text," +
                UserMaster.User.COLPassword  + " text);";

        db.execSQL(sqlEntry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean add(String username,String password){
       SQLiteDatabase db = getWritableDatabase();

       ContentValues values =  new ContentValues();

       values.put(UserMaster.User.COLUsername,username);
       values.put(UserMaster.User.COLPassword,password);

       long result = db.insert(UserMaster.User.TABLE,null,values);

       if(result > 0){
           return true;
       }else{
           return false;
       }
    }

    public boolean updateInfo(String username,String password){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues  values = new ContentValues();
        values.put(UserMaster.User.COLPassword,password);

        String selection = UserMaster.User.COLUsername + " Like ?";
        String[] selectionArgs = {username};

        int count = db.update(UserMaster.User.TABLE,
                values,
                selection,
                selectionArgs
        );
        if(count > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean deleteInfo(String username){
        SQLiteDatabase db = getReadableDatabase();

        String selection = UserMaster.User.COLUsername + " like ?";

        String[] selectionArgs = {username};

        long number = db.delete(UserMaster.User.TABLE,selection,selectionArgs);

        if(number > 0){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Users> readAllInfor(){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {UserMaster.User._ID,UserMaster.User.COLUsername,UserMaster.User.COLPassword};

        String sortOrder = UserMaster.User.COLUsername;
        Cursor values = db.query(UserMaster.User.TABLE,projection,null,null,null,null,sortOrder);

        ArrayList<Users> users = new ArrayList<Users>();

        while(values.moveToNext()){
            String userName  = values.getString(values.getColumnIndexOrThrow(UserMaster.User.COLUsername));
            String password = values.getString(values.getColumnIndexOrThrow(UserMaster.User.COLPassword));

            users.add(new Users(userName,password));
        }
        return users;

    }
}
