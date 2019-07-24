import os
import time
from threading import Thread

from appium_demo.utils import cmd_utils


class AndroidAvd:
    con_device = False

    def __init__(self, _emulator_path, _avd_path):
        self._emulator_path = _emulator_path
        self._avd_path = _avd_path

    def __start_avd__(self):
        self.kill_adb()
        thread_ = Thread(target=self.start_avd_process())
        thread_.start()
        print("Android模拟器启动完成！")

    def start_avd_process(self):
        print("打开Android模拟器：")
        cmd = self._emulator_path + ' -netdelay none -netspeed full -avd ' + self._avd_path
        cmd_utils.execute_code(cmd)

    def kill_adb(self):
        killer = 'adb kill-server'
        starter = 'adb start-server'
        cmd_utils.execute_code(killer, show_log=True)
        cmd_utils.execute_code(starter, show_log=True)

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

    def check_avd(self):
        adb_devices = 'adb devices'
        adb_devices_pro = os.popen(adb_devices)
        adb_devices_line = adb_devices_pro.readlines()
        if len(adb_devices_line) > 2:
            for adb in adb_devices_line[1:-1]:  # 去掉第一个和最后一个
                print('已连接设备：', adb.replace('\n', '').replace('\t', '').replace('device', ''))
                self.con_device = True
        else:
            print("暂无设备连接")
            self.con_device = False
        adb_devices_pro.close()

    def confirmCon(self):
        while not self.con_device:
            time.sleep(1)
            self.check_avd()


def start_avd():
    avd_name = 'Nexus_6P_API_26'
    emulator_path = r'E:\Users\Android\sdk\tools\emulator.exe'
    avd = AndroidAvd(emulator_path, avd_name)
    avd.__start_avd__()
    print("完成")


if __name__ == '__main__':
    start_avd()
