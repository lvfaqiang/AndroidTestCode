package com.lvfq.code.designpatterns.iterator;

/**
 * HuiIterator
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/5 上午11:08
 * @desc :
 */

public class HuiIterator implements Iterator {
    private DataItem[] list;
    private int position;

    public HuiIterator(DataItem[] array) {
        this.list = array;
    }

    @Override
    public boolean hasNext() {
        return position <= list.length - 1 && list[position] != null;
    }

    @Override
    public Object next() {
        return list[position++];
    }
}
