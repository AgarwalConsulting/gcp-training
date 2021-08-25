tf-init:
	cd devops/cloud/gcp && terraform init

tf-apply:
	cd devops/cloud/gcp && terraform apply

tf-destroy:
	cd devops/cloud/gcp && terraform destroy
