provider "google" {
  credentials = var.credsfile
  project = var.project_id
  region  = var.region
  zone    = var.zone
}
