package pl.spring.demo.repository.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "../CommonRepositoryTest-context.xml")
public class BookRepositoryImplTest {

	private BookSearchCriteria bookCriteria;

	@Autowired
	private BookRepositoryImpl bookSearch;

	@Before
	public void before() {
		bookCriteria = new BookSearchCriteria();
	}

	@Test
	public void shouldFindTwoBooksByTitlePrefix() {
		// given
		bookCriteria.setTitle("P");

		// when
		List<BookEntity> result = bookSearch.findBooksByCriteria(bookCriteria);

		// then
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("Potop", result.get(1).getTitle());

	}

	@Test
	public void shouldFindBooksByLibraryPrefix() {
		// given
		bookCriteria.setLibraryName("pierw");

		// when
		List<BookEntity> result = bookSearch.findBooksByCriteria(bookCriteria);

		// then
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("Pierwsza biblioteka", result.get(1).getLibrary().getName());

	}

	@Test
	public void shouldFindBooksByAuthorFirstNamePrefixAndLastNamePrefix() {
		// given
		bookCriteria.setAuthor("Janu Jankows");

		// when
		List<BookEntity> result = bookSearch.findBooksByCriteria(bookCriteria);

		// then
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("Trzecia książka", result.get(0).getTitle());

	}

	@Test
	public void shouldFindBooksByAuthorFirstNamePrefix() {
		// given
		bookCriteria.setAuthor("Jan");

		// when
		List<BookEntity> result = bookSearch.findBooksByCriteria(bookCriteria);

		// then
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("Pierwsza książka", result.get(0).getTitle());

	}

	@Test
	public void shouldFindBooksByAuthorLastNamePrefix() {
		// given

		bookCriteria.setAuthor("N");

		// when
		List<BookEntity> result = bookSearch.findBooksByCriteria(bookCriteria);

		// then
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("Druga książka", result.get(0).getTitle());

	}

	@Test
	public void shouldFindOneBookForAllCriteria() {
		// given
		bookCriteria.setTitle("D");
		bookCriteria.setAuthor("Z");
		bookCriteria.setAuthor("N");

		// when
		List<BookEntity> result = bookSearch.findBooksByCriteria(bookCriteria);

		// then
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("Druga książka", result.get(0).getTitle());

	}

	@Test
	public void shouldReturnAllBooksForNoCriteria() {
		// given

		// when
		List<BookEntity> result = bookSearch.findBooksByCriteria(bookCriteria);

		// then
		assertNotNull(result);
		assertEquals(4, result.size());
		assertEquals("Potop", result.get(3).getTitle());

	}

}
