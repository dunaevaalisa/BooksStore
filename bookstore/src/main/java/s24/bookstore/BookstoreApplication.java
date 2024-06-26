package s24.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import s24.bookstore.domain.AppUser;
import s24.bookstore.domain.AppUserRepository;
import s24.bookstore.domain.Book;
import s24.bookstore.domain.BookRepository;
import s24.bookstore.domain.Category;
import s24.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository rcepository, AppUserRepository urepository) {
		return (args) -> {
			log.info("save a couple of books");

			Category category1 = new Category("Action");
			Category category2 = new Category("Fiction");
			Category category3 = new Category("Mystery");

			rcepository.save(category1);
			rcepository.save(category2);
			rcepository.save(category3);

			brepository.save(new Book("Crime and punishment", "Dostoyevsky", 1867, 2390, 77,
					category2));
			brepository.save(
					new Book("The idiot", "Dostoyevsky", 1869, 2903, 99, category1));

			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "user@email.com");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN", "admin@mail.com");
			//urepository.save(user1);
			//urepository.save(user2);

			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
