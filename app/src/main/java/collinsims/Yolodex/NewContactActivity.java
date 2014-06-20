package collinsims.Yolodex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by collinsims on 6/19/14.
 */
public class NewContactActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_contact_screen);
        Intent intent = getIntent();
    }

    public void cancel(View v){
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
        finish();
    }
    // save new contact
    public void save(View v){
        finish();
    }
}
