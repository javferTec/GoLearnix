#!/bin/bash

# Configuración
PGUSER="postgres"
PGPASSWORD="1234"
PGHOST="127.0.0.1"
PGPORT="5432"

export PGPASSWORD=$PGPASSWORD

# Colores
GREEN='\033[0;32m'
RED='\033[0;31m'
CYAN='\033[0;36m'
NC='\033[0m' # No Color

echo -e "${CYAN}🔄 Terminando conexiones activas...${NC}"
psql -U $PGUSER -h $PGHOST -p $PGPORT -qtAc "SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'auth_db';" >/dev/null
psql -U $PGUSER -h $PGHOST -p $PGPORT -qtAc "SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'course_db';" >/dev/null

echo -e "${CYAN}🧹 Eliminando bases de datos existentes...${NC}"
psql -U $PGUSER -h $PGHOST -p $PGPORT -c "DROP DATABASE IF EXISTS auth_db;" >/dev/null && echo -e "✔️ auth_db eliminada"
psql -U $PGUSER -h $PGHOST -p $PGPORT -c "DROP DATABASE IF EXISTS course_db;" >/dev/null && echo -e "✔️ course_db eliminada"

echo -e "${CYAN}📦 Creando nuevas bases de datos...${NC}"
psql -U $PGUSER -h $PGHOST -p $PGPORT -c "CREATE DATABASE auth_db;" >/dev/null && echo -e "✅ auth_db creada"
psql -U $PGUSER -h $PGHOST -p $PGPORT -c "CREATE DATABASE course_db;" >/dev/null && echo -e "✅ course_db creada"

echo -e "${CYAN}📥 Ejecutando scripts en auth_db...${NC}"
for file in ./auth-db/*.sql; do
  echo -e "📄 $file"
  psql -U $PGUSER -h $PGHOST -p $PGPORT -d auth_db -f "$file" >/dev/null || echo -e "${RED}❌ Error ejecutando $file${NC}"
done

echo -e "${CYAN}📥 Ejecutando scripts en course_db...${NC}"
for file in ./course-db/*.sql; do
  echo -e "📄 $file"
  psql -U $PGUSER -h $PGHOST -p $PGPORT -d course_db -f "$file" >/dev/null || echo -e "${RED}❌ Error ejecutando $file${NC}"
done

echo -e "\n${GREEN}✅ Bases de datos inicializadas correctamente.${NC}"
