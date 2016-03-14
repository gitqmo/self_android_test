package tw.com.taipower.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StopwatchActivity extends AppCompatActivity {
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }

        this.runTimer();
    }

    /**
     * 顯示時間
     */
    private void runTimer(){
        final TextView timeView = (TextView) findViewById(R.id.timeView);
        final Handler handler;

        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours, minutes, second;
                String time;

                hours = seconds / 3600;
                minutes = (seconds % 3600) / 60;
                second = seconds % 60;
                time = String.format("%d:%02d:%02d", hours, minutes, second);
                timeView.setText(time);

                checkTimer();
                handler.postDelayed(this, 1000);
            }
        });
    }

    /**
     * 檢查Timer是否執行
     */
    private void checkTimer(){
        if(this.running){
            seconds++;
        }
    }

    /**
     * Start Button Event
     * @param view
     */
    public void onClickStart(View view){
        this.running = true;
    }

    /**
     * Stop Button Event
     * @param view
     */
    public void onClickStop(View view){
        this.running = false;
    }

    /**
     * Reset Button Event
     * @param view
     */
    public void onClickReset(View view){
        this.running = false;
        this.seconds = 0;
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceSatae){
        saveInstanceSatae.putInt("seconds", seconds);
        saveInstanceSatae.putBoolean("running", running);
        saveInstanceSatae.putBoolean("wasRunning", wasRunning);
    }

//    @Override
//    public void onStart(){
//        super.onStart();
//        if(wasRunning){
//            running = true;
//        }
//    }

    @Override
    public void onResume(){
        super.onResume();
        if(wasRunning){
            running = true;
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        wasRunning = running;
        running = false;
    }

//    @Override
//    public void onStop(){
//        super.onStop();
//        wasRunning = running;
//        running = false;
//    }
}
