#!/bin/bash

CONTAINER=redis

# Patrones de clave para cada entidad
declare -A PATTERNS=(
  [users]="user:*"
  [course_categories]="course_category:*"
  [courses]="course:*"
  [sections]="section:*"
  [lessons]="lesson:*"
  [enrollments]="enrollment:*"
  [progress]="progress:*"
  [reviews]="review:*"
)

echo "🔍 Contando claves en Redis…"
echo

for table in "${!PATTERNS[@]}"; do
  pattern=${PATTERNS[$table]}

  # Recorremos con --scan y contamos
  count=$(docker exec -i $CONTAINER redis-cli --raw --scan --pattern "$pattern" | wc -l)

  if (( count > 0 )); then
    echo -e "✅ $table: $count claves encontradas (“$pattern”)"
  else
    echo -e "❌ $table: ninguna clave encontrada (“$pattern”)"
  fi
done

echo
echo "✅ Conteo completado."
