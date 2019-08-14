package com.environmt.plugin

class EnvironMnt {
    String BASE_URL
    String HOST
    int PORT
    String VIRTUAL_HOST
    String USERNAME
    String PASSWORD

    def config_environ(scenic = 'bsc', build_type = 'GENIUS') {
        String file_name = String.format('environmt/%s.xml', scenic)
        def bsc_config = new XmlParser().parse(file_name)
        bsc_config.environment.each {
            def name = it.attributes().get("name")
            if (build_type == name) {
                this.BASE_URL = it.BASE_URL[0].text()
                this.HOST = it.HOST[0].text()
                this.PORT = it.PORT[0].text().toInteger()
                this.VIRTUAL_HOST = it.VIRTUAL_HOST[0].text()
                this.USERNAME = it.USERNAME[0].text()
                this.PASSWORD = it.PASSWORD[0].text()
            }
        }
    }
}

