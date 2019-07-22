from selenium.webdriver.common.by import By

from appium_demo import AppConfig
from appium_demo.element_helper import AppElement, TitleLayout, waitTime


class Activity(AppElement):
    def __init__(self, class_name):
        self.title = TitleLayout()
        print("%s%s__%s%s" % ("________", class_name, self.title.getTitle(), "________"))

    def finish(self):
        self.title.finish()


class LoginActivity(Activity):
    def __init__(self):
        Activity.__init__(self, self.__class__.__name__)

    def login(self):
        self.getYzm(AppConfig.phone_num)
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
    def __init__(self):
        Activity.__init__(self, self.__class__.__name__)
        self.scrollTest()
        self.toDetail()

    def scrollTest(self):
        vp_venue = self.findElementId("vp_venue")
        self.swipeLeft(element=vp_venue, n=2)

    def toDetail(self):
        tv_to_detail = self.findElementId("tv_to_detail")
        tv_to_detail.click()
        detail = VenueDetailActivity()
        self.title.finish()


class VenueDetailActivity(Activity):
    def __init__(self):
        Activity.__init__(self, self.__class__.__name__)
        self.shareTest()
        self.findElementId("cancel_tv").click()
        self.title.finish()

    def shareTest(self):
        self.title.rightClick()


class NewsListActivity(Activity):
    def __init__(self):
        Activity.__init__(self, self.__class__.__name__)
        self.driver.wait_activity(".module.news.NewsListActivity", 5)
        try:
            self.recyclerView = self.findElementId("recyclerView")
            self.itemViews = self.recyclerView.find_elements(by=By.CLASS_NAME, value="android.widget.LinearLayout")
            waitTime(1)
            self.voteTest(0)
            waitTime(1)
            self.itemClickTest(0)
        except Exception as e:
            print(e)

    def voteTest(self, item_position):
        self.itemViews[item_position].find_element(by=By.ID, value='likes_layout').click()
        waitTime(1)
        tv_title = self.findElementId("tv_title").text
        if tv_title == '登录':
            loginactivity = LoginActivity()
            loginactivity.finish()

    def itemClickTest(self, item_position):
        self.itemViews[item_position].click()
        newsdetailactivity = NewsDetailActivity()
        newsdetailactivity.swipeUp(n=3)
        newsdetailactivity.finish()

    def scrollTest(self):
        self.swipeUp(n=5)


class NewsDetailActivity(Activity):
    def __init__(self):
        Activity.__init__(self, self.__class__.__name__)


class WebViewActivity(Activity):
    def __init__(self):
        Activity.__init__(self, self.__class__.__name__)
        self.driver.wait_activity(".module.mine.web.WebViewActivity", 5)
        self.scrollTest()

    def scrollTest(self):
        title = TitleLayout()
        self.swipeUp(n=5)
        title.finish()
