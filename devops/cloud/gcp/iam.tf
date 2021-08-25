resource "google_project_iam_member" "cm-cf" {
  project = var.project_id
  role    = "roles/cloudfunctions.admin"
  for_each = toset(var.users_list)
  member  = "user:${each.key}"
}

resource "google_project_iam_member" "cm-logva" {
  project = var.project_id
  role    = "roles/logging.viewAccessor"
  for_each = toset(var.users_list)
  member  = "user:${each.key}"
}

# resource "google_project_iam_member" "cm-cf" {
#   project = var.project_id
#   role    = "roles/cloudfunctions.admin"
#   member  = "user:algogrit@gmail.com"
# }

# resource "google_project_iam_member" "cm-logva" {
#   project = var.project_id
#   role    = "roles/logging.viewAccessor"
#   member  = "user:algogrit@gmail.com"
# }
