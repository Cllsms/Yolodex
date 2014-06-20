package collinsims.Yolodex;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by collinsims on 6/19/14.
 */
public class CustomDialogLayout extends DialogPreference {

    private View v;
    public CustomDialogLayout(Context context, AttributeSet attrs){
        super(context, attrs);
        setPersistent(false);
        setDialogLayoutResource(R.layout.custom_dialog_preference);
    }

    @Override
    public void onBindDialogView(View view){
        super.onBindDialogView(view);
    }
    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);
        if (positiveResult) {
            // deal with persisting your values here
        }
    }
}
