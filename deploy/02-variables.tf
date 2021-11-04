variable "project" {
  description = "GCP project ID"
}

variable "project_name" {
  type        = string
  description = "Project name"
}

variable "credentials_filepath" {
  description = "Local path to GCP service account key"
}

variable "region" {
  description = "GCP region"
}

variable "zone" {
  description = "GCP zone"
}

