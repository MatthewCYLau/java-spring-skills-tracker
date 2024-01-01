resource "google_compute_network" "vpc" {
  name                    = "app-vpc"
  auto_create_subnetworks = false
}

resource "google_compute_subnetwork" "subnet" {
  name          = "app-subnet"
  ip_cidr_range = "10.0.0.0/28"
  region        = var.region
  network       = google_compute_network.vpc.id
}

resource "google_compute_global_address" "private_ip_address" {
  name          = "private-ip-address"
  purpose       = "VPC_PEERING"
  address_type  = "INTERNAL"
  prefix_length = 16
  network       = google_compute_network.vpc.id
}

resource "google_service_networking_connection" "private_vpc_connection" {
  network                 = google_compute_network.vpc.id
  service                 = "servicenetworking.googleapis.com"
  reserved_peering_ranges = [google_compute_global_address.private_ip_address.name]
}

resource "google_vpc_access_connector" "cloud_run_connector" {
  provider     = google-beta
  name         = "cloud-run-connector"
  machine_type = "e2-micro"
  subnet {
    name = google_compute_subnetwork.subnet.name
  }
}