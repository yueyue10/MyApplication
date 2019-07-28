import logging
import random
import threading
import time

import requests
# 请求头
from lxml import etree

from appium_demo import log
from appium_demo.log import LogConfig

user_agent_list = [
    'Mozilla/5.0(compatible;MSIE9.0;WindowsNT6.1;Trident/5.0)',
    'Mozilla/4.0(compatible;MSIE8.0;WindowsNT6.0;Trident/4.0)',
    'Mozilla/4.0(compatible;MSIE7.0;WindowsNT6.0)',
    'Opera/9.80(WindowsNT6.1;U;en)Presto/2.8.131Version/11.11',
    'Mozilla/5.0(WindowsNT6.1;rv:2.0.1)Gecko/20100101Firefox/4.0.1',
    'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.71 Safari/537.1 LBBROWSER',
    'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 732; .NET4.0C; .NET4.0E)',
    'Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.84 Safari/535.11 SE 2.X MetaSr 1.0',
    'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Maxthon/4.4.3.4000 Chrome/30.0.1599.101 Safari/537.36',
    'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 UBrowser/4.0.3214.0 Safari/537.36'
]
headers = {'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
           'User-Agent': random.choice(user_agent_list),
           'Accept-Encoding': 'gzip, deflate',
           'Accept-Language': 'zh-CN,zh;q=0.8',
           # 'Connection': 'close',
           # 'keep_alive ': 'False',
           }
# 完善的headers
target_headers = {'Upgrade-Insecure-Requests': '1',
                  'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36',
                  'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
                  'Referer': 'http://www.xicidaili.com/nn/',
                  'Accept-Encoding': 'gzip, deflate, sdch',
                  'Accept-Language': 'zh-CN,zh;q=0.8',
                  }


class RequestRobot(threading.Thread):

    def __init__(self, _page):
        threading.Thread.__init__(self)
        self.page = _page

    def run(self):
        proxys_list = []
        threadLock.acquire()
        # 存储代理的列表
        req = requests.get(daili_url, headers=headers, timeout=5)
        print(time.time(), req.json())
        # print(req.json())
        # print(type(req.json()))
        # print(req.json().get("msg"))
        for ips in req.json().get("msg"):
            proxys_list.append("https" + '#' + ips.get("ip") + '#' + ips.get("port"))
        time.sleep(10)
        threadLock.release()
        # 请求博客详情
        for proxy in proxys_list:
            split_proxy = proxy.split('#')
            self.http(blog_url, _proxyHttp=split_proxy[0], _proxyHost=split_proxy[1], _proxyPort=split_proxy[2])
        # 返回代理列表
        return proxys_list

    # 请求博客详情
    def http(self, _url, _proxyHttp, _proxyHost, _proxyPort):
        proxy_meta = "%(http)s://%(host)s:%(port)s" % {
            "http": _proxyHttp,
            "host": _proxyHost,
            "port": _proxyPort
        }
        proxies = {
            "http": proxy_meta,
            "https": proxy_meta,
        }
        try:
            # print(self.proxies)
            requests.adapters.DEFAULT_RETRIES = 3
            s = requests.session()
            s.keep_alive = False
            req = requests.get(url=_url, headers=headers, proxies=proxies, timeout=5)
            req.encoding = 'utf-8'
            if req.status_code == 200:
                html = req.text
                com_html = etree.HTML(html)
                read_count = com_html.xpath('//span[@class="read-count"]')
                if read_count:  # 输出：阅读数
                    print(proxy_meta, read_count[0].text)
            else:
                print("请求无响应：")
        except Exception as e:
            print("ip不可用：", proxy_meta)
            logging.error("ip不可用：" + str(proxy_meta) + str(e))


def visit_blog(thread_num=20):
    threads = []
    for x in range(1, thread_num):
        threads.append(RequestRobot(_page=x))
    # 启动所有线程
    for t in threads:
        t.start()
    # 主线程中等待所有子线程退出
    for t in threads:
        t.join()


if __name__ == '__main__':
    '''使用《蘑菇代理》网址的动态ip，正常是收费的，因为新用户免费体验送了400条'''
    path = LogConfig.log_path
    blog_url = 'https://blog.csdn.net/a_yue10/article/details/97392747'
    daili_url = 'http://piping.mogumiao.com/proxy/api/get_ip_bs?appKey=5e9bf0cd2f574a93ad38600c67a805a5&count=10&expiryDate=0&format=1&newLine=2'
    threadLock = threading.Lock()
    visit_blog(thread_num=100)
