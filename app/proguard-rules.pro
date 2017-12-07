# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/apple/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-optimizationpasses 5                                                           # 指定代码的压缩级别
-dontusemixedcaseclassnames                                                     # 表示混淆时不使用大小写混合类名
-dontskipnonpubliclibraryclasses                                                # 表示不跳过 library 中的非 public 类
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*        # 混淆时所采用的算法

#--------------------
#保护注解
-keepattributes *Annotation*
###排除所有注解类

-keep class * extends java.lang.annotation.Annotation { *; }
-keep interface * extends java.lang.annotation.Annotation { *; }



-keep public class * extends android.app.Activity  #所有activity的子类不要去混淆
-keep public class * extends android.app.Fragment  #所有Fragment子类不要混淆
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService #指定具体类不要去混淆

-keepclasseswithmembernames class * {
    native <methods>;  #保持 native 的方法不去混淆
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);  #保持自定义控件类不被混淆，指定格式的构造方法不去混淆
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

#表示不混淆任何一个View中的setXxx()和getXxx()方法，因为属性动画需要有相应的setter和getter的方法实现，混淆了就无法工作了。
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View); #保持指定规则的方法不被混淆（Android layout 布局文件中为控件配置的onClick方法不能混淆）
}

-keep public class * extends android.view.View {  #保持自定义控件指定规则的方法不被混淆
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}
-keepclassmembers enum * {  #保持枚举 enum 不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable {  #保持 Parcelable 不被混淆（aidl文件不能去混淆）
    public static final android.os.Parcelable$Creator *;
}
-keepnames class * implements java.io.Serializable #需要序列化和反序列化的类不能被混淆（注：Java反射用到的类也不能被混淆）
-keepclassmembers class * implements java.io.Serializable { #保护实现接口Serializable的类中，指定规则的类成员不被混淆
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keepattributes Signature  #过滤泛型（不写可能会出现类型转换错误，一般情况把这个加上就是了）
-keep class **.R$* { *; }  #保持R文件不被混淆，否则，你的反射是获取不到资源id的
-keep class **.Webview2JsInterface { *; }  #保护WebView对HTML页面的API不被混淆
-keepclassmembers class * extends android.webkit.WebViewClient {  #如果你的项目中用到了webview的复杂操作 ，最好加入
     public void *(android.webkit.WebView,java.lang.String,android.graphics.Bitmap);
     public boolean *(android.webkit.WebView,java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebChromeClient {  #如果你的项目中用到了webview的复杂操作 ，最好加入
     public void *(android.webkit.WebView,java.lang.String);
}


# ---------------- Retrofit 混淆

# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

# ---------------- Glide 混淆
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}


# ------------ 未知混淆代码

#-dontwarn org.apache.http.conn.**
#-keep class org.apache.http.conn.** { *;}
#
#
#-dontwarn android.os.**
#-keep class android.os.** { *;}
#
#-dontwarn com.squareup.okhttp.**
#-keep class com.squareup.okhttp.** { *;}
#
#-dontwarn org.codehaus.mojo.animal_sniffer.**
#-keep class org.codehaus.mojo.animal_sniffer.** { *;}
#
#
#-dontwarn java.nio.file.**
#-keep class java.nio.file.** { *;}
#
#-dontwarn java.lang.invoke.**
#-keep class java.lang.invoke.** { *;}
#
#-dontwarn java.lang.reflect.**
#-keep class java.lang.reflect.** { *;}
#
#-dontwarn android.util.**
#-keep class android.util.** { *;}
#
#
#-dontwarn android.net.http.**
#-keep class android.net.http.** { *;}
#
#-dontwarn org.apache.http.params.**
#-keep class org.apache.http.params.** { *;}