package com.itachi1706.fanfictionreader;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.itachi1706.fanfictionreader.importFromOtherReaders.OldFFLibrary;

import java.io.File;

/**
 * Main Activity on launch
 * Used to just go to another activity instantly
 */
public class MainLoop extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_loop);

        //Check that old ff db exists
        File oldFFDB = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/stories.db");
        if (!oldFFDB.exists()){
            new AlertDialog.Builder(this).setTitle("No Databases")
                    .setMessage("Unable to find a database. Please export a 'stories.db' onto the root" +
                            " of the /sdcard partition")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).setCancelable(false).show();
        } else {
            startActivity(new Intent(MainLoop.this, OldFFLibrary.class));
            finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_loop, menu);
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
