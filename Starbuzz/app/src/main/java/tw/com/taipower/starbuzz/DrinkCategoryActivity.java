package tw.com.taipower.starbuzz;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/* 因為是繼承【ListActivity】，所以有以下2項優點
 * 1.無須建立自己的layout，也就是不須自行撰寫【setContentView()方法】
 * 2.不必自己實作事件監聽器，也就是不需自行撰寫【onListItemClick()方法】
 */
public class DrinkCategoryActivity extends ListActivity {
    private SQLiteDatabase sqLiteDatabase;  //宣告成class元素，是因為能夠在onDestory()中關閉cursor與資料庫。
    private Cursor cursor;                  //宣告成class元素，是因為能夠在onDestory()中關閉cursor與資料庫。

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listDrinks;
        listDrinks = this.getListView();

//        this.getData(listDrinks);
        this.getDataFromDatabase(listDrinks);
    }

    /**
     * 從資料庫讀取出飲料類型
     *
     * @param listDrinks
     */
    private void getDataFromDatabase(ListView listDrinks) {
        SQLiteOpenHelper starbuzzDatabaseHelper;
        CursorAdapter cursorAdapter;

        try {
            starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            this.sqLiteDatabase = starbuzzDatabaseHelper.getReadableDatabase();
            this.cursor = this.sqLiteDatabase.query("DRINK",
                    new String[]{"_id", "NAME"},
                    null,
                    null,
                    null,
                    null,
                    null);
            cursorAdapter = new SimpleCursorAdapter(
                    DrinkCategoryActivity.this,
                    android.R.layout.simple_list_item_1,
                    this.cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0);
            listDrinks.setAdapter(cursorAdapter);
        } catch (SQLiteException e) {
            Toast.makeText(this, this.getClass().toString() + ":Database unavailable", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        Intent intent = new Intent();

        intent.setClass(this, DrinkActivity.class);

        //因為drinks陣列裡面有3個Drink物件，所以id會回傳畫面所點擊的0、1、2
        //Log.d("onListItemClick id:", String.valueOf(id));

        intent.putExtra(DrinkActivity.EXTRA_DRINKNO, id);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        sqLiteDatabase.close();
    }

    /**
     * 從事先定義好的Java Class取出飲料類型
     *
     * @param listDrinks
     */
    private void getData(ListView listDrinks) {
        ArrayAdapter<Drink> listAdaper;
        listAdaper = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,    //Android內建ListView的一種樣式
                Drink.drinks);
        listDrinks.setAdapter(listAdaper);
    }
}
