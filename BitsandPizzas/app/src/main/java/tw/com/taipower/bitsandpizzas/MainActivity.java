package tw.com.taipower.bitsandpizzas;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.titles = this.getResources().getStringArray(R.array.titles);
        this.drawerList = (ListView) this.findViewById(R.id.drawer);
        this.drawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);

        //為drawer's ListView填值
        this.drawerList.setAdapter(
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        this.titles));
        this.drawerList.setOnItemClickListener(new DrawerItemClickListener());

        //如果MainActivity是新建的，就使用selectItem()方法展示TopFragment。
        if (savedInstanceState == null) {
            this.selectIem(0);
        }

        this.drawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,
                this.drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer) {
            //在Drawer處於完全開啟的狀態時被呼叫。
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                MainActivity.this.invalidateOptionsMenu();
            }

            //在Drawer處於完全關閉的狀態時被呼叫。
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                MainActivity.this.invalidateOptionsMenu();
            }
        };
        this.drawerLayout.addDrawerListener(drawerToggle);

        //啟用Up小圖示，讓它能夠被ActionBarDrawerToggle所利用。
        this.getActionBar().setDisplayHomeAsUpEnabled(true);
        this.getActionBar().setHomeButtonEnabled(true);
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

    /**
     * 每當我們呼叫invalidateOptionsMenu()時，這個方法就會被呼叫，來隱藏相關的menu item。
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //假如drawer開啟，就隱藏相關聯的動作項目。
        boolean drawerOpen = this.drawerLayout.isDrawerOpen(this.drawerList);
        menu.findItem(R.id.action_share).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * 依據action bar被點擊之action item的ID，來執行相對應的動作。
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //處理ActionBarDrawerToggle被點擊時的處理狀況。
        if (this.drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

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

    private void setIntent(String text) {
        Intent intent;

        intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        this.shareActionProvider.setShareIntent(intent);
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
     * 設定Action Bar的標題
     *
     * @param position
     */
    private void setActionBarTitle(int position) {
        String title;
        if (position == 0) {
            title = this.getResources().getString(R.string.app_name);

        } else {
            title = this.titles[position];
        }
        this.getActionBar().setTitle(title);
    }

    /**
     * 同步化ActionBarDrawerToggle的狀態與drawer的狀態。
     * @param savedInstanceState
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //在onRestoreInstanceState發生之後同步化ActionBarDrawerToggle狀態。
        this.drawerToggle.syncState();
    }

    /**
     * 若裝置組態改變，我們必須將組態裝置變更的細節傳遞給ActionBarDrawerToggle。
     * 所以透過Activity的onConfigurationChanged()方法呼叫
     * ActionBarDrawerToggle的onConfigurationChanged()方法。
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.drawerToggle.onConfigurationChanged(newConfig);
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
}
