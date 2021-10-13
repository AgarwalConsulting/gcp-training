# https://registry.terraform.io/providers/hashicorp/google/latest/docs/resources/google_project_iam
resource "google_project_iam_member" "cm-cf" {
  project = var.project_id
  role    = "roles/cloudfunctions.admin"
  for_each = toset(var.users_list)
  member  = "user:${each.key}"
}

resource "google_project_iam_member" "cm-gcs" {
  project = var.project_id
  role    = "roles/storage.admin"
  for_each = toset(var.users_list)
  member  = "user:${each.key}"
}

resource "google_project_iam_member" "cm-pse" {
  project = var.project_id
  role    = "roles/pubsub.editor"
  for_each = toset(var.users_list)
  member  = "user:${each.key}"
}

resource "google_project_iam_member" "cm-logva" {
  project = var.project_id
  role    = "roles/logging.viewAccessor"
  for_each = toset(var.users_list)
  member  = "user:${each.key}"
}

resource "google_project_iam_member" "cm-monitorv" {
  project = var.project_id
  role    = "roles/monitoring.viewer"
  for_each = toset(var.users_list)
  member  = "user:${each.key}"
}

resource "google_project_iam_binding" "cm-cloud-cli" {
  project = var.project_id
  role    = "roles/iam.serviceAccountUser"

  for_each = toset(var.users_list)
  members  = ["user:${each.key}"]
}

resource "google_project_iam_binding" "cm-bigquery" {
  project = var.project_id
  role    = "roles/bigquery.admin"

  for_each = toset(var.users_list)
  members  = ["user:${each.key}"]
}

resource "google_project_iam_binding" "cm-bigtable" {
  project = var.project_id
  role    = "roles/bigtable.admin"

  for_each = toset(var.users_list)
  members  = ["user:${each.key}"]
}

resource "google_project_iam_binding" "cm-pubsub" {
  project = var.project_id
  role    = "roles/pubsub.admin"

  for_each = toset(var.users_list)
  members  = ["user:${each.key}"]
}

resource "google_project_iam_binding" "cm-spanner" {
  project = var.project_id
  role    = "roles/spanner.admin"

  for_each = toset(var.users_list)
  members  = ["user:${each.key}"]
}

resource "google_project_iam_binding" "cm-cloudsql" {
  project = var.project_id
  role    = "roles/cloudsql.admin"

  for_each = toset(var.users_list)
  members  = ["user:${each.key}"]
}
