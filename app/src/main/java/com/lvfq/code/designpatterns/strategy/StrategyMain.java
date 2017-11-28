package com.lvfq.code.designpatterns.strategy;

/**
 * StrategyMain
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/28 下午1:51
 * @desc : 策略模式
 */

public class StrategyMain {


    public static void main(String[] args) {
        /**
         * 常规写法，通过type 去判断
         */
        StrategyMain main = new StrategyMain();
        int price = main.calculatePrice(2, SUBWAY);
        System.out.println("Stratety Main : price " + price);


        System.out.println("--------------------");
        /**
         * 使用策略模式写法
         */
        StrategyMain main1 = new StrategyMain();
        main1.setStrategy(new SubwayStrategy());
        int price1 = main1.calculatePrice(2);
        System.out.println("Stratety Main : price1 " + price1);
    }

    private CalculateStrategy strategy;

    public void setStrategy(CalculateStrategy strategy) {
        this.strategy = strategy;
    }

    private int calculatePrice(int km) {
        return strategy.calculatePrice(km);
    }


    //-------------------- 以下 是常规通过 type 去判断，
    private static final int BUS = 1;
    private static final int SUBWAY = 2;
    private static final int TEXI = 3;

    /**
     * 出租车
     *
     * @param km
     * @return
     */
    private int texiPrice(int km) {
        return 3 * km;
    }

    /**
     * 地铁
     *
     * @param km
     * @return
     */
    private int subwayPrice(int km) {
        return 20 * km;
    }

    /**
     * 公交车
     *
     * @param km
     * @return
     */
    private int busPrice(int km) {
        return 1 * km;
    }


    private int calculatePrice(int km, int type) {
        int price = 0;
        switch (type) {
            case BUS:
                price = busPrice(km);
                break;
            case SUBWAY:
                price = subwayPrice(km);
                break;
            case TEXI:
                price = texiPrice(km);
                break;
        }
        return price;
    }

}
