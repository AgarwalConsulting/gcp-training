# Create a cluster and manage the job

- *Step 1*: (Optional) Create a dataproc cluster, with some optional components installed

  `gcloud dataproc clusters create ...`

- *Step 2*: Write a simple pyspark or java based spark job

  - If you, want check out an example [sort.py](https://github.com/AgarwalConsulting/gcp-training/blob/master/examples/dataproc/jobs/sort.py) in the repo

- *Step 3*: Upload the job script/jar onto GCS

- *Step 4*: Submit your job to dataproc

  `gcloud dataproc jobs submit ...`

- *Step 5*: (Optional) You can also run your job directly by *ssh*ing into the master node
