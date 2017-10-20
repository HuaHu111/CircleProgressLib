# CircleProgressLib
进度条




# Dependencies

[![](https://jitpack.io/v/li-xiaojun/OkhttpDownloader.svg)](https://jitpack.io/#li-xiaojun/OkhttpDownloader)



![image](https://github.com/Yesi-hoang/TaoBaoTopLine/blob/master/Gif/TaoBaoTopLineGif.gif)

**Step 1.** Add the JitPack repository to your build file

```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

**Step 2.** Add the dependency

```groovy
dependencies {
	compile 'com.github.HuaHu111:CircleProgressLib:v1.0'
}
```




# How To Use
布局文件中

    <com.liyulong.circleprogresslib.CircleProgressView
        android:id="@+id/cir"
        cir:circle_stoken="5dp"
        cir:circle_color="#f00"
        cir:circle_progress="66"
        cir:circle_isAnime="true"
        cir:circle_speed="1"
        android:layout_width="100dp"
        android:layout_height="100dp" />

或者在代码中

        CircleProgressView circleProgressView = (CircleProgressView) findViewById(R.id.cir);
        circleProgressView.setAnime(true);
        circleProgressView.setMaxProgress(100);
        circleProgressView.setProgress(88);
        circleProgressView.setColor(Color.parseColor("#00ff00"));
  
  circle_stoken ： 进度条宽度<br>
  circle_color  ： 进度条颜色<br>
  circle_progress： 进度条进度<br>
  circle_isAnime ： 进度条是否开启动画
	​
