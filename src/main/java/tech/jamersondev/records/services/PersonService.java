package tech.jamersondev.records.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import tech.jamersondev.records.exceptions.NotFoundPersonException;
import tech.jamersondev.records.model.Person;
import tech.jamersondev.records.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Bean
    public void injectData(){
        Person person = new Person("Jamerson", "123.456.789-10", 26);
        personRepository.saveAll(List.of(person));
    }

    public void deletePerson(UUID uuid) {
        Optional<Person> personById = this.personRepository.findById(uuid);
        if(personById.isEmpty()){
            throw new NotFoundPersonException("UUID informado não encontrou nenhum registro");
        } else {
            Person person = personById.get();
            if(person.getAge() < 18){
                this.personRepository.delete(person);
                LOGGER.info("A pessoa {} foi excluída", person.getName());
            }
        }
    }
}
