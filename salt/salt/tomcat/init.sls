tomcat8_installed:
    pkg.installed:
        - name: tomcat8

/etc/tomcat8/server.xml:
    file.managed:
        - source: salt://tomcat/server.xml.jinja
        - user: tomcat8
        - group: tomcat8
        - mode: 400

tomcat8_runs:
    service.running:
        - name: tomcat8
        - enable: true
        - watch:
            - file: /etc/tomcat8/server.xml
