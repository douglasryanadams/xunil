tomcat8_installed:
    pkg.installed:
        - pkgs:
            - tomcat8
            - authbind

/etc/tomcat8/server.xml:
    file.managed:
        - source: salt://tomcat/server.xml.jinja
        - user: tomcat8
        - group: tomcat8
        - mode: 400

/etc/default/tomcat8:
    file.managed:
        - source: salt://tomcat/tomcat8.default_file
        - user: tomcat8
        - group: root
        - mode: 440

tomcat8_runs:
    service.running:
        - name: tomcat8
        - enable: true
        - watch:
            - file: /etc/tomcat8/server.xml
            - file: /etc/default/tomcat8
