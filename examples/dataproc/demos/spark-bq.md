# Running spark job with a BQ connector

This example reads data from BigQuery into a Spark DataFrame to perform a word count using the standard data source API.

- Start a cluster with:

```bash
  --properties "spark:spark.jars=gs://spark-lib/bigquery/spark-bigquery-latest_2.12.jar" \
```

or

```bash
  --initialization-actions gs://goog-dataproc-initialization-actions-${GOOGLE_CLOUD_REGION}/connectors/connectors.sh \
  --metadata bigquery-connector-version=1.2.0 \
  --metadata spark-bigquery-connector-version=0.21.0 \
```

- Create BigQuery dataset

  - `bq mk wordcount_dataset`

- Create GCS bucket

  - `gsutil mb gs://cm-tech-wc-data`

- Write the job to read from public bq table into our dataset. See [spark-bq.py](https://github.com/AgarwalConsulting/gcp-training/tree/master/examples/dataproc/demos/spark-bq.py)

- Run the job

```bash
gcloud dataproc jobs submit pyspark examples/dataproc/demos/spark-bq.py \
    --cluster=demo-cluster \
    --region=$GOOGLE_CLOUD_REGION \
    --jars=gs://spark-lib/bigquery/spark-bigquery-latest_2.12.jar
```

- Run the job from the cluster's master node using

```bash
spark-submit --jars gs://spark-lib/bigquery/spark-bigquery-latest_2.12.jar wordcount.py
```
