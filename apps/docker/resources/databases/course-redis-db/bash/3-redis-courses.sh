#!/bin/bash

# Archivos temporales
CSV_FILE="$OUTPUT_CSV_DIR/courses.csv"
REDIS_FILE="$OUTPUT_REDIS_DIR/courses.redis"

# Exportar cursos desde PostgreSQL
psql -h "$PGHOST" -U "$PGUSER" -d "$PGDATABASE" -c "\copy (SELECT id, title, description, category_id, instructor_id, created_at, updated_at FROM courses) TO STDOUT WITH CSV" > "$CSV_FILE"
if [ $? -ne 0 ]; then
  echo -e "${RED}❌ Error: Falló la exportación desde PostgreSQL.${NC}"
  exit 1
fi

# Generar comandos Redis para cada curso
awk -F',' '{ printf("HSET course:%s title \"%s\" description \"%s\" category_id \"%s\" instructor_id \"%s\" created_at \"%s\" updated_at \"%s\"\r\n", $1, $2, $3, $4, $5, $6, $7) }' "$CSV_FILE" > "$REDIS_FILE"
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

echo -e "${GREEN}✅ Cursos importados exitosamente en Redis.${NC}"
