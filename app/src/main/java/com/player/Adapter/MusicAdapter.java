package com.player.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.player.Data.MusicData;
import com.player.R;

import java.util.List;

/**
 * Created by z on 2016/10/8.
 */
public class MusicAdapter extends BaseAdapter {
    Context mcontext;
    List<MusicData> mlist;
    LayoutInflater inflater;

    public MusicAdapter(Context context, List<MusicData> list) {
        mcontext = context;
        mlist = list;
        inflater = LayoutInflater.from(mcontext);

    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_menu, null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_music_name = (TextView) convertView.findViewById(R.id.tv_musicName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_music_name.setText(mlist.get(position).getTitle()+"");
        holder.tv_name.setText("——"+mlist.get(position).getArtist() + "");
        return convertView;
    }


    class ViewHolder {
        public TextView tv_music_name;//歌曲名字
        public TextView tv_name;//歌手名字
    }
    public static String formatTime(Long time){                     //将歌曲的时间转换为分秒的制度
        String min = time / (1000 * 60) + "";
        String sec = time % (1000 * 60) + "";

        if(min.length() < 2)
            min = "0" + min;
        switch (sec.length()){
            case 4:
                sec = "0" + sec;
                break;
            case 3:
                sec = "00" + sec;
                break;
            case 2:
                sec = "000" + sec;
                break;
            case 1:
                sec = "0000" + sec;
                break;
        }
        return min + ":" + sec.trim().substring(0,2);
    }
}
