# Employee Server

A simple in-memory stateful employee management server.

## Deploy using Cloud Run

To deploy first build an image and push to GCR:

```bash
# build image
docker build -t gcr.io/${GOOGLE_CLOUD_PROJECT}/emp-server .

# Authenticate into GCR
gcloud auth print-access-token | docker login -u oauth2accesstoken --password-stdin https://gcr.io/${GOOGLE_CLOUD_PROJECT}

# push image
docker push gcr.io/${GOOGLE_CLOUD_PROJECT}/emp-server
```

Then deploy using:

```bash
gcloud run deploy emp-server --image=gcr.io/${GOOGLE_CLOUD_PROJECT}/emp-server
```
