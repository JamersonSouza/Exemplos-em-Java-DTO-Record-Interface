package tech.jamersondev.records.rabbit.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ExclusaoPersonConsumer {

    @RabbitListener(queues = "EXCLUSAO-DE-PERSON-VIA-RABBIT")
    public void exclusaoPersonMensagem(String message){
        System.out.println("Mensagem recebida da fila: " + message);
    }

}
