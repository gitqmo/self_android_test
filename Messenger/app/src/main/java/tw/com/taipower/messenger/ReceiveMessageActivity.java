package tw.com.taipower.messenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveMessageActivity extends AppCompatActivity {
    //public static final String EXTRA_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);

        Intent intent = getIntent();
        //String messageText = intent.getStringExtra(EXTRA_MESSAGE);
        String messageText = intent.getStringExtra(Intent.EXTRA_TEXT);
        TextView messageView = (TextView) findViewById(R.id.showMessage);

        messageView.setText(messageText);
    }
}
