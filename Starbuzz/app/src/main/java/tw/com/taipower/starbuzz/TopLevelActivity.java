package tw.com.taipower.starbuzz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

public class TopLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView listView;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> listView, View view, int positon, long id){
                if(positon == 0){
                    Intent intent = new Intent(TopLevelActivity.this, DrinkCategoryActivity.class);
                    startActivity(intent);
                }
            }
        };

        listView = (ListView) findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);


        String[] colors = {"Pink", "Red", "Orange", "Yellow", "Green", "Blue", "Purple"};
        //Spinner spinner = findViewById(R.id.spinner);

    }
}
