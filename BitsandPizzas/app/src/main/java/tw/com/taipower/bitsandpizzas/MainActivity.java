package tw.com.taipower.bitsandpizzas;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ShareActionProvider;

public class MainActivity extends LogTraceActivity {
    private ShareActionProvider shareActionProvider;
    private String[] titles;
    private ListView drawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.titles = this.getResources().getStringArray(R.array.titles);
        this.drawerList = (ListView) this.findViewById(R.id.drawer);
        this.drawerList.setAdapter(
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        this.titles));
        this.drawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    /**
     * 將create order項目添加到action bar
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //透過反序列化取的選單；如果action bar存在，就將項目添加道裡頭。
        //反序列化(inflate或deserialize)，表示從XML檔案(或JSON等其他格式)將物件還原。
        MenuItem menuItem;

        this.getMenuInflater().inflate(R.menu.menu_main, menu);
        menuItem = menu.findItem(R.id.action_share);
        this.shareActionProvider = (ShareActionProvider) menuItem.getActionProvider();
        this.setIntent("This is a example text.");
        return super.onCreateOptionsMenu(menu);
    }

    private void setIntent(String text) {
        Intent intent;

        intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        this.shareActionProvider.setShareIntent(intent);
    }

    /**
     * 依據action bar被點擊之action item的ID，來執行相對應的動作。
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create_order:
                //Create Order項目被點擊時要執行的程式碼
                Intent intent;

                intent = new Intent(MainActivity.this, OrderActivity.class);
                this.startActivity(intent);
                return true;
            case R.id.action_settings:
                //Settings項目被點擊時要執行的程式碼
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 依據被點擊的選項呈現不同的功能選單。
     *
     * @param position
     */
    private void selectIem(int position) {
        //切換所對應之功能選單
        Fragment fragment;
        switch (position) {
            case 1:
                fragment = new PizzaFragment();
                break;
            case 2:
                fragment = new PastaFragment();
                break;
            case 3:
                fragment = new StoresFragment();
                break;
            default:
                fragment = new TopFragment();
        }
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

        //設定Action Bar的標題
        this.setActionBarTitle(position);

        //關閉Drawer
        DrawerLayout drawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(this.drawerList);
    }

    /**
     * 當Navigation Drawer被點擊時，呈現的所對應之功能選單。
     */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MainActivity.this.selectIem(position);
        }
    }

    /**
     * 設定Action Bar的標題
     * @param position
     */
    private void setActionBarTitle(int position){
        String title;
        if(position == 0){
            title = this.getResources().getString(R.string.app_name);

        }else{
            title = this.titles[position];
        }
        this.getActionBar().setTitle(title);
    }
}
