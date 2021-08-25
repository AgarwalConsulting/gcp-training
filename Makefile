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
