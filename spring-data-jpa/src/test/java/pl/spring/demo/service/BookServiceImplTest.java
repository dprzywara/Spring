package pl.spring.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookServiceImplTest {

	@Autowired
	private BookService bookService;

	@Test
	public void testShouldFindAllBooks() {

		// when
		List<BookTo> allBooks = bookService.findAllBooks();

		// then
		assertNotNull(allBooks);
		assertFalse(allBooks.isEmpty());
		assertEquals(6, allBooks.size());
	}

	@Test
	public void testShouldFindAllBooksByTitle() {
		// given
		final String title = "Opium w rosole";
		// when
		List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
		// then
		assertNotNull(booksByTitle);
		assertFalse(booksByTitle.isEmpty());
		assertTrue(booksByTitle.get(0).getTitle() == title);
	}

	@Test
	public void testShouldFindBooksByPrefixTitle() {
		// given
		final String title = "Opiu";
		final String result = "Opium w rosole";
		// when
		List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
		// then
		assertNotNull(booksByTitle);
		assertFalse(booksByTitle.isEmpty());
		assertTrue(booksByTitle.get(0).getTitle() == result);
	}

	@Test
	public void testShouldFind2BooksByPrefixTitle() {
		// given
		final String title = "P";
		// when
		List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
		// then
		assertNotNull(booksByTitle);
		assertFalse(booksByTitle.isEmpty());
		assertEquals(2, booksByTitle.size());
	}

	@Test
	public void testShouldFindBookByAuthor() {
		// given
		final String author = "alek";
		final String result = "Aleksander Fredro;";

		// when
		List<BookTo> booksByTitle = bookService.findBooksByAuthor(author);
		// then
		assertNotNull(booksByTitle);
		assertFalse(booksByTitle.isEmpty());
		assertTrue(booksByTitle.get(0).getAuthors().equals(result));

	}

	@Test
	public void testShouldFindBookByAuthorPrefix() {
		// given
		final String author = "wIl";
		final String result = "Wiliam Szekspir;";

		// when
		List<BookTo> booksByTitle = bookService.findBooksByAuthor(author);
		// then
		assertNotNull(booksByTitle);
		assertFalse(booksByTitle.isEmpty());
		assertTrue(booksByTitle.get(0).getAuthors().equals(result));

	}

	@Test(expected = BookNotNullIdException.class)
	public void testShouldThrowBookNotNullIdException() {
		// given
		final BookTo bookToSave = new BookTo();
		bookToSave.setId(22L);
		// when
		bookService.saveBook(bookToSave);
		// then
		fail("test should throw BookNotNullIdException");
	}

}
