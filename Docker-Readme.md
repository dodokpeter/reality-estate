# Getting Started with Docker

- https://www.docker.com/
- https://hub.docker.com/
- https://rancherdesktop.io/

## Commands

- **docker --version**  Displays the currently installed version of Docker.
- **docker images**  Lists all Docker images on the local machine.
- **docker ps**  Shows currently running Docker containers.
- **docker ps -a**  Lists all containers, including stopped ones.
- **docker volume ls**  Displays a list of all Docker volumes.
- **docker rmi <image_id || image_name>**  Removes the specified Docker image by ID or name.
- **docker rm <container_id || container_name>**  Deletes the specified Docker container.
- **docker stop <container_id || container_name>**  Stops a running container by ID or name.
- **docker start <container_id || container_name>**  Starts a stopped container by ID or name.
- **docker pull <image_name>**  Downloads a Docker image from a registry.
- **docker logs <container_id>**  Displays logs from a specific container.


## Example - Postgres
**docker run --name container_db_name -p 5432:5432 -e POSTGRES_USER=user -e POSTGRES_PASSWORD=Pass123 -d -v my_db_data:/var/lib/postgresql/data postgres**  
This command runs a PostgreSQL container named `container_db_name` with the following options:
- Maps the host port `5432` to the container's port `5432`.
- Sets environment variables for the PostgreSQL user and password.
- Runs the container in detached mode (`-d`).
- Mounts a volume `my_db_data` to persist database data.



