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

        }
        if (summary != null){

        }
        if (misc != null){

        }
    }
}
