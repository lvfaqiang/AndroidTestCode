package com.lvfq.code.designpatterns.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * IteratorMain
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/5 上午10:57
 * @desc :
 */

public class IteratorMain {
    public static void main(String[] args) {
        List<DataItem> min = new ArrayList<>();
        min.add(new DataItem("min1", 10));
        min.add(new DataItem("min2", 20));
        min.add(new DataItem("min3", 30));
        min.add(new DataItem("min4", 40));
        min.add(new DataItem("min5", 50));

        MinIterator minIterator = new MinIterator(min);

        DataItem[] hui = new DataItem[]{
                new DataItem("hui1", 10),
                new DataItem("hui2", 20),
                new DataItem("hui3", 30),
                new DataItem("hui4", 40),
                new DataItem("hui5", 50)
        };

        HuiIterator huiIterator = new HuiIterator(hui);

        check(minIterator);
        check(huiIterator);
    }

    public static void check(Iterator iterator) {
        while (iterator.hasNext()) {
            DataItem dataitem = (DataItem) iterator.next();
            System.out.println("DataItem ： " + dataitem.getName() + " , " + dataitem.getAge());
        }
    }
}
