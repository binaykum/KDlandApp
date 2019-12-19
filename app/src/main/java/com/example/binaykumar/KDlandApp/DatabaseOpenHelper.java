
package com.example.binaykumar.KDlandApp;
import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


/**
 * Created by Binay Kumar on 15-01-2018.
 */

public class DatabaseOpenHelper extends SQLiteAssetHelper{


    private static final String Database_name="cbaKDCMP.db";


    public DatabaseOpenHelper(Context context) {
        super(context, Database_name, null, 1);
    }

}
