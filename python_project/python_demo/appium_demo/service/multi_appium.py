# coding=utf-8
import io
import os
import subprocess
import time


class AppiumService:

    def kill_appium(self, port):
        print('关闭Appium 进程')
        process = os.popen("netstat -aon |findstr " + str(port))
        process_list = process.readlines()
        process.close()
        for pro in process_list:
            print(pro)
            pid = pro.split(" ")[-1]
            m = os.popen("taskkill -f -pid %s" % pid)
            print(m.read())
            m.close()
        time.sleep(2)

    def appium_start(self, js_path, host, port):
        '''方法一：通过appium方式 启动appium server
            proc.wait() 有日志，经常无响应。即使启动服务以后也不能用
            proc.communicate() 无日志，不能用。
        '''
        cmd = r'start /b node ' + js_path + ' --address ' + host + ' --port ' + str(port)
        print('cmd执行%s' % cmd)
        proc = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        # proc.communicate()
        proc.wait()
        if proc.returncode == 0:
            try:
                stdout = io.TextIOWrapper(proc.stdout, encoding='utf-8').read()
                stderr = io.TextIOWrapper(proc.stderr, encoding='utf-8').read()
                print(stdout)
                print(stderr)
            except Exception as e:
                print(e)

    def node_start_appium(self, js_path, host, port):
        '''方法二：通过node方式 启动appium server
            os.system 执行没有日志，但是服务可以使用
            os.popen 有日志，但是不成功
        '''

        print('开始启动Appium Service')
        print('cmd执行', r'start /b node ' + js_path + ' --address ' + host + ' --port ' + str(port))
        # res = os.popen('ipconfig').read()
        res = os.system(r'start /b node ' + js_path + ' --address ' + host + ' --port ' + str(port))
        # res = os.popen(r'start /b node ' + js_path + ' --address ' + host + ' --port ' + str(port))
        # res_line = res.read()
        # 用来执行操作系统命令，但是只能帮你执行，获取不到结果；执行后会出现黑色乱码显示
        # print(res_line)  # 输出为1
        # res.close()


def start_service():
    js_path = r"C:\Progra~1\Appium\resources\app\node_modules\appium\build\lib\main.js"
    host = '127.0.0.1'
    port = 4723
    app_service = AppiumService()
    app_service.kill_appium(port)
    # app_service.appium_start(js_path, host, port)
    app_service.node_start_appium(js_path, host, port)


if __name__ == '__main__':
    start_service()

"""
cmd 窗口测试代码
"""
# 定位到main.js目录以后使用命令：     node main.js --address 127.0.0.1 --port 4723
# main.js文件位置：   C:\Program Files\Appium\resources\app\node_modules\appium\build\lib\main.js
# 空格问题使用Progra~1替代
# cmd通用的命令为：   node C:\Progra~1\Appium\resources\app\node_modules\appium\build\lib\main.js --address 127.0.0.1 --port 4723
# 启动成功截图如：appium_success.png所示

# 查看4723端口占用信息：   netstat -aon |findstr 4723
# 杀掉pid是23112的程序：   taskkill -f -pid 23112
