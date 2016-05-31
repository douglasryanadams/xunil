/var/log/xunil.log:
    file.managed:
        - user: tomcat8
        - group: tomcat8
        - mode: 640

/var/lib/tomcat8/webapps/ROOT/script/config.js:
    file.managed:
        - source: salt://xunil/javascript_config.js
        - user: tomcat8
        - group: tomcat8
        - mode: 644
