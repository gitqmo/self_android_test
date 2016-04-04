package tw.com.taipower.bitsandpizzas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

public class MainActivity extends LogTraceActivity {
    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
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
}
