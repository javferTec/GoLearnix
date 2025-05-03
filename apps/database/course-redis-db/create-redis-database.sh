#!/bin/bash

# Cargar variables de entorno desde el archivo .env
source ../.env
export PGUSER
export PGPASSWORD
export PGHOST
export PGPORT
export PGDATABASE

# Colores
GREEN='\033[0;32m'
RED='\033[0;31m'
CYAN='\033[0;36m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${CYAN}📦 Preparando entorno...${NC}"

# Directorio de salida para los archivos generados
export OUTPUT_REDIS_DIR="redis"
mkdir -p "$OUTPUT_REDIS_DIR"
echo -e "${GREEN}✅ Carpeta redis creada o ya existente${NC}"

export OUTPUT_CSV_DIR="csv"
mkdir -p "$OUTPUT_CSV_DIR"
echo -e "${GREEN}✅ Carpeta csv creada o ya existente${NC}"

# Parar y eliminar el contenedor Redis si ya existe
if [ "$(docker ps -q -f name=redis)" ]; then
  echo -e "${YELLOW}🛑 Deteniendo el contenedor Redis existente...${NC}"
  docker stop redis >/dev/null
fi
if [ "$(docker ps -aq -f status=exited -f name=redis)" ]; then
  echo -e "${YELLOW}🗑️ Eliminando el contenedor Redis detenido...${NC}"
  docker rm redis >/dev/null
fi

# Crear contenedor Redis
echo -e "${CYAN}📦 Creando contenedor Redis...${NC}"
docker run -d --name redis -p 6379:6379 redis redis-server --save 60 1 --loglevel warning >/dev/null
if [ $? -eq 0 ]; then
  echo -e "${GREEN}✅ Contenedor Redis iniciado correctamente${NC}"
else
  echo -e "${RED}❌ Error al iniciar el contenedor Redis${NC}"
  exit 1
fi

# Permisos de ejecución para los scripts
chmod +x bash/*.sh

# Ejecutar los scripts de importación de datos
echo -e "${CYAN}🚀 Ejecutando scripts de importación...${NC}"
for script in bash/*.sh; do
  echo -e "${YELLOW}➡️ Ejecutando ${script}${NC}"
  "$script"
  if [ $? -eq 0 ]; then
    echo -e "${GREEN}✅ ${script} ejecutado correctamente${NC}"
  else
    echo -e "${RED}❌ Error en ${script}${NC}"
  fi
done

echo -e "${GREEN}🎉 Proceso completo.${NC}"
