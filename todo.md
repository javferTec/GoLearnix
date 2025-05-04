TERMINAR ANTES DEL MIÉRCOLES. VAMOSS!!!

- Completar insfrastrucutra de red. Conectar los servicios (docker)
- Extraer configuración de docker al fichero .env
- Modificar scripts de creación de datos en redis, utilizando el fichero .env y arreglando las rutas
- Si con esto ya se crea todo bien, ampliar datos en cursos
  
- Arreglar parámetros de conexión en la aplicación de autenticación de Go tras pasarse a docker.

- Conectar la aplicación de gestión de cursos de Java con PostgreSQL y Redis
- La lógica de negocio y los controladores ya están hechos. Conectar con bases de datos y ver que las acciones se realizan bien.

- Añadir swagger (rápido).
- Conrol de versiones de PostgreSQL con Flyway (rápido con Flyway Initialitzr).

- Probar eventos. Implementar los eventos tras las acciones de escritura.

- Cuando un usuario se elimina en Go se emite un evento, consumirlo con Java y eliminar todo lo relacionado con usuario

---

Aplicación completada -> Documentar.

---


Go usa RabbitMQ (más simple).

Java usa Kafka (más complejo pero consistente).

Debezium mira los logs de postgresql y envía los logs a kafka.

Kafka necesita de zookeper.

El producto mínimo viable ya está definido, las acciones se encuentran en los controladores de ambas aplicaciones.
