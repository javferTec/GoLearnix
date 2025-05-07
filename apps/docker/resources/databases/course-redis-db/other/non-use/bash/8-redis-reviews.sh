#!/bin/bash

TSV_FILE="$OUTPUT_CSV_DIR/reviews.tsv"

# 1) Exportar reseñas como TSV sin quoting
psql -h "$PGHOST" -U "$PGUSER" -d "$PGDATABASE" \
  -P format=unaligned -P fieldsep=$'\t' -P tuples_only=on \
  -c "SELECT id, course_id, user_id, rating, comment, created_at FROM reviews;" \
  > "$TSV_FILE" \
|| { echo -e "${RED}❌ Error: Falló la exportación desde PostgreSQL.${NC}"; exit 1; }

# 2) Para cada línea, usamos docker exec redis-cli HSET,
#    que respeta correctamente las comillas y espacios.
while IFS=$'\t' read -r id course_id user_id rating comment created_at; do
  # Escapamos barras invertidas y comillas en el comment
  safe_comment=${comment//\\/\\\\}
  safe_comment=${safe_comment//\"/\\\"}

  docker exec -i redis redis-cli HSET "review:$id" \
    course_id "$course_id" \
    user_id    "$user_id"    \
    rating     "$rating"     \
    comment    "$safe_comment" \
    created_at "$created_at" \
  || { echo -e "${RED}❌ Error en redis-cli dentro del contenedor para review:$id${NC}"; exit 1; }
done < "$TSV_FILE"

echo -e "${GREEN}✅ Reseñas importadas exitosamente en Redis.${NC}"
