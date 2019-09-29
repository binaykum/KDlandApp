
package com.example.binaykumar.landcb;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


/**
 * Created by Binay Kumar on 15-01-2018.
 */

public class DatabaseOpenHelper extends SQLiteAssetHelper{
    private static final String Database_name="landCBCMP2.db";


    public DatabaseOpenHelper(Context context) {
        super(context, Database_name, null, 1);
    }

}
