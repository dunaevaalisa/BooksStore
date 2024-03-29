package s24.bookstore;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import s24.bookstore.domain.Book;
import s24.bookstore.domain.BookRepository;
import s24.bookstore.domain.Category;
import s24.bookstore.domain.CategoryRepository;

import org.junit.jupiter.api.Test;

//@DataJpaTest

//@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    private BookRepository brepository;

    @Autowired
    private CategoryRepository crepository;
    
    @Test
    public void findByLastnameShouldReturnBook() {
        List<Book> books = brepository.findById(1);
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Crime and punishment");
    }
    
    @Test
    public void createNewBook() {
    	Category category = new Category("History");
    	crepository.save(category);
    	Book book = new Book("Demons", "Dostoyevsky", 1872, 9892346, 376, category);
    	brepository.save(book);
    	assertThat(book.getId()).isNotNull();
    }    
    @Test
    public void deleteNewBook() {
		List<Book> books = brepository.findById(1);
		Book book = books.get(0);
		brepository.delete(book);
		List<Book> newBooks = brepository.findById(2);
		assertThat(newBooks).hasSize(1);
     }

}