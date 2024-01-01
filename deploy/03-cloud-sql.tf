resource "google_sql_database_instance" "db_instance" {
  name   = "${var.application_name}-db-instance"
  region = var.region

  depends_on = [google_service_networking_connection.private_vpc_connection]

  settings {
    tier              = "db-f1-micro"
    availability_type = "ZONAL"
    user_labels = {
      app : var.application_name
    }

    ip_configuration {
      /*
      ipv4_enabled = true
      authorized_networks {
        name  = "public"
        value = "0.0.0.0/0"
      }
      */

      ipv4_enabled    = false
      private_network = google_compute_network.vpc.id
    }

  }
  database_version    = "POSTGRES_13"
  deletion_protection = "false"
}

resource "google_sql_database" "db" {
  name     = "skillsdb"
  instance = google_sql_database_instance.db_instance.id
}