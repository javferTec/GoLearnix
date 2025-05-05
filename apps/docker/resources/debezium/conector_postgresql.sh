#!/bin/bash

curl -X POST http://localhost:8083/connectors \
  -H 'Content-Type: application/json' \
  -d '{
    "name": "course-psql-connector",
    "config": {
      "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
      "database.hostname": "course-psql-db",
      "database.port": "5432",
      "database.user": "golearnix",
      "database.password": "golearnix",
      "database.dbname": "course-psql-db",
      "database.server.name": "course-server",
      "slot.name": "debezium_slot",
      "publication.name": "dbserver_publication",
      "plugin.name": "pgoutput"
    }
  }'
