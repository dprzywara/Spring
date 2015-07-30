package pl.spring.demo.mock;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import pl.spring.demo.common.BookMapper;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

public class BookMapperTest {
	BookMapper mapper ;
	@Before
	public void before(){
		mapper = new BookMapper();
		
	}
//nie dzialalo
//  ALL_BOOKS.add(mapper.mapToBookEntity(new BookTo(1L, "Romeo i Julia", "Wiliam Szekspir;")));
//  ALL_BOOKS.add(mapper.mapToBookEntity(new BookTo(2L, "Opium w rosole", "Hanna Ożogowska;")));
// 
	
	@Test
	public void shouldReturnTrueForId() {
		BookTo book = new BookTo(1L, "title", "author nazw;");
		BookEntity entity = new BookEntity();
		entity=mapper.mapToBookEntity(book);
		assertEquals(1L,entity.getId().longValue());
	}
	@Test
	public void shouldReturnTrueForTitle() {
		BookTo book = new BookTo(1L, "title", "author nazw;");
		BookEntity entity =mapper.mapToBookEntity(book);
		assertEquals("title",entity.getTitle());
	}
	@Test
	public void shouldReturnTrueForFirstName() {
		BookTo book = new BookTo(1L, "title", "author nazw;");
		BookEntity entity =mapper.mapToBookEntity(book);
		assertEquals("author",entity.getAuthors().get(0).getFirstName());
	}
	@Test
	public void shouldReturnTrueForLastName() {
		BookTo book = new BookTo(1L, "title", "author nazw;");
		BookEntity entity =mapper.mapToBookEntity(book);
		assertEquals("nazw",entity.getAuthors().get(0).getLastName());
	}
	
	
	
	@Test
	public void shouldReturnTrueForAuthors() {
		BookEntity entity = new BookEntity(1L, "title", Arrays.asList(new AuthorTo("imie1 nazwisko1;"),new AuthorTo("imie2 nazwisko2;")));
		BookTo book =mapper.mapToBookTo(entity);
		assertEquals("imie1 nazwisko1;imie2 nazwisko2;",book.getAuthors());
	}

}
