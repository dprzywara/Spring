package pl.spring.demo.mock;
/**
 * @COPYRIGHT (C) 2015 Schenker AG
 *
 * All rights reserved
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.common.BookMapper;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

/**
 * TODO The class BookServiceImplTest is supposed to be documented...
 *
 * @author AOTRZONS
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("spring-context.xml")

public class BookServiceImplTest {

	@InjectMocks
	private BookServiceImpl bookService;
	@Mock
	private BookDao bookDao;
	@Mock
	private BookMapper mapper;

	@Before
	public void setUpt() {
		MockitoAnnotations.initMocks(this);
		//mapper = new BookMapper();
		//Whitebox.setInternalState(bookService, "mapper", mapper);
	}

	@Test
	public void testShouldSaveBook() {
		// given
		  BookTo book = new BookTo(null, "title", "author nazw;");
		  Mockito.when(mapper.mapToBookEntity(Mockito.any())).thenCallRealMethod();
		  Mockito.when(mapper.mapToBookTo(Mockito.any())).thenCallRealMethod();
		  
		  BookEntity mapToBookEntity = mapper.mapToBookEntity(book); 
		  BookEntity mapToBookEntity2 = mapper.mapToBookEntity(new BookTo(1L, "title","author nazw;"));
		  
		  
		  Mockito.when(bookDao.save(mapToBookEntity)).thenReturn(mapToBookEntity2); 
		  //when
		  
		  BookTo result =bookService.saveBook(book); 
		  // then
		  Mockito.verify(bookDao).save(mapToBookEntity); 
		  assertEquals(1L,result.getId().longValue());
		  

//		// given
//		BookEntity book = new BookEntity(null, "title", Arrays.asList(new AuthorTo("imie nazwisko;")));
//		Mockito.when(bookDao.save(book)).thenReturn(new BookEntity(1L, "title", Arrays.asList(new AuthorTo("imie nazwisko;"))));
//		// when
//		BookTo result = bookService.saveBook(mapper.mapToBookTo(book));
//		// then
//		Mockito.verify(bookDao).save(book);
//		assertEquals(1L, result.getId().longValue());

	}
}
