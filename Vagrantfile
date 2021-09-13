# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure("2") do |config|
  config.vm.box = "gcp-cli.box"
  config.vm.box_url = "https://transfer.sh/3mjCuz/gcp-cli.box"

  ## Shell Provisioning for GCP class
  # config.vm.box = "ubuntu/focal64"

  # config.vm.provision "shell", path: "setup/vm/root.sh"
  # config.vm.provision "shell", privileged: false, path: "setup/vm/user.sh"

  config.vm.hostname = "gcp-basevm"
end
