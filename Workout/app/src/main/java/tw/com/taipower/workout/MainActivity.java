package tw.com.taipower.workout;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements WorkoutListListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        WorkoutDetailFragment fragment;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        fragment = (WorkoutDetailFragment) this.getFragmentManager().findFragmentById(R.id.detail_fragment);
//        fragment.setWorkoutId(1);
    }

    @Override
    public void itemClicked(long id) {
        WorkoutDetailFragment details;
        FragmentTransaction fragmentTransaction;

        details = new WorkoutDetailFragment();
        fragmentTransaction = this.getFragmentManager().beginTransaction();

        details.setWorkoutId(id);

        //把【R.id.fragment_container】替換成【details】的fragment
        fragmentTransaction.replace(R.id.fragment_container, details);

        /*
        為了增加transaction到fragment transaction的back stack。
        Back stack由activity管理且允許用戶按"BACK"按鈕回傳前一個fragment的狀態。
         */
        fragmentTransaction.addToBackStack(null);

        //設定交易切換的過度動畫，【TRANSIT_FRAGMENT_FADE】採用淡出及漸顯
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        //確認變更提交
        fragmentTransaction.commit();
    }
}
