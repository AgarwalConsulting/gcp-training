# Setup

## Local VM setup

We are going to be using the latest tools like `gCloud SDK` & `terraform`, within a vagrant box (You can also do this locally.)

To bring the box up:

```bash
vagrant plugin install vagrant-vbguest

# vagrant box add --name gcp-cli.box <url-to-the-box>.box

## If setting up from scratch
vagrant box add --insecure ubuntu/focal64

vagrant up
```

## Working with gcloud locally

### Step 1

Initialize gcloud with your account & project details:

```bash
gcloud init
```

### Step 2

Make sure you select the right project.
