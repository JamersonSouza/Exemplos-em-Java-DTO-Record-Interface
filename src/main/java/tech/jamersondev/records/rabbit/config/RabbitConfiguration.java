package tech.jamersondev.records.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Bean
    public Queue queue(){
        return new Queue("EXCLUSAO-DE-PERSON-VIA-RABBIT");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setRoutingKey("EXCLUSAO-DE-PERSON-VIA-RABBIT");
        return rabbitTemplate;
    }

    @Bean
    public Binding binding(Queue queue, Exchange exchange){
        return BindingBuilder.bind(queue)
                .to(exchange())
                .with("routingKey");
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("exchange.exclusao.person");
    }

}
