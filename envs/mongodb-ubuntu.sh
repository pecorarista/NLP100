#!/bin/sh
# Ubuntu 15.04, 15.10
# http://askubuntu.com/questions/617097/mongodb-2-6-does-not-start-on-ubuntu-15-04
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com --recv EA312927
echo "deb http://repo.mongodb.org/apt/debian wheezy/mongodb-org/3.2 main" | sudo tee /etc/apt/sources.list.d/mongodb-org-3.2.list
sudo apt-get update
sudo apt-get install -y mongodb-org
sudo systemctl start mongod
