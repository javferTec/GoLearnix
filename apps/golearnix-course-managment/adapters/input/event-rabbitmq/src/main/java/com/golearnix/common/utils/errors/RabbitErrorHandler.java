package com.golearnix.common.utils.errors;

import com.golearnix.common.annotations.Util;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;

@Slf4j
@Util
@RequiredArgsConstructor
public class RabbitErrorHandler implements RabbitListenerErrorHandler {

  public Object handleError(Message amqpMessage, Channel channel, org.springframework.messaging.Message<?> message, ListenerExecutionFailedException exception) {
    String queue = amqpMessage.getMessageProperties().getConsumerQueue();
    String reason = exception.getCause() != null ? exception.getCause().getMessage() : exception.getMessage();

    ListenerErrorResponse error = new ListenerErrorResponse(queue, reason);
    log.error("Error en listener: {}", error);

    throw exception;
  }

}
