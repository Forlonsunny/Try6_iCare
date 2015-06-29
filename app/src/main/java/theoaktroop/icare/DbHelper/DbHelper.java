package theoaktroop.icare.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Mobile App Develop on 24-6-15.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "profile.db";
    public static final int DATABASE_VERSION = 1;

    //For Profile Table
    public static final String TABLE_PROFILE = "profile";
    public static final String COLUMN_PROFILE_ID = "_id";
    public static final String COLUMN_PROFILE_NAME = "profile_name";
    public static final String COLUMN_PROFILE_RELATION = "relation";
    public static final String COLUMN_PROFILE_AGE = "age";
    public static final String COLUMN_PROFILE_PIC = "picture";


    private static final String SQL_CREATE_TABLE_PROFILE= "CREATE TABLE "+ TABLE_PROFILE
            +"("
            + COLUMN_PROFILE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_PROFILE_NAME+" TEXT NOT NULL, "
            +COLUMN_PROFILE_RELATION+" TEXT NOT NULL, "
            +COLUMN_PROFILE_AGE+" TEXT NOT NULL, "
            +COLUMN_PROFILE_PIC+" BLOB "
            +");";
    //

    //For Health Table
    public static final String TABLE_HEALTH="health";
    public static final String COLUMN_HEALTH_ID="_id";
    public static final String COLUMN_HEALTH_HEIGHT="height";
    public static final String COLUMN_HEALTH_WEIGHT="weight";
    public static final String COLUMN_HEALTH_BLOOD_GROUP="blood_group";
    public static final String COLUMN_HEALTH_BLOOD_PRESSURE="blood_pressure";
    public static final String COLUMN_HEALTH_BLOOD_SUGAR="blood_sugar";

    private static final String SQL_CREATE_TABLE_HEALTH= "CREATE TABLE "+TABLE_HEALTH
            +"("
            +COLUMN_HEALTH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_HEALTH_HEIGHT+" TEXT , "
            +COLUMN_HEALTH_WEIGHT+" TEXT , "
            +COLUMN_HEALTH_BLOOD_GROUP+" TEXT , "
            +COLUMN_HEALTH_BLOOD_PRESSURE+" TEXT , "
            +COLUMN_HEALTH_BLOOD_SUGAR+" TEXT  "
            +");";
    //


   //For Diet Table
    public static final String TABLE_DIET="diet_chart";
    public static final String COLUMN_DIET_ID="_id_diet";
    public static final String COLUMN_DIET_MEAL_TYPE="meal_type";
    public static final String COLUMN_DIET_DAY="day";
    public static final String COLUMN_DIET_FOOD_MENU="food_menu";
    public static final String COLUMN_DIET_TIME = "time";

    private static final String SQL_CREATE_TABLE_DIET= "CREATE TABLE "+TABLE_DIET
            +"("
            +COLUMN_DIET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_PROFILE_ID +" INTEGER NOT NULL , "
            +COLUMN_DIET_DAY +" TEXT , "
            +COLUMN_DIET_MEAL_TYPE +" TEXT , "
            +COLUMN_DIET_FOOD_MENU +" TEXT , "
            +COLUMN_DIET_TIME + " TEXT "
            +");";
    //


    //For DocTor table
    public static final String TABLE_DOCTOR="doctor";
    public static final String COLUMN_DOCTOR_ID="_id_doc";
    public static final String COLUMN_DOCTOR_NAME="doctor_name";
    public static final String COLUMN_DOCTOR_ADDRESS="doctor_address";
    public static final String COLUMN_DOCTOR_PHONE="doctor_phone";
    public static final String COLUMN_DOCTOR_TYPE="doctor_type";
    public static final String COLUMN_DOCTOR_APPOINTMENT="doctor_appointment";
    public static final String COLUMN_DOCTOR_APPOINTMENT_TIME="doctor_appointment_time";


    private static final String SQL_CREATE_TABLE_DOCTOR= "CREATE TABLE "+TABLE_DOCTOR
            +"("
            +COLUMN_DOCTOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_PROFILE_ID +" INTEGER NOT NULL , "
            +COLUMN_DOCTOR_NAME +" TEXT , "
            +COLUMN_DOCTOR_TYPE +" TEXT , "
            +COLUMN_DOCTOR_ADDRESS +" TEXT , "
            +COLUMN_DOCTOR_PHONE +" TEXT , "
            +COLUMN_DOCTOR_APPOINTMENT +" TEXT , "
            +COLUMN_DOCTOR_APPOINTMENT_TIME +" TEXT  "
            +");";


//For vaccination  Table
public static final String TABLE_VACCINE="vaccine";
    public static final String COLUMN_VACCINE_ID="_id_vc";
    public static final String COLUMN_VACCINE_NAME="vaccine_name";
    public static final String COLUMN_VACCINE_REASON="vaccine_reason";
    public static final String COLUMN_VACCINE_DATE="vaccine_date";

    private static final String SQL_CREATE_TABLE_VACCINE= "CREATE TABLE "+TABLE_VACCINE
            +"("
            +COLUMN_VACCINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_PROFILE_ID +" INTEGER NOT NULL , "
            +COLUMN_VACCINE_NAME +" TEXT , "
            +COLUMN_VACCINE_REASON +" TEXT , "
            +COLUMN_VACCINE_DATE +" TEXT  "
            +");";



    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_PROFILE);
        db.execSQL(SQL_CREATE_TABLE_HEALTH);
        db.execSQL(SQL_CREATE_TABLE_DIET);
        db.execSQL(SQL_CREATE_TABLE_DOCTOR);
        db.execSQL(SQL_CREATE_TABLE_VACCINE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
