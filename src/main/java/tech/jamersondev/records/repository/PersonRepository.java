package tech.jamersondev.records.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.jamersondev.records.interfaces.IPerson;
import tech.jamersondev.records.model.Person;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    @Query(value = "select * from person_lesson", nativeQuery = true)
    Page<IPerson> findAllPersons(PageRequest pr, Class<IPerson> iPersonClass);
}
