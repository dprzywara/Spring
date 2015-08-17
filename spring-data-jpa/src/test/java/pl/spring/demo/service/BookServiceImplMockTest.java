package pl.spring.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.exceptions.NullLibraryIdException;
import pl.spring.demo.repository.BookRepository;
import pl.spring.demo.repository.LibraryRepository;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.BookTo;

public class BookServiceImplMockTest {

	@InjectMocks
	private BookServiceImpl bookServiceImpl;

	@Mock
	LibraryRepository libraryRepository;
	@Mock
	BookRepository bookRepository;


	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldSaveBookCallSaveMethod() throws NullLibraryIdException {
		// given
		Long libId = 2L;
		BookTo book = new BookTo(1L, "Title", "Authors", libId);
		// when
		bookServiceImpl.saveBook(book);
		// then
		Mockito.verify(bookRepository).save(Mockito.any(BookEntity.class));

	}

}
