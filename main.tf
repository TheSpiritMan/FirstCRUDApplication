terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "~> 3.0.1"
    }
  }
}

provider "docker" {}

resource "docker_network" "my_network" {
  driver = "bridge"
  name   = "my_network"
}

resource "docker_container" "db" {
  image = "mysql"
  name  = "db"
  env = [
    "MYSQL_ROOT_PASSWORD=root",
    "MYSQL_DATABASE=FirstCRUDApp"
  ]
  ports {
    internal = 3306
    external = 6603
  }
  restart = "always"
  healthcheck {
    test         = ["CMD-SHELL", "mysqladmin ping -h localhost -u root -proot"]
    interval     = "5s"
    timeout      = "3s"
    start_period = "10s"
    retries      = 3
  }
  network_mode = "my_network"
}

resource "docker_container" "firstcrudapp" {
  image = "thespiritman/firstcrudapplication"
  name  = "firstcrudapp"
  ports {
    internal = 5555
    external = 5555
  }
  restart = "always"
  depends_on = [
    docker_container.db,
  ]
  provisioner "local-exec" {
    command = "until docker inspect --format '{{json .State.Health}}' db | grep -q '\"Status\":\"healthy\"'; do sleep 3; done"
  }
  network_mode = "my_network"
}
