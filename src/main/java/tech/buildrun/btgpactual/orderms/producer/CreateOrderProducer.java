package tech.buildrun.btgpactual.orderms.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.buildrun.btgpactual.orderms.listener.dto.OrderCreatedEvent;
import tech.buildrun.btgpactual.orderms.config.RabbitMqConfig;


@Component
public class CreateOrderProducer {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public CreateOrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private final Logger logger = LoggerFactory.getLogger(CreateOrderProducer.class);

    
    public String sendOrder(OrderCreatedEvent order) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.CREATE_ORDER_QUEUE, order);
        logger.info("Message produced: {}", order);

        return("Order created.");
    }
}
