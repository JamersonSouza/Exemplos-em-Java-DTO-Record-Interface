package tech.jamersondev.records.rabbit.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExclusaoPersonProducer {

    private final RabbitTemplate rabbitTemplate;

    public ExclusaoPersonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ExclusaoPersonProducer.class);

    public void messageProducerForRabbit(String mensagem){
        LOGGER.info(String.format("Mensagem que foi produzida: %s", mensagem));
        rabbitTemplate.convertAndSend("exchange.exclusao.person", "routingKey", mensagem);
        System.out.println("mensagem enviada...");
    }

}
