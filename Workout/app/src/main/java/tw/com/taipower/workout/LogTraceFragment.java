package tw.com.taipower.workout;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by new on 2016/3/28.
 */
public class LogTraceFragment extends Fragment {
    @Override
    public void onAttach(Context context) {
        Log.d("LogTrace", this.getClass().toString() + ":onAttach()");
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("LogTrace", this.getClass().toString() + ":onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("LogTrace", this.getClass().toString() + ":onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d("LogTrace", this.getClass().toString() + ":onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d("LogTrace", this.getClass().toString() + ":onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("LogTrace", this.getClass().toString() + ":onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("LogTrace", this.getClass().toString() + ":onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("LogTrace", this.getClass().toString() + ":onStop()");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d("LogTrace", this.getClass().toString() + ":onDestroyView()");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d("LogTrace", this.getClass().toString() + ":onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d("LogTrace", this.getClass().toString() + ":onDetach()");
        super.onDetach();
    }
}
