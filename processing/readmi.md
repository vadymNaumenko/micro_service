## Создание Docker контейнера Postgres
* docker run --name micro-service -e POSTGRES_DB=processing -e POSTGRES_PASSWORD=pass -p 5433:5432 -d postgres