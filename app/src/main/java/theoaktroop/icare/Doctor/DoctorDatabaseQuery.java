package theoaktroop.icare.Doctor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import theoaktroop.icare.DbHelper.DbHelper;
import theoaktroop.icare.DietChart.DietChartClass;

/**
 * Created by Hasan Abdullah on 21-Jun-15.
 */
public class DoctorDatabaseQuery {
   
    private DbHelper mDbhelper;
    private SQLiteDatabase mSqLiteDatabase;
    private Context mContext;
    private String[] allColumns={
            DbHelper.COLUMN_DOCTOR_ID,
            DbHelper.COLUMN_PROFILE_ID,
            DbHelper.COLUMN_DOCTOR_NAME,
            DbHelper.COLUMN_DOCTOR_TYPE,
            DbHelper.COLUMN_DOCTOR_ADDRESS,
            DbHelper.COLUMN_DOCTOR_PHONE,
            DbHelper.COLUMN_DOCTOR_APPOINTMENT,
            DbHelper.COLUMN_DOCTOR_APPOINTMENT_TIME
    };

    public DoctorDatabaseQuery(Context context) {

        this.mContext = context;

       mDbhelper= new DbHelper(mContext);
        // open the database
        try {
            open();
        } catch (SQLException e) {
            // Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void open() throws SQLException{
        mSqLiteDatabase=  mDbhelper.getWritableDatabase();
    }
    public void close() {
        mDbhelper.close();
    }

    public DoctorClass createNewDoctor(String profile_id,String mName, String mType, String mAddress, String mPhone, String mAppointment, String mAppointmentTime) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_PROFILE_ID,profile_id);
        values.put(DbHelper.COLUMN_DOCTOR_NAME, mName);
        values.put(DbHelper.COLUMN_DOCTOR_TYPE, mType);
        values.put(DbHelper.COLUMN_DOCTOR_ADDRESS, mAddress);
        values.put(DbHelper.COLUMN_DOCTOR_PHONE, mPhone);
        values.put(DbHelper.COLUMN_DOCTOR_APPOINTMENT, mAppointment);
        values.put(DbHelper.COLUMN_DOCTOR_APPOINTMENT_TIME, mAppointmentTime);

        long insertId=mSqLiteDatabase.insert(DbHelper.TABLE_DOCTOR,null,values);

        Cursor cursor=mSqLiteDatabase.query(DbHelper.TABLE_DOCTOR,allColumns, DbHelper.COLUMN_DOCTOR_ID+" = "+insertId,null,null,null,null);
        cursor.moveToFirst();
        DoctorClass newDoctor = cursorToDoctor(cursor);
        cursor.close();

        return newDoctor;

    }

    public void updateDoctor(long insertId, long mProfileId, String mName, String mType, String mAddress, String mPhone, String mAppointment, String mAppointmentTime)
    {  open();
        ContentValues values = new ContentValues();

        values.put(DbHelper.COLUMN_PROFILE_ID, mProfileId);
        values.put(DbHelper.COLUMN_DOCTOR_NAME, mName);
        values.put(DbHelper.COLUMN_DOCTOR_TYPE, mType);
        values.put(DbHelper.COLUMN_DOCTOR_ADDRESS, mAddress);
        values.put(DbHelper.COLUMN_DOCTOR_PHONE, mPhone);
        values.put(DbHelper.COLUMN_DOCTOR_APPOINTMENT, mAppointment);
        values.put(DbHelper.COLUMN_DOCTOR_APPOINTMENT_TIME, mAppointmentTime);

        mSqLiteDatabase.update(DbHelper.TABLE_DOCTOR, values, DbHelper.COLUMN_DOCTOR_ID + " = " + insertId, null);
        close();
    }

    public void deleteDoctor(long insertId){

        open();
        mSqLiteDatabase.delete(DbHelper.TABLE_DOCTOR, DbHelper.COLUMN_DOCTOR_ID + " = " + insertId, null);
        close();
    }

    public List<DoctorClass> getAllDoctors(){
        List<DoctorClass>listDoctors=new ArrayList<DoctorClass>();
        Cursor cursor=mSqLiteDatabase.query(DbHelper.TABLE_DOCTOR, allColumns, null, null, null, null, null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                DoctorClass doctor =cursorToDoctor(cursor);
                listDoctors.add(doctor);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return listDoctors;

    }

    public List<DoctorClass> getAllDoctorsById(long id) {

        List<DoctorClass> listDoctor=new ArrayList<DoctorClass>();
        Cursor cursor = mSqLiteDatabase.query(DbHelper.TABLE_DOCTOR, allColumns,
                DbHelper.COLUMN_PROFILE_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);


//        Cursor cursor = mSqLiteDatabase.query(DbHelper.TABLE_DIET, allColumns,
//                DbHelper.COLUMN_PROFILE_ID + " = ?",
//                new String[]{String.valueOf(id)}, null, null, null);
        //Cursor cursor = mSqLiteDatabase.query(DbHelper.TABLE_DIET,allColumns,null,null, null, null, null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                DoctorClass mDoctorClass =cursorToDoctor(cursor);
               listDoctor.add(mDoctorClass);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return listDoctor;
    }


    public DoctorClass getDoctorById(long id) {


        Cursor cursor = mSqLiteDatabase.query(DbHelper.TABLE_DOCTOR, allColumns,
                DbHelper.COLUMN_DOCTOR_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);


        if (cursor != null) {
            cursor.moveToFirst();
        }

        DoctorClass doctorClass = cursorToDoctor(cursor);
        return doctorClass;
    }


    protected DoctorClass cursorToDoctor(Cursor cursor) {
        DoctorClass mDoctorClass = new DoctorClass();
        mDoctorClass.setId(cursor.getLong(0));
        mDoctorClass.setProfileID(cursor.getString(1));
        mDoctorClass.setDoctorName(cursor.getString(2));
        mDoctorClass.setDoctorType(cursor.getString(3));
        mDoctorClass.setDoctorAddress(cursor.getString(4));
        mDoctorClass.setDoctorPhone(cursor.getString(5));
        mDoctorClass.setAppointmentDate(cursor.getString(6));
        mDoctorClass.setAppointmentTime(cursor.getString(7));

        System.out.println("From Doctor Database "+cursor.getString(2));
        return mDoctorClass;
    }


//    public void updateDoctor(String insertId, String profile_id,String mName, String mType, String mAddress, String mPhone, String mAppointment)
//    {  open();
//        ContentValues values = new ContentValues();
//+
//        values.put(DbHelper.COLUMN_PROFILE_ID, profileID);
//        values.put(DbHelper.COLUMN_DIET_DAY, dayName);
//        values.put(DbHelper.COLUMN_DIET_MEAL_TYPE, mealType);
//        values.put(DbHelper.COLUMN_DIET_FOOD_MENU, foodMenu);
//        values.put(DbHelper.COLUMN_DIET_TIME, timeString);
//
//        mSqLiteDatabase.update(DbHelper.TABLE_DIET, values, DbHelper.COLUMN_DIET_ID + " = " + insertId, null);
//        close();
//    }


}
