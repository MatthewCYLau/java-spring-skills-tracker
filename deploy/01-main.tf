terraform {
  required_providers {
    google = {
      source  = "hashicorp/google"
      version = "4.56.0"
    }
  }

  backend "gcs" {
    bucket = "java-spring-skills-tracker-tf-state"
    prefix = "terraform/state"
  }
}

provider "google" {
  project = var.project
  region  = var.region
  zone    = var.zone
}