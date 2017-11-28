package com.lvfq.code.designpatterns.single;

import java.util.HashMap;
import java.util.Map;

/**
 * Singletons
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/26 下午11:38
 * @desc : 单例模式
 */

public class Singletons {

    /**
     * 推荐使用静态内部类单例模式
     * - 摘自 【Android 源码设计模式解析与实战】
     */

    public static class LanHanSingle {
        /**
         * 懒汉单例模式，
         * 优点，当使用的时候，才会被实例化，在一定程度上节约了资源，
         * 缺点，第一次加载时需要及时的实例化，加载稍慢。然后就是每次调用都需要去进行同步。造成不必要的开销，
         * <p>
         * 这种模式一般不建议使用。
         */
        private static LanHanSingle instance;

        public static synchronized LanHanSingle getInstance() {
            if (instance == null) {
                instance = new LanHanSingle();
            }
            return instance;
        }
    }


    public static class DCLSingle {
        /**
         * Double Check Lock 双重检查锁定
         * 优点，资源利用率高，效率高。第一次执行 getInstance 方法才会被实例化。
         * 缺点，第一次加载时反应稍慢。在高并发的场景下有一定的缺陷(可能会创建失败），虽然概率很小，但是确实会有。
         * 在低于JDK 1.6 的版本中使用，会出现失败问题，1.6之后，基本能满足需求，
         */
        private volatile static DCLSingle instance = null;

        public static DCLSingle getInstance() {
            if (instance == null) {
                synchronized (DCLSingle.class) {
                    if (instance == null) {
                        instance = new DCLSingle();
                    }
                }
            }
            return instance;
        }
    }

    public static class InnerSingle {

        public static InnerSingle getInstance() {
            return SingleHelper.instance;
        }

        /**
         * 静态内部类单例模式
         * 第一次加载时 InnerSingle 时，不会初始化 instance , 当第一次调用 getInstance 方法时，才会进行初始化。
         * <p>
         * 这种方式不仅能够确保线程安全，也能保证对象的唯一性，也延迟了单例的初始化。
         * <p>
         * 这里是推荐使用的单例模式。
         */
        private static class SingleHelper {
            private static final InnerSingle instance = new InnerSingle();
        }
    }


    public static class SingleManager {
        /**
         * 使用容器实现单例模式
         */
        private static Map<String, Object> objMap = new HashMap<>();

        public static void registerService(String key, Object object) {
            if (!objMap.containsKey(key)) {
                objMap.put(key, object);
            }
        }

        public static Object getService(String key) {
            return objMap.get(key);
        }
    }

}
