package tw.com.taipower.workout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DetailActivity extends LogTraceActivity {
    public static final String EXTRA_WORKOUT_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("LogTrace", this.getLocalClassName() + ":onCreate()");
        int workoutId;
        WorkoutDetailFragment workoutDetailFragment;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        workoutDetailFragment = (WorkoutDetailFragment) this.getFragmentManager().findFragmentById(R.id.detail_fragment);
        workoutId = (int) this.getIntent().getExtras().get(EXTRA_WORKOUT_ID);
        workoutDetailFragment.setWorkoutId(workoutId);
    }
}
