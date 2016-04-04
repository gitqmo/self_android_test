package tw.com.taipower.bitsandpizzas;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

/**
 * Created by new on 2016/3/27.
 */
public class LogTraceActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        Log.d("LogTrace", this.getLocalClassName() + ":\tonCreate()");
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d("LogTrace", this.getLocalClassName() + ":\tonRestoreInstanceState()");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        Log.d("LogTrace", this.getLocalClassName() + ":\tonPostCreate()");
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        Log.d("LogTrace", this.getLocalClassName() + ":\tonStart()");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d("LogTrace", this.getLocalClassName() + ":\tonRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.d("LogTrace", this.getLocalClassName() + ":\tonResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("LogTrace", this.getLocalClassName() + ":\tonPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("LogTrace", this.getLocalClassName() + ":\tonStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("LogTrace", this.getLocalClassName() + ":\tonDestroy()");
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d("LogTrace", this.getLocalClassName() + ":\tonConfigurationChanged()");
        super.onConfigurationChanged(newConfig);
    }
}
