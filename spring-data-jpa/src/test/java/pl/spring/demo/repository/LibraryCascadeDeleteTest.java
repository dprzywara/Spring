package pl.spring.demo.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.repository.BookRepository;
import pl.spring.demo.repository.LibraryRepository;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class LibraryCascadeDeleteTest {

	@Autowired
	BookRepository bookRepository;
	@Autowired
	LibraryRepository libraryRepository;
	

	@Test
	public void testShouldDelete1LibraryAnd2Books() {
		//given
		long oldSize=bookRepository.count();
		//when
		libraryRepository.delete(2L);
		//then
		assertEquals(oldSize-1, bookRepository.count());
		assertEquals(2, libraryRepository.count());
	}

}
