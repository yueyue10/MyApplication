import os
import time
from multiprocessing import Process
from threading import Thread

from appium_demo.utils import cmd_utils


class AndroidAvd:
    # 是否有设备连接
    con_device = False

    def __init__(self, _emulator_path, _avd_name):
        self._emulator_path = _emulator_path
        self._avd_name = _avd_name

    def start_avd_thread(self):
        self.kill_adb()
        thread_ = Thread(target=self.start_avd())
        thread_.start()
        print("Android模拟器启动完成！")

    def start_avd_process(self):
        self.kill_adb()
        p1 = Process(target=self.start_avd())
        p1.start()
        print('主进程', os.getpid())
        print("Android模拟器启动完成！")

    def start_avd(self):
        print("打开Android模拟器：")
        print("子进程：", os.getppid(), os.getpid())
        cmd = self._emulator_path + ' -netdelay none -netspeed full -avd ' + self._avd_name
        cmd_utils.cmd_system(cmd, show_log=True)

    def kill_adb(self):
        if not self.check_avd(device_name=self._avd_name):
            killer = 'adb kill-server'
            starter = 'adb start-server'
            cmd_utils.cmd_popen(killer, show_log=True)
            cmd_utils.cmd_popen(starter, show_log=True)

    def kill_adb_port(self, port):
        print('关闭adb 进程')
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

    def check_avd(self, device_name=''):
        device_open = False
        adb_devices = 'adb devices'
        adb_devices_pro = os.popen(adb_devices)
        adb_devices_line = adb_devices_pro.readlines()
        if len(adb_devices_line) > 2:
            for adb in adb_devices_line[1:-1]:  # 去掉第一个和最后一个
                con_device_ = adb.replace('\n', '').replace('\t', '').replace('device', '')
                print('已连接设备：', con_device_)
                self.con_device = True
                if device_name == con_device_:
                    device_open = True
        else:
            print("暂无设备连接")
            self.con_device = False
        adb_devices_pro.close()
        return device_open

    def confirmCon(self):
        while not self.con_device:
            time.sleep(1)
            self.check_avd()


def start_avd():
    avd_name = 'Nexus_6P_API_26'
    emulator_path = r'E:\Users\Android\sdk\tools\emulator.exe'
    avd = AndroidAvd(emulator_path, avd_name)
    # avd.start_avd_thread()
    avd.start_avd_process()
    print("完成")


if __name__ == '__main__':
    start_avd()
