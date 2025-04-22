#!/bin/bash

# Verificar si ya hay un contenedor con ese nombre
if [ "$(docker ps -a -q -f name=rabbit)" ]; then
  echo "🟡 RabbitMQ ya existe, iniciando..."
  docker start rabbit
else
  echo "🟢 Creando nuevo contenedor RabbitMQ..."
  docker run -d \
    --hostname rabbit \
    --name rabbit \
    -p 5672:5672 \
    -p 15672:15672 \
    rabbitmq:3-management
fi

echo "✅ RabbitMQ está en marcha. UI: http://localhost:15672 (usuario: guest / contraseña: guest)"
