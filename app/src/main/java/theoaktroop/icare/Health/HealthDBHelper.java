package theoaktroop.icare.Health;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import theoaktroop.icare.ProfileActivity.ProfileDBHelper;


public class HealthDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "profile.db";
    public static final int DATABASE_VERSION = 1;


    public static final String TABLE_HEALTH="health";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_HEALTH_HEIGHT="height";
    public static final String COLUMN_HEALTH_WEIGHT="weight";
    public static final String COLUMN_HEALTH_BLOOD_GROUP="blood_group";
    public static final String COLUMN_HEALTH_BLOOD_PRESSURE="blood_pressure";
    public static final String COLUMN_HEALTH_BLOOD_SUGAR="blood_sugar";

//    private static final String SQL_CREATE_TABLE_PROFILE= "CREATE TABLE "+ TABLE_PROFILE
//            +"("
//            + COLUMN_PROFILE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//            +COLUMN_PROFILE_NAME+" TEXT NOT NULL, "
//            +COLUMN_PROFILE_RELATION+" TEXT NOT NULL, "
//            +COLUMN_PROFILE_AGE+" TEXT NOT NULL "
//            +");";
    private static final String SQL_CREATE_TABLE_HEALTH= "CREATE TABLE "+TABLE_HEALTH
            +"("
            +COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_HEALTH_HEIGHT+" TEXT NOT NULL, "
            +COLUMN_HEALTH_WEIGHT+" TEXT NOT NULL, "
            +COLUMN_HEALTH_BLOOD_GROUP+" TEXT NOT NULL, "
            +COLUMN_HEALTH_BLOOD_PRESSURE+" TEXT NOT NULL, "
            +COLUMN_HEALTH_BLOOD_SUGAR+" TEXT NOT NULL "
            +");";


    public HealthDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL(SQL_CREATE_TABLE_HEALTH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
