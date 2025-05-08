package com.golearnix.adapters;

import com.golearnix.common.annotations.EventAdapter;
import com.golearnix.common.dto.messages.UserDeletedEvent;
import com.golearnix.ports.input.UserServicePort;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.io.IOException;
import java.util.UUID;

@EventAdapter
@RequiredArgsConstructor
public class UserRabbitConsumerAdapter {

  private final UserServicePort userServicePort;

  @RabbitListener(queues = "user.deleted.queue", containerFactory = "rabbitListenerContainerFactory")
  public void delete(UserDeletedEvent event, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {

    userServicePort.delete(UUID.fromString(event.getData().getUserID()));
    channel.basicAck(tag, false);

  }

}
