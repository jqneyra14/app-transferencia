## Pasos para compilar en docker:

### 1.- Generar Dockerfile: Dockerfile

### 2.- Clean and install del proyecto
    mvn clean package -DskipTests

### 3.- Construir la imagen (estando en la misma carpeta donde esta el Dockerfile)
    docker build -t jqn/api-transferencias .

### 4.- Ejecutar el contenedor
#### 4.1. Docker Run:
    docker run -d \
        --name api-transferencias \
        -p 8084:8084 \
        -e SPRING_PROFILES_ACTIVE=dev \
        -e app.configsql.server-db=192.168.18.11:1433\
        -e app.configsql.username=sa \
        -e app.configsql.password=NewHorizons_2023@ \
        -e app.config.urlsistema=http://api-gestion-sistemas:8081/api
        jqn/api-transferencias:latest

docker run -d --name api-transferencias -p 8084:8084 -e SPRING_PROFILES_ACTIVE=dev -e app.configsql.server-db=192.168.18.11:1433 -e app.configsql.username=sa -e app.configsql.password=NewHorizons_2023@ -e app.config.urlsistema=http://api-gestion-sistemas:8081/api jqn/api-transferencias:latest
#### 4.2.- Con Docker compose file: docker-compose.yml
    docker compose -f docker-compose.yml -p api-transferencias-v2 --env-file .dev.env up --force-recreate --build -d

#### 4.3. Con docker compose nombrando el dockercompose file: docker-compose.yml
    docker-compose -f docker-compose.yml -p jqn-java up -d
    docker-compose -f .\docker-compose.yml -p jqn-java up –d