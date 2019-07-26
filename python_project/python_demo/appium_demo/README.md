# appium_demo介绍
### 参考网上关于appium测试框架的文档构建该项目
### 本项目实现的功能有:
### 运行test.py程序可以(配置后打开Android模拟器)开启Appium服务,并使用appium测试代码对梦廊坊APP(我们单位做的一个APP,下载地址在下方)进程自动测试
## 目的:
## 1.回顾学习的python知识;
## 2.学习appium测试框架的使用
## 3.以后有机会爬取其他app数据
##系统环境: Win10
##视频录制使用: FSCapture
##开发工具使用: IntelliJ IDEA Community Edition
#
##appium运行过程中遇到的问题:
##1.安装过程比较繁琐,考验耐心的时间啊` v `
##2.配置AndroidSdk环境变量,每次修改后需要重启电脑才能生效
##2.1配置好环境变量,启动appium时检测tools和build-tools目录下的程序时每次都会多添加一个tools导致失败
##>>>处理方法:环境变量里面只需要配置ANDROID_HOME,并在path里面引用(%ANDROID_HOME%).而不需要配置其下面的tools和build-tools
##>>>然后重启重试.
##3.因为要使用C盘里面的东西,但是没有权限会报错
##3.1 Win10系统下C盘没有操作权限的解决方法 http://www.xitongtiandi.net/wenzhang/win10/21037.html
##3.2 Windows10家庭版没有组策略的解决方法 http://www.xitongcheng.com/jiaocheng/win10_article_41129.html
#
##使用命令启动Android模拟器遇到的问题:
# 三种方式启动Android模拟器，在android_avd.start_avd里面:
# 1.第一种和第二种方式启动模拟器，是阻塞式的。不能放在项目中的test.py里面和appium测试代码组合使用。
# 2.第三种方式启动模拟器单独开启window应用程序，不会阻塞进程。可以放在test.py里面和appium测试代码组合使用，但是会新建一个命令窗口。
# 3.我自己比较喜欢第二种方式。
##使用命令启动AppiumService遇到的问题:
# 参考网上的文档，测试了多种启动方式最终找到了一种正确的启动方式。在项目中的multi_appium.start_service里面可以看代码。
# 这里赘述一下我遇到的各种失败:
# 1.网上介绍通过命令行 appium -a 127.0.0.1 -p 4723 --session-override
# 电脑安装的是Appium Desktop,找不到appium命令.以失败结束.
# 2.网上介绍通过命令行 node D:\software\Appium\node_modules\appium\lib\server\main.js --address 127.0.0.1 --port 4723
# 2.1在Appium安装目录下和上面的node_modules\appium\lib\server结构不一样.
# 2.2经过多次测试最终得到Appium\resources\app\node_modules\appium\build\lib\main.js是可以使用的
# 2.3因为我的Appium是安装到C:\Program Files目录下,在执行node C:\Program Files\... 命令时报错找不到路径C:\Program
# 2.4多次测试之后发现将C:\Program Files\修改成C:\Progra~1\即可.
# 3.现在通过项目中的multi_appium.start_service已经可以正常启动AppiumService.
#   每次启动后日志都不受控制的输出到了控制台上,这是我不想看到的.
#   现在想要做到日志可控,error的日志可以输出控制台,其他日志不输出.或者将日志输出到另一个控制台也行.或者将日志输出到日志文件也可以.

### 执行步骤如下：
> * 必要条件是:
> * Appium各种环境已配置好
> * 通过AppiumDoctor检测必备条件已具备
> * 可以通过Appium Desktop启动Appium
> * 电脑已经连接Android设备或者Android模拟器
> * 具体执行步骤:
> * 1.启动Android模拟器或者连接Android设备
> * 2.将梦廊坊安装包安装到Android模拟器或者Android设备(下载地址在下方)
> * 3.在IntelliJ IDEA或者PyCharm开发工具打开该项目,或者通过命令行python ...
> * 3.1测试python启动appium服务,根据个人电脑配置修改appium_demo/android_avd里面的js_path,host,port
> * 3.2如果需要测试python打开Android设备,根据个人电脑配置修改appium_demo/android_avd里面的avd_name,device_name,emulator_path
> * 4.运行appium_demo/test.py程序,排查错误.正常情况就可以看到效果.

[![appium测试视频](https://github.com/yueyue10/MyApplication/raw/master/python_project/python_demo/appium_demo/doc/video_appium_test1.png)](https://v.qq.com/txp/iframe/player.html?vid=j0884yv58jc)

[![启动Android模拟器视频](https://github.com/yueyue10/MyApplication/raw/master/python_project/python_demo/appium_demo/doc/video_start_avd.png)](https://v.qq.com/txp/iframe/player.html?vid=j0884yv58jc)

![apk_path](https://github.com/yueyue10/MyApplication/raw/master/python_project/python_demo/appium_demo/doc/apk_path.png)
![appium_test1](https://github.com/yueyue10/MyApplication/raw/master/python_project/python_demo/appium_demo/doc/appium_test1.png)
![start_avd](https://github.com/yueyue10/MyApplication/raw/master/python_project/python_demo/appium_demo/doc/start_avd.png)

##参考:   https://blog.csdn.net/huilan_same/article/details/52544521
##参考:   https://blog.csdn.net/Thinkingcao/article/details/88039558
##参考:   https://blog.csdn.net/duzilonglove/article/details/78455051
##参考:   https://blog.csdn.net/happyuu/article/details/82965984
##参考:   https://www.jianshu.com/p/46359b8758d4
##参考:   http://www.sohu.com/a/283518254_820120
##参考:   https://www.cnblogs.com/yoyoketang/tag/
##参考:   https://blog.csdn.net/ak739105231/article/details/89066070
##参考:   https://blog.csdn.net/sinat_41774836/article/details/88965281
##参考:   https://www.cnblogs.com/abao0/p/9166334.html
##参考:   https://testerhome.com/topics/12433
##参考:   https://www.cnblogs.com/harry-xiaojun/p/9554687.html
##参考:   https://blog.csdn.net/Ls4034/article/details/89161157
##参考:   https://www.cnblogs.com/meitian/p/6375960.html?utm_source=itdadao&utm_medium=referral
