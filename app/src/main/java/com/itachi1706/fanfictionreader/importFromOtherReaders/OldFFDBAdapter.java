package com.itachi1706.fanfictionreader.importFromOtherReaders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.itachi1706.fanfictionreader.R;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;

/**
 * Created by Kenneth on 22/2/2015, 2:44 PM
 * for Fanfiction Reader in package com.itachi1706.fanfictionreader.importFromOtherReaders
 */
public class OldFFDBAdapter extends ArrayAdapter<OldFFStories> {

    private ArrayList<OldFFStories> items = new ArrayList<>();

    public OldFFDBAdapter(Context context, int textViewResourceId, ArrayList<OldFFStories> objects){
        super(context, textViewResourceId, objects);
        this.items = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.listview_old_ff_db, parent, false);
        }

        OldFFStories i = items.get(position);

        TextView title = (TextView) v.findViewById(R.id.tvTitle);
        TextView summary = (TextView) v.findViewById(R.id.tvSummary);
        TextView misc = (TextView) v.findViewById(R.id.tvMisc);
        if (title != null){
            title.setText(i.getTitle());
        }
        if (summary != null){
            summary.setText(i.getSummary());
        }
        if (misc != null){
            FanficDB dbHandler = new FanficDB();
            String authorName = dbHandler.getAuthorName(i.getAuthor_id());
            String chapters = i.getChapters();
            String wordCount = i.getWordCount();
            String language = i.getLanguage();
            String updateDateString  = i.getUpdatedDate();
            String rating = i.getRating();
            String type = i.getType();
            String category = i.getCategory();
            String miscs = i.getMisc();
            String classification = i.getClassification();
            StringBuilder builder = new StringBuilder();
            builder.append(authorName).append("\n");
            builder.append("Chapters: ").append(chapters).append(" Word Count: ").append(wordCount).append("\n");
            if (type != null)
                builder.append(type).append(" ");
            if (language!= null)
                builder.append(language).append(" ");
            if (updateDateString != null)
                builder.append(updateDateString).append(" ");
            if (category != null)
                builder.append(category).append(" ");
            if (rating != null)
                builder.append(rating).append(" ");
            if (classification != null)
                builder.append(classification).append(" ");
            if (miscs != null)
                builder.append("\n").append(miscs).append(" ");
            misc.setText(builder.toString());
        }

        return v;
    }
}
