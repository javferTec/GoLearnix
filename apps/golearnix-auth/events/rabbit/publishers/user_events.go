package publishers

import (
	"encoding/json"
	"fmt"
	"github.com/rabbitmq/amqp091-go"
	"golearnix-auth/domain/dto/events"
)

// PublishUserDeleted publica un evento cuando un usuario es eliminado
func (p *EventPublisher) PublishUserDeleted(event events.UserDeletedEvent) error {
	// Crear el evento de usuario eliminado
	body, err := json.Marshal(struct {
		Event string                  `json:"event"`
		Data  events.UserDeletedEvent `json:"data"`
	}{
		Event: "UserDeleted",
		Data:  event,
	})
	if err != nil {
		return fmt.Errorf("error serializando evento: %w", err)
	}

	// Publicar el evento en RabbitMQ
	err = p.channel.Publish(
		"golearnix.events", // exchange
		"user.deleted",     // routing key
		false,              // mandatory
		false,              // immediate
		amqp091.Publishing{
			ContentType: "application/json",
			Body:        body,
		},
	)

	if err != nil {
		return fmt.Errorf("error publicando evento: %w", err)
	}

	return nil
}
