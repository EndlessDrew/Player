package com.player.Activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.player.Data.MusicData;
import com.player.Find.FindSongs;
import com.player.Fragement.diantaiFragement;
import com.player.Fragement.gedanFragement;
import com.player.Fragement.gexingFragement;
import com.player.Fragement.paihangbangFragement;
import com.player.R;

import java.util.List;

public class MainActivity extends Activity  implements View.OnClickListener{
    Button gexing;
    Button paihangbang;
    Button diantai;
    Button gedan;

    //歌手图片
    ImageView iv_picture;
    //歌单
    //歌手名字
    TextView tv_name;
    //歌曲名字
    TextView tv_music_name;
    ImageView iv_menu;
    //播放或者暂停
    ImageView iv_play_pause;
    //下一首
    ImageView iv_next;
    List<MusicData> list;
    String[] items;
    int mWhich;
    TextView tv_menu_title;
    TextView tv_menu_artist;
    //播放视频按钮
    private TextView mTextView01;
    private VideoView mVideoView01;
    private  String strVideoPath;
    private Button mbutton1,mbutton2;
    private  String TGA= "HIPPO_VIDEOVIEW";
    private boolean bifSDExist = false;
    boolean is_exit=false;
    //第一次点击退出的时间戳
    long  l_firstClickTime;
    //第二次点击的时间戳
    long l_secondClickTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        gexing = (Button)findViewById(R.id.gexing);
        gexing.setOnClickListener(this);
        gedan = (Button)findViewById(R.id.gedan);
        gedan.setOnClickListener(this);
        diantai = (Button)findViewById(R.id.diantai);
        diantai.setOnClickListener(this);
        paihangbang = (Button)findViewById(R.id.paihangbang);
        paihangbang.setOnClickListener(this);
        tv_menu_title=(TextView)findViewById(R.id.tv_menu_title) ;
        tv_menu_artist=(TextView)findViewById(R.id.tv_menu_artist);
        iv_picture = (ImageView) findViewById(R.id.iv_picture);
        iv_picture.setOnClickListener(this);
        iv_play_pause = (ImageView) findViewById(R.id.iv_play_pause);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        iv_menu = (ImageView) findViewById(R.id.iv_menu);
        iv_menu.setOnClickListener(this);
        initItem();

    }


    private void initItem() {
        //设置列表参数 此处为数组  可以用ArrayList
        FindSongs songs = new FindSongs();
        list = songs.getMp3Infos(getContentResolver());
        if (list.size() == 0) {
            tv_menu_title.setText("null");
            tv_menu_artist.setText("null");
        } else {
            items = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                items[i] = list.get(i).getTitle() + " ———— " + list.get(i).getArtist();
            }
            mWhich = (int) (Math.random() * list.size());
            tv_menu_title.setText(list.get(mWhich).getTitle());
            tv_menu_artist.setText(list.get(mWhich).getArtist() + "");
        }
    }


    protected void dialogMusic() {
        //得到构造器
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置标题
        builder.setTitle("音乐列表");
//        builder.setMessage("是否退出");
        //设置了列表实现就不能设置setMassage 否则设置不起作用
        builder.setIcon(R.mipmap.musicdialog);
        //设置列表显示
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("key", which);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }


    @Override
    public void onClick(View v) {
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();


        switch (v.getId()) {
            case R.id.iv_picture:
                Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("key", mWhich);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.iv_menu:
                dialogMusic();
                break;
            case R.id.gexing:
                transaction.replace(R.id.ll_fragement,new gexingFragement());
                transaction.commit();
                break;
            case R.id.gedan:
                transaction.replace(R.id.ll_fragement,new gedanFragement());
                transaction.commit();
                break;
            case R.id.diantai:
                transaction.replace(R.id.ll_fragement,new diantaiFragement());
                transaction.commit();
                break;
            case R.id.paihangbang:
                transaction.replace(R.id.ll_fragement,new paihangbangFragement());
                transaction.commit();
                break;

        }
    }
}
