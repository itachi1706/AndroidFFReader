package com.itachi1706.fanfictionreader.importFromOtherReaders;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.itachi1706.fanfictionreader.R;

import java.util.ArrayList;

public class OldFFLibrary extends ActionBarActivity {

    ListView view;
    private ArrayList<OldFFStories> ffStoriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_fflibrary);

        view = (ListView) findViewById(R.id.lvOldFFDBStories);
        FanficDB dbHandler = new FanficDB(this.getApplicationContext());
        ffStoriesList = dbHandler.getAllStories();
        if (ffStoriesList.size() > 0){

        } else {
            ArrayList<String> nope = new ArrayList<>();
            nope.add("No Stories Found");
            ArrayAdapter<String> noStories = new ArrayAdapter<String>(this.getApplicationContext(), android.R.layout.simple_list_item_1, nope);
            view.setAdapter(noStories);
        }

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
