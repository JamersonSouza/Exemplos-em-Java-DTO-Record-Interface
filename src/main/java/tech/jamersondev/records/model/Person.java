package tech.jamersondev.records.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "Person_lesson")
public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = -8938062684753587934L;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID personUUID;
    private String name;
    private String cpf;
    private int age;

    public Person(String cpf) {
        this.cpf = cpf;
    }

    public Person(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public Person() {
    }

    public UUID getPersonUUID() {
        return personUUID;
    }

    public void setPersonUUID(UUID personUUID) {
        this.personUUID = personUUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
