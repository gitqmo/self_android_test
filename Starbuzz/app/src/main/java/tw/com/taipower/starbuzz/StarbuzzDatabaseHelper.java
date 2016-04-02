package tw.com.taipower.starbuzz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by new on 2016/3/28.
 */
public class StarbuzzDatabaseHelper extends SQLiteOpenHelper {
    public static final String DatabaseName = "Starbuzz";   //資料庫名稱
    public static final int DatabaseVersion = 2;            //資料庫版本

    public StarbuzzDatabaseHelper(Context context) {
        super(context, DatabaseName, null, DatabaseVersion);    //第3個參數是cursor(資料指標)
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.updateMyDatabase(db, 0, DatabaseVersion);
    }

    /**
     * 升級資料庫
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.updateMyDatabase(db, oldVersion, newVersion);
    }

    /**
     * 降級資料庫
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 3) {
            //當資料庫版本為3時要執行的程式碼
        }
    }

    /**
     * 更新資料庫
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("LogTrace", "oldVersion:" + oldVersion + "、newVersion:" + newVersion);
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE DRINK (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "NAME TEXT," +
                    "DESCRIPTION TEXT," +
                    "IMAGE_RESOURCE_ID INTEGER);");
            StarbuzzDatabaseHelper.insertDrink(db, "Latte", "Espresso and steamed milk", R.drawable.latte);
            StarbuzzDatabaseHelper.insertDrink(db, "Cappuccino", "Espresso, hot milk and steamed-milk foam", R.drawable.cappuccino);
            StarbuzzDatabaseHelper.insertDrink(db, "Filter", "Our best drip coffee", R.drawable.filter);
        }

        if (oldVersion < 2) {
            // 在DRINK資料表中新增一欄存放數值【FAVORITE】欄位
            db.execSQL("ALTER TABLE DRINK " +
                    "ADD COLUMN FAVORITE NUMERIC;");
        }
    }

    private static void insertDrink(SQLiteDatabase db, String name, String description, int resourceId) {
        ContentValues drinkValues;

        drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("DRINK", null, drinkValues);
    }
}
