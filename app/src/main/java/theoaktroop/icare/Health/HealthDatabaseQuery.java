package theoaktroop.icare.Health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hasan Abdullah on 21-Jun-15.
 */
public class HealthDatabaseQuery {

    private HealthDBHelper healthDBHelper;
    private SQLiteDatabase mSqLiteDatabase;
    private Context mContext;
    private String[] allColumns={
            HealthDBHelper.COLUMN_ID,
            HealthDBHelper.COLUMN_HEALTH_HEIGHT,
            HealthDBHelper.COLUMN_HEALTH_WEIGHT,
            HealthDBHelper.COLUMN_HEALTH_BLOOD_GROUP,
            HealthDBHelper.COLUMN_HEALTH_BLOOD_PRESSURE,
            HealthDBHelper.COLUMN_HEALTH_BLOOD_SUGAR
    };

    public HealthDatabaseQuery(Context context) {

        this.mContext = context;

        healthDBHelper = new HealthDBHelper(mContext);
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

        values.put(HealthDBHelper.COLUMN_HEALTH_HEIGHT, mHeight);
        values.put(HealthDBHelper.COLUMN_HEALTH_WEIGHT, mWeight);
        values.put(HealthDBHelper.COLUMN_HEALTH_BLOOD_GROUP, mBloodGroup);
        values.put(HealthDBHelper.COLUMN_HEALTH_BLOOD_PRESSURE, mBloodPressure);
        values.put(HealthDBHelper.COLUMN_HEALTH_BLOOD_SUGAR, mBloodSugar);

        long insertId=mSqLiteDatabase.insert(HealthDBHelper.TABLE_HEALTH,null,values);

        Cursor cursor=mSqLiteDatabase.query(HealthDBHelper.TABLE_HEALTH,allColumns,HealthDBHelper.COLUMN_ID+" = "+insertId,null,null,null,null);
        cursor.moveToFirst();
        HealthClass newHealth = cursorToHealth(cursor);
        cursor.close();

        return newHealth;

    }


    public List<HealthClass> getAllHealths(){
        List<HealthClass>listHealths=new ArrayList<HealthClass>();
        Cursor cursor=mSqLiteDatabase.query(HealthDBHelper.TABLE_HEALTH, allColumns, null, null, null, null, null);
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

    public HealthClass getAllHealthsById(long id) {
        Cursor cursor = mSqLiteDatabase.query(HealthDBHelper.TABLE_HEALTH, allColumns,
                HealthDBHelper.COLUMN_ID + " = ?",
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
