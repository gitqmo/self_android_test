package tw.com.taipower.bitsandpizzas;


import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * A simple {@link ListFragment} subclass.
 */
public class PizzaFragment extends ListFragment {

    public PizzaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("LogTrace", this.getClass().toString() + ":\tonCreateView()");
        ArrayAdapter<String> adapter;

        adapter = new ArrayAdapter<>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1,
                this.getResources().getStringArray(R.array.pizzas));
        this.setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
