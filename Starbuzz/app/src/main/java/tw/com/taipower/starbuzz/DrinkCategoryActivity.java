package tw.com.taipower.starbuzz;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/* 因為是繼承【ListActivity】，所以有以下2項優點
 * 1.無須建立自己的layout，也就是不須自行撰寫【setContentView()方法】
 * 2.不必自己實作事件監聽器，也就是不需自行撰寫【onListItemClick()方法】
 */
public class DrinkCategoryActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listDrinks;
        ArrayAdapter<Drink> listAdaper;

        listDrinks = this.getListView();
        listAdaper = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,    //Android內建ListView的一種樣式
                Drink.drinks);
        listDrinks.setAdapter(listAdaper);
    }
}
