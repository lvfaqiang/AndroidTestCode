package com.lvfq.code.dynamic;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lvfq.code.R;
import com.lvfq.code.dynamic.data.CommentsBean;
import com.lvfq.code.dynamic.data.UserBean;
import com.lvfq.code.dynamic.view.CommentsView;
import com.lvfq.code.dynamic.view.LikesView;
import com.lvfq.library.utils.LvV;

/**
 * CommentsActivity
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/9/3 下午9:56
 * @desc :
 */

public class CommentsActivity extends AppCompatActivity {

    private CommentsView commentView;
    private LikesView likeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        commentView = LvV.find(this, R.id.commentView);
        likeView = LvV.find(this, R.id.likeView);

        likeView.setList(Data.getLikes());
        likeView.setListener(new LikesView.onItemClickListener() {
            @Override
            public void onItemClick(int position, UserBean bean) {
                new MyThread().start();
            }
        });
        likeView.notifyDataSetChanged();

        commentView = LvV.find(this, R.id.commentView);
        commentView.setList(Data.getComments());
        commentView.setOnItemClickListener(new CommentsView.onItemClickListener() {
            @Override
            public void onItemClick(int position, CommentsBean bean) {
                Log.i("lfq", Thread.currentThread().getName());
                new MyThread().start();
            }
        });
        commentView.notifyDataSetChanged();
    }

    private class MyThread extends Thread {
        private Handler handler;

        @Override
        public void run() {
//            Looper.prepare();
            handler = new Handler(getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 1) {
                        Log.i("lfq", msg.what + " , mas.what");
                        Toast.makeText(CommentsActivity.this, Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();
                        commentView.setVisibility(View.GONE);
                    }
                }
            };

            handler.sendEmptyMessage(1);
//            Looper.loop();

        }
    }
}
