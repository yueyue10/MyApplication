# python_demo介绍
##根据廖雪峰Python教程编写的Python测试程序
##教程地址：https://www.liaoxuefeng.com/wiki/1016959663602400
## 方法一：在AndroidStudio中运行可使用Terminal工具运行使用

### 执行步骤如下：(当然必要条件是安装Python应用并将其配置到环境变量中)
> * 1.在AS工具中复制python程序的所在路径
> * 2.打开AS中的Terminal命令行工具
> *     输入命令并回车：    cd E:\Users\Github\MyApplication\python_demo\basic
> *     输入命令并回车：    python basic_data.py

> * 3.在AS的Terminal命令中就可以看到执行的结果了，效果如下图所示

![python_demo](https://raw.github.com/yueyue10/MyApplication/master/doc/pic_python.png)

## 方法二：使用Intellij IDEA打开项目运行即可，效果如下图所示
### 参考：https://blog.csdn.net/u010586151/article/details/79065447
![python_demo](https://raw.github.com/yueyue10/MyApplication/master/python_project/python_demo/python_demo.png)


### 项目结构
(和教程目录相比缺少的内容是：电子邮件、访问数据库、Web开发、异步IO)
```
|-- python_demo
    |-- basic [Python基础]
    |   |-- basic_data.py [数据类型和变量]
    |   |-- dict_set.py [使用dict和set]
    |   |-- factor.py [条件判断]
    |   |-- list_tuple.py [使用list和tuple]
    |   |-- round.py [循环]
    |-- com_build_in_module [常用内建模块]
    |   |-- base641.py [base64]
    |   |-- collections1.py [collections]
    |   |-- contextlib1.py [contextlib]
    |   |-- datetime1.py [datetime]
    |   |-- hashlib1.py [hashlib]
    |   |-- html_parser.py [HTMLParser]
    |   |-- itertools1.py [itertools]
    |   |-- struct1.py [struct]
    |   |-- urllib1.py [urllib]
    |   |-- xml1.py [XML]
    |-- com_third_party_mods [常用第三方模块]
    |   |-- chardet1.py [chardet]
    |   |-- psutil1.py [psutil]
    |   |-- requests1.py [requests]
    |   |-- virtualenv1.py [virtualenv]
    |   |-- pillow [Pillow]
    |   |   |-- pillow1.py
    |   |   |-- pillow2.py
    |-- error_debug_test [错误、调试和测试]
    |   |-- debug.py [调试]
    |   |-- doc_test.py [文档测试]
    |   |-- error_handling.py [错误处理]
    |   |-- unit_test.py [单元测试]
    |-- func_program [函数式编程]
    |   |-- decorator.py [装饰器]
    |   |-- lambda_func.py [匿名函数]
    |   |-- partial_func.py [偏函数]
    |   |-- return_func.py [返回函数]
    |   |-- higher_order_func [高阶函数]
    |       |-- filter.py [filter]
    |       |-- map_reduce.py [map/reduce]
    |       |-- sorted.py [sorted]
    |-- gui [图形界面]
    |   |-- gui1.py
    |-- high_feature [高级特性]
    |   |-- generator.py [生成器]
    |   |-- iteration.py [迭代]
    |   |-- iterator.py [迭代器]
    |   |-- list_comprehensions.py [列表生成式]
    |   |-- slice.py [切片]
    |-- high_oop_program [面向对象高级编程]
    |   |-- custom_class.py [定制类]
    |   |-- multiple_extends.py [多重继承]
    |   |-- use_enum.py [使用枚举类]
    |   |-- use_metaclass.py [使用元类]
    |   |-- use_property.py [使用@property]
    |   |-- use_slots.py [使用__slots__]
    |-- io_program [IO编程]
    |   |-- file_read_write.py [文件读写]
    |   |-- handle_file_dir.py [操作文件和目录]
    |   |-- serialize.py [序列化]
    |   |-- stringio_byteio.py [StringIO和BytesIO]
    |-- method [函数]
    |   |-- define_method.py [定义函数]
    |   |-- method_param.py [函数的参数]
    |   |-- recall_method.py [递归函数]
    |   |-- use_method.py [调用函数]
    |-- oop_program [面向对象编程]
    |   |-- access_control.py [类和实例]
    |   |-- attributes.py [实例属性和类属性]
    |   |-- class_instance.py [类和实例]
    |   |-- extends_subclass.py [继承和多态]
    |   |-- get_object_type.py [获取对象信息]
    |-- process_thread [进程和线程]
    |   |-- process.py [多进程]
    |   |-- process1.py
    |   |-- process2.py
    |   |-- task_master.py [分布式进程]
    |   |-- task_worker.py
    |   |-- thread.py [多线程]
    |   |-- threadLocal.py [ThreadLocal]
    |-- winsock [网络编程]
        |-- tcp_ip.py [TCP/IP简介]
        |-- tcp_program.py [TCP编程]
        |-- udp_program.py [UDP编程]
        |-- udp_program1.py
```
