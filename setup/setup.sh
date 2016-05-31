#!/bin/bash

sudo apt-get install git

git clone git@github.com:douglasryanadams/xunil-private.git
git clone git@github.com:douglasryanadams/xunil.git

sudo add-apt-repository ppa:saltstack/salt
sudo apt-get update
sudo apt-get install salt-minion salt-master

sudo add-apt-repository --remove ppa:saltstack/salt
sudo apt-get update

cd /etc/salt

sudo rm -r master minion
sudo ln -s /home/rook/xunil/salt/config/master
sudo ln -s /home/rook/xunil/salt/config/minion

cd /srv
sudo ln -s /home/rook/xunil/salt/salt

sudo mkdir -p /srv/pillar
sudo cp -R /home/rook/xunil-private/certificates /srv/pillar/certificates

sudo systemctl restart salt-master
sudo systemctl restart salt-minion
sudo salt-key --accept=xunil

salt '*' state.highstate
