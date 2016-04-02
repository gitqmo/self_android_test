package tw.com.taipower.starbuzz;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class TopLevelActivity extends LogTraceActivity {
    private ListView favoritesListView;

    private SQLiteDatabase sqLiteDatabase;
    private Cursor favoritesCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_top_level);
        this.favoritesListView = (ListView) this.findViewById(R.id.list_favorites);
        this.setListener();

        this.setOptionListView();
        this.setFavoritesListView();

        this.testSpinner();
    }

    private void setListener() {
        this.favoritesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;

                intent = new Intent(TopLevelActivity.this, DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINKNO, id);
                TopLevelActivity.this.startActivity(intent);
            }
        });
    }

    /**
     * 設定飲料選單
     */
    private void setOptionListView() {
        ListView listView;
        AdapterView.OnItemClickListener itemClickListener;

        //為 list_options ListView 建立 OnItemClickListener
        itemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView,
                                    View view,      //被點擊的項目視圖元件(item view)
                                    int position,   //在列表視圖裡的位置(position)
                                    long id) {       //底層資料的列ID(row id)
                if (position == 0) {
                    Intent intent = new Intent(TopLevelActivity.this, DrinkCategoryActivity.class);
                    startActivity(intent);
                }
            }
        };

        //為 list_options ListView 增加偵聽器
        listView = (ListView) findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);
    }

    /**
     * 設定喜愛飲料清單
     */
    private void setFavoritesListView() {
        CursorAdapter favoriteAdapter;

        this.setFavoritesCursor();
        favoriteAdapter = new SimpleCursorAdapter(
                TopLevelActivity.this,
                android.R.layout.simple_list_item_1,
                this.favoritesCursor,
                new String[]{"NAME"},
                new int[]{android.R.id.text1},
                0);
        this.favoritesListView.setAdapter(favoriteAdapter);
    }

    /**
     * 隨堂測驗：嘗試使用下拉式選單(Spinner)
     */
    private void testSpinner() {
        String[] colors = {"Pink", "Red", "Orange", "Yellow", "Green", "Blue", "Purple"};
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                colors
        );
        spinner.setAdapter(adapter);
    }

    /**
     * 從資料庫取得最新的喜愛飲料清單
     *
     * @return
     */
    private void setFavoritesCursor() {
        SQLiteOpenHelper starbuzzDatabaseHelper;
        try {
            starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            this.sqLiteDatabase = starbuzzDatabaseHelper.getReadableDatabase();
            this.favoritesCursor = this.sqLiteDatabase.query(
                    "DRINK",
                    new String[]{"_id", "NAME"},
                    "FAVORITE = 1",
                    null, null, null, null);
        } catch (SQLiteException e) {
            Toast.makeText(this, this.getClass().toString() + ":Database unavailable", Toast.LENGTH_LONG).show();
            Log.d("LogTrace", this.getClass().toString() + ":Database unavailable");
        }
    }

    @Override
    protected void onRestart() {
        CursorAdapter favoriteAdapter;
        super.onRestart();

        //重新再從資料庫取的favorite=1的飲料
        this.setFavoritesCursor();

        //從favoritesListView取得CursorAdapter，因為接下來要置換成新的CursorAdapter資料
        favoriteAdapter = (CursorAdapter) this.favoritesListView.getAdapter();

        //將CursorAdapter使用的Cursor改變成新版本
        favoriteAdapter.changeCursor(this.favoritesCursor);
        this.favoritesListView.setAdapter(favoriteAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //當Activity被銷毀時，關閉Cursor與資料庫。
        if (this.favoritesCursor != null || this.sqLiteDatabase != null) {
            this.favoritesCursor.close();
            this.sqLiteDatabase.close();
        } else {
            Log.d("LogTrace", this.getClass().toString() + ":favoritesCursor and sqLiteDatabase are null.");
        }
    }
}
