package com.golearnix.common.utils.errors;

import com.golearnix.common.annotations.Util;
import com.rabbitmq.client.Channel;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;

@Slf4j
@Util
@RequiredArgsConstructor
public class RabbitErrorHandler implements RabbitListenerErrorHandler {

  private final MeterRegistry meterRegistry;

  @Override
  public Object handleError(Message amqpMessage, Channel channel, org.springframework.messaging.Message<?> springMsg, ListenerExecutionFailedException exception) {

    String queue      = amqpMessage.getMessageProperties().getConsumerQueue();
    String routingKey = amqpMessage.getMessageProperties().getReceivedRoutingKey();
    Object headers    = amqpMessage.getMessageProperties().getHeaders();
    String reason     = exception.getCause() != null
        ? exception.getCause().getMessage()
        : exception.getMessage();

    Counter.builder("rabbitmq.listener.errors")
        .description("Número de errores en RabbitMQ listener")
        .tag("queue", queue)
        .tag("exception", exception.getClass().getSimpleName())
        .register(meterRegistry)
        .increment();

    log.error("Error en listener [queue={}, routingKey={}, headers={}]: {}",
        queue, routingKey, headers, reason, exception.getCause());

    throw new AmqpRejectAndDontRequeueException(reason, exception);
  }

}
