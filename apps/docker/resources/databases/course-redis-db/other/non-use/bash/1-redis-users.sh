#!/bin/bash


# Archivos temporales
CSV_FILE="$OUTPUT_CSV_DIR/users.csv"
REDIS_FILE="$OUTPUT_REDIS_DIR/users.redis"

# Exportar IDs desde PostgreSQL
psql -h "$PGHOST" -U "$PGUSER" -d "$PGDATABASE" -c "\copy (SELECT id FROM users) TO STDOUT WITH CSV" > "$CSV_FILE"
if [ $? -ne 0 ]; then
  echo -e "${RED}❌ Error: Falló la exportación desde PostgreSQL.${NC}"
  exit 1
fi

# Generar comandos Redis
awk -F',' '{ printf("SET user:%s 1\r\n", $1) }' "$CSV_FILE" > "$REDIS_FILE"
if [ $? -ne 0 ]; then
  echo -e "${RED}❌ Error: Falló la generación de comandos Redis.${NC}"
  exit 1
fi

# Importar en Redis dentro del contenedor
cat "$REDIS_FILE" | docker exec -i redis redis-cli --pipe
if [ $? -ne 0 ]; then
  echo -e "${RED}❌ Error: Falló la importación de datos en Redis.${NC}"
  exit 1
fi

echo -e "${GREEN}✅ Usuarios importados exitosamente en Redis.${NC}"
