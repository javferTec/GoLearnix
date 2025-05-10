TERMINAR ANTES DEL MIÉRCOLES. VAMOSS!!!

- Poner en go como mejora que si un usuario se elimina q verifique si se elimina de la otra bd, sino hacer rollback

---

Aplicación completada -> Documentar.

---


Go usa RabbitMQ (más simple).

Java usa Kafka (más complejo pero consistente).

Debezium mira los logs de postgresql y envía los logs a kafka.

Kafka necesita de zookeper.

El producto mínimo viable ya está definido, las acciones se encuentran en los controladores de ambas aplicaciones.
