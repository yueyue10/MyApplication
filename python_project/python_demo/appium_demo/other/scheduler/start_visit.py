from apscheduler.schedulers.blocking import BlockingScheduler

from appium_demo.log import save_log
from appium_demo.other.visitors.kuaidaili_visitor import KuiDaiLi
from appium_demo.utils.time_util import now_datetime

scheduler = BlockingScheduler()


@scheduler.scheduled_job('cron', day_of_week='mon-sun', hour='17-18', minute='*/1')
def scheduled_job_visit():
    '''
    访问博客
    采集频率：1分整倍数，就执行这个函数
    '''
    save_log("_________visit start_________", now_datetime(), _log_name='scheduler')
    KuiDaiLi().start()
    save_log("_________visit end_________", now_datetime(), "\n", _log_name='scheduler')


scheduler.start()
