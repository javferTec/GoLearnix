#!/bin/bash

# Archivos temporales
CSV_FILE="$OUTPUT_CSV_DIR/progress.csv"
REDIS_FILE="$OUTPUT_REDIS_DIR/progress.redis"

# Exportar progreso desde PostgreSQL
psql -h "$PGHOST" -U "$PGUSER" -d "$PGDATABASE" -c "\copy (SELECT id, lesson_id, user_id, completed, updated_at FROM progress) TO STDOUT WITH CSV" > "$CSV_FILE"
if [ $? -ne 0 ]; then
  echo -e "${RED}❌ Error: Falló la exportación desde PostgreSQL.${NC}"
  exit 1
fi

# Generar comandos Redis para cada registro de progreso
awk -F',' '{ printf("HSET progress:%s lesson_id \"%s\" user_id \"%s\" completed \"%s\" updated_at \"%s\"\r\n", $1, $2, $3, $4, $5) }' "$CSV_FILE" > "$REDIS_FILE"
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

echo -e "${GREEN}✅ Progreso importado exitosamente en Redis.${NC}"
