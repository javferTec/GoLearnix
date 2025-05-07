#!/bin/bash

# Archivos temporales
CSV_FILE="$OUTPUT_CSV_DIR/sections.csv"
REDIS_FILE="$OUTPUT_REDIS_DIR/sections.redis"

# Exportar secciones desde PostgreSQL
psql -h "$PGHOST" -U "$PGUSER" -d "$PGDATABASE" -c "\copy (SELECT id, course_id, title, \"order\", created_at FROM sections) TO STDOUT WITH CSV" > "$CSV_FILE"
if [ $? -ne 0 ]; then
  echo -e "${RED}❌ Error: Falló la exportación desde PostgreSQL.${NC}"
  exit 1
fi

# Generar comandos Redis para cada sección
awk -F',' '{ printf("HSET section:%s course_id \"%s\" title \"%s\" order \"%s\" created_at \"%s\"\r\n", $1, $2, $3, $4, $5) }' "$CSV_FILE" > "$REDIS_FILE"
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

echo -e "${GREEN}✅ Secciones importadas exitosamente en Redis.${NC}"
