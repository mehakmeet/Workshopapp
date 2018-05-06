package internshala.mehakmeet.workshopapp.SqliteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import internshala.mehakmeet.workshopapp.SqliteDB.WorkshopContract;

/**
 * Created by MEHAKMEET on 07-05-2018.
 */

public class WorkshopDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="WORKSHOPINFO.DB";
    private static final int DATABASE_VERSION=1;

    private static final String CREATE_QUERY=
            "CREATE TABLE "+ WorkshopContract.NewWorkshopInfo.TABLE_NAME+"("+
                    WorkshopContract.NewWorkshopInfo.WORKSHOP_NAME+" TEXT,"+
                    WorkshopContract.NewWorkshopInfo.WORKSHOP_DESC+" TEXT,"+
                    WorkshopContract.NewWorkshopInfo.WORKSHOP_DATE+" TEXT,"+
                    WorkshopContract.NewWorkshopInfo.WORKSHOP_TIME+" TEXT);";


    public WorkshopDbHelper(Context context)
    {

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS","Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS","Table created...");

    }

    public void addInfo(String name, String desc,String date,String time, SQLiteDatabase db){

        ContentValues contentValues=new ContentValues();
        contentValues.put(WorkshopContract.NewWorkshopInfo.WORKSHOP_NAME,name);
        contentValues.put(WorkshopContract.NewWorkshopInfo.WORKSHOP_DESC,desc);
        contentValues.put(WorkshopContract.NewWorkshopInfo.WORKSHOP_DATE,date);
        contentValues.put(WorkshopContract.NewWorkshopInfo.WORKSHOP_TIME,time);

        db.insert(WorkshopContract.NewWorkshopInfo.TABLE_NAME,null,contentValues);

        Log.e("DATABASE OPERATIONS","One row inserted...");

    }

    public Cursor getInfo(SQLiteDatabase db)
    {
        Cursor cursor;
        String[] projections = {WorkshopContract.NewWorkshopInfo.WORKSHOP_NAME, WorkshopContract.NewWorkshopInfo.WORKSHOP_DESC,
                WorkshopContract.NewWorkshopInfo.WORKSHOP_DATE, WorkshopContract.NewWorkshopInfo.WORKSHOP_TIME};

        cursor=db.query(WorkshopContract.NewWorkshopInfo.TABLE_NAME,projections,null,null,null,null,null);

        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
