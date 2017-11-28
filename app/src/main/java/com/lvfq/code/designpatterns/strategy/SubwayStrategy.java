package com.lvfq.code.designpatterns.strategy;

/**
 * SubwayStrategy
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/28 下午1:56
 * @desc :
 */

public class SubwayStrategy implements CalculateStrategy {
    @Override
    public int calculatePrice(int km) {
        // 计算地铁费用
        return 20 * km;
    }
}
