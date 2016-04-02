package tw.com.taipower.starbuzz;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends LogTraceActivity {
    public static final String EXTRA_DRINKNO = "DrinkNo";

    private int drinkNo;
    private ImageView photo;
    private TextView name, description;
    private CheckBox favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_drink);

        this.initial();
        this.defaultSetting();

        //方法一：從事先定義好的Java Class取出飲料基本資訊
//        Drink drink;
//        drink = this.getData(drinkNo);
//        this.setData(drink.getName(), drink.getDescription(), drink.getImageRescourceId(), drink.getDescription());

        //方法二：從資料庫讀取出飲料基本資訊
        this.getDataFromDatabase();
    }

    /**
     * 物件初始化
     */
    private void initial() {
        this.photo = (ImageView) this.findViewById(R.id.photo);
        this.name = (TextView) this.findViewById(R.id.name);
        this.description = (TextView) this.findViewById(R.id.description);
        this.favorite = (CheckBox) this.findViewById(R.id.favorite);
    }

    private void defaultSetting() {
        //從intent取得飲料id
        this.drinkNo = ((Long) this.getIntent().getExtras().get(EXTRA_DRINKNO)).intValue();
    }

    /**
     * 從資料庫讀取出飲料基本資訊
     */
    private void getDataFromDatabase() {
        SQLiteOpenHelper starbuzzDatabaseHelper;
        SQLiteDatabase sqLiteDatabase;
        Cursor cursor;

        try {
            starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            sqLiteDatabase = starbuzzDatabaseHelper.getReadableDatabase();
//            sqLiteDatabase = starbuzzDatabaseHelper.getWritableDatabase();

            cursor = sqLiteDatabase.query("DRINK",
                    new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID, FAVORITE"},
                    "_id=?",
                    new String[]{String.valueOf(this.drinkNo)},
                    null, null, null);

            if (cursor.moveToFirst()) {
                String name, description;
                int photo;
                boolean isFavorite;

                name = cursor.getString(0);
                description = cursor.getString(1);
                photo = cursor.getInt(2);
                isFavorite = (cursor.getInt(3) == 1);   //1：TRUE、0：FALSE

                this.setData(name, description, photo, name, isFavorite);
            }

            cursor.close();                 //關閉cursor
            sqLiteDatabase.close();         //關閉資料庫
            starbuzzDatabaseHelper.close();
        } catch (SQLiteException e) {
            Toast.makeText(this, this.getClass().toString() + ":Database unavailable", Toast.LENGTH_SHORT).show();
            Log.d("LogTrace", this.getClass().toString() + ":Database unavailable");
        }
    }

    /**
     * 設定GUI元件的基本資訊
     *
     * @param name
     * @param description
     * @param imageId
     * @param imageDescription
     * @param isFavorite
     */
    private void setData(String name, String description, Integer imageId, String imageDescription, boolean isFavorite) {
        //填寫飲料名稱
        this.name.setText(name);

        //填寫飲料敘述
        this.description.setText(description);

        //填寫飲料圖像
        this.photo.setImageResource(imageId);
        this.photo.setContentDescription(imageDescription);

        //為favorite核取方塊填值
        this.favorite.setChecked(isFavorite);
    }

    /**
     * 從事先定義好的Java Class取出飲料基本資訊
     *
     * @param id
     * @return
     */
    private Drink getData(int id) {
        //取得飲料資訊
        return Drink.drinks[id];
    }

    /**
     * 設定GUI元件的基本資訊
     *
     * @param name
     * @param description
     * @param imageId
     * @param imageDescription
     */
    private void setData(String name, String description, Integer imageId, String imageDescription) {
        //填寫飲料名稱
        this.name.setText(name);

        //填寫飲料敘述
        this.description.setText(description);

        //填寫飲料圖像
        this.photo.setImageResource(imageId);
        this.photo.setContentDescription(imageDescription);
    }

    /**
     * 當checkbox被點擊時，更新資料庫。
     *
     * @param view
     */
    public void onFavoriteClicked(View view) {
        //方法一：在Main Thread中執行存取資料庫，有可能造成App變慢。
//        this.updateDatabase();

        //方法二：使用背景執行緒來更新資料庫，可不影響Main Thread與使用者互動。
        new UpdateDrinkTask().execute(this.drinkNo);
    }

    /**
     * 把checkbox被點擊的isChecked值存入資料庫。
     */
    private void updateDatabase() {
        ContentValues drinkValues;
        SQLiteOpenHelper starbuzzDatabaseHelper;

        drinkValues = new ContentValues();
        drinkValues.put("FAVORITE", this.favorite.isChecked());
        starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);

        try {
            SQLiteDatabase sqLiteDatabase;

            sqLiteDatabase = starbuzzDatabaseHelper.getWritableDatabase();
            sqLiteDatabase.update("DRINK", drinkValues, "_id = ?", new String[]{String.valueOf(this.drinkNo)});
            sqLiteDatabase.close();
        } catch (SQLiteException e) {
            Toast.makeText(this, this.getClass().toString() + ":Database unavailable", Toast.LENGTH_LONG).show();
            Log.d("LogTrace", this.getClass().toString() + ":Database unavailable");
        }
    }

    /**
     * 使用AsyncTask的背景執行緒來更新資料庫
     */
    private class UpdateDrinkTask extends AsyncTask<Integer, Void, Boolean> {
        ContentValues drinkValues;

        @Override
        protected Boolean doInBackground(Integer... params) {
            int drinkNo;
            SQLiteOpenHelper sqLiteOpenHelper;

            drinkNo = params[0];
            sqLiteOpenHelper = new StarbuzzDatabaseHelper(DrinkActivity.this);
            try {
                SQLiteDatabase sqLiteDatabase;

                sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
                sqLiteDatabase.update("DRINK",
                        drinkValues,
                        "_id = ?",
                        new String[]{String.valueOf(drinkNo)});
                sqLiteDatabase.close();
                return true;
            } catch (SQLiteException e) {
                Log.d("LogTrace", this.getClass().toString() + ":Database unavailable");
                return false;
            }
        }

        @Override
        protected void onPreExecute() {
            drinkValues = new ContentValues();
            drinkValues.put("FAVORITE", DrinkActivity.this.favorite.isChecked());
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (!success) {
                Toast.makeText(DrinkActivity.this, this.getClass().toString() + ":Database unavailable", Toast.LENGTH_LONG).show();
                Log.d("LogTrace", this.getClass().toString() + ":Database unavailable");
            }
        }
    }
}
