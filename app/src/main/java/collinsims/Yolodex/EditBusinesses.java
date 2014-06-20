package collinsims.Yolodex;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by collinsims on 6/20/14.
 */
public class EditBusinesses extends Activity {

    private ArrayList<String> Businesses;
    private ArrayAdapter<String> adapter;
    private int position = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_businesses);

        final GlobalVariables globalVariable = (GlobalVariables) getApplicationContext();

        ListView lv = (ListView) findViewById(R.id.businessesListView);
        try {
            if(globalVariable.hasItems()) globalVariable.Load_Businesses();
            Businesses = globalVariable.getBusinesses();
        } catch (IOException e) {
            e.printStackTrace();
        }
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Businesses);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                onListItemClick(v, pos, id);
            }
        });
    }

    public void onListItemClick(View v, int pos, long id) {
        Log.d("short click", "onListItemClick id=" + id);
       /* Intent intent = new Intent(this, .class);
        intent.putExtra("playlist title", playlists.get(pos).getPlaylistName());
        intent.putExtra("playlist", playlists.get(pos).getSongs());
        startActivity(intent);*/
    }

    public boolean onLongListItemClick(View v, int pos, long id) {
        Log.i("long click", "onLongListItemClick id=" + id);
        return true;
    }

    //Populates the contextual menu that is created on a long press
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.businessesListView) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle(Businesses.get(info.position));
            String[] menuItems = getResources().getStringArray(R.array.editBusinessesMenu);
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }
    //Deals with the option that you've selected from the contextual menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = getResources().getStringArray(R.array.editBusinessesMenu);
        String menuItemName = menuItems[menuItemIndex];
        String listItemName = Businesses.get(info.position);
        Log.d("",("Selected "+ menuItemName +" for item " + listItemName));
        switch(menuItemIndex){
            case 0:
                position = info.position;
               /* Intent intent = new Intent(this, Rename_Playlist_Activity.class);
                intent.putExtra("newTitle","");
                //intent.putExtra("position", info.position);
                startActivityForResult(intent, PL_TITLE_REQUEST1);*/
                break;
            case 1:
                Remove_Data(info.position);
                break;
        }
        return true;
    }

    public void Remove_Data(int pos){
        Log.d("Delete","Remove_Data Calling -->");
        Businesses.remove(pos);
        try {
            GlobalVariables.Save_Business();
        } catch (IOException e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
    }
}
