package com.itachi1706.fanfictionreader.importFromOtherReaders;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Kenneth on 22/2/2015, 1:50 PM
 * for Fanfiction Reader in package com.itachi1706.fanfictionreader.importFromOtherReaders
 */
public class FanficDB {

    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "stories.db";

    //Tables
    private static final String TABLE_AUTHORS = "authors";
    private static final String TABLE_BOOKMARKS = "bookmarks";
    private static final String TABLE_CHAPTERS = "chapters";
    private static final String TABLE_SEARCHMARKS = "searchmarks";
    private static final String TABLE_STORIES = "stories";

    //Author Keys
    private static final String KEY_AUTHORS_ID = "id";
    private static final String KEY_AUTHORS_PAGE_ID = "page_id";
    private static final String KEY_AUTHORS_NAME = "name";
    private static final String KEY_AUTHORS_STORY_SITE = "story_site";

    //Bookmarks Keys
    private static final String KEY_BOOKMARKS_ID = "id";
    private static final String KEY_BOOKMARKS_NAME = "name";
    private static final String KEY_BOOKMARKS_CHAPTER_ID = "chapter_id";
    private static final String KEY_BOOKMARKS_STORY_ID = "story_id";
    private static final String KEY_BOOKMARKS_POSITION = "position";

    //Chapter Keys
    private static final String KEY_CHAPTERS_ID = "id";
    private static final String KEY_CHAPTERS_TITLE = "title";
    private static final String KEY_CHAPTERS_NUMBER = "number";
    private static final String KEY_CHAPTERS_CONTENT = "content";
    private static final String KEY_CHAPTERS_PATH = "path";
    private static final String KEY_CHAPTERS_ISREAD = "isread";
    private static final String KEY_CHAPTERS_STORY_ID = "story_id";

    //Searchmarks Keys
    private static final String KEY_SEARCHMARKS_ID = "id";
    private static final String KEY_SEARCHMARKS_NAME = "name";
    private static final String KEY_SEARCHMARKS_PATH = "path";
    private static final String KEY_SEARCHMARKS_QUERY = "query";
    private static final String KEY_SEARCHMARKS_DESC = "desc";

    //Stories Keys
    private static final String KEY_STORIES_ID = "id";
    private static final String KEY_STORIES_PAGE_ID = "page_id";
    private static final String KEY_STORIES_TITLE = "title";
    private static final String KEY_STORIES_AUTHOR_ID = "author_id";
    private static final String KEY_STORIES_WORD_COUNT = "wordCount";
    private static final String KEY_STORIES_CHAPTERS = "chapters";
    private static final String KEY_STORIES_LANGUAGE = "language";
    private static final String KEY_STORIES_UPDATED_DATE = "updatedDate";
    private static final String KEY_STORIES_RATING = "rating";
    private static final String KEY_STORIES_CATEGORY = "category";
    private static final String KEY_STORIES_IS_READ = "isread";
    private static final String KEY_STORIES_CLASSIFICATION = "classification";
    private static final String KEY_STORIES_MISC = "misc";
    private static final String KEY_STORIES_ISPREVIEW = "ispreview";
    private static final String KEY_STORIES_SUMMARY = "summary";
    private static final String KEY_STORIES_TYPE = "type";
    private static final String KEY_STORIES_TAG = "tag";

    private static final String DB_FILE_PATH = Environment.getExternalStorageDirectory().getPath() + "/stories.db";

    private static SQLiteDatabase db;

    public FanficDB(){
        File file = new File(DB_FILE_PATH);
        db = SQLiteDatabase.openOrCreateDatabase(file, null);
    }

    public ArrayList<OldFFStories> getAllStories(){
        String queryString = "SELECT * FROM " + TABLE_STORIES + ";";
        Cursor mainCursor = db.rawQuery(queryString, null);
        ArrayList<OldFFStories> result = new ArrayList<>();

        if (mainCursor.moveToFirst()){
            do {
                OldFFStories ff = new OldFFStories(mainCursor.getInt(0), mainCursor.getInt(1), mainCursor.getString(2),
                    mainCursor.getInt(3), mainCursor.getString(4), mainCursor.getString(5), mainCursor.getString(6),
                        mainCursor.getString(7), mainCursor.getString(8), mainCursor.getString(9), mainCursor.getInt(10),
                        mainCursor.getString(11), mainCursor.getString(12), mainCursor.getInt(13), mainCursor.getString(14),
                        mainCursor.getString(15), mainCursor.getString(16));

                String subQuery = "SELECT * FROM " + TABLE_CHAPTERS + " WHERE " + KEY_CHAPTERS_STORY_ID + " = " + ff.getId() + " ORDER BY " + KEY_CHAPTERS_NUMBER + ";";
                Cursor subCursor = db.rawQuery(subQuery, null);
                ArrayList<OldFFStoriesChapters> chapterList = new ArrayList<>();
                chapterList.clear();
                if (subCursor.moveToFirst()){
                    do{
                        OldFFStoriesChapters ffC = new OldFFStoriesChapters(subCursor.getInt(0), subCursor.getString(1),
                                subCursor.getInt(2), subCursor.getString(3), subCursor.getString(4), subCursor.getInt(5),
                                subCursor.getInt(6));
                        chapterList.add(ffC);
                    } while (subCursor.moveToNext());
                }

                subCursor.close();
                ff.setStoryChapters(chapterList);
                result.add(ff);
            } while (mainCursor.moveToNext());
        }
        mainCursor.close();
        return result;
    }

    public String getAuthorName(int authorId){
        String queryString = "SELECT * FROM " + TABLE_AUTHORS + " WHERE " + KEY_AUTHORS_ID + " = " + authorId + ";";
        Cursor cursor = db.rawQuery(queryString, null);
        String authorName = "";
        if (cursor.moveToFirst()){
            do{
                authorName = cursor.getString(2);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return authorName;
    }
}
