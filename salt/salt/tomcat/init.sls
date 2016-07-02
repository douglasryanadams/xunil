tomcat8_installed:
    pkg.installed:
        - pkgs:
            - haveged
            - tomcat8
            - authbind

/etc/ssl/certs/xunil.intermediate.ca:
    file.managed:
        - source: salt://tomcat/public_certs/xunil.intermediate.ca
        - user: root
        - group: root
        - mode: 400

/etc/ssl/certs/xunil.intermediate.cert:
    file.managed:
        - source: salt://tomcat/public_certs/xunil.cert
        - user: root
        - group: root
        - mode: 400

/etc/ssl/private/xunil.key:
    file.managed:
        - contents: "{{ salt['pillar.get']('ssl_key:xunil_key') }}"
        - user: root
        - group: root
        - mode: 400

/etc/tomcat8/server.xml:
    file.managed:
        - source: salt://tomcat/server.xml.jinja
        - user: tomcat8
        - group: tomcat8
        - mode: 400

/etc/tomcat8/logging.properties:
    file.managed:
        - source: salt://tomcat/logging.properties
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
            - file: /etc/tomcat8/logging.properties
            - file: /etc/default/tomcat8
