package pl.spring.demo.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import pl.spring.demo.entity.LibraryEntity;
import pl.spring.demo.repository.LibraryRepository;
import pl.spring.demo.service.impl.LibraryServiceImpl;

public class LibraryServiceImplTest {

	@InjectMocks
	private LibraryServiceImpl libraryService;
	@Mock
	private LibraryRepository libraryRepository;


	@Before
	public void setUpt() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void shouldCallRemoveMethod() {
		//given
		Long id=1L;
		LibraryEntity library = new LibraryEntity(id, "library");
		Mockito.when(libraryRepository.findOne(id)).thenReturn(library);
		//when
		libraryService.removeLibrary(id);
		//then
		Mockito.verify(libraryRepository).delete(id);
	}

}
