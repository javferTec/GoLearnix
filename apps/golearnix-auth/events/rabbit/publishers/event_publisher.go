package publishers

import "github.com/rabbitmq/amqp091-go"

// EventPublisher es la estructura que maneja la conexión y el canal de RabbitMQ
type EventPublisher struct {
	conn    *amqp091.Connection
	channel *amqp091.Channel
}
