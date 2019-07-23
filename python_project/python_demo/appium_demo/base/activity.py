from appium_demo.base.appelement import AppElement
from appium_demo.base.titlelayout import TitleLayout


class Activity(AppElement):
    def __init__(self, class_name):
        self.title = TitleLayout()
        print("%s%s__%s%s" % ("________", class_name, self.title.getTitle(), "________"))

    def finish(self):
        self.title.finish()
