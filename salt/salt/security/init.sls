/etc/ssh/sshd_config:
    file.managed:
        - source: salt://security/sshd_config
        - user: root
        - group: root
        - mode: 400

