package com.itachi1706.fanfictionreader.importFromOtherReaders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.itachi1706.fanfictionreader.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class OldFFChapterActivity extends ActionBarActivity {

    TextView chapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_ffchapter);
        chapter = (TextView) findViewById(R.id.tv_old_ff_chapter);
        if (!(getIntent().hasExtra("STORY-ID") && getIntent().hasExtra("CURRENT-CHAPTER") && getIntent().hasExtra("STORY-SIZE"))){
            errorOccuredDialog(null);
        } else {
            String currentStoryID = getIntent().getStringExtra("STORY-ID");
            int currentChapterNum = getIntent().getIntExtra("CURRENT-CHAPTER", 0);
            int storySize = getIntent().getIntExtra("STORY-SIZE", 0);
            if (storySize == 0){
                errorOccuredDialog("Not correct size");
            } else {
                OldFFStoriesChapters c = OldFFStaticVars.OldFFcurrentStory.getStoryChapters().get(currentChapterNum);
                if (c.getContent().length() > 0){
                    chapter.setText(c.getContent());
                } else {
                    String path = c.getPath();
                    File file = new File(path);
                    if (!file.exists()){
                        errorOccuredDialog(path);
                    } else {
                        try {
                            BufferedReader reader = new BufferedReader(new FileReader(file));
                            StringBuilder builder = new StringBuilder();
                            String line;
                            while ((line = reader.readLine()) != null){
                                builder.append(line).append("\n");
                            }
                            chapter.setText(builder.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
    }

    private void errorOccuredDialog(String msg){
        if (msg == null) {
            new AlertDialog.Builder(this).setTitle("An error occured")
                    .setMessage("Unable to find story chapters. Please try again")
                    .setCancelable(false).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();
        } else {
            new AlertDialog.Builder(this).setTitle("An error occured")
                    .setMessage("Unable to find story chapters (" + msg + "). Please try again")
                    .setCancelable(false).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_old_ffchapter, menu);
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
