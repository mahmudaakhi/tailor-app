package com.example.doorsteptailors;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.se.omapi.Session;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="NewIsdDataBase.db";

    private static final int VERSION_NUMBER=8;



    private static final String NEW_TAILOR_TABLE="New_Tailor_Details";
    private static final String NEW_T_ID="T_id";
    private static final String NEW_T_NAME="New_T_Name";
    private static final String NEW_T_USERNAME="New_T_Username";
    private static final String NEW_T_ADDRESS="New_T_Address";
    private static final String NEW_T_CONTACT_NUMBER="New_T_Contact_number";
    private static final String NEW_T_EMAIL="New_T_Email";
    private static final String NEW_T_PASSWORD="New_T_Password";
    private static final String NEW_T_SHOP_NAME="New_T_Shop_name";
    private static final String NEW_T_WORKING_EXPERIENCE="New_T_Working_Experience";
    private static final String NEW_T_AVAILABILITY="New_T_Availability";
    private static final String NEW_T_RATING="New_T_Rating";




    private static final String CUSTOMER_TABLE="Customer_Details";
    private static final String C_ID="C_id";
    private static final String C_NAME="C_Name";
    private static final String C_USERNAME="C_Username";
    private static final String C_ADDRESS="C_Address";
    private static final String C_CONTACT_NUMBER="C_Contact_number";
    private static final String C_EMAIL="C_Email";
    private static final String C_PASSWORD="C_Password";
    private static final String C_RATING="C_Rating";


    private static final String DeliveryMan_TABLE="DeliveryMan_Details";
    private static final String D_ID="D_id";
    private static final String D_NAME="D_Name";
    private static final String D_CONTACT_NUMBER="D_Contact_number";
    private static final String D_GENDER="D_Gender";
    private static final String T_FOREIGN_KEY="T_FOREIGN_KEY";




    private Context context;

    //private static final String CREATE_TABLE_TAILOR= "CREATE TABLE "+TAILOR_TABLE+"("+T_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+T_NAME+" VARCHAR(255) NOT NULL,"+T_USERNAME+" VARCHAR(255) NOT NULL,"+T_ADDRESS+" VARCHAR(255) NOT NULL,"+T_CONTACT_NUMBER+" TEXT NOT NULL,"+T_EMAIL+" TEXT NOT NULL,"+T_PASSWORD+" TEXT NOT NULL,"+T_SHOP_NAME+" TEXT NOT NULL,"+T_WORKING_EXPERIENCE+" TEXT NOT NULL,"+T_AVAILABILITY+"TEXT NOT NULL,"+T_RATING+"TEXT NOT NULL);";
    //private static final String DROP_TABLE_TAILOR ="DROP TABLE IF EXISTS "+TAILOR_TABLE;
    private static final String NEW_CREATE_TABLE_TAILOR="CREATE TABLE "+NEW_TAILOR_TABLE+"("+NEW_T_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+NEW_T_NAME+" VARCHAR(255) NOT NULL,"+NEW_T_USERNAME+" VARCHAR(255) NOT NULL,"+NEW_T_ADDRESS+" VARCHAR(255) NOT NULL,"+NEW_T_CONTACT_NUMBER+" TEXT NOT NULL,"+NEW_T_EMAIL+" TEXT NOT NULL,"+NEW_T_PASSWORD+" TEXT NOT NULL,"+NEW_T_SHOP_NAME+" TEXT NOT NULL,"+NEW_T_WORKING_EXPERIENCE+" TEXT NOT NULL,"+NEW_T_AVAILABILITY+" TEXT NOT NULL,"+NEW_T_RATING+" TEXT NOT NULL);";
    private static final String NEW_DROP_TABLE_TAILOR ="DROP TABLE IF EXISTS "+NEW_TAILOR_TABLE;
    private static final String CREATE_TABLE_CUSTOMER= "CREATE TABLE "+CUSTOMER_TABLE+"("+C_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+C_NAME+" VARCHAR(255) NOT NULL,"+C_USERNAME+" VARCHAR(255) NOT NULL,"+C_ADDRESS+" VARCHAR(255) NOT NULL,"+C_CONTACT_NUMBER+" TEXT NOT NULL,"+C_EMAIL+" TEXT NOT NULL,"+C_PASSWORD+" TEXT NOT NULL);";
    private static final String DROP_TABLE_CUSTOMER ="DROP TABLE IF EXISTS "+CUSTOMER_TABLE;

    private static final String CREATE_TABLE_DELIVERYMAN= "CREATE TABLE "+DeliveryMan_TABLE+"("+D_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+T_FOREIGN_KEY+" int NOT NULL,"+D_NAME+" VARCHAR(255) NOT NULL,"+D_CONTACT_NUMBER+" TEXT NOT NULL,"+D_GENDER+" TEXT NOT NULL);";
    private static final String DROP_TABLE_DELIVERYMAN = "DROP TABLE IF EXISTS "+DeliveryMan_TABLE;


    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,VERSION_NUMBER);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL(CREATE_TABLE_DELIVERYMAN);
            sqLiteDatabase.execSQL(NEW_CREATE_TABLE_TAILOR);
            sqLiteDatabase.execSQL(CREATE_TABLE_CUSTOMER);
            Toast.makeText(context,"onCreate is called", Toast.LENGTH_LONG).show();
        }
        catch(Exception e) {
            Toast.makeText(context,"Exception:"+e, Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try{
            Toast.makeText(context,"onUpgrade is called", Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(NEW_DROP_TABLE_TAILOR);
            sqLiteDatabase.execSQL(DROP_TABLE_DELIVERYMAN);
            sqLiteDatabase.execSQL(DROP_TABLE_CUSTOMER);
            onCreate(sqLiteDatabase);

        }
        catch(Exception e) {
            Toast.makeText(context,"Exception:"+e, Toast.LENGTH_LONG).show();

        }
    }
    public long InsertTailor(UserDetails user)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NEW_T_NAME,user.getname());
        values.put(NEW_T_USERNAME,user.getusername());
        values.put(NEW_T_ADDRESS,user.getaddress());
        values.put(NEW_T_CONTACT_NUMBER,user.getConNo());
        values.put(NEW_T_EMAIL,user.getEmail());
        values.put(NEW_T_PASSWORD,user.getPw());
        values.put(NEW_T_SHOP_NAME,user.getShopName());
        values.put(NEW_T_WORKING_EXPERIENCE,user.getWorkingExperience());
        values.put(NEW_T_AVAILABILITY,user.getAvailability());
        values.put(NEW_T_RATING,user.getRating());

        long rowID=sqLiteDatabase.insert(NEW_TAILOR_TABLE,null,values);

        return rowID;
    }

    public long InsertCustomer(UserDetails user)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(C_NAME,user.getname());
        values.put(C_USERNAME,user.getusername());
        values.put(C_ADDRESS,user.getaddress());
        values.put(C_CONTACT_NUMBER,user.getConNo());
        values.put(C_EMAIL,user.getEmail());
        values.put(C_PASSWORD,user.getPw());

        long rowID=sqLiteDatabase.insert(CUSTOMER_TABLE,null,values);

        return rowID;
    }
    public Boolean SignInTailor(String uname, String pass)
    {
        SQLiteDatabase sqLiteDatabase =this.getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT*FROM "+NEW_TAILOR_TABLE,null);
        Boolean result=false;
        if(cursor.getCount()==0)
        {
            Toast.makeText(context,"No data is found",Toast.LENGTH_LONG).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                //Session["T_Id"]=cursor.getString(0)
                String username=cursor.getString(2);
                String password=cursor.getString(6);

                if(username.equals(uname)&& password.equals(pass))
                {
                    result=true;
                    break;
                }
            }
        }
        return result;
    }
    public Boolean SignInCustomer(String uname, String pass)
    {
        //Toast.makeText(context,"Database is working",Toast.LENGTH_LONG).show();

        SQLiteDatabase sqLiteDatabase =this.getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT*FROM "+CUSTOMER_TABLE,null);
        Boolean result=false;
        if(cursor.getCount()==0)
        {
            Toast.makeText(context,"No data is found",Toast.LENGTH_LONG).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                //Toast.makeText(context,"Searching",Toast.LENGTH_LONG).show();

                String username=cursor.getString(2);
                //Toast.makeText(context,uname,Toast.LENGTH_LONG).show();
                //Toast.makeText(context,username,Toast.LENGTH_LONG).show();

                String password=cursor.getString(6);
                //Toast.makeText(context,password,Toast.LENGTH_LONG).show();
                //Toast.makeText(context,pass,Toast.LENGTH_LONG).show();

                if(username.equals(uname)&& password.equals(pass))
                {
                    result=true;
                    break;
                }
            }
        }
        return result;
    }

    public Cursor showAllData()
    {
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+NEW_TAILOR_TABLE,null);
        return cursor;
    }

    /*public long insertDeliveryMan(DeliveryManInfo deliveryManInfo)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(C_NAME,user.getname());
        values.put(C_USERNAME,user.getusername());
        values.put(C_ADDRESS,user.getaddress());


        long rowID=sqLiteDatabase.insert(CUSTOMER_TABLE,null,values);

        return rowID;
    }*/

}
