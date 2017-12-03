package com.lvfq.code.designpatterns.memo.v2

import com.lvfq.code.designpatterns.memo.MemoBean

/**
 * NoteManager
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/30 下午2:56
 * @desc :
 *
 */
class NoteManager {
    //最大存储数量
    val MAX = 30
    var index = -1
    private val memos = ArrayList<MemoBean>(30)


    fun saveMemo(memo: MemoBean) {
        if (memos.size > MAX) {
            memos.removeAt(0)
        }
        memos.add(memo)
        index = memos.size - 1
    }

    fun proMemo(): MemoBean? {
        index = if (index > 0) --index else index
        if (index == 0 && memos.size <= 0) {
            return null
        }
        return memos[index]
    }

    fun nextMemo(): MemoBean {
        index = if (index < memos.size - 1) ++index else index
        return memos[index]
    }
}