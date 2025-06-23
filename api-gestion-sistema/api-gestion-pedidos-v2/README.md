## Pasos para compilar en docker:

### 1.- Generar Dockerfile: Dockerfile

### 2.- Clean and install del proyecto
    mvn clean package -DskipTests

### 3.- Construir la imagen (estando en la misma carpeta donde esta el Dockerfile)
    docker build -t jqn/api-gestion-sistemas .

### 4.- Ejecutar el contenedor
#### 4.1. Docker Run:
    docker run -d \
        --name api-gestion-sistemas \
        -p 8081:8081 \
        -e SPRING_PROFILES_ACTIVE=dev \
        -e app.config.server-db=192.168.18.11:3306\
        -e app.config.username=root \
        -e app.config.password=123456789 \
        jqn/api-gestion-sistemas:latest

docker run -d --name api-gestion-sistemas -p 8081:8081 -e SPRING_PROFILES_ACTIVE=dev -e app.config.server-db=192.168.18.11:3306 -e app.config.username=root -e app.config.password=123456789 jqn/api-gestion-sistemas:latest

#### 4.2.- Con Docker compose file: docker-compose.yml
    docker compose -f docker-compose.yml -p api-gestion-sistemas --env-file .dev.env up --force-recreate --build -d