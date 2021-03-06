/etc/ssh/sshd_config:
    file.managed:
        - source: salt://security/sshd_config
        - user: root
        - group: root
        - mode: 400

/etc/sysctl.conf:
    file.managed:
        - source: salt://security/sysctl.conf
        - user: root
        - group: root
        - mode: 400

update_if_sysctl_changes:
    cmd.run:
        - name: sysctl -p
        - onchanges:
            - file: /etc/sysctl.conf

localhost_in:
    iptables.append:
        - table: filter
        - chain: INPUT
        - jump: ACCEPT
        - in-interface: lo
        - save: True

localhost_out:
    iptables.append:
        - table: filter
        - chain: OUTPUT
        - jump: ACCEPT
        - out-interface: lo
        - save: True

ssh_in_request:
    iptables.append:
        - table: filter
        - chain: INPUT
        - jump: ACCEPT
        - match: tcp
        - proto: tcp
        - dport: 22
        - save: True

ssh_in_response:
    iptables.append:
        - table: filter
        - chain: INPUT
        - jump: ACCEPT
        - match: tcp
        - proto: tcp
        - sport: 22
        - save: True

ssh_out_response:
    iptables.append:
        - table: filter
        - chain: OUTPUT 
        - jump: ACCEPT
        - match: tcp
        - proto: tcp
        - sport: 22
        - save: True

ssh_out_request:
    iptables.append:
        - table: filter
        - chain: OUTPUT 
        - jump: ACCEPT
        - match: tcp
        - proto: tcp
        - dport: 22
        - save: True

dns_request_udp:
    iptables.append:
        - table: filter
        - chain: OUTPUT
        - jump: ACCEPT
        - match: 
            - udp
            - state
        - proto: udp
        - dport: 53
        - connstate: NEW,ESTABLISHED

dns_response_udp:
    iptables.append:
        - table: filter
        - chain: INPUT
        - jump: ACCEPT
        - match: 
            - udp
            - state
        - proto: udp
        - sport: 53
        - connstate: ESTABLISHED

dns_request_tcp:
    iptables.append:
        - table: filter
        - chain: OUTPUT
        - jump: ACCEPT
        - match: 
            - tcp
            - state
        - proto: tcp 
        - dport: 53
        - connstate: NEW,ESTABLISHED

dns_response_tcp:
    iptables.append:
        - table: filter
        - chain: INPUT
        - jump: ACCEPT
        - match: 
            - tcp
            - state
        - proto: tcp 
        - sport: 53
        - connstate: ESTABLISHED

http_input_request:
    iptables.append:
        - table: filter
        - chain: INPUT
        - jump: ACCEPT
        - match: 
            - tcp
            - state
        - proto: tcp 
        - dport: 80
        - connstate: NEW,ESTABLISHED

http_input_response:
    iptables.append:
        - table: filter
        - chain: INPUT
        - jump: ACCEPT
        - match: 
            - tcp
            - state
        - proto: tcp 
        - sport: 80
        - connstate: ESTABLISHED

http_out_response:
    iptables.append:
        - table: filter
        - chain: OUTPUT
        - jump: ACCEPT
        - match: 
            - tcp
            - state
        - proto: tcp 
        - sport: 80
        - connstate: ESTABLISHED

http_out_request:
    iptables.append:
        - table: filter
        - chain: OUTPUT
        - jump: ACCEPT
        - match: 
            - tcp
            - state
        - proto: tcp 
        - dport: 80
        - connstate: NEW,ESTABLISHED

https_in_request:
    iptables.append:
        - table: filter
        - chain: INPUT
        - jump: ACCEPT
        - match: 
            - tcp
            - state
        - proto: tcp 
        - dport: 443
        - connstate: NEW,ESTABLISHED

https_in_response:
    iptables.append:
        - table: filter
        - chain: INPUT
        - jump: ACCEPT
        - match: 
            - tcp
            - state
        - proto: tcp 
        - sport: 443
        - connstate: ESTABLISHED

https_out_response:
    iptables.append:
        - table: filter
        - chain: OUTPUT
        - jump: ACCEPT
        - match: 
            - tcp
            - state
        - proto: tcp 
        - sport: 443
        - connstate: ESTABLISHED

https_out_request:
    iptables.append:
        - table: filter
        - chain: OUTPUT
        - jump: ACCEPT
        - match: 
            - tcp
            - state
        - proto: tcp 
        - dport: 443
        - connstate: NEW,ESTABLISHED

default_in:
    iptables.set_policy:
        - chain: INPUT
        - policy: DROP

default_forward:
    iptables.set_policy:
        - chain: FORWARD
        - policy: DROP

default_output:
    iptables.set_policy:
        - chain: OUTPUT
        - policy: DROP
