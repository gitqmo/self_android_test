package tw.com.taipower.bitsandpizzas;

import android.app.ActionBar;
import android.os.Bundle;

public class OrderActivity extends LogTraceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar;

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_order);

        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
