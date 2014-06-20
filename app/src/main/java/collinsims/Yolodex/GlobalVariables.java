package collinsims.Yolodex;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by collinsims on 6/19/14.
 *
 * Create Separate Business Class that contains contacts and events. Save in JSON format
 */
public class GlobalVariables extends Application implements Serializable {

    private static File file;
    public static ArrayList<String> Businesses = new ArrayList<String>();

    public boolean hasItems() {return Businesses.isEmpty();}

    public static boolean hasItem(String name){
        boolean found = false;
        for(String s:Businesses){
            if(s.equals(name)) found = true;
        }
        return found;
    }

    public ArrayList<String> getBusinesses(){return Businesses;}

    public void Load_Businesses() throws IOException {
        Log.d("load_data()", "load_data() calling---->");
        File sdCard = new File(Environment.getExternalStorageDirectory() + "/Businesses/");
        boolean success = true;
        if (!sdCard.exists()) {
            success = sdCard.mkdir();
        }
        if (success) {
            // Do something on success
            file = new File(sdCard, "Businesses" + ".txt");
            file.createNewFile();
            Log.d("F_Path", " File Path :" + file);
        } else {
            // Do something else on failure
            Log.d("File Not Found", "The File / Directory Weren't Found");
        }
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while(line!=null){
                Businesses.add(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void Add_Business(String Business){
        if(!Businesses.contains(Business)) Businesses.add(Business);
    }

    public static void Save_Business() throws IOException {
        try {
            FileWriter fw = new FileWriter(file);
            for(String s:Businesses){
                fw.write(s + "\n");
            }
            fw.close();
        } catch (Exception e) {
            Log.d("Save", "writeListToFile call error");
            e.printStackTrace();
        }
    }

    public static void Save_Contacts() throws IOException {
        Log.d("Save", "Save Contacts");
        try {
            FileWriter fw = new FileWriter(file);

            fw.close();
        } catch (Exception e) {
            Log.d("Save", "writeListToFile call error");
            e.printStackTrace();
        }
    }

}
