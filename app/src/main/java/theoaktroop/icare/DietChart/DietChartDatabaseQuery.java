package theoaktroop.icare.DietChart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import theoaktroop.icare.DbHelper.DbHelper;

/**
 * Created by Hasan Abdullah on 21-Jun-15.
 */
public class DietChartDatabaseQuery {

    private DbHelper dietChartDBHelper;
    private SQLiteDatabase mSqLiteDatabase;
    private Context mContext;
    private String[] allColumns={
            DbHelper.COLUMN_DIET_ID,
            DbHelper.COLUMN_PROFILE_ID,
            DbHelper.COLUMN_DIET_DAY,
            DbHelper.COLUMN_DIET_MEAL_TYPE,
            DbHelper.COLUMN_DIET_FOOD_MENU,
            DbHelper.COLUMN_DIET_TIME
    };

    public DietChartDatabaseQuery(Context context) {

        this.mContext = context;

        dietChartDBHelper = new DbHelper(mContext);
        // open the database
        try {
            open();
        } catch (SQLException e) {
            // Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void open() throws SQLException{
        mSqLiteDatabase=dietChartDBHelper.getWritableDatabase();
    }
    public void close() {
        dietChartDBHelper.close();
    }

    public DietChartClass createNewDietChart(String profileID, String dayName, String mealType,  String foodMenu, String timeString) {
        ContentValues values = new ContentValues();

        values.put(DbHelper.COLUMN_PROFILE_ID, profileID);
        values.put(DbHelper.COLUMN_DIET_DAY, dayName);
        values.put(DbHelper.COLUMN_DIET_MEAL_TYPE, mealType);
        values.put(DbHelper.COLUMN_DIET_FOOD_MENU, foodMenu);
        values.put(DbHelper.COLUMN_DIET_TIME, timeString);

        long insertId=mSqLiteDatabase.insert(DbHelper.TABLE_DIET,null,values);
        System.out.println("From the diet database "+insertId+" "+dayName);
        Cursor cursor=mSqLiteDatabase.query(DbHelper.TABLE_DIET,allColumns, DbHelper.COLUMN_DIET_ID+" = "+insertId,null,null,null,null);
        cursor.moveToFirst();
        DietChartClass newDietChart = cursorToDiet(cursor);
        cursor.close();

        return newDietChart;

    }

    public void updateDiet(long insertId, String profileID, String dayName, String mealType,  String foodMenu, String timeString)
    {  open();
        ContentValues values = new ContentValues();

        values.put(DbHelper.COLUMN_PROFILE_ID, profileID);
        values.put(DbHelper.COLUMN_DIET_DAY, dayName);
        values.put(DbHelper.COLUMN_DIET_MEAL_TYPE, mealType);
        values.put(DbHelper.COLUMN_DIET_FOOD_MENU, foodMenu);
        values.put(DbHelper.COLUMN_DIET_TIME, timeString);

        mSqLiteDatabase.update(DbHelper.TABLE_DIET, values, DbHelper.COLUMN_DIET_ID + " = " + insertId, null);
        close();
    }

    public void deleteDiet(long insertId){

        open();
        mSqLiteDatabase.delete(DbHelper.TABLE_DIET, DbHelper.COLUMN_DIET_ID + " = " + insertId, null);
        close();
    }


    public List<DietChartClass> getAllDiets(){
        List<DietChartClass>listDiets=new ArrayList<DietChartClass>();
        Cursor cursor=mSqLiteDatabase.query(DbHelper.TABLE_DIET, allColumns, null, null, null, null, null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                DietChartClass dietChart =cursorToDiet(cursor);
                listDiets.add(dietChart);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return listDiets;

    }

    public List<DietChartClass> getAllDietsById(long id) {
        List<DietChartClass>listDiets=new ArrayList<DietChartClass>();
open();
        Cursor cursor = mSqLiteDatabase.query(DbHelper.TABLE_DIET, allColumns,
                DbHelper.COLUMN_PROFILE_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        //Cursor cursor = mSqLiteDatabase.query(DbHelper.TABLE_DIET,allColumns,null,null, null, null, null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                DietChartClass dietChart =cursorToDiet(cursor);
                listDiets.add(dietChart);
                cursor.moveToNext();
            }
            cursor.close();
        }
        close();

        return listDiets;

//        if (cursor != null) {
//            cursor.moveToFirst();
//        }
//
//        DietChartClass dietChart = cursorToDiet(cursor);
//        return dietChart;
    }

    public DietChartClass getDietById(long id) {


        Cursor cursor = mSqLiteDatabase.query(DbHelper.TABLE_DIET, allColumns,
                DbHelper.COLUMN_DIET_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);


        if (cursor != null) {
            cursor.moveToFirst();
        }

        DietChartClass dietChart = cursorToDiet(cursor);
        return dietChart;
    }


    protected DietChartClass cursorToDiet(Cursor cursor) {

        DietChartClass dietChart = new DietChartClass();
        dietChart.setId(cursor.getLong(0));
        dietChart.setProfileID(cursor.getString(1));
        dietChart.setDay(cursor.getString(2));
        dietChart.setMealType(cursor.getString(3));
        dietChart.setFoodMenu(cursor.getString(4));
        dietChart.setDietTime(cursor.getString(5));
        System.out.println("From DietChartDatabase "+cursor.getString(3));



        return dietChart;
    }
}
