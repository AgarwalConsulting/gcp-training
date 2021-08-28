tf-init:
	cd devops/cloud/gcp && terraform init

tf-apply:
	cd devops/cloud/gcp && terraform apply

tf-destroy:
	cd devops/cloud/gcp && terraform destroy

vagrant-up:
	vagrant up

vagrant-tssh:
	vagrant ssh -c "cd /vagrant && tmux a -t basevm || tmux new -s basevm"

vagrant-down:
	vagrant destroy

vagrant-halt:
	vagrant halt

k8s-kind-create:
	kind create cluster --config devops/config/kind/multi-node.yaml

k8s-kind-delete:
	kind delete cluster --name kind
