package pl.spring.demo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javassist.bytecode.stackmap.BasicBlock;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.LibraryEntity;
import pl.spring.demo.exceptions.NullLibraryIdException;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.repository.BookRepository;
import pl.spring.demo.repository.LibraryRepository;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookServiceImplTest {

	@Autowired
	private BookService bookService;
	
	@InjectMocks 
	private BookServiceImpl bookServiceImpl;
	
	@Mock
	LibraryRepository libraryRepository;
	@Mock
	BookRepository bookRepository;
	
	 @Before
	 public void initMocks(){
	    MockitoAnnotations.initMocks(this);
	    }
	

	@Test
	public void testShouldSaveBook() throws NullLibraryIdException {
		// given
		BookTo bookTo = new BookTo(null, "Title", "Authors",  1L);
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
		final BookTo book = new BookTo(null, "Title", "Authors",  null);
		// when
		bookService.saveBook(book);
		// then
		fail("test should throw NullLibraryIdException");
	}
	
	@Test
	@Ignore
	public void testShouldSetLibraryInBook() throws NullLibraryIdException {
		// given
		Long libId=2L;
		BookTo book = new BookTo(null, "Title", "Authors",  libId);
		Mockito.when(libraryRepository.getOne(libId)).thenReturn(new LibraryEntity(libId, "LIB"));
		//Mockito.when(bookRepository.save(Mockito.any(BookEntity.class))).thenCallRealMethod();
		// when
		//BookTo result= bookServiceImpl.saveBook(book);
		BookTo result= bookServiceImpl.saveBook(book);
		// then
		//Mockito.verify(bookRepository).save(Mockito.any(BookEntity.class));
		assertNotNull(result);
		Long libraryId=result.getLibraryId();
		assertEquals(libraryId, libId);
	}

}
