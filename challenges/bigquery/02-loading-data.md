# Loading data / External table

- Download any dataset from [here](https://data.world/datasets/opendata)

- Upload it to GCS Bucket

  - Create a bucket using `gsutil mb`

    - Do note the bq dataset and bucket should be in the same region

  - Copy a file into into it using `gsutil cp`

- Create a table, with source as "Google Cloud Storage"

  - You can try creating either a Native Table or External table

- Query the table
