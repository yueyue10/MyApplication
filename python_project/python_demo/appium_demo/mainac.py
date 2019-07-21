from selenium.webdriver.common.by import By

from appium_demo.element_helper import waitTime, AppElement
from appium_demo.modules import LoginActivity, VenueListActivity, WebViewActivity


class MainActivity(AppElement):
    def __init__(self, driver):
        AppElement.__init__(self, driver=driver)
        print("________主页界面测试________")
        self.driver.wait_activity(".module.main.MainActivity", 10)

    def homeTest(self, vp_test=True, grid_test=True):
        home_fragment = HomeFragment(self.driver)
        if vp_test: home_fragment.viewPagerTest()
        if grid_test: home_fragment.gridClick()

    def mineTest(self):
        waitTime(1)
        self.driver.find_element_by_xpath(
            "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.RelativeLayout[2]/android.widget.ImageView").click()
        user_name_tv = self.findElementId('user_name_tv').text
        if user_name_tv == '未登录':
            self.findElementId('user_icon_iv').click()
            loginactivity = LoginActivity(self.driver)
        else:
            pass


class HomeFragment(AppElement):
    def __init__(self, driver):
        AppElement.__init__(self, driver=driver)
        print("_______App首页UI测试_______")

    def viewPagerTest(self):
        viewPager = self.findElementId("viewPager")
        if viewPager:
            self.swipeLeft(element=viewPager, n=3)
            viewPager.click()
            webviewactivity = WebViewActivity(self.driver)
        waitTime(1)

    def gridClick(self):
        module_rv = self.findElementId("module_rv")
        # 门票预定点击
        module_rv.find_elements(by=By.CLASS_NAME, value="android.widget.LinearLayout")[0].click()
        waitTime(1)
        # 场馆介绍点击
        module_rv.find_elements(by=By.CLASS_NAME, value="android.widget.LinearLayout")[1].click()
        venuelistactivity = VenueListActivity(self.driver)
        waitTime(1)
        # 活动资讯点击

        # 建议反馈点击
