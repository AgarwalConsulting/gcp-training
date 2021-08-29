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

vagrant-restart:
	vagrant halt
	vagrant up

vagrant-package-and-push:
	rm -f package.box
	vagrant package
	curl --upload-file package.box https://transfer.sh/gcp-cli.box >> project-resources/link.txt

vagrant-box-refresh:
	vagrant box remove gcp-cli.box
	vagrant box add --name gcp-cli.box package.box

k8s-kind-create:
	kind create cluster --config devops/config/kind/multi-node.yaml

k8s-kind-delete:
	kind delete cluster --name kind
