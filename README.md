# android-no-trace-log
###无痕埋点
1、方案的整体思路是通过继承View的方式，通过反射给每一个view设置一个唯一的id，这个id是当前的activity或者fragment的名字+该view的变量名

2、所有的Activity和Fragment都要继承自BaseActivity和BaseFragment

缺点：代码中所有的view都要用logview包中的LogView
