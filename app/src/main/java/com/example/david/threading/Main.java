package com.example.david.threading;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.MenuItem;
import android.view.Menu;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public class Main extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public ArrayAdapter<String> itemsAdapter;// = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
    public List<String> S;

    public void CreateButtonPressed(View view) {
        File file = new File(getFilesDir(), "numbers.txt");
        WriteToFile();
    }

    public void LoadButtonPressed(View view) {
        ReadFile();
    }

    public void ClearButtonPressed(View view) {
        // in the program if you press the clear button right away it will crash
        if (itemsAdapter != null)
            itemsAdapter.clear();
    }

    private void ReadFile() {
        //InputStreamReader InputReader = null;
        try {
            S = new ArrayList<String>();
            InputStream in = openFileInput("numbers.txt");
            InputStreamReader InputReader = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(InputReader);
            String NotListString;
            while ((NotListString = br.readLine()) != null) {
                S.add(NotListString);
                java.lang.Thread.sleep(250);
            }
             /*
            while((i=InputReader.read()) != -1){
                c = (char) i;
                S.add(valueOf(c));
                java.lang.Thread.sleep(250);
            }
            */
            // Use the adapter to change the listView
            //This component we are manipulating, the place its at, the string list.
            itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, S);
            ListView listView = (ListView) findViewById(R.id.ListViewID);
            listView.setAdapter(itemsAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void WriteToFile() {
        try {
            OutputStreamWriter OutWriter = new OutputStreamWriter(openFileOutput("numbers.txt", Context.MODE_PRIVATE));
            BufferedWriter bw = new BufferedWriter(OutWriter);
            for (int i = 1; i < 11; i++) {
                Integer one = new Integer(i);
                String x = one.toString() + "\n";
                OutWriter.write(x);
                java.lang.Thread.sleep(250);
            }
            OutWriter.close();
        } catch (IOException e) {
            Log.e("Exception has been made", "File writer failed: " + e.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}


