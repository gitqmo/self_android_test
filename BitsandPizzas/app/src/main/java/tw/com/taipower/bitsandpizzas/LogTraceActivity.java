package tw.com.taipower.bitsandpizzas;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

/**
 * Created by new on 2016/3/27.
 */
public class LogTraceActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        Log.d("LogTrace", this.getLocalClassName() + ":onCreate()");
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onStart() {
        Log.d("LogTrace", this.getLocalClassName() + ":onStart()");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d("LogTrace", this.getLocalClassName() + ":onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.d("LogTrace", this.getLocalClassName() + ":onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("LogTrace", this.getLocalClassName() + ":onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("LogTrace", this.getLocalClassName() + ":onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("LogTrace", this.getLocalClassName() + ":onDestroy()");
        super.onDestroy();
    }
}
