#!/usr/bin/env bash

touch ~/.bash_load
echo "source ~/.bash_load" >> ~/.bashrc
echo "source ~/.bash_load" >> ~/.bash_profile

# gcloud components update

# Go
wget https://golang.org/dl/go1.17.linux-amd64.tar.gz
sudo rm -rf /usr/local/go && sudo tar -C /usr/local -xzf go1.17.linux-amd64.tar.gz
echo 'export PATH=/usr/local/go/bin:$PATH' >> ~/.bash_load
mkdir -p ~/go
echo 'export GOPATH=~/go' >> ~/.bash_load
echo 'export PATH=$GOPATH/bin:$PATH' >> ~/.bash_load

# Java
sudo apt install -y openjdk-11-jdk
sudo apt install -y maven gradle

# Kind
curl -Lo ./kind https://kind.sigs.k8s.io/dl/v0.11.1/kind-linux-amd64
chmod +x ./kind
sudo mv ./kind /usr/local/bin/kind
