package ca.robertgleason.database.repositories;

import ca.robertgleason.database.entities.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends CrudRepository<Book, String> {
}
