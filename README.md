# AndroidHomework6-lv3
AndroidHomework6-lv3
调用拨号时出现问题，我的拨号按钮设置在第二个Activity，
<intent-filter>
     <action android:name="android.intent.action.DIAL"/>
     <category android:name="android.intent.category.DEFAULT"/>
     <data android:scheme="tel"/>
</intent-filter>
设置在主活动，不知道是不是这个原因，暂时没解决。
