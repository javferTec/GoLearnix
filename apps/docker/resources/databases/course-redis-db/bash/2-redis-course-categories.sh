#!/bin/bash

# Archivos temporales
CSV_FILE="$OUTPUT_CSV_DIR/course_categories.csv"
REDIS_FILE="$OUTPUT_REDIS_DIR/course_categories.redis"

# Exportar categorías de cursos desde PostgreSQL
psql -h "$PGHOST" -U "$PGUSER" -d "$PGDATABASE" -c "\copy (SELECT id, name, description FROM course_categories) TO STDOUT WITH CSV" > "$CSV_FILE"
if [ $? -ne 0 ]; then
  echo -e "${RED}❌ Error: Falló la exportación desde PostgreSQL.${NC}"
  exit 1
fi

# Generar comandos Redis para cada categoría
awk -F',' '{ printf("HSET course_category:%s name \"%s\" description \"%s\"\r\n", $1, $2, $3) }' "$CSV_FILE" > "$REDIS_FILE"
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

echo -e "${GREEN}✅ Categorías de cursos importadas exitosamente en Redis.${NC}"