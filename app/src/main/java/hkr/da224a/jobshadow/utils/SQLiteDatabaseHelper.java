package hkr.da224a.jobshadow.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import hkr.da224a.jobshadow.model.Application;
import hkr.da224a.jobshadow.model.Business;
import hkr.da224a.jobshadow.model.Offer;
import hkr.da224a.jobshadow.model.OfferCategory;
import hkr.da224a.jobshadow.model.Student;

/**
 * The type Database helper.
 */
public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLiteDatabaseHelper";
    private Context context;

    // Database Version
    private static int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "JobShadow.db";

    // Student table name
    private static final String TABLE_STUDENT = "studentTable";
    // Business table name
    private static final String TABLE_BUSINESS = "businessTable";
    // Offer table name
    private static final String TABLE_OFFER = "offerTable";
    // Application table name
    private static final String TABLE_APPLICATION = "applicationTable";
    // Application table name
    private static final String TABLE_OFFER_CATEGORY = "offerCategoryTable";

    // Student Table Columns names
    private static final String COLUMN_STUDENT_ACCOUNT_TYPE = "account_type";
    private static final String COLUMN_STUDENT_ID = "student_id";
    private static final String COLUMN_STUDENT_PASSWORD = "student_password";
    private static final String COLUMN_STUDENT_FIRST_NAME = "first_name";
    private static final String COLUMN_STUDENT_LAST_NAME = "last_name";
    private static final String COLUMN_STUDENT_PHONE_NUMBER = "phone_number";
    private static final String COLUMN_STUDENT_EMAIL_ADDRESS = "email_address";
    private static final String COLUMN_STUDENT_CITY = "city";
    private static final String COLUMN_STUDENT_DATE_OF_BIRTH = "date_of_birthday";
    private static final String COLUMN_STUDENT_IN_CASE_OF_EMERGENCY = "in_case_of_emergency";
    private static final String COLUMN_STUDENT_DEGREE_PROGRAM = "degree_program";
    private static final String COLUMN_STUDENT_SEARCH_PREFS = "search_prefs";
    private static final String COLUMN_STUDENT_DESC = "description";
    private static final String COLUMN_STUDENT_DATE_CREATED = "date_created";


    // Business Table Columns names
    private static final String COLUMN_BUSINESS_ACCOUNT_TYPE = "account_type";
    private static final String COLUMN_BUSINESS_ID = "business_id";
    private static final String COLUMN_BUSINESS_NAME = "business_name";
    private static final String COLUMN_BUSINESS_PASSWORD = "business_password";
    private static final String COLUMN_BUSINESS_HQ_ADDRESS = "hq_address";
    private static final String COLUMN_BUSINESS_PHONE_NUMBER = "phone_number";
    private static final String COLUMN_BUSINESS_CONTACT_NAME = "owner_name";
    private static final String COLUMN_BUSINESS_EMAIL_ADDRESS = "email_address";
    private static final String COLUMN_BUSINESS_VERIFIED = "verified";
    private static final String COLUMN_BUSINESS_DATE_CREATED = "date_created";

    // Offer Table Columns names
    private static final String COLUMN_OFFER_ID = "offer_id";
    private static final String COLUMN_OFFER_BUSINESS_ID = "business_id";
    private static final String COLUMN_OFFER_CATEGORY_ID = "cat_id";
    private static final String COLUMN_OFFER_TITLE = "title";
    private static final String COLUMN_OFFER_LENGTH = "length";
    private static final String COLUMN_OFFER_POSITION_QUAL = "position_qual";
    private static final String COLUMN_OFFER_LOCATION = "location";
    private static final String COLUMN_OFFER_DESCRIPTION = "offer_desc";
    private static final String COLUMN_OFFER_DATE_CREATED = "date_created";

    // Application Table Columns names
    private static final String COLUMN_APPLICATION_ID = "application_id";
    private static final String COLUMN_APPLICATION_STUDENT_ID = "student_id";
    private static final String COLUMN_APPLICATION_OFFER_ID = "offer_id";
    private static final String COLUMN_APPLICATION_DATE = "date_created";

    // Offer Category Table Columns names
    private static final String COLUMN_CATEGORY_ID = "category_id";
    private static final String COLUMN_CATEGORY_NAME = "category_name";


    // create table sql query for student table
    private String CREATE_STUDENT_TABLE = "CREATE TABLE "
            + TABLE_STUDENT + "("
            + COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_STUDENT_ACCOUNT_TYPE + " VARCHAR,"
            + COLUMN_STUDENT_PASSWORD + " VARCHAR,"
            + COLUMN_STUDENT_FIRST_NAME + " VARCHAR,"
            + COLUMN_STUDENT_LAST_NAME + " VARCHAR,"
            + COLUMN_STUDENT_PHONE_NUMBER + " BIGINT,"
            + COLUMN_STUDENT_EMAIL_ADDRESS + " VARCHAR,"
            + COLUMN_STUDENT_CITY + " VARCHAR,"
            + COLUMN_STUDENT_DATE_OF_BIRTH + " VARCHAR,"
            + COLUMN_STUDENT_IN_CASE_OF_EMERGENCY + " LONG,"
            + COLUMN_STUDENT_DEGREE_PROGRAM + " VARCHAR,"
            + COLUMN_STUDENT_SEARCH_PREFS + " VARCHAR,"
            + COLUMN_STUDENT_DESC + " VARCHAR,"
            + COLUMN_STUDENT_DATE_CREATED + " DATE" + ")";

    // create table sql query for business table
    private String CREATE_BUSINESS_TABLE = "CREATE TABLE "
            + TABLE_BUSINESS + "("
            + COLUMN_BUSINESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_BUSINESS_ACCOUNT_TYPE + " VARCHAR,"
            + COLUMN_BUSINESS_NAME + " VARCHAR,"
            + COLUMN_BUSINESS_PASSWORD + " VARCHAR,"
            + COLUMN_BUSINESS_HQ_ADDRESS + " VARCHAR,"
            + COLUMN_BUSINESS_PHONE_NUMBER + " BIGINT,"
            + COLUMN_BUSINESS_CONTACT_NAME + " VARCHAR,"
            + COLUMN_BUSINESS_EMAIL_ADDRESS + " VARCHAR,"
            + COLUMN_BUSINESS_VERIFIED + " BIGINT,"
            + COLUMN_BUSINESS_DATE_CREATED + " DATE" + ")";

    // create table sql query for business table
    private String CREATE_OFFER_TABLE = "CREATE TABLE "
            + TABLE_OFFER + "("
            + COLUMN_OFFER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_OFFER_BUSINESS_ID + " VARCHAR,"
            + COLUMN_OFFER_CATEGORY_ID + " VARCHAR,"
            + COLUMN_OFFER_TITLE + " VARCHAR,"
            + COLUMN_OFFER_LENGTH + " VARCHAR,"
            + COLUMN_OFFER_POSITION_QUAL + " VARCHAR,"
            + COLUMN_OFFER_LOCATION + " VARCHAR,"
            + COLUMN_OFFER_DESCRIPTION + " VARCHAR,"
            + COLUMN_OFFER_DATE_CREATED + " DATE" + ")";

    // create table sql query for application table
    private String CREATE_APPLICATION_TABLE = "CREATE TABLE "
            + TABLE_APPLICATION + "("
            + COLUMN_APPLICATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_APPLICATION_STUDENT_ID + " INTEGER,"
            + COLUMN_APPLICATION_OFFER_ID + " INTEGER,"
            + COLUMN_APPLICATION_DATE + " DATE" + ")";

    // create table sql query for application table
    private String CREATE_OFFER_CATEGORY_TABLE = "CREATE TABLE "
            + TABLE_OFFER_CATEGORY + "("
            + COLUMN_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_CATEGORY_NAME + " VARCHAR" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_STUDENT;
    private String DROP_BUSINESS_TABLE = "DROP TABLE IF EXISTS " + TABLE_BUSINESS;
    private String DROP_OFFER_TABLE = "DROP TABLE IF EXISTS " + TABLE_OFFER;
    private String DROP_APPLICATION_TABLE = "DROP TABLE IF EXISTS " + TABLE_APPLICATION;
    private String DROP_CATEGORY_TABLE = "DROP TABLE IF EXISTS " + TABLE_OFFER_CATEGORY;

    /**
     * Constructor
     *
     * @param context the context
     */
    public SQLiteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDENT_TABLE);
        db.execSQL(CREATE_BUSINESS_TABLE);
        db.execSQL(CREATE_APPLICATION_TABLE);
        db.execSQL(CREATE_OFFER_TABLE);
        db.execSQL(CREATE_OFFER_CATEGORY_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_BUSINESS_TABLE);
        db.execSQL(DROP_APPLICATION_TABLE);
        db.execSQL(DROP_OFFER_TABLE);
        db.execSQL(DROP_CATEGORY_TABLE);

        // Create tables again
        onCreate(db);

        DATABASE_VERSION = newVersion;
    }

    //**************************************** STUDENT CRUD FUNCTIONALITY *********************************************//

    /**
     * This method is to create user record
     *
     * @param student the student
     */
    public boolean addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_ACCOUNT_TYPE, Student.getAccountType());
        values.put(COLUMN_STUDENT_PASSWORD, student.getPassword());
        values.put(COLUMN_STUDENT_FIRST_NAME, student.getFirstName());
        values.put(COLUMN_STUDENT_LAST_NAME, student.getLastName());
        values.put(COLUMN_STUDENT_PHONE_NUMBER, student.getPhoneNumber());
        values.put(COLUMN_STUDENT_EMAIL_ADDRESS, student.getEmailAddress());
        values.put(COLUMN_STUDENT_CITY, student.getCity());
        values.put(COLUMN_STUDENT_DATE_OF_BIRTH, student.getDateOfBirth());
        values.put(COLUMN_STUDENT_IN_CASE_OF_EMERGENCY, student.getInCaseOfEmergency());
        values.put(COLUMN_STUDENT_DEGREE_PROGRAM, student.getDegreeProgram());
        values.put(COLUMN_STUDENT_SEARCH_PREFS, student.getSearchPrefs());
        values.put(COLUMN_STUDENT_DESC, student.getDescription());
        values.put(COLUMN_STUDENT_DATE_CREATED, new Timestamp(Calendar.getInstance().getTime().getTime()).toString());

        // Inserting Row
        db.insert(TABLE_STUDENT, null, values);
        Log.e(TAG, "INSERTED INTO STUDENT TABLE");
        Log.e(TAG, student.toString());
        db.close();

        return FirebaseDatabaseHelper.signInNewStudent(student);
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list all students
     */
    public ArrayList<Student> getAllStudents() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_STUDENT_ACCOUNT_TYPE,
                COLUMN_STUDENT_ID,
                COLUMN_STUDENT_PASSWORD,
                COLUMN_STUDENT_FIRST_NAME,
                COLUMN_STUDENT_LAST_NAME,
                COLUMN_STUDENT_PHONE_NUMBER,
                COLUMN_STUDENT_EMAIL_ADDRESS,
                COLUMN_STUDENT_CITY,
                COLUMN_STUDENT_DATE_OF_BIRTH,
                COLUMN_STUDENT_IN_CASE_OF_EMERGENCY,
                COLUMN_STUDENT_DEGREE_PROGRAM,
                COLUMN_STUDENT_SEARCH_PREFS,
                COLUMN_STUDENT_DESC,
                COLUMN_STUDENT_DATE_CREATED,
        };
        // sorting orders
        String sortOrder =
                COLUMN_STUDENT_ID + " ASC";
        ArrayList<Student> userList = new ArrayList<Student>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /*
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_STUDENT, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setStudentID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_ID))));
                student.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_PASSWORD)));
                student.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_FIRST_NAME)));
                student.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_LAST_NAME)));
                student.setPhoneNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_STUDENT_PHONE_NUMBER)));
                student.setEmailAddress(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_EMAIL_ADDRESS)));
                student.setCity(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_CITY)));
                student.setDateOfBirth(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_DATE_OF_BIRTH)));
                student.setInCaseOfEmergency(cursor.getLong(cursor.getColumnIndex(COLUMN_STUDENT_IN_CASE_OF_EMERGENCY)));
                student.setDegreeProgram(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_DEGREE_PROGRAM)));
                student.setSearchPrefs(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_SEARCH_PREFS)));
                student.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_DESC)));
                student.setDateCreated(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_DATE_CREATED))));
                // Adding user record to list
                userList.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    /**
     * This method to update user record
     *
     * @param student the student
     */
    public void updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_PASSWORD, student.getPassword());
        values.put(COLUMN_STUDENT_FIRST_NAME, student.getFirstName());
        values.put(COLUMN_STUDENT_LAST_NAME, student.getLastName());
        values.put(COLUMN_STUDENT_PHONE_NUMBER, student.getPhoneNumber());
        values.put(COLUMN_STUDENT_EMAIL_ADDRESS, student.getEmailAddress());
        values.put(COLUMN_STUDENT_CITY, student.getCity());
        values.put(COLUMN_STUDENT_DATE_OF_BIRTH, student.getDateOfBirth());
        values.put(COLUMN_STUDENT_IN_CASE_OF_EMERGENCY, student.getInCaseOfEmergency());
        values.put(COLUMN_STUDENT_DEGREE_PROGRAM, student.getDegreeProgram());
        values.put(COLUMN_STUDENT_DESC, student.getDescription());
        values.put(COLUMN_STUDENT_SEARCH_PREFS, student.getSearchPrefs());

        // updating row
        db.update(TABLE_STUDENT, values, COLUMN_STUDENT_ID + " = ?",
                new String[]{String.valueOf(student.getStudentID())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param student the student
     */
    public void deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_STUDENT, COLUMN_STUDENT_ID + " = ?",
                new String[]{String.valueOf(student.getStudentID())});
        Log.e(TAG, "DELETED FROM STUDENT TABLE");
        Log.e(TAG, student.toString());
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email    the email
     * @param password the password
     * @return true /false
     */
    public boolean checkStudentCredentials(String email, String password) {

        if (isNetworkAvailable()) {
            FirebaseDatabaseHelper.loginUser(email, password);
        }
        // array of columns to fetch
        String[] columns = {
                COLUMN_STUDENT_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_STUDENT_EMAIL_ADDRESS + " = ?" + " AND " + COLUMN_STUDENT_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_STUDENT, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        return cursorCount > 0;


    }

    /**
     * Gets student.
     *
     * @param email the email
     * @return the student
     */
    public Student getStudent(String email) {
        Student returnedUser = null;
        ArrayList<Student> userArrayList = this.getAllStudents();
        for (int i = 0; i < userArrayList.size(); i++) {
            if (userArrayList.get(i).getEmailAddress().equals(email)) {
                returnedUser = userArrayList.get(i);
                return returnedUser;
            }
        }
        Log.e(TAG, "SELECT USER FROM STUDENT TABLE");
        Log.e(TAG, returnedUser.toString());
        return returnedUser;
    }

    //************************************** BUSINESS CRUD FUNCTIONALITY *********************************************//

    /**
     * This method is to create user record
     *
     * @param business the business
     */
    public boolean addBusiness(Business business) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_BUSINESS_ACCOUNT_TYPE, Business.getAccountType());
        values.put(COLUMN_BUSINESS_ID, business.getBusinessID());
        values.put(COLUMN_BUSINESS_NAME, business.getBusinessName());
        values.put(COLUMN_BUSINESS_PASSWORD, business.getPassword());
        values.put(COLUMN_BUSINESS_HQ_ADDRESS, business.getHqAddress());
        values.put(COLUMN_BUSINESS_PHONE_NUMBER, business.getContactPhone());
        values.put(COLUMN_BUSINESS_CONTACT_NAME, business.getContactName());
        values.put(COLUMN_BUSINESS_EMAIL_ADDRESS, business.getContactEmail());
        values.put(COLUMN_BUSINESS_VERIFIED, business.isVerified());
        values.put(COLUMN_BUSINESS_DATE_CREATED, new Timestamp(Calendar.getInstance().getTime().getTime()).toString());

        // Inserting Row
        db.insert(TABLE_BUSINESS, null, values);
        Log.e(TAG, "INSERTED INTO BUSINESS TABLE");
        Log.e(TAG, business.toString());
        db.close();

        return FirebaseDatabaseHelper.signInNewBusiness(business);
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list all businesses
     */
    public ArrayList<Business> getAllBusinesses() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_BUSINESS_ACCOUNT_TYPE,
                COLUMN_BUSINESS_ID,
                COLUMN_BUSINESS_NAME,
                COLUMN_BUSINESS_PASSWORD,
                COLUMN_BUSINESS_HQ_ADDRESS,
                COLUMN_BUSINESS_PHONE_NUMBER,
                COLUMN_BUSINESS_PHONE_NUMBER,
                COLUMN_BUSINESS_CONTACT_NAME,
                COLUMN_BUSINESS_EMAIL_ADDRESS,
                COLUMN_BUSINESS_VERIFIED,
                COLUMN_BUSINESS_DATE_CREATED
        };
        // sorting orders
        String sortOrder =
                COLUMN_BUSINESS_ID + " ASC";
        ArrayList<Business> businessList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /*
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_BUSINESS, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Business business = new Business();
                business.setBusinessID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_BUSINESS_ID))));
                business.setBusinessName(cursor.getString(cursor.getColumnIndex(COLUMN_BUSINESS_NAME)));
                business.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_BUSINESS_PASSWORD)));
                business.setHqAddress(cursor.getString(cursor.getColumnIndex(COLUMN_BUSINESS_HQ_ADDRESS)));
                business.setContactPhone(Long.parseLong(cursor.getString(cursor.getColumnIndex(COLUMN_BUSINESS_PHONE_NUMBER))));
                business.setContactName(cursor.getString(cursor.getColumnIndex(COLUMN_BUSINESS_CONTACT_NAME)));
                business.setContactEmail(cursor.getString(cursor.getColumnIndex(COLUMN_BUSINESS_EMAIL_ADDRESS)));
                business.setVerified(Boolean.parseBoolean((cursor.getString(cursor.getColumnIndex(COLUMN_BUSINESS_VERIFIED)))));
                business.setDateCreated(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_BUSINESS_DATE_CREATED))));

                // Adding user record to list
                businessList.add(business);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return businessList;
    }

    /**
     * This method to update user record
     *
     * @param business the business
     */
    public void updateBusiness(Business business) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_BUSINESS_ACCOUNT_TYPE, Business.getAccountType());
        values.put(COLUMN_BUSINESS_ID, business.getBusinessID());
        values.put(COLUMN_BUSINESS_NAME, business.getBusinessName());
        values.put(COLUMN_BUSINESS_PASSWORD, business.getPassword());
        values.put(COLUMN_BUSINESS_HQ_ADDRESS, business.getHqAddress());
        values.put(COLUMN_BUSINESS_PHONE_NUMBER, business.getContactPhone());
        values.put(COLUMN_BUSINESS_CONTACT_NAME, business.getContactName());
        values.put(COLUMN_BUSINESS_EMAIL_ADDRESS, business.getContactEmail());
        values.put(COLUMN_BUSINESS_VERIFIED, business.isVerified());
        values.put(COLUMN_BUSINESS_DATE_CREATED, business.getDateCreated().toString());


        // updating row
        db.update(TABLE_BUSINESS, values, COLUMN_BUSINESS_ID + " = ?",
                new String[]{String.valueOf(business.getBusinessID())});
        Log.e(TAG, "UPDATED USER FROM BUSINESS TABLE");
        Log.e(TAG, business.toString());
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param business the business
     */
    public void deleteBusiness(Business business) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_BUSINESS, COLUMN_BUSINESS_ID + " = ?",
                new String[]{String.valueOf(business.getBusinessID())});
        Log.e(TAG, "DELETED USER FROM BUSINESS TABLE");
        Log.e(TAG, business.toString());
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email    the email
     * @param password the password
     * @return true /false
     */
    public boolean checkBusinessCredentials(String email, String password) {

        if (isNetworkAvailable()) {
            FirebaseDatabaseHelper.loginUser(email, password);
        }
        // array of columns to fetch
        String[] columns = {
                COLUMN_BUSINESS_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_BUSINESS_EMAIL_ADDRESS + " = ?" + " AND " + COLUMN_BUSINESS_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_BUSINESS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        return cursorCount > 0;

    }

    /**
     * Gets business.
     *
     * @param email the email
     * @return the business
     */
    public Business getBusiness(String email) {
        Business returnedUser = null;
        ArrayList<Business> userArrayList = this.getAllBusinesses();
        for (int i = 0; i < userArrayList.size(); i++) {
            if (userArrayList.get(i).getContactEmail().equals(email)) {
                returnedUser = userArrayList.get(i);
                return returnedUser;
            }
        }
        Log.e(TAG, "SEARCH USER FROM BUSINESS TABLE");
        Log.e(TAG, returnedUser.toString());
        return returnedUser;
    }

    //************************************** APPLICATION CRUD FUNCTIONALITY *********************************************//

    /**
     * This method is to create application record
     *
     * @param application the application
     */
    public void addApplication(Application application) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_APPLICATION_ID, application.getApplicationID());
        values.put(COLUMN_APPLICATION_STUDENT_ID, application.getStudentID());
        values.put(COLUMN_APPLICATION_OFFER_ID, application.getOfferID());
        values.put(COLUMN_APPLICATION_DATE, new Timestamp(Calendar.getInstance().getTime().getTime()).toString());

        // Inserting Row
        db.insert(TABLE_APPLICATION, null, values);
        Log.e(TAG, "INSERTED INTO APPLICATION TABLE");
        Log.e(TAG, application.toString());
        db.close();
    }

    /**
     * This method is to fetch all applications and return the list of application records
     *
     * @return list all businesses
     */
    public ArrayList<Application> getAllApplications() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_APPLICATION_ID,
                COLUMN_APPLICATION_STUDENT_ID,
                COLUMN_APPLICATION_OFFER_ID,
                COLUMN_APPLICATION_DATE
        };
        // sorting orders
        String sortOrder =
                COLUMN_APPLICATION_ID + " ASC";
        ArrayList<Application> applicationArrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /*
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_APPLICATION, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Application application = new Application();
                application.setApplicationID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_APPLICATION_ID))));
                application.setStudentID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_APPLICATION_STUDENT_ID))));
                application.setOfferID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_APPLICATION_OFFER_ID))));
                application.setApplicationDate(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_APPLICATION_DATE))));

                // Adding user record to list
                applicationArrayList.add(application);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return applicationArrayList;
    }

    /**
     * This method to update application record
     *
     * @param application the application
     */
    public void updateApplication(Application application) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_APPLICATION_ID, application.getApplicationID());
        values.put(COLUMN_APPLICATION_STUDENT_ID, application.getStudentID());
        values.put(COLUMN_APPLICATION_OFFER_ID, application.getOfferID());
        values.put(COLUMN_APPLICATION_DATE, application.getApplicationID());


        // updating row
        db.update(TABLE_APPLICATION, values, COLUMN_APPLICATION_ID + " = ?",
                new String[]{String.valueOf(application.getApplicationID())});
        Log.e(TAG, "UPDATED APPLICATION FROM APPLICATION TABLE");
        Log.e(TAG, application.toString());
        db.close();
    }

    /**
     * This method is to delete application record
     *
     * @param application the application
     */
    public void deleteApplication(Application application) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_APPLICATION, COLUMN_APPLICATION_ID + " = ?",
                new String[]{String.valueOf(application.getApplicationID())});
        Log.e(TAG, "DELETED APPLICATION FROM APPLICATION TABLE");
        Log.e(TAG, application.toString());
        db.close();
    }

    /**
     * Gets business.
     *
     * @param id the id
     * @return the Application
     */
    public Application getApplication(int id) {
        Application returnedApplication = null;
        ArrayList<Application> applicationArrayList = this.getAllApplications();
        for (int i = 0; i < applicationArrayList.size(); i++) {
            if (applicationArrayList.get(i).getApplicationID() == id) {
                returnedApplication = applicationArrayList.get(i);
                return returnedApplication;
            }
        }
        Log.e(TAG, "SEARCH APPLICATION FROM APPLICATION TABLE");
        Log.e(TAG, returnedApplication.toString());
        return returnedApplication;
    }

    //************************************** OFFER CRUD FUNCTIONALITY *********************************************//

    /**
     * This method is to create application record
     *
     * @param offer the offer
     */
    public void addOffer(Offer offer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_OFFER_ID, offer.getOfferID());
        values.put(COLUMN_OFFER_BUSINESS_ID, offer.getBusinessID());
        values.put(COLUMN_OFFER_CATEGORY_ID, offer.getCategoryID());
        values.put(COLUMN_OFFER_TITLE, offer.getOfferTitle());
        values.put(COLUMN_OFFER_LENGTH, offer.getOfferLength());
        values.put(COLUMN_OFFER_POSITION_QUAL, offer.getOfferPositionQuals());
        values.put(COLUMN_OFFER_LOCATION, offer.getOfferLocation());
        values.put(COLUMN_OFFER_DESCRIPTION, offer.getDescription());
        values.put(COLUMN_OFFER_DATE_CREATED, new Timestamp(Calendar.getInstance().getTime().getTime()).toString());

        // Inserting Row
        db.insert(TABLE_OFFER, null, values);
        Log.e(TAG, "INSERTED INTO OFFER TABLE");
        Log.e(TAG, offer.toString());
        db.close();
    }

    /**
     * This method is to fetch all applications and return the list of application records
     *
     * @return list all businesses
     */
    public ArrayList<Offer> getAllOffers() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_OFFER_ID,
                COLUMN_OFFER_BUSINESS_ID,
                COLUMN_OFFER_CATEGORY_ID,
                COLUMN_OFFER_TITLE,
                COLUMN_OFFER_LENGTH,
                COLUMN_OFFER_POSITION_QUAL,
                COLUMN_OFFER_LOCATION,
                COLUMN_OFFER_DESCRIPTION,
                COLUMN_OFFER_DATE_CREATED
        };
        // sorting orders
        String sortOrder =
                COLUMN_OFFER_ID + " ASC";
        ArrayList<Offer> offerArrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /*
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_OFFER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Offer offer = new Offer();
                offer.setOfferID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_OFFER_ID))));
                offer.setBusinessID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_OFFER_BUSINESS_ID))));
                offer.setCategoryID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_OFFER_CATEGORY_ID))));
                offer.setOfferTitle(cursor.getString(cursor.getColumnIndex(COLUMN_OFFER_TITLE)));
                offer.setOfferLength(cursor.getString(cursor.getColumnIndex(COLUMN_OFFER_LENGTH)));
                offer.setOfferPositionQuals((cursor.getString(cursor.getColumnIndex(COLUMN_OFFER_POSITION_QUAL))));
                offer.setOfferLocation((cursor.getString(cursor.getColumnIndex(COLUMN_OFFER_LOCATION))));
                offer.setDescription((cursor.getString(cursor.getColumnIndex(COLUMN_OFFER_DESCRIPTION))));
                offer.setDateCreated(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_OFFER_DATE_CREATED))));

                // Adding user record to list
                offerArrayList.add(offer);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return offerArrayList;
    }

    /**
     * This method to update application record
     *
     * @param offer the application
     */
    public void updateOffer(Offer offer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_OFFER_BUSINESS_ID, offer.getBusinessID());
        values.put(COLUMN_OFFER_CATEGORY_ID, offer.getCategoryID());
        values.put(COLUMN_OFFER_TITLE, offer.getOfferTitle());
        values.put(COLUMN_OFFER_LENGTH, offer.getOfferLength());
        values.put(COLUMN_OFFER_POSITION_QUAL, offer.getOfferPositionQuals());
        values.put(COLUMN_OFFER_LOCATION, offer.getOfferLocation());
        values.put(COLUMN_OFFER_DESCRIPTION, offer.getDescription());
        values.put(COLUMN_OFFER_DATE_CREATED, offer.getDateCreated().toString());


        // updating row
        db.update(TABLE_OFFER, values, COLUMN_OFFER_ID + " = ?",
                new String[]{String.valueOf(offer.getOfferID())});
        Log.e(TAG, "UPDATED OFFER FROM OFFER TABLE");
        Log.e(TAG, offer.toString());
        db.close();
    }

    /**
     * This method is to delete application record
     *
     * @param offer the offer
     */
    public void deleteOffer(Offer offer) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_OFFER, COLUMN_OFFER_ID + " = ?",
                new String[]{String.valueOf(offer.getOfferID())});
        Log.e(TAG, "DELETED OFFER FROM OFFER TABLE");
        Log.e(TAG, offer.toString());
        db.close();
    }

    /**
     * Gets business.
     *
     * @param id the id
     * @return the Application
     */
    public Offer getOffer(int id) {
        Offer returnedApplication = null;
        ArrayList<Offer> offerArrayList = this.getAllOffers();
        for (int i = 0; i < offerArrayList.size(); i++) {
            if (offerArrayList.get(i).getOfferID() == id) {
                returnedApplication = offerArrayList.get(i);
                return returnedApplication;
            }
        }
        Log.e(TAG, "SEARCH OFFER FROM OFFER TABLE");
        Log.e(TAG, returnedApplication.toString());
        return returnedApplication;
    }

    //************************************** OFFER CATEGORY CRUD FUNCTIONALITY *********************************************//

    /**
     * This method is to create application record
     *
     * @param offerCategory the offer category
     */
    public void addOfferCategory(OfferCategory offerCategory) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY_ID, offerCategory.getCategoryID());
        values.put(COLUMN_CATEGORY_NAME, offerCategory.getCategoryName());

        // Inserting Row
        db.insert(TABLE_OFFER_CATEGORY, null, values);
        Log.e(TAG, "INSERTED INTO OFFER_CATEGORY TABLE");
        Log.e(TAG, offerCategory.toString());
        db.close();
    }

    /**
     * This method is to fetch all applications and return the list of application records
     *
     * @return list all businesses
     */
    public ArrayList<OfferCategory> getAllOfferCategories() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_CATEGORY_ID,
                COLUMN_CATEGORY_NAME
        };
        // sorting orders
        String sortOrder =
                COLUMN_APPLICATION_ID + " ASC";
        ArrayList<OfferCategory> applicationArrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /*
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_OFFER_CATEGORY, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                OfferCategory offerCategory = new OfferCategory();
                offerCategory.setCategoryID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_ID))));
                offerCategory.setCategoryName(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_NAME)));

                // Adding user record to list
                applicationArrayList.add(offerCategory);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return applicationArrayList;
    }

    /**
     * This method to update application record
     *
     * @param offerCategory the offerCategory
     */
    public void updateOfferId(OfferCategory offerCategory) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY_ID, offerCategory.getCategoryID());
        values.put(COLUMN_CATEGORY_NAME, offerCategory.getCategoryName());


        // updating row
        db.update(TABLE_OFFER_CATEGORY, values, COLUMN_CATEGORY_ID + " = ?",
                new String[]{String.valueOf(offerCategory.getCategoryID())});
        Log.e(TAG, "UPDATED OFFER_CATEGORY FROM APPLICATION TABLE");
        Log.e(TAG, offerCategory.toString());
        db.close();
    }

    /**
     * This method is to delete application record
     *
     * @param offerCategory the offerCategory
     */
    public void deleteOfferCategory(OfferCategory offerCategory) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete offer_category record by id
        db.delete(TABLE_OFFER_CATEGORY, COLUMN_CATEGORY_ID + " = ?",
                new String[]{String.valueOf(offerCategory.getCategoryID())});
        Log.e(TAG, "DELETED APPLICATION FROM APPLICATION TABLE");
        Log.e(TAG, offerCategory.toString());
        db.close();
    }

    /**
     * Gets business.
     *
     * @param id the id
     * @return the Application
     */
    public OfferCategory getOfferCategory(int id) {
        OfferCategory returnedOfferCategory = null;
        ArrayList<OfferCategory> applicationArrayList = this.getAllOfferCategories();
        for (int i = 0; i < applicationArrayList.size(); i++) {
            if (applicationArrayList.get(i).getCategoryID() == id) {
                returnedOfferCategory = applicationArrayList.get(i);
                return returnedOfferCategory;
            }
        }
        Log.e(TAG, "SEARCH OFFER_CATEGORY FROM OFFER_CATEGORY TABLE");
        Log.e(TAG, returnedOfferCategory.toString());
        return returnedOfferCategory;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
