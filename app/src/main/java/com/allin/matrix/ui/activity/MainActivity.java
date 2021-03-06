package com.allin.matrix.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.allin.matrix.R;
import com.allin.matrix.base.ui.BaseActivity;
import com.allin.matrix.model.MessageEvent;
import com.allin.matrix.ui.adapter.MainAdapter;
import com.allin.matrix.util.LogUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Allin on 2016/6/20.
 */
public class MainActivity extends BaseActivity {
    private ListView listView;

    private String[] activitys = {
        "TabActivity",
        "BlurActivity",
        "BlurringActivity",
        "RerofitActivity",
        "EventActivity",
        "LifeActivity",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState){
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        ListAdapter adapter = new MainAdapter(activitys);
        listView.setAdapter(adapter);
    }

    @Override
    protected boolean initEvent(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String activity = String.format("com.allin.matrix.ui.activity.%s", activitys[position]);
                    Intent intent = new Intent(MainActivity.this, Class.forName(activity));
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMsgEvent(MessageEvent msg){
        LogUtil.i(TAG, "onMsgEvent");
    }

}
