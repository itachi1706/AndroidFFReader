package com.itachi1706.fanfictionreader.importFromOtherReaders;

import java.util.ArrayList;

/**
 * Created by Kenneth on 22/2/2015, 2:08 PM
 * for Fanfiction Reader in package com.itachi1706.fanfictionreader.importFromOtherReaders
 */
public class OldFFStories {

    private int id, page_id, author_id, isRead, isPreview;
    private String title, wordCount, chapters, language, updatedDate, rating, category, classification,
    misc, summary, type, tag;
    private ArrayList<OldFFStoriesChapters> storyChapters;

    public OldFFStories(int id, int page_id, String title, int author_id, String wordCount,
                        String chapters, String language, String updatedDate, String rating,
                        String category, int isRead, String classification, String misc,
                        int isPreview, String summary, String type, String tag){
        this.id = id;
        this.page_id = page_id;
        this.title = title;
        this.author_id = author_id;
        this.wordCount = wordCount;
        this.chapters = chapters;
        this.language = language;
        this.updatedDate = updatedDate;
        this.rating = rating;
        this.category = category;
        this.isRead = isRead;
        this.classification = classification;
        this.misc = misc;
        this.isPreview = isPreview;
        this.summary = summary;
        this.type = type;
        this.tag = tag;
    }

    public OldFFStories(int id, int page_id, String title, int author_id, String wordCount,
                        String chapters, String language, String updatedDate, String rating,
                        String category, int isRead, String classification, String misc,
                        int isPreview, String summary, String type, String tag,
                        ArrayList<OldFFStoriesChapters> chaptersInStory){
        this.id = id;
        this.page_id = page_id;
        this.title = title;
        this.author_id = author_id;
        this.wordCount = wordCount;
        this.chapters = chapters;
        this.language = language;
        this.updatedDate = updatedDate;
        this.rating = rating;
        this.category = category;
        this.isRead = isRead;
        this.classification = classification;
        this.misc = misc;
        this.isPreview = isPreview;
        this.summary = summary;
        this.type = type;
        this.tag = tag;
        this.storyChapters = chaptersInStory;
    }

    public ArrayList<OldFFStoriesChapters> getStoryChapters() {
        return storyChapters;
    }

    public void setStoryChapters(ArrayList<OldFFStoriesChapters> storyChapters) {
        this.storyChapters = storyChapters;
    }

    public int getId() {
        return id;
    }

    public int getPage_id() {
        return page_id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public int getIsRead() {
        return isRead;
    }

    public int getIsPreview() {
        return isPreview;
    }

    public String getLanguage() {
        return language;
    }

    public String getTitle() {
        return title;
    }

    public String getWordCount() {
        return wordCount;
    }

    public String getChapters() {
        return chapters;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public String getRating() {
        return rating;
    }

    public String getCategory() {
        return category;
    }

    public String getClassification() {
        return classification;
    }

    public String getMisc() {
        return misc;
    }

    public String getSummary() {
        return summary;
    }

    public String getType() {
        return type;
    }

    public String getTag() {
        return tag;
    }
}
