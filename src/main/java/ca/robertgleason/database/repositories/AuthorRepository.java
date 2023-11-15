package ca.robertgleason.database.repositories;

import ca.robertgleason.database.domain.entities.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Iterable<Author> findByAgeLessThan(int age);


    @Query("select a from Author a where a.age > ?1")
    Iterable<Author> findAuthorsWithAgeGreateThan(int age);
}
