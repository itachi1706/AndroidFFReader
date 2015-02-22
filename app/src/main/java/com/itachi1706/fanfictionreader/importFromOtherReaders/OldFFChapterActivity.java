package com.itachi1706.fanfictionreader.importFromOtherReaders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
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
    int currentChapterNum, storySize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_ffchapter);
        chapter = (TextView) findViewById(R.id.tv_old_ff_chapter);
        chapter.setMovementMethod(new ScrollingMovementMethod());
        if (!(getIntent().hasExtra("STORY-ID") && getIntent().hasExtra("CURRENT-CHAPTER") && getIntent().hasExtra("STORY-SIZE"))){
            errorOccuredDialog(null);
        } else {
            String currentStoryID = getIntent().getStringExtra("STORY-ID");
            currentChapterNum = getIntent().getIntExtra("CURRENT-CHAPTER", 0);
            storySize = getIntent().getIntExtra("STORY-SIZE", 0);
            if (storySize == 0){
                errorOccuredDialog("Not correct size");
            } else {
                OldFFStoriesChapters c = OldFFStaticVars.OldFFcurrentStory.getStoryChapters().get(currentChapterNum);
                ActionBar ab = getSupportActionBar();
                ab.setTitle(OldFFStaticVars.OldFFcurrentStory.getTitle() + " - Chapter " + (currentChapterNum + 1));
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
                            chapter.setText(Html.fromHtml(builder.toString()));
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
        } else if (id == R.id.next_chapter){
            if (currentChapterNum == (storySize - 1)){
                //Fail
                new AlertDialog.Builder(this).setTitle("Failed to go to next chapter")
                        .setMessage("You are already on the last chapter!").setPositiveButton(android.R.string.ok, null)
                        .show();
            } else {
                //Pass
                Intent intent = new Intent(OldFFChapterActivity.this, OldFFChapterActivity.class);
                OldFFStories f = OldFFStaticVars.OldFFcurrentStory;
                intent.putExtra("STORY-ID", f.getId());
                intent.putExtra("CURRENT-CHAPTER", currentChapterNum + 1);
                intent.putExtra("STORY-SIZE", f.getStoryChapters().size());
                startActivity(intent);
                this.finish();
            }
        } else if (id == R.id.prev_chapter){
            if (currentChapterNum == 0){
                //Fail
                new AlertDialog.Builder(this).setTitle("Failed to go to previous chapter")
                        .setMessage("You are already on the first chapter!").setPositiveButton(android.R.string.ok, null)
                        .show();
            } else {
                Intent intent = new Intent(OldFFChapterActivity.this, OldFFChapterActivity.class);
                OldFFStories f = OldFFStaticVars.OldFFcurrentStory;
                intent.putExtra("STORY-ID", f.getId());
                intent.putExtra("CURRENT-CHAPTER", currentChapterNum - 1);
                intent.putExtra("STORY-SIZE", f.getStoryChapters().size());
                startActivity(intent);
                this.finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
