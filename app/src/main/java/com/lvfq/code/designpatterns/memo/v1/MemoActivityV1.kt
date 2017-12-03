package com.lvfq.code.designpatterns.memo.v1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lvfq.code.R
import com.lvfq.code.designpatterns.memo.MemoBean
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
class MemoActivityV1 : AppCompatActivity() {

    //最大存储数量
    val MAX = 30
    var index = -1
    private val memos = ArrayList<MemoBean>(30)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo_v1)

        btn_pre.setOnClickListener {
            // 撤销
            restoreEdit(proMemo())
        }

        btn_next.setOnClickListener {
            // 回复下一个状态
            restoreEdit(nextMemo())
        }

        btn_save.setOnClickListener {
            val memo = MemoBean(memo_et.text.toString(), memo_et.selectionStart)
            saveMemo(memo)
        }
    }

    private fun saveMemo(memo: MemoBean) {
        if (memos.size > MAX) {
            memos.removeAt(0)
        }
        memos.add(memo)
        index = memos.size - 1
    }

    private fun proMemo(): MemoBean? {
        if (index < 0) {
            return null
        }
        index = if (index > 0) --index else index
        return memos[index]
    }

    private fun nextMemo(): MemoBean {
        index = if (index < memos.size - 1) ++index else index
        return memos[index]
    }

    private fun restoreEdit(memo: MemoBean?) {
        memo_et.setText(memo?.text ?: "")
        memo_et.setSelection(memo?.cursor ?: 0)
    }
}