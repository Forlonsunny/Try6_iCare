package theoaktroop.icare.ProfileActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class ProfileDataBase {

    private ProfileDBHelper mProfileDbHelper;
    private SQLiteDatabase mSqLiteDatabase;
    private Context mContext;
    private String[] allColumns={
            ProfileDBHelper.COLUMN_PROFILE_ID,
            ProfileDBHelper.COLUMN_PROFILE_NAME,
            ProfileDBHelper.COLUMN_PROFILE_RELATION,
            ProfileDBHelper.COLUMN_PROFILE_AGE
    };

    public ProfileDataBase(Context context) {

        this.mContext = context;

        mProfileDbHelper = new ProfileDBHelper(mContext);
        // open the database
        try {
            open();
        } catch (SQLException e) {
            // Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void open() throws SQLException{
        mSqLiteDatabase=mProfileDbHelper.getWritableDatabase();
    }
    public void close() {
        mProfileDbHelper.close();
    }

    public Profile createNewProfile(String mName, String mRelation, String mAge) {
        ContentValues values = new ContentValues();


        values.put(ProfileDBHelper.COLUMN_PROFILE_NAME, mName);
        values.put(ProfileDBHelper.COLUMN_PROFILE_RELATION, mRelation);
        values.put(ProfileDBHelper.COLUMN_PROFILE_AGE, mAge);
        long insertId=mSqLiteDatabase.insert(ProfileDBHelper.TABLE_PROFILE,null,values);

        Cursor cursor=mSqLiteDatabase.query(ProfileDBHelper.TABLE_PROFILE,allColumns,ProfileDBHelper.COLUMN_PROFILE_ID +" = "+insertId,null,null,null,null);
        cursor.moveToFirst();
        Profile newProfile = cursorToProfile(cursor);
        cursor.close();

        return newProfile;

    }


    public List<Profile> getAllProfiles(){
        List<Profile>listProfiles=new ArrayList<Profile>();
        Cursor cursor=mSqLiteDatabase.query(ProfileDBHelper.TABLE_PROFILE,allColumns, null, null, null, null, null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Profile profile=cursorToProfile(cursor);
                listProfiles.add(profile);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return listProfiles;

    }

    public Profile getAllProfilesById(long id) {
        Cursor cursor = mSqLiteDatabase.query(ProfileDBHelper.TABLE_PROFILE, allColumns,
                ProfileDBHelper.COLUMN_PROFILE_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Profile profile = cursorToProfile(cursor);
        return profile;
    }


    protected Profile cursorToProfile(Cursor cursor) {
        Profile profile = new Profile();
        profile.setId(cursor.getLong(0));
        profile.setProfileName(cursor.getString(1));
        profile.setRelation(cursor.getString(2));
        profile.setAge(cursor.getString(3));

        return profile;
    }
}
