package tech.jamersondev.records.rabbit.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.jamersondev.records.rabbit.producer.ExclusaoPersonProducer;

@Component
public class PersonJob {

    private final ExclusaoPersonProducer excluirPerson;

    public PersonJob(ExclusaoPersonProducer excluirPerson) {
        this.excluirPerson = excluirPerson;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonJob.class);

    @Scheduled(fixedRate = 60000)
    public void execute(){
        LOGGER.info("Executando job...");
        excluirPerson.messageProducerForRabbit();
    }

}
