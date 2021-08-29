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

vagrant-package-and-push:
	rm -f package.box
	vagrant package
	curl --upload-file package.box https://transfer.sh/gcp-cli.box | tee -a project-resources/link.txt

k8s-kind-create:
	kind create cluster --config devops/config/kind/multi-node.yaml

k8s-kind-delete:
	kind delete cluster --name kind
