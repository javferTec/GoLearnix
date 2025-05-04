#!/bin/bash

CONTAINER=redis

# Colores
RESET="\e[0m"
CYAN="\e[36m"
BOLD="\e[1m"

# Patrones de clave por entidad
declare -A ENTITIES=(
  [Users]="user:*"
  [Course_Categories]="course_category:*"
  [Courses]="course:*"
  [Sections]="section:*"
  [Lessons]="lesson:*"
  [Enrollments]="enrollment:*"
  [Progress]="progress:*"
  [Reviews]="review:*"
)

echo -e "${BOLD}=== Verificación de datos en Redis ===${RESET}"
echo

for entity in "${!ENTITIES[@]}"; do
  pattern=${ENTITIES[$entity]}
  echo -e "${BOLD}>>> $entity (${pattern})${RESET}"

  # 1) Recoger todas las claves
  mapfile -t keys < <(docker exec -i $CONTAINER redis-cli --raw --scan --pattern "$pattern")
  count=${#keys[@]}

  if (( count == 0 )); then
    echo "  No se encontraron claves."
    echo
    continue
  fi

  # 2) Mostrar hasta 10 claves
  echo "  Claves encontradas ($count):"
  for ((i=0; i< count && i<10; i++)); do
    echo "    - ${keys[i]}"
  done
  if (( count > 10 )); then
    echo "    ... y $((count-10)) más"
  fi

  # 3) Ejemplo con la primera clave
  example_key=${keys[0]}
  echo
  echo "  > Detalle de \"$example_key\":"
  key_type=$(docker exec -i $CONTAINER redis-cli TYPE "$example_key")

  if [[ "$key_type" == "hash" ]]; then
    mapfile -t hv < <(docker exec -i $CONTAINER redis-cli HGETALL "$example_key")
    echo "    (hash)"
    for ((j=0; j<${#hv[@]}; j+=2)); do
      field=${hv[j]}
      value=${hv[j+1]}
      # Aquí usamos %b para que interprete \e[... como color
      printf "      %b%-15s%b : %s\n" "$CYAN" "$field" "$RESET" "$value"
    done
  else
    value=$(docker exec -i $CONTAINER redis-cli GET "$example_key")
    echo "    ($key_type)"
    echo "      Value: $value"
  fi

  echo
done

echo -e "${BOLD}=== Comprobación completada ===${RESET}"
