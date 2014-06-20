package collinsims.Yolodex;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by collinsims on 6/18/14.
 */
public class spinnerAdapter extends Activity implements AdapterView.OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}