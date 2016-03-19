package tw.com.taipower.starbuzz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkActivity extends AppCompatActivity {
    public static final String EXTRA_DRINKNO = "DrinkNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        int drinkNo;
        ImageView photo;
        TextView name, description;


        //從intent取得飲料資訊
        drinkNo = ((Long) this.getIntent().getExtras().get(EXTRA_DRINKNO)).intValue();
        Drink drink = Drink.drinks[drinkNo];

        //填寫飲料圖像
        photo = (ImageView) this.findViewById(R.id.photo);
        photo.setImageResource(drink.getImageRescourceId());
        photo.setContentDescription(drink.getDescription());

        //填寫飲料名稱
        name = (TextView) this.findViewById(R.id.name);
        name.setText(drink.getName());

        //填寫飲料敘述
        description = (TextView) this.findViewById(R.id.description);
        description.setText(drink.getDescription());

    }
}
