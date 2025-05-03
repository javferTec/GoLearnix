#!/bin/bash

# Archivos temporales
CSV_FILE="$OUTPUT_CSV_DIR/lessons.csv"
REDIS_FILE="$OUTPUT_REDIS_DIR/lessons.redis"

# Exportar lecciones desde PostgreSQL
psql -h "$PGHOST" -U "$PGUSER" -d "$PGDATABASE" -c "\copy (SELECT id, section_id, title, content, \"order\", created_at FROM lessons) TO STDOUT WITH CSV" > "$CSV_FILE"
if [ $? -ne 0 ]; then
  echo -e "${RED}❌ Error: Falló la exportación desde PostgreSQL.${NC}"
  exit 1
fi

# Generar comandos Redis para cada lección
awk -F',' '{ printf("HSET lesson:%s section_id \"%s\" title \"%s\" content \"%s\" order \"%s\" created_at \"%s\"\r\n", $1, $2, $3, $4, $5, $6) }' "$CSV_FILE" > "$REDIS_FILE"
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

echo -e "${GREEN}✅ Lecciones importadas exitosamente en Redis.${NC}"
