package tw.com.taipower.workout;


import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutListFragment extends ListFragment {
    private WorkoutListListener listener;

    public WorkoutListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        Log.d("Debuuuuug", this.getClass().toString() + " Hello onAttach");
        super.onAttach(context);
        this.listener = (WorkoutListListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Debuuuuug", this.getClass().toString() + " Hello onCreateView");
        // Inflate the layout for this fragment

        String[] names;
        ArrayAdapter<String> adpter;

        names = new String[Workout.workouts.length];
        for(int i=0; i< Workout.workouts.length; i++){
            names[i] = Workout.workouts[i].getName();
        }

        adpter = new ArrayAdapter<String>(
            inflater.getContext(), android.R.layout.simple_list_item_1, names);
        this.setListAdapter(adpter);    //將陣列適配器(Adapter)繫結到列表視圖(ListView)

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.d("Debuuuuug", this.getClass().toString() + " Hello onListItemClick");
        //當ListView裡的項目被點擊時，呼叫自訂的偵聽器(WorkoutListListener)
        if(this.listener != null){
            this.listener.itemClicked(id);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("Debuuuuug", this.getClass().toString() + " Hello onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d("Debuuuuug", this.getClass().toString() + " Hello onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        Log.d("Debuuuuug", this.getClass().toString() + " Hello onViewStateRestored");
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d("Debuuuuug", this.getClass().toString() + " Hello onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("Debuuuuug", this.getClass().toString() + " Hello onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("Debuuuuug", this.getClass().toString() + " Hello onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("Debuuuuug", this.getClass().toString() + " Hello onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d("Debuuuuug", this.getClass().toString() + " Hello onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d("Debuuuuug", this.getClass().toString() + " Hello onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d("Debuuuuug", this.getClass().toString() + " Hello onDetach");
        super.onDetach();
    }
}
