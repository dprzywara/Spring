package pl.spring.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.exceptions.NullLibraryIdException;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookServiceImplTest {

	@Autowired
	private BookService bookService;

	@Test
	public void testShouldSaveBook() throws NullLibraryIdException {
		// given
		BookTo bookTo = new BookTo(null, "Title", "Authors",  1L);
		List<BookTo> bookList = bookService.findAllBooks();//getBookRepository().count();
		// when
		bookTo = bookService.saveBook(bookTo);
		// then
		assertNotNull(bookTo);
		assertEquals(bookList.size() + 1, bookService.findAllBooks().size());
	}

}
