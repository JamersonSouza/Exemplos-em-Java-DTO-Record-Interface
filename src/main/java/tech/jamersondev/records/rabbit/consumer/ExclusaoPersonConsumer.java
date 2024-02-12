package tech.jamersondev.records.rabbit.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import tech.jamersondev.records.rabbit.producer.ExclusaoPersonProducer;
import tech.jamersondev.records.services.PersonService;

import java.util.UUID;

@Component
public class ExclusaoPersonConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExclusaoPersonConsumer.class);

    private final PersonService personService;

    public ExclusaoPersonConsumer(PersonService personService) {
        this.personService = personService;
    }

    @RabbitListener(queues = "EXCLUSAO-DE-PERSON-VIA-RABBIT")
    public void exclusaoPersonMensagem(UUID uuid){
      if(uuid != null){
          this.personService.deletePerson(uuid);
      }
        LOGGER.info("Nenhuma pessoa com menos de 18 anos {}", uuid);
    }

}
