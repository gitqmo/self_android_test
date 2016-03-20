package tw.com.taipower.workout;


import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
        super.onAttach(context);
        this.listener = (WorkoutListListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        //當ListView裡的項目被點擊時，呼叫自訂的偵聽器(WorkoutListListener)
        if(this.listener != null){
            this.listener.itemClicked(id);
        }
    }
}
