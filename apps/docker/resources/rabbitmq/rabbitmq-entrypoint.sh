#!/bin/bash
# TODO: NO SE USA - ELIMINAR!!
set -e

# Arrancar en segundo plano para que genere los archivos
/docker-entrypoint.sh rabbitmq-server -detached

# Esperar a que se cree el archivo .erlang.cookie
while [ ! -f /var/lib/rabbitmq/.erlang.cookie ]; do
  echo "Esperando a que se genere .erlang.cookie..."
  sleep 1
done

# Cambiar permisos y propietario
chown -R rabbitmq:rabbitmq /var/lib/rabbitmq
chmod 400 /var/lib/rabbitmq/.erlang.cookie

# Detener el proceso en segundo plano y volver a lanzarlo en primer plano
rabbitmqctl stop
sleep 3

# Ahora sí, lanzar en primer plano
exec /docker-entrypoint.sh rabbitmq-server
