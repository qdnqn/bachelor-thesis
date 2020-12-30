#!/bin/bash

cd "$(dirname "$0")"
set -e

cd vagrant/kube
vagrant up

cd ../../ansible/kube

ansible-playbook -i inventory master-node.yaml
ansible-playbook -i inventory worker-nodes.yaml

echo "Cluster is created."

cd ../../application

mvn clean install
mvn docker:build -Pbuild-wnode-1
mvn docker:build -Pbuild-wnode-2
mvn exec:exec -Pdeploy

echo "Application built and deployed."

cd ../application-consumer

mvn clean install
mvn docker:build -Pbuild-wnode-1
mvn docker:build -Pbuild-wnode-2
mvn exec:exec -Pdeploy

echo "Application consumer built and deployed."