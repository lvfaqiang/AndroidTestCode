package com.lvfq.code.designpatterns.iterator;

import java.util.List;

/**
 * MinIterator
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/5 上午11:05
 * @desc :
 */

public class MinIterator implements Iterator {
    private List<DataItem> list;
    private int position;   // 这里的 position 表示下一个数据下标

    public MinIterator(List<DataItem> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return position <= list.size() - 1 && list.get(position) != null;
    }

    @Override
    public Object next() {
        return list.get(position++);
    }
}
