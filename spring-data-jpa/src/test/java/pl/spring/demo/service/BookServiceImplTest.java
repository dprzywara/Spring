package pl.spring.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
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

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShouldSaveBook() throws NullLibraryIdException {
		// given
		BookTo bookTo = new BookTo(null, "Title", "Authors", 1L);
		List<BookTo> bookList = bookService.findAllBooks();
		// when
		bookTo = bookService.saveBook(bookTo);
		// then
		assertNotNull(bookTo);
		assertEquals(bookList.size() + 1, bookService.findAllBooks().size());
	}

	@Test(expected = NullLibraryIdException.class)
	public void testShouldThrowNullLibraryIdExceptionException() throws NullLibraryIdException {
		// given
		final BookTo book = new BookTo(null, "Title", "Authors", null);
		// when
		bookService.saveBook(book);
		// then
		fail("test should throw NullLibraryIdException");
	}

	@Test
	public void testShouldSetLibraryInBook() throws NullLibraryIdException {
		// given
		Long libraryId;
		Long libId = 2L;
		BookTo book = new BookTo(null, "Title", "Authors", libId);
		// when
		BookTo result = bookService.saveBook(book);
		// then
		libraryId = result.getLibraryId();
		assertNotNull(result);
		assertEquals(libraryId, libId);
	}

}
