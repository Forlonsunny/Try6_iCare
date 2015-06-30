package theoaktroop.icare.Vaccine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import theoaktroop.icare.DbHelper.DbHelper;
import theoaktroop.icare.Doctor.DoctorClass;

/**
 * Created by Hasan Abdullah on 21-Jun-15.
 */
public class VaccineDatabaseQuery {

    private DbHelper mDbHelper;
    private SQLiteDatabase mSqLiteDatabase;
    private Context mContext;
    private String[] allColumns={
            DbHelper.COLUMN_VACCINE_ID,
            DbHelper.COLUMN_PROFILE_ID,
            DbHelper.COLUMN_VACCINE_NAME,
            DbHelper.COLUMN_VACCINE_REASON,
            DbHelper.COLUMN_VACCINE_DATE
    };

    public VaccineDatabaseQuery(Context context) {

        this.mContext = context;

        mDbHelper = new DbHelper(mContext);
        // open the database
        try {
            open();
        } catch (SQLException e) {
            // Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void open() throws SQLException{
        mSqLiteDatabase= mDbHelper.getWritableDatabase();
    }
    public void close() {
        mDbHelper.close();
    }

    public VaccinationClass createNewVaccine(String profile_ID,String mName, String mReason, String mDate) {
        ContentValues values = new ContentValues();

        values.put(DbHelper.COLUMN_PROFILE_ID,profile_ID);
        values.put(DbHelper.COLUMN_VACCINE_NAME, mName);
        values.put(DbHelper.COLUMN_VACCINE_REASON, mReason);
        values.put(DbHelper.COLUMN_VACCINE_DATE, mDate);

        long insertId=mSqLiteDatabase.insert(DbHelper.TABLE_VACCINE,null,values);

        Cursor cursor=mSqLiteDatabase.query(DbHelper.TABLE_VACCINE,allColumns, DbHelper.COLUMN_VACCINE_ID+" = "+insertId,null,null,null,null);
        cursor.moveToFirst();
        VaccinationClass newVaccine = cursorToVaccine(cursor);
        cursor.close();

        return newVaccine;

    }

    public void updateVaccine(long insertID, String mName, String mReason, String mDate){
        open();
        ContentValues values = new ContentValues();

        values.put(DbHelper.COLUMN_VACCINE_ID, insertID);
        values.put(DbHelper.COLUMN_VACCINE_NAME, mName);
        values.put(DbHelper.COLUMN_VACCINE_REASON, mReason);
        values.put(DbHelper.COLUMN_VACCINE_DATE, mDate);

        mSqLiteDatabase.update(DbHelper.TABLE_VACCINE, values, DbHelper.COLUMN_VACCINE_ID + " = " + insertID, null);
        close();
    }

    public void deleteVaccine(long insertID){
        open();
        mSqLiteDatabase.delete(DbHelper.TABLE_VACCINE, DbHelper.COLUMN_VACCINE_ID + " = " + insertID, null);
        close();
    }


    public List<VaccinationClass> getAllVaccines(){
        List<VaccinationClass>listVaccines=new ArrayList<VaccinationClass>();
        Cursor cursor=mSqLiteDatabase.query(DbHelper.TABLE_VACCINE, allColumns, null, null, null, null, null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                VaccinationClass vaccine =cursorToVaccine(cursor);
                listVaccines.add(vaccine);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return listVaccines;

    }

    public List<VaccinationClass>  getAllVaccinesById(long id) {
        List<VaccinationClass>listVaccines=new ArrayList<VaccinationClass>();
        Cursor cursor = mSqLiteDatabase.query(DbHelper.TABLE_VACCINE, allColumns,
                DbHelper.COLUMN_PROFILE_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                VaccinationClass vaccine =cursorToVaccine(cursor);
                listVaccines.add(vaccine);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return listVaccines;
    }


    protected VaccinationClass cursorToVaccine(Cursor cursor) {
        VaccinationClass vaccine = new VaccinationClass();
        vaccine.setId(cursor.getLong(0));
        vaccine.setProfileID(cursor.getString(1));
        vaccine.setVaccineName(cursor.getString(2));
        vaccine.setReason(cursor.getString(3));
        vaccine.setVaccineDate(cursor.getString(4));
//        System.out.println("From VaccineDataBasequery = "+cursor.getString(2));
        return vaccine;
    }

    public VaccinationClass getVaccineById(long insertID) {
        Cursor cursor = mSqLiteDatabase.query(DbHelper.TABLE_VACCINE, allColumns,
                DbHelper.COLUMN_VACCINE_ID + " = ?",
                new String[]{String.valueOf(insertID)}, null, null, null);


        if (cursor != null) {
            cursor.moveToFirst();
        }

        VaccinationClass vaccinationClass = cursorToVaccine(cursor);
        return vaccinationClass;
    }
}
