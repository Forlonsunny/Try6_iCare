package theoaktroop.icare.DietChart;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import theoaktroop.icare.ProfileActivity.ProfileDBHelper;


public class DietChartDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "diet.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_DIET="diet_chart";
    public static final String COLUMN_ID="_id_diet";
    public static final String COLUMN_DIET_MEAL_TYPE="meal_type";
    public static final String COLUMN_DIET_DAY="day";
    public static final String COLUMN_DIET_FOOD_MENU="food_menu";


    private static final String SQL_CREATE_TABLE_DIET= "CREATE TABLE "+TABLE_DIET
            +"("
            +COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ProfileDBHelper.COLUMN_PROFILE_ID +" TEXT NOT NULL, "
            +COLUMN_DIET_DAY +" TEXT NOT NULL, "
            +COLUMN_DIET_MEAL_TYPE +" TEXT NOT NULL, "
            +COLUMN_DIET_FOOD_MENU +" TEXT NOT NULL "
            +");";


    public DietChartDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_DIET);
        System.out.println("From DietChartDbHelper ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
