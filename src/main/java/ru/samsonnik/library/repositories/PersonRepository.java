package ru.samsonnik.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.samsonnik.library.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
