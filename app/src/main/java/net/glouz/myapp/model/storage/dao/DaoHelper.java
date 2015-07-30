package net.glouz.myapp.model.storage.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.glouz.myapp.model.api.service.RequestServiceBase;

import java.io.File;

/**
 * Created by glouzaille on 06/05/2015.
 */
public class DaoHelper extends BaseDao {

    /**
     * checks that the database has been created.
     *
     * @param context
     * @return
     */
    public static boolean isDatabaseExists(Context context) {

        File dbFile = context.getDatabasePath(RequestServiceBase.DATABASE_NAME);

        if (dbFile.exists()){
            return true;
        }

        return false;
    }

    /**
     * checks if a table has been created.
     *
     * @param context
     * @param tableName
     * @return
     */
    public static boolean isTableExists(Context context, String tableName) {

        String dbPath;
        SQLiteDatabase sQLiteDatabase;

        if (isDatabaseExists(context)){
            dbPath = context.getDatabasePath(RequestServiceBase.DATABASE_NAME).getAbsolutePath();

            if (dbPath !=null && !dbPath.isEmpty()) {
                sQLiteDatabase = SQLiteDatabase.openDatabase(dbPath, null,
                        SQLiteDatabase.OPEN_READONLY);

                if (sQLiteDatabase != null) {
                    Cursor cursor = sQLiteDatabase.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);
                    if (cursor != null) {
                        if (cursor.getCount() > 0) {
                            cursor.close();
                            sQLiteDatabase.close();
                            return true;
                        }
                        cursor.close();
                        sQLiteDatabase.close();
                    }
                }
            }
        }

        return false;
    }
}