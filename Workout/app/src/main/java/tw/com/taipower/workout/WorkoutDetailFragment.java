package tw.com.taipower.workout;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutDetailFragment extends LogTraceFragment {
    private long workoutId;

    public WorkoutDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("LogTrace", this.getClass().toString() + ":onCreateView()");
        if(savedInstanceState != null){
            this.workoutId = savedInstanceState.getLong("WorkoutId");
        }else{
            FragmentTransaction fragment;
            StopwatchFragment stopwatchFragment;

            fragment = this.getChildFragmentManager().beginTransaction();
            stopwatchFragment = new StopwatchFragment();

            fragment.replace(R.id.stopwatch_container, stopwatchFragment);
            fragment.addToBackStack(null);
            fragment.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragment.commit();
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }

    @Override
    public void onStart() {
        View view;

        super.onStart();
        view = this.getView();

        if(view != null){
            TextView titleView, descriptionView;
            Workout workout;

            titleView = (TextView) view.findViewById(R.id.textTitle);
            descriptionView = (TextView) view.findViewById(R.id.textDescription);
            workout = Workout.workouts[((int) workoutId)];

            titleView.setText(workout.getName());
            descriptionView.setText(workout.getDescription());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        Log.d("LogTrace", this.getClass().toString() + ":onSaveInstanceState()");
        saveInstanceState.putLong("WorkoutId", this.workoutId);
    }

    public void setWorkoutId(long workoutId) {
        Log.d("LogTrace", this.getClass().toString() + ":setWorkoutId()");
        this.workoutId = workoutId;
    }
}
