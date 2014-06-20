package collinsims.Yolodex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by collinsims on 6/19/14.
 */
public class ContactsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_list);
        Intent intent = getIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_contact:
                Intent i = new Intent(this, NewContactActivity.class);
                startActivity(i);
                finish();
                break;
        }
        return true;
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause -----> ", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume -----> ", "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop -----> ", "onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("onDestroy -----> ","onDestroy");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("onRestoreInstanceState ------> ", "onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("onSaveInstanceState -----> ","onSaveInstanceState");
    }
}
