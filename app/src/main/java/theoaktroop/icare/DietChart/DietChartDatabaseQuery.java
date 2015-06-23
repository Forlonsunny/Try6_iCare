package theoaktroop.icare.DietChart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import theoaktroop.icare.ProfileActivity.Profile;
import theoaktroop.icare.ProfileActivity.ProfileDBHelper;

/**
 * Created by Hasan Abdullah on 21-Jun-15.
 */
public class DietChartDatabaseQuery {

    private DietChartDBHelper dietChartDBHelper;
    private SQLiteDatabase mSqLiteDatabase;
    private Context mContext;
    private String[] allColumns={
            DietChartDBHelper.COLUMN_ID,
            ProfileDBHelper.COLUMN_PROFILE_ID,
            DietChartDBHelper.COLUMN_DIET_DAY,
            DietChartDBHelper.COLUMN_DIET_MEAL_TYPE,
            DietChartDBHelper.COLUMN_DIET_FOOD_MENU
    };

    public DietChartDatabaseQuery(Context context) {

        this.mContext = context;

        dietChartDBHelper = new DietChartDBHelper(mContext);
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

    public DietChartClass createNewDietChart(String profileID, String dayName, String mealType,  String foodMenu) {
        ContentValues values = new ContentValues();

        values.put(ProfileDBHelper.COLUMN_PROFILE_ID, profileID);
        values.put(DietChartDBHelper.COLUMN_DIET_DAY, dayName);
        values.put(DietChartDBHelper.COLUMN_DIET_MEAL_TYPE, mealType);
        values.put(DietChartDBHelper.COLUMN_DIET_FOOD_MENU, foodMenu);

        long insertId=mSqLiteDatabase.insert(DietChartDBHelper.TABLE_DIET,null,values);

        Cursor cursor=mSqLiteDatabase.query(DietChartDBHelper.TABLE_DIET,allColumns,DietChartDBHelper.COLUMN_ID+" = "+insertId,null,null,null,null);
        cursor.moveToFirst();
        DietChartClass newDietChart = cursorToDiet(cursor);
        cursor.close();

        return newDietChart;

    }


    public List<DietChartClass> getAllDiets(){
        List<DietChartClass>listDiets=new ArrayList<DietChartClass>();
        Cursor cursor=mSqLiteDatabase.query(DietChartDBHelper.TABLE_DIET, allColumns, null, null, null, null, null);
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

    public DietChartClass getAllDietsById(long id) {
        Cursor cursor = mSqLiteDatabase.query(DietChartDBHelper.TABLE_DIET, allColumns,
                ProfileDBHelper.COLUMN_PROFILE_ID + " = ?",
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
        System.out.println("From DietChartDatabase "+cursor.getString(3));



        return dietChart;
    }
}
