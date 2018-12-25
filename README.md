# AndroidTestCode
用于Android 日常代码测试，效果整理

### Update Log:
 - PagerSlidingTabStrip 整理（对之前整理的进一步的调整）， [Kotlin 版本](https://github.com/lvfaqiang/AndroidTestCode/blob/master/app/src/main/java/com/lvfq/code/view/PagerSlidingTabStrip.kt)、[Java版本](https://github.com/lvfaqiang/AndroidTestCode/blob/master/app/src/main/java/com/lvfq/code/tablayout/PagerSlidingTabStrip.java)
    ```
    <declare-styleable name="PagerSlidingTabStrip">
            <!-- 指示器颜色 -->
            <attr name="indicatorColor" format="color" />
            <!-- 下划线颜色 -->
            <attr name="underlineColor" format="color" />
            <!-- 分割线颜色 -->
            <attr name="dividerColor" format="color" />
            <!-- 指示器高度 -->
            <attr name="indicatorHeight" format="dimension" />
            <!-- 指示器宽度 ，isFixedIndicatorWidth 为 true 时有效 -->
            <attr name="indicatorWidth" format="dimension" />
            <!-- 是否固定指示器宽度 -->
            <attr name="isFixedIndicatorWidth" format="boolean" />
            <!-- 指示器左右内边距 -->
            <attr name="indicatorPaddingLeftRight" format="dimension" />
            <!-- 下划线高度 -->
            <attr name="underlineHeight" format="dimension" />
            <!-- 分割线内边距 -->
            <attr name="dividerPadding" format="dimension" />
            <!-- 每个 Tab 的 左右内边距 -->
            <attr name="tabPaddingLeftRight" format="dimension" />
            <!-- 滚动偏移值 -->
            <attr name="scrollOffset" format="dimension" />
            <!-- tab 背景 -->
            <attr name="tabBackground" format="reference" />
            <!-- 暂时无用 -->
            <attr name="shouldExpand" format="boolean" />
            <!-- tab 选中文字大小 -->
            <attr name="tabSelTextSize" format="dimension" />
            <!-- tab 默认文字大小 -->
            <attr name="tabDefTextSize" format="dimension" />
            <!-- tab 选中文字颜色 -->
            <attr name="tabSelTextColor" format="color" />
            <!-- tab 默认文字颜色 -->
            <attr name="tabDefTextColor" format="color" />
        </declare-styleable>
    ```
 - [strings.xml 配置 brand、tag 等公共文本](https://github.com/lvfaqiang/AndroidTestCode/blob/master/app/src/main/res/values/strings.xml)
 - 2018年09月02日

    [获取、移除 View 的OnClickListener](https://github.com/lvfaqiang/AndroidTestCode/blob/master/app/src/main/java/com/lvfq/code/view/click/ViewClickActivity.kt)

 - 2018年08月27日

    [Kotlin 协程+Channel 控制线程切换](https://github.com/lvfaqiang/AndroidTestCode/blob/master/app/src/main/java/com/lvfq/code/coroutines/thread_toggle/CoroutineToggleActivity.kt)
 - 2018年08月24日

    [Kotlin Channel](https://github.com/lvfaqiang/AndroidTestCode/blob/master/app/src/main/java/com/lvfq/code/coroutines/eventbus)

    [Kotlin 协程](https://github.com/lvfaqiang/AndroidTestCode/blob/master/app/src/main/java/com/lvfq/code/coroutines)
 - 2018年07月12日

    [渐变圆形进度条](https://github.com/lvfaqiang/AndroidTestCode/blob/master/app/src/main/java/com/lvfq/code/view/ViewMainActivity.kt)
 - 2018年07月10日

    [自定义数字键盘](https://github.com/lvfaqiang/AndroidTestCode/blob/master/app/src/main/java/com/lvfq/code/payment/)
    ```
    方式一 ， 简单的写了个 xml 布局，然后实现输入效果。
    方式二 ， 借助系统的 KeyBoardView 定义一个数字键盘。
    ```
 - 2018年06月21日

    [模拟一下 ButterKnife](https://github.com/lvfaqiang/AndroidTestCode/blob/master/app/src/main/java/com/lvfq/code/butterknife/)
 - 2018年06月18日

    [7.0 拍照适配测试](https://github.com/lvfaqiang/AndroidTestCode/blob/master/app/src/main/java/com/lvfq/code/file_provider/FileProviderActivity.java)
    （如需在 library 中使用，可以参考[多图选择库](https://github.com/lvfaqiang/Multi-Image-Selector)）
 - 2018年02月26日
 
    [Canvas 的基本用法](https://github.com/lvfaqiang/AndroidTestCode/tree/master/app/src/main/java/com/lvfq/code/custom/column_1/CanvasTestView1)
 - 2018年01月03日
 
    [使用 dagger.android, LiveData, ViewModel 实现一个项目列表 Demo](https://github.com/lvfaqiang/AndroidTestCode/tree/master/app/src/main/java/com/lvfq/code/architectureComponents)
 - 2017年12月27日
 
    [Dagger.android 测试](https://github.com/lvfaqiang/AndroidTestCode/tree/master/app/src/main/java/com/lvfq/code/dagger_android)
 - 2017年12月11日
 
    [Dagger2 Singleton ,自定义 Scope 测试](https://github.com/lvfaqiang/AndroidTestCode/tree/master/app/src/main/java/com/lvfq/code/dagger2)<br/>
    [Dagger2 注入多个module](https://github.com/lvfaqiang/AndroidTestCode/tree/master/app/src/main/java/com/lvfq/code/dagger2/Dagger2Activity.java)
 - 2017年12月07日
 
    [添加通用混淆代码](https://github.com/lvfaqiang/AndroidTestCode/blob/master/app/proguard-rules.pro)
 - 2017年12月06日
    
    新增一个 javaLib ,用于测试自定义 ButterKnife <br/>
    [AIDL跨进程通信](https://github.com/lvfaqiang/AndroidTestCode/tree/master/app/src/main/java/com/lvfq/code/aidl)<br/>
    [代理模式](https://github.com/lvfaqiang/AndroidTestCode/tree/master/app/src/main/java/com/lvfq/code/proxy)<br/>
    [Notification代理模式实战](https://github.com/lvfaqiang/AndroidTestCode/tree/master/app/src/main/java/com/lvfq/code/proxy/demo)
 - 2017年12月05日
    
    [优雅的停止线程](https://github.com/lvfaqiang/AndroidTestCode/tree/master/app/src/main/java/com/lvfq/code/thread/ThreadMain.java)<br/> 
    [迭代器模式](https://github.com/lvfaqiang/AndroidTestCode/tree/master/app/src/main/java/com/lvfq/code/designpatterns/iterator) <font color="blue"> - 摘自 Android源码设计模式解析与实战</font>
 - 2017年12月03日
    
    [备忘录模式](https://github.com/lvfaqiang/AndroidTestCode/tree/master/app/src/main/java/com/lvfq/code/designpatterns/memo) <font color="blue"> - 摘自 Android源码设计模式解析与实战</font><br/>
 - 2017年11月30日
 
    [带红色 * 号文本](https://github.com/lvfaqiang/AndroidTestCode/tree/master/app/src/main/java/com/lvfq/code/view/MustTextView.kt)(标注必选项)
 - 2017年11月29日

    [观察者模式](https://github.com/lvfaqiang/AndroidTestCode/tree/master/app/src/main/java/com/lvfq/code/designpatterns/observer) <font color="blue"> - 摘自 Android源码设计模式解析与实战</font><br/>
    [责任链模式](https://github.com/lvfaqiang/AndroidTestCode/tree/master/app/src/main/java/com/lvfq/code/designpatterns/dutychain) <font color="blue"> - 摘自 Android源码设计模式解析与实战</font><br/>
    [跳转手机百度、高德地图](https://github.com/lvfaqiang/AndroidTestCode/tree/master/app/src/main/java/com/lvfq/code/map/MapActivity.kt)
 - 2017年11月28日
    
    [工厂类模式](https://github.com/lvfaqiang/AndroidTestCode/tree/master/app/src/main/java/com/lvfq/code/designpatterns/factory) <font color="blue"> - 摘自 Android源码设计模式解析与实战</font><br/>
    [策略模式](https://github.com/lvfaqiang/AndroidTestCode/tree/master/app/src/main/java/com/lvfq/code/designpatterns/strategy) <font color="blue"> - 摘自 Android源码设计模式解析与实战</font>
 - 2017年11月28日
 
    添加设计模式 package<br/>
    [Builder设计模式](https://github.com/lvfaqiang/AndroidTestCode/tree/master/app/src/main/java/com/lvfq/code/designpatterns/builder) <font color="blue"> - 摘自 Android源码设计模式解析与实战</font><br/>
    使用简单的 [ImageLoader](https://github.com/lvfaqiang/AndroidTestCode/tree/master/app/src/main/java/com/lvfq/code/designpatterns/imageload) 测试 开闭原则，单一原则 <font color="blue"> - 摘自 Android源码设计模式解析与实战</font><br/>
    [单例模式（Single）](https://github.com/lvfaqiang/AndroidTestCode/tree/master/app/src/main/java/com/lvfq/code/designpatterns/single)测试<font color="blue"> - 摘自 Android源码设计模式解析与实战</font>
 - 2017年11月21日
 
    新增 NotificationTestActivity <br/>
    用于 测试 Android 系统 Notification ,以及接收到推送之后，默认调用系统的 震动和提示音 
 - 2017年11月20日
 
    新增 Kotlin 版本的 [SwipeRefreshView](https://github.com/lvfaqiang/AndroidTestCode/tree/master/app/src/main/java/com/lvfq/code/swipe) <br/>
    (SwipeRefreshLayout + RecyclerView + BaseRecyclerViewAdapterHelper)