from appium_demo import Config
from appium_demo.element_helper import AppElement, TitleLayout, waitTime


class Activity(AppElement):
    def __init__(self, driver):
        AppElement.__init__(self, driver=driver)
        self.title = TitleLayout(self.driver)


class LoginActivity(Activity):
    def __init__(self, driver):
        Activity.__init__(self, driver=driver)
        print("________登录界面测试________")
        self.getYzm(Config.phone_num)
        self.loginByYzm()

    def getYzm(self, phone_num):
        et_phone = self.findElementId('et_phone')
        et_phone.send_keys(phone_num)
        self.findElementId('time_code').click()

    def loginByYzm(self):
        yzm = input("请输入验证码:")
        et_code = self.findElementId('et_code')
        et_code.send_keys(yzm)
        self.findElementId('tv_login').click()


class VenueListActivity(Activity):
    def __init__(self, driver):
        Activity.__init__(self, driver=driver)
        print("________场馆介绍测试________")
        self.scrollTest()
        self.toDetail()

    def scrollTest(self):
        vp_venue = self.findElementId("vp_venue")
        self.swipeLeft(element=vp_venue, n=2)

    def toDetail(self):
        tv_to_detail = self.findElementId("tv_to_detail")
        tv_to_detail.click()
        detail = VenueDetailActivity(self.driver)
        self.title.finish()


class VenueDetailActivity(Activity):
    def __init__(self, driver):
        Activity.__init__(self, driver=driver)
        print("________场馆详情测试________")
        self.shareTest()
        self.findElementId("cancel_tv").click()
        self.title.finish()

    def shareTest(self):
        self.title.rightClick()


class NewsListActivity(Activity):
    def __init__(self, driver):
        Activity.__init__(self, driver=driver)
        print("________活动资讯测试________")
        # todo


class WebViewActivity(Activity):
    def __init__(self, driver):
        Activity.__init__(self, driver=driver)
        print("________WebView详情页面测试________")
        self.driver.wait_activity(".module.mine.web.WebViewActivity", 5)
        self.scrollTest()

    def scrollTest(self):
        title = TitleLayout(self.driver)
        self.swipeUp(n=5)
        title.finish()
