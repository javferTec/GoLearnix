#!/bin/bash

# Archivos temporales
CSV_FILE="$OUTPUT_CSV_DIR/enrollments.csv"
REDIS_FILE="$OUTPUT_REDIS_DIR/enrollments.redis"

# Exportar inscripciones desde PostgreSQL
psql -h "$PGHOST" -U "$PGUSER" -d "$PGDATABASE" -c "\copy (SELECT id, course_id, user_id, enrolled_at FROM enrollments) TO STDOUT WITH CSV" > "$CSV_FILE"
if [ $? -ne 0 ]; then
  echo -e "${RED}❌ Error: Falló la exportación desde PostgreSQL.${NC}"
  exit 1
fi

# Generar comandos Redis para cada inscripción
awk -F',' '{ printf("HSET enrollment:%s course_id \"%s\" user_id \"%s\" enrolled_at \"%s\"\r\n", $1, $2, $3, $4) }' "$CSV_FILE" > "$REDIS_FILE"
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

echo -e "${GREEN}✅ Inscripciones importadas exitosamente en Redis.${NC}"
