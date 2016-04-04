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
        Log.d("LogTrace", this.getLocalClassName() + ":\t\tonCreate()");
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d("LogTrace", this.getLocalClassName() + ":\t\tonRestoreInstanceState()");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        Log.d("LogTrace", this.getLocalClassName() + ":\t\tonPostCreate()");
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        Log.d("LogTrace", this.getLocalClassName() + ":\t\tonStart()");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d("LogTrace", this.getLocalClassName() + ":\t\tonRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.d("LogTrace", this.getLocalClassName() + ":\t\tonResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("LogTrace", this.getLocalClassName() + ":\t\tonPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("LogTrace", this.getLocalClassName() + ":\t\tonStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("LogTrace", this.getLocalClassName() + ":\t\tonDestroy()");
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d("LogTrace", this.getLocalClassName() + ":\t\tonConfigurationChanged()");
        super.onConfigurationChanged(newConfig);
    }
}
