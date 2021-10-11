# Authenticating one function to invoke another

## Deploy the calling function

```bash
gcloud functions deploy hello-auth --entry-point Hello \
  --runtime go116 --trigger-http
```

## Deploy function with a service account

```bash
gcloud iam service-accounts create invoker \
    --description="Invoker Fn service account" \
    --display-name="Invoker Fn"

gcloud functions deploy invoker-fn --runtime go116 \
  --entry-point Invoker --trigger-http \
  --service-account=invoker@${GOOGLE_CLOUD_PROJECT}.iam.gserviceaccount.com \
  --allow-unauthenticated
```

Authenticate one function to call another:

```bash
gcloud functions add-iam-policy-binding hello-auth \
  --member="serviceAccount:invoker@${GOOGLE_CLOUD_PROJECT}.iam.gserviceaccount.com" \
  --role='roles/cloudfunctions.invoker'
```

Verify if policy is added:

```bash
gcloud functions get-iam-policy hello-auth
```
