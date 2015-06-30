package theoaktroop.icare.Health;

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
public class HealthDatabaseQuery {

    private DbHelper healthDBHelper;
    private SQLiteDatabase mSqLiteDatabase;
    private Context mContext;
    private String[] allColumns={
            DbHelper.COLUMN_HEALTH_ID,
            DbHelper.COLUMN_HEALTH_HEIGHT,
            DbHelper.COLUMN_HEALTH_WEIGHT,
            DbHelper.COLUMN_HEALTH_BLOOD_GROUP,
            DbHelper.COLUMN_HEALTH_BLOOD_PRESSURE,
            DbHelper.COLUMN_HEALTH_BLOOD_SUGAR
    };

    public HealthDatabaseQuery(Context context) {

        this.mContext = context;

        healthDBHelper = new DbHelper(mContext);
        // open the database
        try {
            open();
        } catch (SQLException e) {
            // Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void open() throws SQLException{
        mSqLiteDatabase=healthDBHelper.getWritableDatabase();
    }
    public void close() {
        healthDBHelper.close();
    }

    public HealthClass createNewHealth(String mHeight, String mWeight, String mBloodGroup, String mBloodPressure, String mBloodSugar) {

        ContentValues values = new ContentValues();

        values.put(DbHelper.COLUMN_HEALTH_HEIGHT, mHeight);
        values.put(DbHelper.COLUMN_HEALTH_WEIGHT, mWeight);
        values.put(DbHelper.COLUMN_HEALTH_BLOOD_GROUP, mBloodGroup);
        values.put(DbHelper.COLUMN_HEALTH_BLOOD_PRESSURE, mBloodPressure);
        values.put(DbHelper.COLUMN_HEALTH_BLOOD_SUGAR, mBloodSugar);

        long insertId=mSqLiteDatabase.insert(DbHelper.TABLE_HEALTH,null,values);

        Cursor cursor=mSqLiteDatabase.query(DbHelper.TABLE_HEALTH,allColumns, DbHelper.COLUMN_HEALTH_ID+" = "+insertId,null,null,null,null);
        cursor.moveToFirst();
        HealthClass newHealth = cursorToHealth(cursor);
        cursor.close();

        return newHealth;

    }


    public List<HealthClass> getAllHealths(){
        List<HealthClass>listHealths=new ArrayList<HealthClass>();
        Cursor cursor=mSqLiteDatabase.query(DbHelper.TABLE_HEALTH, allColumns, null, null, null, null, null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                HealthClass health=cursorToHealth(cursor);
                listHealths.add(health);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return listHealths;

    }
public void upDateHealth(long insertId,String mHeight, String mWeight, String mBloodGroup, String mBloodPressure, String mBloodSugar)
    {  open();
        ContentValues values = new ContentValues();

        values.put(DbHelper.COLUMN_HEALTH_HEIGHT, mHeight);
        values.put(DbHelper.COLUMN_HEALTH_WEIGHT, mWeight);
        values.put(DbHelper.COLUMN_HEALTH_BLOOD_GROUP, mBloodGroup);
        values.put(DbHelper.COLUMN_HEALTH_BLOOD_PRESSURE, mBloodPressure);
        values.put(DbHelper.COLUMN_HEALTH_BLOOD_SUGAR, mBloodSugar);



        mSqLiteDatabase.update(DbHelper.TABLE_HEALTH,values, DbHelper.COLUMN_HEALTH_ID+" = "+insertId,null);
      close();
    }
    public HealthClass getAllHealthsById(long id) {
        Cursor cursor = mSqLiteDatabase.query(DbHelper.TABLE_HEALTH, allColumns,
                DbHelper.COLUMN_HEALTH_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        HealthClass health = cursorToHealth(cursor);
        return health;
    }


    protected HealthClass cursorToHealth(Cursor cursor) {
        HealthClass health = new HealthClass();
        health.setId(cursor.getLong(0));
        health.setHeight(cursor.getString(1));
        health.setWight(cursor.getString(2));
        health.setBloodGroup(cursor.getString(3));
        health.setBloodPressure(cursor.getString(4));
        health.setBloodSugar(cursor.getString(5));

        return health;
    }
}
