//package edu.gatech.seclass.jobcompare6300;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//
//public class JobDBHandler extends SQLiteOpenHelper {
//    private Context context;
//    public static final String DATABASE_NAME = "job.db";
//    public static final int DATABASE_VERSION = 1;
//
//    // db table column names
//    private static final String TABLE_NAME =  "job";
//    private static final String COLUMN_ID = "_id";
//    private static final String COLUMN_JOBISCURRENT = "job_is_current";
//    private static final String COLUMN_TITLE = "title";
//    private static final String COLUMN_COMPANY = "company";
//
//    private static final String COLUMN_CITY = "city";
//    private static final String COLUMN_STATE = "state";
//    private static final String COLUMN_LIVINGCOSTINDEX = "living_cost_index";
//    private static final String COLUMN_COMMUTETIME = "commute_time";
//
//    private static final String COLUMN_YEARLYSALARY = "yearly_salary";
//    private static final String COLUMN_YEARLYBONUS = "yearly_bonus";
//    private static final String COLUMN_RETIREMENTBENEFITS = "retirement_benefits";
//    private static final String COLUMN_LEAVETIME = "leave_time";
//
//    private static final String COLUMN_SCORE = "score";
//
//
//
//    public JobDBHandler(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        this.context = context;
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        //SQL to structure query language
//        String SQL_CREATE_JOB_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
//                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                COLUMN_JOBISCURRENT + " BOOLEAN NOT NULL, " +
//                COLUMN_TITLE + " TEXT NOT NULL, " +
//                COLUMN_COMPANY + " TEXT NOT NULL, " +
//                COLUMN_CITY + " TEXT NOT NULL, " +
//                COLUMN_STATE + " TEXT NOT NULL, " +
//                COLUMN_LIVINGCOSTINDEX +  " INTEGER NOT NULL, " +
//                COLUMN_COMMUTETIME + " REAL NOT NULL, " +
//                COLUMN_YEARLYSALARY + " REAL NOT NULL, " +
//                COLUMN_YEARLYBONUS + " REAL NOT NULL, " +
//                COLUMN_RETIREMENTBENEFITS + " REAL NOT NULL, " +
//                COLUMN_LEAVETIME + " INTEGER NOT NULL, " +
//                COLUMN_SCORE + " REAL" +  // the score column could be null, only used to show a ranked list.
//                ");" ;
//
//        db.execSQL(SQL_CREATE_JOB_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
//    }
//
//    void addJobOffer(String title,
//                     String company,
//                     String city,
//                     String state,
//                     int livingCostIndex,
//                     double commuteTime,
//                     double yearlySalary,
//                     double yearlyBonus,
//                     double retirementBenefits,
//                     int leaveTime){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues cv = new ContentValues();
//
//        cv.put(COLUMN_JOBISCURRENT, false); // hard code for the job offer
//        cv.put(COLUMN_TITLE, title);
//        cv.put(COLUMN_COMPANY, company);
//        cv.put(COLUMN_CITY, city);
//        cv.put(COLUMN_STATE, state);
//        cv.put(COLUMN_LIVINGCOSTINDEX, livingCostIndex);
//        cv.put(COLUMN_COMMUTETIME, commuteTime);
//        cv.put(COLUMN_YEARLYSALARY, yearlySalary);
//        cv.put(COLUMN_YEARLYBONUS, yearlyBonus);
//        cv.put(COLUMN_RETIREMENTBENEFITS, retirementBenefits);
//        cv.put(COLUMN_LEAVETIME, leaveTime);
//
//        long result = db.insert(TABLE_NAME, null, cv);
//        if(result == -1){
//            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    Cursor readRankedJobList(){
//
//        // get weigths
//        int AYS_w, AYB_w, RBP_w, LT_w, CT_w;
//
//        double AYS_f, AYB_f, RBP_f, LT_f, CT_f, sum_w;
//        String AYS_f_s, AYB_f_s, RBP_f_s, LT_f_s, CT_f_s;
//
//        Weights weights = Weights.getInstance();
//        AYS_w = weights.getYearlySalaryWeight();
//        AYB_w = weights.getYearlyBonusWeight();
//        RBP_w = weights.getRetirementBenefitsWeight();
//        LT_w = weights.getLeaveTimeWeight();
//        CT_w = weights.getCommuteTimeWeight();
//
//        sum_w = (double) (AYS_w + AYB_w + RBP_w + LT_w + CT_w);
//        AYS_f = (AYS_w/sum_w);
//        AYB_f = AYB_w/sum_w;
//        RBP_f = RBP_w/sum_w;
//        LT_f = LT_w/sum_w;
//        CT_f = CT_w/sum_w;
//
//        AYS_f_s = Double.toString(AYS_f);
//        AYB_f_s = Double.toString(AYB_f);
//        RBP_f_s = Double.toString(RBP_f);
//        LT_f_s = Double.toString(LT_f);
//        CT_f_s = Double.toString(CT_f);
//
////        String SQL_CALCULATE_SCORE = "UPDATE " + TABLE_NAME +
////                " SET " + COLUMN_SCORE +
////                " = " +
////                AYS_f_s + " * " + COLUMN_YEARLYSALARY + " + " +
////                AYB_f_s + " * " + COLUMN_YEARLYBONUS + " + " +
////                RBP_f_s + " * " + COLUMN_RETIREMENTBENEFITS + " + " +
////                LT_f_s + " * " + COLUMN_LEAVETIME + " + " +
////                CT_f_s + " * " + COLUMN_COMMUTETIME + ";"
////                ;
//
//        String SQL_CALCULATE_SCORE = String.format("UPDATE " + TABLE_NAME +
//                " SET " + COLUMN_SCORE +
//                " = " +
//                "%.5f * " + COLUMN_YEARLYSALARY + " + " +
//                "%.5f * " + COLUMN_YEARLYBONUS + " + " +
//                "%.5f * " + COLUMN_RETIREMENTBENEFITS + " + " +
//                "%.5f * " + COLUMN_LEAVETIME + " + " +
//                "%.5f * " + COLUMN_COMMUTETIME + ";", AYS_f, AYB_f, RBP_f, LT_f, CT_f);
//
//        String SQL_GET_RANKED_LIST = "SELECT " +
//                COLUMN_ID + ", " +
//                COLUMN_TITLE + ", " +
//                COLUMN_COMPANY + ", " +
//                COLUMN_JOBISCURRENT + ", " +
//                COLUMN_SCORE  +
//                " FROM " + TABLE_NAME + " ORDER BY " + COLUMN_SCORE + " DESC;"; // this query needs update
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = null;
//        if (db != null){
//            db.execSQL(SQL_CALCULATE_SCORE);
//            cursor = db.rawQuery(SQL_GET_RANKED_LIST, null);
//        }
//
//        return cursor;
//    }
//
//    Cursor readCurrentJob(){
//        String SQL_GET_CURRENT_JOB = "SELECT * FROM " + TABLE_NAME +
//                " WHERE " + COLUMN_JOBISCURRENT + " = 1;";
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = null;
//        if (db != null){
//            cursor = db.rawQuery(SQL_GET_CURRENT_JOB, null);
//        }
//        return cursor;
//
//    }
//
//    void addCurrentJob(String title,
//                       String company,
//                       String city,
//                       String state,
//                       int livingCostIndex,
//                       double commuteTime,
//                       double yearlySalary,
//                       double yearlyBonus,
//                       double retirementBenefits,
//                       int leaveTime){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues cv = new ContentValues();
//
//        cv.put(COLUMN_JOBISCURRENT, true); // hard code for current job
//
//        cv.put(COLUMN_TITLE, title);
//        cv.put(COLUMN_COMPANY, company);
//        cv.put(COLUMN_CITY, city);
//        cv.put(COLUMN_STATE, state);
//        cv.put(COLUMN_LIVINGCOSTINDEX, livingCostIndex);
//        cv.put(COLUMN_COMMUTETIME, commuteTime);
//        cv.put(COLUMN_YEARLYSALARY, yearlySalary);
//        cv.put(COLUMN_YEARLYBONUS, yearlyBonus);
//        cv.put(COLUMN_RETIREMENTBENEFITS, retirementBenefits);
//        cv.put(COLUMN_LEAVETIME, leaveTime);
//
//        long result = db.insert(TABLE_NAME, null, cv);
//        if(result == -1){
//            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    void updateCurrentJobData(int row_id,
//                                  String title,
//                                  String company,
//                                  String city,
//                                  String state,
//                                  int livingCostIndex,
//                                  double commuteTime,
//                                  double yearlySalary,
//                                  double yearlyBonus,
//                                  double retirementBenefits,
//                                  int leaveTime){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(COLUMN_ID, row_id);
//        cv.put(COLUMN_JOBISCURRENT, true);
//        cv.put(COLUMN_TITLE, title);
//        cv.put(COLUMN_COMPANY, company);
//        cv.put(COLUMN_CITY, city);
//        cv.put(COLUMN_STATE, state);
//        cv.put(COLUMN_LIVINGCOSTINDEX, livingCostIndex);
//        cv.put(COLUMN_COMMUTETIME, commuteTime);
//        cv.put(COLUMN_YEARLYSALARY, yearlySalary);
//        cv.put(COLUMN_YEARLYBONUS, yearlyBonus);
//        cv.put(COLUMN_RETIREMENTBENEFITS, retirementBenefits);
//        cv.put(COLUMN_LEAVETIME, leaveTime);
//
//        long result = db.update(TABLE_NAME, cv, "_id="+row_id, null);
//        if(result == -1){
//            Toast.makeText(context, "Update Failed!", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    Cursor readJob(int id){
//        String SQL_GET_JOB = String.format("SELECT * FROM " + TABLE_NAME +
//                " WHERE " + COLUMN_ID + " = %d;", id);
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = null;
//        if (db != null){
//            cursor = db.rawQuery(SQL_GET_JOB, null);
//        }
//        return cursor;
//
//    }
//
//    Cursor readLatestJobOffer(){
//
//        String SQL_GET_LATEST_JOB_OFFER = "SELECT * FROM " + TABLE_NAME +
//                " WHERE " + COLUMN_ID + " = (SELECT MAX(" + COLUMN_ID + ") FROM " + TABLE_NAME  +");";
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = null;
//        if (db != null){
//            cursor = db.rawQuery(SQL_GET_LATEST_JOB_OFFER, null);
//
//        }
//        return cursor;
//    }
//
//
//}


