terraform {
  required_providers {
    google = {
      source  = "hashicorp/google"
      version = "3.90.0"
    }
  }
}

terraform {
  backend "gcs" {
    bucket = "java-spring-skills-tracker-tf-state"
    prefix = "terraform/state"
  }
}

provider "google" {

  credentials = file(var.credentials_filepath)
  region      = var.region
  zone        = var.zone
  project     = var.project
}