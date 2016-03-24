package tw.com.taipower.workout;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutDetailFragment extends Fragment {
    private long workoutId;

    public WorkoutDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(savedInstanceState != null){
            this.workoutId = savedInstanceState.getLong("WorkoutId");
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
        saveInstanceState.putLong("WorkoutId", this.workoutId);
    }

    public void setWorkoutId(long workoutId) {
        this.workoutId = workoutId;
    }
}
