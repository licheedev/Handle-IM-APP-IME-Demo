package com.licheedev.handleimedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by John on 2015/10/21.
 */
public class MsgAdapter extends ArrayAdapter<Msg> {
    
    public MsgAdapter(Context context, List<Msg> msgs) {
        super(context, 0, msgs);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getWho();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            int layoutId = type == Msg.ME ? R.layout.list_item_me : R.layout.list_item_other;
            convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.tvContent.setText(getItem(position).getContent());
        return convertView;
    }

    private static class ViewHolder {
        TextView tvContent;

        public ViewHolder(View view) {
            tvContent = (TextView) view.findViewById(R.id.tvContent);
        }
    } 
}
