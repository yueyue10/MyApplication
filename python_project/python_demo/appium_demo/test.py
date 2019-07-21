import os

from appium import webdriver

from appium_demo import Config, desired_caps
from appium_demo.element_helper import AppElement
from appium_demo.mainac import MainActivity


class App(AppElement):

    def __init__(self, driver):
        AppElement.__init__(self, driver)
        print("正在打开APP...")

    def testMain(self):
        mainactivity = MainActivity(self.driver)
        mainactivity.homeTest(vp_test=False, grid_test=True)
        # mainactivity.mineTest()


if __name__ == "__main__":
    print("环境变量检查:", os.environ)
    print("ANDROID_HOME:", os.environ.get('ANDROID_HOME'))
    print("\n连接模拟器...")
    app = App(webdriver.Remote(Config.service_path, desired_caps))
    app.testMain()
