package pl.spring.demo.mock;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.spring.demo.common.BookMapper;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

public class BookMapperTest {
	BookMapper mapper;

	@Before
	public void before() {
		mapper = new BookMapper();

	}

	@Test
	public void shouldReturnTrueForId() {
		BookTo book = new BookTo(1L, "title", "author nazw;");
		BookEntity entity = new BookEntity();
		entity = mapper.mapToBookEntity(book);
		assertEquals(1L, entity.getId().longValue());
	}

	@Test
	public void shouldReturnTrueForTitle() {
		BookTo book = new BookTo(1L, "title", "author nazw;");
		BookEntity entity = mapper.mapToBookEntity(book);
		assertEquals("title", entity.getTitle());
	}

	@Test
	public void shouldReturnTrueForFirstName() {
		BookTo book = new BookTo(1L, "title", "author nazw;");
		BookEntity entity = mapper.mapToBookEntity(book);
		assertEquals("author", entity.getAuthors().get(0).getFirstName());
	}

	@Test
	public void shouldReturnTrueForLastName() {
		BookTo book = new BookTo(1L, "title", "author nazw;");
		BookEntity entity = mapper.mapToBookEntity(book);
		assertEquals("nazw", entity.getAuthors().get(0).getLastName());
	}

	@Test
	public void shouldReturnTrueForAuthors() {
		BookEntity entity = new BookEntity(1L, "title",
				Arrays.asList(new AuthorTo("imie1 nazwisko1"), new AuthorTo("imie2 nazwisko2")));
		BookTo book = mapper.mapToBookTo(entity);
		assertEquals("imie1 nazwisko1;imie2 nazwisko2;", book.getAuthors());
	}

	@Test
	public void shouldReturnListOfBookTo() {
		List<BookEntity> lisfOfBookEntity = new ArrayList<BookEntity>();
		List<BookTo> listOfBookTo = new ArrayList<BookTo>();
		BookEntity entity1 = new BookEntity(1L, "title",
				Arrays.asList(new AuthorTo("imie1 nazwisko1"), new AuthorTo("imie2 nazwisko2")));
		BookEntity entity2 = new BookEntity(2L, "title2",
				Arrays.asList(new AuthorTo("imie3 nazwisko3"), new AuthorTo("imie4 nazwisko4")));

		lisfOfBookEntity.add(entity1);
		lisfOfBookEntity.add(entity2);
		listOfBookTo = mapper.mapToBookToList(lisfOfBookEntity);
		assertFalse(listOfBookTo.isEmpty());
		assertEquals("title", listOfBookTo.get(0).getTitle());
	}

}
