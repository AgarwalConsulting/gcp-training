variable "credsfile" {}
variable "publickeyfile" {}

variable "project_id" {}
variable "region" {}
variable "zone" {}

variable "vm_name" {}

variable "users_list" {
  type    = list(string)
  default = ["algogrit@gmail.com"]
}

# variable "roles_list" {
#   type    = list(string)
#   default = ["logging.viewAccessor"]
# }
