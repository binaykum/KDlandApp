package com.example.binaykumar.KDlandApp;
/**
 * Created by Binay Kumar on 21-01-2018.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

/**
 * Created by Binay Kumar on 15-01-2018.
 */

public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context) {
        this.openHelper = new com.example.binaykumar.KDlandApp.DatabaseOpenHelper(context);
    }
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }
    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getReadableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public String res(String v, Editable p){
        //System.out.println(v+p);

        Cursor c=database.rawQuery("SELECT * FROM plot_master WHERE (villageName= '"+ v +"')" +" AND"+ "(plotNo='"+p+"')", null);

        c.moveToFirst();
        switch (c.getCount()) {
            case 0 :
                c.close();
                return "No such plot notified in this village. "+"\n"+"contact Mr Anil Kumar";



            //break;
            case 1:

                Cursor c1=database.rawQuery("SELECT SUM(paidArea)  FROM payment WHERE (villageName= '"+ v +"')" +" AND" + "(plotNo='"+p+"')", null);
                int i=1;
                String s="";
                // Cursor c=database.rawQuery("SELECT * FROM plot_master ", null);
                c.moveToFirst();c1.moveToFirst();
                while (i != c.getColumnCount()) {
                    s= s+ c.getColumnName(i)+":- "+c.getString(i)+"\n";
                    ++i;
                }
                //s=s+"(Row no-"+c.getString(0)+")";
                s=s+"paidArea:-"+c1.getString(0);

                c.close();
                c1.close();

                return (s);


            // return "No of rows/records: " + c.getCount() + "\n" + "Vill name:- " + c.getString(c.getColumnIndex("villageName")) + "\n" + "Plot No:- " + c.getString(c.getColumnIndex("plotNo")) +"\n"+"Khata No:-";
            //    +c.getInt(c.getColumnIndex("khataNo")) +"\n"+"Acquired area:-" ;

            //     +  c.getDouble(c.getColumnIndex("acqdArea"))+"acres"+ "\n"+"Paid Area:-" +c.getDouble(c.getColumnIndex("paidArea"))+"acres"+"\n";

            // break;
            default:
                // System.out.println(c.getCount());
                String st= c.getString(0);
                String vt=String.valueOf(c.getCount());
                c.close();
                return vt+" times this plot in this village. Seems some error. Contact Mr Anil Kumar \n"+st;

        }
    }


    public String res1(String v, Editable p) {


        Cursor c2 = database.rawQuery("SELECT _id, substr(CRno,5),paidArea,SUBSTR(claimantName,1,13)  FROM payment WHERE (villageName= '" + v + "')" + " AND" + "(plotNo='" + p + "')", null);



        //s=s+"(Row no-"+c.getString(0)+")";
        //s=s+"paidArea:-"+c1.getString(0);
        int j = 0;
        String s = "";

        System.out.println(c2.getCount());
        if (c2 != null) {
         //   c2.moveToFirst();

           for (c2.moveToFirst(); !c2.isAfterLast();c2.moveToNext()) {
               s=s+(c2.getPosition()+1)+". ";
                for (j=1;j!=c2.getColumnCount();++j){
                // while (j != c2.getColumnCount()) {
                        s = s +  c2.getString(j)+" -";
                     //   ++j;

                   }
                   s=s+ "\n";
              //  c2.moveToNext();
               Log.i("message to sony","test");
                } //;


            c2.close();
        }


        return (s);

    }

        public List<String> getVillages() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT  distinct trim(villageName) FROM  plot_master ", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

   public List<String> getCRnos(String v, Editable p) {
       List<String> list = new ArrayList<>();
       Cursor cursor = database.rawQuery("SELECT  distinct SUBSTR(CRno,5) FROM  payment WHERE (villageName= '" + v + "')" + " AND" + "(plotNo='" + p + "') ", null);
       cursor.moveToFirst();
       while (!cursor.isAfterLast()) {
           list.add(cursor.getString(0));
           cursor.moveToNext();
       }
       cursor.close();
       return list;

          };


    public List<String> getCRnos1(String v, Editable p) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT  distinct SUBSTR(CRno,5) FROM  payment WHERE (villageName= '" + v + "')" + " AND" + "(khataNo='" + p + "') ", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }

        cursor.close();
        return list;

    };
    public List<String> getClaimants(String v, Editable p) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT   distinct SUBSTR(claimantName,1,18) FROM  payment WHERE (villageName= '" + v + "')" + " AND" + "(plotNo='" + p + "') ", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;


    };
    public List<String> getClaimants1(String v, Editable p) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT   distinct SUBSTR(claimantName,1,18) FROM  payment WHERE (villageName= '" + v + "')" + " AND" + "(khataNo='" + p + "') ", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;


    };

    public String res2(String v) {

        Cursor c3 = database.rawQuery("SELECT   _id, SUBSTR(villageName,1,6), khataNo,plotNo, paidArea,SUBSTR(claimantName,1,15) FROM payment WHERE (SUBSTR(CRno,5)= '" + v + "')" , null);
        Cursor c31 = database.rawQuery("SELECT   SUM(paidArea) FROM payment WHERE (SUBSTR(CRno,5)= '" + v + "')" , null);
        int j = 0;
        String s = "";
        s=" "+v;
       if (c31.moveToFirst()){ s=s+":-   Total paid area="+c31. getString(0)+"\n"+"\n";}

        s=s+"  village - khata - plot - paidArea - claimant"+"\n"+"\n";

         if (c3 != null) {

            for (c3.moveToFirst(); !c3.isAfterLast();c3.moveToNext()) {
                s=s+(c3.getPosition()+1)+". ";
                for (j=1;j!=c3.getColumnCount();++j){
                    s = s +  c3.getString(j)+" - ";

                }
                s = s.replace(s.substring(s.length()-1), "");
                s=s+ "\n";
            }

            c3.close();
            c31.close();
        } else {s="null";}

       return (s);
    }

    public String res3(String v) {

        Cursor c4 = database.rawQuery("SELECT   _id, SUBSTR(villageName,1,6), khataNo,plotNo, paidArea, substr(CRno,5) FROM payment WHERE (  SUBSTR(claimantName,1,12)= '" + v + "')" , null);
        Cursor c41 = database.rawQuery("SELECT   SUM(paidArea) FROM payment WHERE (  SUBSTR(claimantName,1,12)= '" + v + "')" , null);
        int j = 0;
        String s = "";
        s="  "+v;
        if (c41.moveToFirst()){ s=s+"     Total paid area="+c41. getString(0)+"\n"+"\n";}


        s=s+"  village - khata - plot - paidArea-  CRno"+"\n"+"\n";

        if (c4 != null) {

            for (c4.moveToFirst(); !c4.isAfterLast();c4.moveToNext()) {
                s=s+(c4.getPosition()+1)+". ";
                for (j=1;j!=c4.getColumnCount();++j){
                    s = s +  c4.getString(j)+" - ";

                }
                s = s.replace(s.substring(s.length()-1), "");
                s=s+ "\n";
            }

            c4.close();
        } else {s="null";}

        return (s);
    }

    public List<String> getPlots(String item) {

        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT TRIM(plotNo)  FROM  plot_master where villagename='"+item+"' ORDER BY LENGTH(plotno),  plotNo", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
                              cursor.moveToNext();
        }


        cursor.close();
        return list;


    }

    public List<String> getKhatas(String item) {

        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT  distinct TRIM(khataNo)  FROM  plot_master where villagename='"+item+"' ORDER BY LENGTH(plotno),  plotNo", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }


        cursor.close();
        return list;


    }
    public String res4(String v, Editable p){
        //System.out.println(v+p); for khata searching

        Cursor c=database.rawQuery("SELECT * FROM plot_master WHERE (villageName= '"+ v +"')" +" AND"+ "(khataNo='"+p+"')", null);
        c.moveToFirst();
        switch (c.getCount()) {
            case 0:
                c.close();
                return "No such Khata notified in this village. " + "\n" + "contact Mr Ramakant behra";


            //break;
            default:

                Cursor c1 = database.rawQuery("SELECT SUM(paidArea)  FROM payment WHERE (villageName= '" + v + "')" + " AND" + "(khataNo='" + p + "')", null);
                Cursor c2 = database.rawQuery("SELECT SUM(acqdArea)  FROM plot_master WHERE (villageName= '" + v + "')" + " AND" + "(khataNo='" + p + "')", null);

                int i = 1;
                String s = "";
                // Cursor c=database.rawQuery("SELECT * FROM plot_master ", null);
                c.moveToFirst();
                c1.moveToFirst();
                c2.moveToFirst();

                s= s + "villageName:-  "+ c.getString(c.getColumnIndex("villageName"))
                        +"\nkhataNo:-  "+ c.getString(c.getColumnIndex("khataNo"))
                        +"\nlandType:-  "+ c.getString(c.getColumnIndex("landType"))
                        ;


          /*      while (i != c.getColumnCount() )  {

                        s = s + c.getColumnName(i) + ":- " + c.getString(i) + "\n";
                        ++i;
                }
                */
       //         s=s+c.getString(c.getColumnIndex("villageName"))  ;

//                System.out.println("res4" + s);
  //              System.out.println("res4  c1" + "SELECT SUM(paidArea)  FROM payment WHERE (villageName= '" + v + "')" + " AND" + "(khataNo='" + p + "')");
                //s=s+"(Row no-"+c.getString(0)+")";
                s = s + "\nnoOfPlots:- " + String.valueOf(c.getCount());
                s = s + "\nacqdArea:- " + c2.getString(0);
                s = s + "\npaidArea:-" + c1.getString(0);
                s = s + "\nnoOfCRsPaid:- " +  getCRnos1(v, p).size() ;
                s = s + "\nnoOfClaimantsPaid:- " +  getClaimants1(v, p).size() ;
                s = s + "\n\n";

                c.moveToFirst();
                s=s+ "plotNo "+ "acqdArea "+ "paidArea "+"priorY " + "priorP" +"\n";
                while (!c.isAfterLast()) {
                   // list.add(cursor.getString(0));

                    s=s+ "  " +c.getString(c.getColumnIndex("plotNo"))+"     "
                        + c.getString(c.getColumnIndex("acqdArea"))+"     ";
                    String q= c.getString(c.getColumnIndex("plotNo"));
                    Cursor c3 = database.rawQuery("SELECT SUM(paidArea)  FROM payment WHERE (villageName= '" + v + "')" + " AND" + "(plotNo='" + q + "')", null);
                    c3.moveToFirst();


                    s=s+c3.getString(0)+ "    ";c3.close();
                    s=s+c.getString(c.getColumnIndex("priorityYear"))+"     "
                    + c.getString(c.getColumnIndex("priorityPurpose"))+"    "
                    ;

                    s=s+ "\n";

                    c.moveToNext();

                }

                System.out.println("test"+s);
                c.close();
                c1.close();
                c2.close();

                return (s);


            // return "No of rows/records: " + c.getCount() + "\n" + "Vill name:- " + c.getString(c.getColumnIndex("villageName")) + "\n" + "Plot No:- " + c.getString(c.getColumnIndex("plotNo")) +"\n"+"Khata No:-";
            //    +c.getInt(c.getColumnIndex("khataNo")) +"\n"+"Acquired area:-" ;

            //     +  c.getDouble(c.getColumnIndex("acqdArea"))+"acres"+ "\n"+"Paid Area:-" +c.getDouble(c.getColumnIndex("paidArea"))+"acres"+"\n";

            // break;
   //TODO: something         default:
                // System.out.println(c.getCount());
             //   String st= c.getString(0);
               // String vt=String.valueOf(c.getCount());
               // c.close();
               // return vt+" times this plot in this village. Seems some error. Contact Mr Ramakant behra \n"+st;

        }


    }
    public String res5(String v, Editable p) {


        Cursor c2 = database.rawQuery("SELECT _id, substr(CRno,5),plotNo,paidArea,SUBSTR(claimantName,1,13)  FROM payment WHERE (villageName= '" + v + "')" + " AND" + "(khataNo='" + p + "')", null);



        //s=s+"(Row no-"+c.getString(0)+")";
        //s=s+"paidArea:-"+c1.getString(0);
        int j = 0;
        String s = "";

        System.out.println(c2.getCount());
        if (c2 != null) {
            //   c2.moveToFirst();

            for (c2.moveToFirst(); !c2.isAfterLast();c2.moveToNext()) {
                s=s+(c2.getPosition()+1)+". ";
                for (j=1;j!=c2.getColumnCount();++j){
                    // while (j != c2.getColumnCount()) {
                    s = s +  c2.getString(j)+" -";
                    //   ++j;

                }
                s=s+ "\n";
                //  c2.moveToNext();
                Log.i("message to sony","test");
            } //;


            c2.close();
        }


        return (s);

    }





}
