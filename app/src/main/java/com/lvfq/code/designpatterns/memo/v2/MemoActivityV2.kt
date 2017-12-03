package com.lvfq.code.designpatterns.memo.v2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lvfq.code.R
import kotlinx.android.synthetic.main.activity_memo_v1.*

/**
 * MemoActivityV1
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/30 下午2:00
 * @desc :
 *
 */
class MemoActivityV2 : AppCompatActivity() {

    val noteManager = NoteManager()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo_v1)
        memo_et.setText("测试内容")
        btn_pre.setOnClickListener {
            // 撤销
            memo_et.restore(noteManager.proMemo())
        }

        btn_next.setOnClickListener {
            // 回复下一个状态
            memo_et.restore(noteManager.nextMemo())
        }

        btn_save.setOnClickListener {
            noteManager.saveMemo(memo_et.createMemo())
        }
    }


}