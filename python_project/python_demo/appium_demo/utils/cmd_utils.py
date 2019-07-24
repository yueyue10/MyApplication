import os


def execute_code(cmd_code, show_log=False):
    if show_log: print("执行cmd命令", cmd_code)
    cmd_pro = os.popen(cmd_code)
    print(cmd_pro.read())
    cmd_pro.close()
