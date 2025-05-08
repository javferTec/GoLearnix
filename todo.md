TERMINAR ANTES DEL MIÉRCOLES. VAMOSS!!!

- Añadir swagger (rápido).
  
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
