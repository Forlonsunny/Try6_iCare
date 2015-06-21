package theoaktroop.icare.ProfileActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ProfileDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "profile.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_PROFILE="profile";
    public static final String COLUMN_PROFILE_ID ="_id";
    public static final String COLUMN_PROFILE_NAME="profile_name";
    public static final String COLUMN_PROFILE_RELATION="relation";
    public static final String COLUMN_PROFILE_AGE="age";


    private static final String SQL_CREATE_TABLE_PROFILE= "CREATE TABLE "+ TABLE_PROFILE
            +"("
            + COLUMN_PROFILE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_PROFILE_NAME+" TEXT NOT NULL, "
            +COLUMN_PROFILE_RELATION+" TEXT NOT NULL, "
            +COLUMN_PROFILE_AGE+" TEXT NOT NULL "
            +");";

//
    public ProfileDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_PROFILE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
