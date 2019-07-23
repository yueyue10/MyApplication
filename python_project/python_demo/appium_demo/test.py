import os

from appium import webdriver

from appium_demo import AppConfig, desired_caps
from appium_demo.base.appelement import AppElement
from appium_demo.module.mainac import MainActivity


class App(AppElement):
    # APP 里面的参数
    isLogin = False

    def __init__(self):
        print("正在打开APP...")

    def test(self):
        mainAc = MainActivity()
        mainAc.homeTest(vp_test=False, grid_test=True)
        # mainAc.mineTest()


if __name__ == "__main__":
    print("环境变量检查:", os.environ)
    print("ANDROID_HOME:", os.environ.get('ANDROID_HOME'))
    print("\n连接模拟器...")
    AppElement.driver = webdriver.Remote(AppConfig.service_path, desired_caps)
    app = App()
    app.test()
