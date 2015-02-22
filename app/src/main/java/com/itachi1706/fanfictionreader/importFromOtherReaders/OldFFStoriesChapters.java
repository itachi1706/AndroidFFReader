package com.itachi1706.fanfictionreader.importFromOtherReaders;

/**
 * Created by Kenneth on 22/2/2015, 2:12 PM
 * for Fanfiction Reader in package com.itachi1706.fanfictionreader.importFromOtherReaders
 */
public class OldFFStoriesChapters {

    private int id, number, isRead, story_id;
    private String title, content, path;

    public OldFFStoriesChapters(int id, String title, int number, String content, String path, int isRead, int story_id){
        this.id = id;
        this.title = title;
        this.number = number;
        this.content = content;
        this.path = path;
        this.isRead = isRead;
        this.story_id = story_id;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public int getIsRead() {
        return isRead;
    }

    public int getStory_id() {
        return story_id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getPath() {
        return path;
    }
}
