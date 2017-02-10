package com.bwei.fragmenttabhosttext;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import static com.bwei.fragmenttabhosttext.R.color.pedo_actionbar_bkg;

public class MainActivity extends AppCompatActivity {

    //图片
    private int mImages[] = {
            R.drawable.tea_selector,
            R.drawable.favorable_selector,
            R.drawable.shop_selector,
            R.drawable.my_selector

    };
    //标题
    private String mFragmentTags[] = {
            "月光茶人",
            "优惠",
            "购物车",
            "我的"
    };
    private TextView main_title_tv;
    private NewsFragmentTabHost tabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_title_tv = (TextView) findViewById(R.id.title);
        tabhost = (NewsFragmentTabHost) findViewById(R.id.tabhost);
        tabhost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);
        tabhost.getTabWidget().setDividerDrawable(null);//去掉分割线
        tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                main_title_tv.setText(s);
            }
        });
        //初始化TabHost
        for (int i = 0;i<mImages.length;i++){
            //tabHost添加文字图片
            TabHost.TabSpec tabSpec = tabhost.newTabSpec(mFragmentTags[i]).setIndicator(getImageView(i,mFragmentTags[i]));
            //添加Fragment
            Bundle bundle = new Bundle();
            tabhost.addTab(tabSpec,FragmentTab.class,bundle);
            //设置tabHost按钮的背景
            tabhost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.pedo_actionbar_bkg);
        }
    }
    // 获得图片资源 设置初始化指示器
    private View getImageView(int index,String str) {
        @SuppressLint("InflateParams")
        View view = getLayoutInflater().inflate(R.layout.view_table_indicator, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_image);
        TextView label=(TextView)view.findViewById(R.id.tab_tv);
        label.setText(str);
        imageView.setImageResource(mImages[index]);
        return view;
    }
}