package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class JobDBHandler extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "job.db";
    public static final int DATABASE_VERSION = 1;

    // db table column names
    private static final String TABLE_NAME =  "job";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_JOBISCURRENT = "job_is_current";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_COMPANY = "company";

    private static final String COLUMN_CITY = "city";
    private static final String COLUMN_STATE = "state";
    private static final String COLUMN_LIVINGCOSTINDEX = "living_cost_index";
    private static final String COLUMN_COMMUTETIME = "commute_time";

    private static final String COLUMN_YEARLYSALARY = "yearly_salary";
    private static final String COLUMN_YEARLYBONUS = "yearly_bonus";
    private static final String COLUMN_RETIREMENTBENEFITS = "retirement_benefits";
    private static final String COLUMN_LEAVETIME = "leave_time";

    private static final String COLUMN_SCORE = "score";

    private int current_job_living_cost_index;




    public JobDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL to structure query language
        String SQL_CREATE_JOB_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_JOBISCURRENT + " BOOLEAN NOT NULL, " +
                COLUMN_TITLE + " TEXT NOT NULL, " +
                COLUMN_COMPANY + " TEXT NOT NULL, " +
                COLUMN_CITY + " TEXT NOT NULL, " +
                COLUMN_STATE + " TEXT NOT NULL, " +
                COLUMN_LIVINGCOSTINDEX +  " INTEGER NOT NULL, " +
                COLUMN_COMMUTETIME + " REAL NOT NULL, " +
                COLUMN_YEARLYSALARY + " REAL NOT NULL, " +
                COLUMN_YEARLYBONUS + " REAL NOT NULL, " +
                COLUMN_RETIREMENTBENEFITS + " REAL NOT NULL, " +
                COLUMN_LEAVETIME + " INTEGER NOT NULL, " +
                COLUMN_SCORE + " REAL" +  // the score column could be null, only used to show a ranked list.
                ");" ;

        db.execSQL(SQL_CREATE_JOB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addJobOffer(String title,
                     String company,
                     String city,
                     String state,
                     int livingCostIndex,
                     double commuteTime,
                     double yearlySalary,
                     double yearlyBonus,
                     double retirementBenefits,
                     int leaveTime){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_JOBISCURRENT, false); // hard code for the job offer
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_COMPANY, company);
        cv.put(COLUMN_CITY, city);
        cv.put(COLUMN_STATE, state);
        cv.put(COLUMN_LIVINGCOSTINDEX, livingCostIndex);
        cv.put(COLUMN_COMMUTETIME, commuteTime);
        cv.put(COLUMN_YEARLYSALARY, yearlySalary);
        cv.put(COLUMN_YEARLYBONUS, yearlyBonus);
        cv.put(COLUMN_RETIREMENTBENEFITS, retirementBenefits);
        cv.put(COLUMN_LEAVETIME, leaveTime);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readRankedJobList(){

        // get weigths
        int AYS_w, AYB_w, RBP_w, LT_w, CT_w;
        int lci = getCurrentJobCostIndex();

        double AYS_f, AYB_f, RBP_f, LT_f, CT_f, sum_w;

        String AYS_f_s, AYB_f_s, RBP_f_s, LT_f_s, CT_f_s;

        Weights weights = Weights.getInstance();
        AYS_w = weights.getYearlySalaryWeight();
        AYB_w = weights.getYearlyBonusWeight();
        RBP_w = weights.getRetirementBenefitsWeight();
        LT_w = weights.getLeaveTimeWeight();
        CT_w = weights.getCommuteTimeWeight();

        sum_w = (double) (AYS_w + AYB_w + RBP_w + LT_w + CT_w);
        AYS_f = (AYS_w/sum_w);
        AYB_f = AYB_w/sum_w;
        RBP_f = RBP_w/sum_w;
        LT_f = LT_w/sum_w;
        CT_f = CT_w/sum_w;

        AYS_f_s = Double.toString(AYS_f);
        AYB_f_s = Double.toString(AYB_f);
        RBP_f_s = Double.toString(RBP_f);
        LT_f_s = Double.toString(LT_f);
        CT_f_s = Double.toString(CT_f);


        String SQL_CALCULATE_SCORE = String.format("UPDATE " + TABLE_NAME +
                " SET " + COLUMN_SCORE +
                " = " +
                "%.5f * " + COLUMN_YEARLYSALARY + " * " + COLUMN_LIVINGCOSTINDEX + " / " + "%d " + " + " +
                "%.5f * " + COLUMN_YEARLYBONUS + " * " + COLUMN_LIVINGCOSTINDEX + " / " + "%d " + " + " +
                "%.5f * " + COLUMN_RETIREMENTBENEFITS + " / " + "%d" + " * " + COLUMN_YEARLYSALARY + " * " + COLUMN_LIVINGCOSTINDEX + " / " + "%d" + " + " +
                "%.5f * " + COLUMN_LEAVETIME + " * " + COLUMN_YEARLYSALARY + " * " + COLUMN_LIVINGCOSTINDEX + " / " + "%d" + " / " + "%d" + " - " +
                "%.5f * " + COLUMN_COMMUTETIME +  " * " + COLUMN_YEARLYSALARY + " * " + COLUMN_LIVINGCOSTINDEX + " / " + "%d" + " / " + "%d"  +  ";", AYS_f, lci, AYB_f, lci, RBP_f, 100, lci, LT_f, lci, 260, CT_f, lci, 8);

        String SQL_GET_RANKED_LIST = "SELECT " +
                COLUMN_ID + ", " +
                COLUMN_TITLE + ", " +
                COLUMN_COMPANY + ", " +
                COLUMN_JOBISCURRENT + ", " +
                COLUMN_SCORE  +
                " FROM " + TABLE_NAME + " ORDER BY " + COLUMN_SCORE + " DESC;"; // this query needs update
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            db.execSQL(SQL_CALCULATE_SCORE);
            cursor = db.rawQuery(SQL_GET_RANKED_LIST, null);
        }

        return cursor;
    }


    int getCurrentJobCostIndex(){
        String SQL_GET_CURRENT_JOB = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_JOBISCURRENT + " = 1;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(SQL_GET_CURRENT_JOB, null);
            cursor.moveToNext();
            current_job_living_cost_index = Integer.parseInt(cursor.getString(6));
        }
        return current_job_living_cost_index;
    }

    Cursor readCurrentJob(){
        String SQL_GET_CURRENT_JOB = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_JOBISCURRENT + " = 1;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(SQL_GET_CURRENT_JOB, null);
        }
        return cursor;

    }

    void addCurrentJob(String title,
                       String company,
                       String city,
                       String state,
                       int livingCostIndex,
                       double commuteTime,
                       double yearlySalary,
                       double yearlyBonus,
                       double retirementBenefits,
                       int leaveTime){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_JOBISCURRENT, true); // hard code for current job

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_COMPANY, company);
        cv.put(COLUMN_CITY, city);
        cv.put(COLUMN_STATE, state);
        cv.put(COLUMN_LIVINGCOSTINDEX, livingCostIndex);
        cv.put(COLUMN_COMMUTETIME, commuteTime);
        cv.put(COLUMN_YEARLYSALARY, yearlySalary);
        cv.put(COLUMN_YEARLYBONUS, yearlyBonus);
        cv.put(COLUMN_RETIREMENTBENEFITS, retirementBenefits);
        cv.put(COLUMN_LEAVETIME, leaveTime);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    void updateCurrentJobData(int row_id,
                              String title,
                              String company,
                              String city,
                              String state,
                              int livingCostIndex,
                              double commuteTime,
                              double yearlySalary,
                              double yearlyBonus,
                              double retirementBenefits,
                              int leaveTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, row_id);
        cv.put(COLUMN_JOBISCURRENT, true);
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_COMPANY, company);
        cv.put(COLUMN_CITY, city);
        cv.put(COLUMN_STATE, state);
        cv.put(COLUMN_LIVINGCOSTINDEX, livingCostIndex);
        cv.put(COLUMN_COMMUTETIME, commuteTime);
        cv.put(COLUMN_YEARLYSALARY, yearlySalary);
        cv.put(COLUMN_YEARLYBONUS, yearlyBonus);
        cv.put(COLUMN_RETIREMENTBENEFITS, retirementBenefits);
        cv.put(COLUMN_LEAVETIME, leaveTime);

        long result = db.update(TABLE_NAME, cv, "_id="+row_id, null);
        if(result == -1){
            Toast.makeText(context, "Update Failed!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readJob(int id){
        String SQL_GET_JOB = String.format("SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_ID + " = %d;", id);
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(SQL_GET_JOB, null);
        }
        return cursor;
    }

    Cursor readLatestJobOffer(){

        String SQL_GET_LATEST_JOB_OFFER = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_ID + " = (SELECT MAX(" + COLUMN_ID + ") FROM " + TABLE_NAME  +");";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(SQL_GET_LATEST_JOB_OFFER, null);

        }
        return cursor;
    }

}
