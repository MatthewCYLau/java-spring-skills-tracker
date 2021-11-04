resource "google_sql_database_instance" "db_instance" {
  name   = "java-apps-cloud-sql-db-instance"
  region = var.region

  settings {
    tier              = "db-f1-micro"
    availability_type = "ZONAL"
    user_labels = {
      "environment" : "production"
    }

    ip_configuration {
      ipv4_enabled = true
      authorized_networks {
        name  = "public"
        value = "0.0.0.0/0"
      }
    }

  }
  database_version    = "POSTGRES_13"
  deletion_protection = "false"
}

resource "google_sql_database" "db" {
  name     = "skillsdb"
  instance = google_sql_database_instance.db_instance.id
}