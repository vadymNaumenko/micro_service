## Создание Docker контейнера Postgres
* docker run --name micro-service -e POSTGRES_DB=processing -e POSTGRES_PASSWORD=pass -p 5433:5432 -d postgres
## Миграцыя баз даных flyway
*   flyway:
    enabled: true
    locations: classpath:db
    url: jdbc:postgresql://localhost:5433/processing
    user: postgres
    password: pass
* 