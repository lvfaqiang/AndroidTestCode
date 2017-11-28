package com.lvfq.code.designpatterns.strategy;

/**
 * BusCalculate
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/28 下午1:55
 * @desc :
 */

public class BusCalculate implements CalculateStrategy {
    @Override
    public int calculatePrice(int km) {
        // 这里计算公交车指定距离的费用
        return 10;
    }
}
