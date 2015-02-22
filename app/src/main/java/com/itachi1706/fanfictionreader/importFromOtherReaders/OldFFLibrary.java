package com.itachi1706.fanfictionreader.importFromOtherReaders;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.itachi1706.fanfictionreader.R;

import java.util.ArrayList;

public class OldFFLibrary extends ActionBarActivity {

    ListView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_fflibrary);

        view = (ListView) findViewById(R.id.lvOldFFDBStories);
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setTitle("Querying Database");
        dialog.setMessage("Currently getting stories from database... Please wait...");

        final FanficDB dbHandler = new FanficDB();
        dialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<OldFFStories> ffStoriesList;
                ffStoriesList = dbHandler.getAllStories();
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        if (ffStoriesList.size() > 0){
                            OldFFDBAdapter adapter = new OldFFDBAdapter(getApplication().getApplicationContext(), R.layout.listview_old_ff_db, ffStoriesList);
                            view.setAdapter(adapter);
                        } else {
                            ArrayList<String> nope = new ArrayList<>();
                            nope.add("No Stories Found");
                            ArrayAdapter<String> noStories = new ArrayAdapter<String>(getApplication().getApplicationContext(), android.R.layout.simple_list_item_1, nope);
                            view.setAdapter(noStories);
                        }
                    }
                });
            }
        }).start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_old_fflibrary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
