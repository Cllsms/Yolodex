package collinsims.Yolodex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by collinsims on 6/19/14.
 */
public class AddNewBusiness extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_business);
    }

    public void save(View view){
        EditText title = (EditText)findViewById(R.id.businessNameInput);
        String businessTitle = title.getText().toString();
        Log.d("Add Business Title ------> ", businessTitle);
        if(businessTitle.length()>0 && !GlobalVariables.hasItem(businessTitle)) {
            Intent resultIntent = new Intent(this, HomeScreen.class);
            resultIntent.putExtra("newBusiness", businessTitle);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        } else {
            if(businessTitle.length()==0) Toast.makeText(AddNewBusiness.this, "You Must Enter A Name For Your Business", Toast.LENGTH_SHORT).show();
            if(GlobalVariables.hasItem(businessTitle)) Toast.makeText(AddNewBusiness.this, "Business Has Already Been Entered", Toast.LENGTH_SHORT).show();
        }
    }
}
