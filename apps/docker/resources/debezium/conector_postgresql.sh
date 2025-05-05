#!/bin/bash

curl -i -X POST http://localhost:8083/connectors \
  -H 'Content-Type: application/json' \
  -d '
  {
    "name": "course-postgres-connector",
    "config": 
    {
      "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
      "plugin.name": "pgoutput",
      "database.hostname": "course-psql-db",
      "database.port": "5432",
      "database.user": "golearnix",
      "database.password": "golearnix",
      "database.dbname": "course-psql-db",
      "database.server.name": "course_db",                            
      "publication.name": "debezium_pub",                             
      "slot.name": "debezium_slot",                                   
      "max.queue.size": "8192",                                       
      "max.batch.size": "2048",
      "schema.include.list": "public",                                
      "table.include.list": "public.*",                               
      "heartbeat.interval.ms": "10000",
      "slot.drop.on.stop": "false",                                  
      "tombstones.on.delete": "true"                                 
    }
  }'
