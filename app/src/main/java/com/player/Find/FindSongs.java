package com.player.Find;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;

import com.player.Data.MusicData;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by z on 2016/9/18.
 */
public class FindSongs {
    Cursor cursor;
    public List<MusicData> getMp3Infos(ContentResolver contentResolver){

      cursor =contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        List<MusicData>mp3Infos = new ArrayList<MusicData>();
        for (int i =0 ;i<cursor.getCount();i++){
            MusicData data = new MusicData();
            cursor.moveToNext();
            long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));//音乐id
            String  title =cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));//音乐标题

            String artist = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ARTIST));//艺术家

            long duration = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DURATION));//时长

            long size = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.SIZE));  //文件大小
            String url = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DATA));  //文件路径

            int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
            if(isMusic != 0 ){
                data.setId(id);
                data.setArtist(artist);
                data.setTitle(title);
                data.setUrl(url);
                data.setDuration(duration);
                data.setSize(size);
                mp3Infos.add(data);
            }
        }
        return mp3Infos;

    }
}
