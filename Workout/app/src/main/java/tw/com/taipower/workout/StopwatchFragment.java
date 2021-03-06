package tw.com.taipower.workout;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StopwatchFragment extends LogTraceFragment implements View.OnClickListener{
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.setContentView(R.layout.activity_stopwatch); //Fragment不是在onCreate()方法裡設定layout。

        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }

//        this.runTimer();  //還不要呼叫runTimer()，因為尚未設定layout，也就是還沒有任何視圖(View)元件。
    }


    /**
     * 設定Fragment的Layout
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("LogTrace", this.getClass().toString() + ":onCreateView()");
        Button startButton, stopButton, resetButton;
        View layout = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        this.runTimer(layout);

        startButton = (Button) layout.findViewById(R.id.startButton);
        stopButton = (Button) layout.findViewById(R.id.stopButton);
        resetButton = (Button) layout.findViewById(R.id.resetButton);

        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);

        return layout;
    }

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

    @Override
    public void onSaveInstanceState(Bundle saveInstanceSatae){
        Log.d("LogTrace", this.getClass().toString() + ":onSaveInstanceState()");
        saveInstanceSatae.putInt("seconds", seconds);
        saveInstanceSatae.putBoolean("running", running);
        saveInstanceSatae.putBoolean("wasRunning", wasRunning);
    }

    /**
     * 顯示時間
     */
    private void runTimer(View view){
        Log.d("LogTrace", this.getClass().toString() + ":runTimer()");
        final TextView timeView = (TextView) view.findViewById(R.id.timeView);
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

                StopwatchFragment.this.checkTimer();
                handler.postDelayed(this, 1000);
            }
        });
    }

    /**
     * 檢查Timer是否執行
     */
    private void checkTimer(){
//        Log.d("LogTrace", this.getClass().toString() + ":checkTimer()");
        if(this.running){
            seconds++;
        }
    }

    /**
     * Start Button Event
     * @param view
     */
    public void onClickStart(View view){
        Log.d("LogTrace", this.getClass().toString() + ":onClickStart()");
        this.running = true;
    }

    /**
     * Stop Button Event
     * @param view
     */
    public void onClickStop(View view){
        Log.d("LogTrace", this.getClass().toString() + ":onClickStop()");
        this.running = false;
    }

    /**
     * Reset Button Event
     * @param view
     */
    public void onClickReset(View view){
        Log.d("LogTrace", this.getClass().toString() + ":onClickReset()");
        this.running = false;
        this.seconds = 0;
    }

    @Override
    public void onClick(View v) {
        Log.d("LogTrace", this.getClass().toString() + ":onClick()");
        switch(v.getId()){
            case R.id.startButton:
                this.onClickStart(v);
                break;
            case R.id.stopButton:
                this.onClickStop(v);
                break;
            case R.id.resetButton:
                this.onClickReset(v);
                break;
        }
    }
}
