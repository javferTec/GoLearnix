package com.golearnix.common.utils.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.golearnix.common.annotations.UtilConfiguration;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@UtilConfiguration
public class RabbitConfig {

  @Value("${spring.rabbitmq.host}")
  private String host;

  @Value("${spring.rabbitmq.username}")
  private String user;

  @Value("${spring.rabbitmq.password}")
  private String pass;

  @Bean
  public CachingConnectionFactory connectionFactory() {
    CachingConnectionFactory cf = new CachingConnectionFactory(host);
    cf.setUsername(user);
    cf.setPassword(pass);
    return cf;
  }

  @Bean
  public TopicExchange exchange() {
    return new TopicExchange("golearnix.events");
  }

  @Bean
  public Queue queue() {
    return new Queue("user.deleted.queue", true);
  }

  @Bean
  public Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue)
        .to(exchange)
        .with("user.deleted");
  }

  @Bean
  public Jackson2JsonMessageConverter jackson2Converter(ObjectMapper mapper) {
    return new Jackson2JsonMessageConverter(mapper);
  }

  @Bean
  public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
      ConnectionFactory cf,
      Jackson2JsonMessageConverter converter) {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    factory.setConnectionFactory(cf);
    factory.setMessageConverter(converter);
    factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
    return factory;
  }
}