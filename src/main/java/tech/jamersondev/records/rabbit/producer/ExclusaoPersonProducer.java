package tech.jamersondev.records.rabbit.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import tech.jamersondev.records.repository.PersonRepository;

import java.util.List;
import java.util.UUID;

@Component
public class ExclusaoPersonProducer {

    private final RabbitTemplate rabbitTemplate;

    private final PersonRepository personRepository;

    public ExclusaoPersonProducer(RabbitTemplate rabbitTemplate, PersonRepository personRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.personRepository = personRepository;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ExclusaoPersonProducer.class);

    public void messageProducerForRabbit(){
        List<UUID> allPersons = personRepository.findAllPersons();
        if(!allPersons.isEmpty()){
            allPersons.forEach(p -> {
                rabbitTemplate.convertAndSend("exchange.exclusao.person", "routingKey", p);
            });
        }else{
            LOGGER.info("Nenhuma pessoa encontrada");
        }

        System.out.println("mensagem enviada...");
    }

}
