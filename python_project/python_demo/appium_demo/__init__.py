"""
Appium 测试框架使用
"""

desired_caps = {
    'platformName': 'Android',
    'automationName': 'UiAutomator2',
    'deviceName': 'emulator-5554',
    'platformVersion': '8.0',
    'appPackage': 'com.ennova.dreamlf',
    'appActivity': 'com.ennova.dreamlf.module.main.splash.SplashActivity',
    'noReset': "True",
    'deviceReadyTimeout': "5",
    'androidDeviceReadyTimeout': "5",
    'androidInstallTimeout': "5",
    'avdLaunchTimeout': "5",
    'avdReadyTimeout': "5"
}


class AppConfig:
    element_path = "com.ennova.dreamlf:id/"
    phone_num = "18810126510"
    service_path = 'http://127.0.0.1:4723/wd/hub'

