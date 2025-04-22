package publishers

import (
	"fmt"
	"github.com/rabbitmq/amqp091-go"
)

// EventPublisher es la estructura que maneja la conexión y el canal de RabbitMQ
type EventPublisher struct {
	conn    *amqp091.Connection
	channel *amqp091.Channel
}

// NewEventPublisher establece la conexión y canal con RabbitMQ
func NewEventPublisher(amqpURL string) (*EventPublisher, error) {
	conn, err := amqp091.Dial(amqpURL)
	if err != nil {
		return nil, fmt.Errorf("error conectando a RabbitMQ: %w", err)
	}

	ch, err := conn.Channel()
	if err != nil {
		return nil, fmt.Errorf("error creando canal de RabbitMQ: %w", err)
	}

	// Declarar exchange
	err = ch.ExchangeDeclare(
		"golearnix.events", // nombre
		"topic",            // tipo
		true,               // durable
		false,              // auto-delete
		false,              // internal
		false,              // no-wait
		nil,                // args
	)
	if err != nil {
		return nil, fmt.Errorf("error al declarar el exchange: %w", err)
	}

	return &EventPublisher{conn: conn, channel: ch}, nil
}

// Close cierra la conexión y el canal
func (p *EventPublisher) Close() {
	if err := p.channel.Close(); err != nil {
		fmt.Printf("Error cerrando canal: %v\n", err)
	}

	if err := p.conn.Close(); err != nil {
		fmt.Printf("Error cerrando conexión: %v\n", err)
	}
}
