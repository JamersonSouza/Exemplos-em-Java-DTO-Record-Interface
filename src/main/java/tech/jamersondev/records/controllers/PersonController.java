package tech.jamersondev.records.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jamersondev.records.exceptions.NotFoundPersonException;
import tech.jamersondev.records.interfaces.IPerson;
import tech.jamersondev.records.model.Person;
import tech.jamersondev.records.recordsClass.PersonRecords;
import tech.jamersondev.records.repository.PersonRepository;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping
    public ResponseEntity<PersonRecords> create(@RequestBody PersonRecords person){
        Person personObj = new Person(person.name(), person.cpf(), person.age());
        personRepository.save(personObj);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{personUUID}")
    public ResponseEntity<Person> getPersonByUUID(@PathVariable UUID personUUID){
        Person personFindByID = this.personRepository.findById(personUUID)
                .orElseThrow(() -> new NotFoundPersonException("Nenhuma pessoa encontrada com o id: " + personUUID));
        return ResponseEntity.ok(personFindByID);
    }

    @GetMapping
    public ResponseEntity<Page<IPerson>> listAllPerson(@RequestParam(defaultValue = "0") int numberPages,
                                                       @RequestParam(defaultValue = "10") int sizePage){

        PageRequest pr = PageRequest.of(numberPages, sizePage);
        Page<IPerson> allPerson = this.personRepository.findAllPersons(pr, IPerson.class);
        return ResponseEntity.of(Optional.ofNullable(allPerson));

    }


}
