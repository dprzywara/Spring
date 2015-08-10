package pl.spring.demo.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

public class BookControllerTest {

	@Mock
	private BookService bookService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/templates/");
		viewResolver.setSuffix(".html");
		BookController bookController = new BookController();
		Whitebox.setInternalState(bookController, "bookService", bookService);
		mockMvc = MockMvcBuilders.standaloneSetup(bookController)
				.setViewResolvers(viewResolver).build();
	}
	
	
	@Test
	public void testShouldRedirectToConfirm() throws Exception {
		// given
		Long id = 2L;
		String title = "tytul44444";
		String authors = "Zenon Loska";
		final BookTo book = new BookTo(id, title, authors);
		Mockito.when(bookService.deleteBookById(Mockito.anyLong())).thenReturn(book);
		Mockito.when(bookService.findBookById(Mockito.anyLong())).thenReturn(book);

		// when
		ResultActions response = this.mockMvc.perform(post("/booksTable").param("id", "2"));
		// then
		Mockito.verify(bookService).deleteBookById(2L);
		response.andExpect(view().name("redirect:/deleteConfirm"))
		.andExpect(redirectedUrl("/deleteConfirm"))
		.andExpect(flash().attributeExists("title"))
		.andExpect(flash().attributeCount(1))
		.andExpect(flash().attribute("title", title));

		
	}
	
	
	
	 @Test
	 public void testShouldReturnAllBooks() throws Exception {
	 // given
	 Long id=2L;
	 String title="tytul";
	 String authors="Zenon Loska";
	 final BookTo book1 = new BookTo(id,title,authors);
	 final BookTo book2 = new BookTo(new Long(2),"tythgasdfhg",authors);
	
	 Mockito.when(bookService.findAllBooks()).thenReturn(Arrays.asList(book1,book2));
	 // when
	 ResultActions response = this.mockMvc.perform(get("/booksTable")
	 .accept(MediaType.ALL)
	 .contentType(MediaType.ALL));
	
	 // then
	 Mockito.verify(bookService).findAllBooks();
	 response.andExpect(status().isOk())
	 .andExpect(view().name("bookTable"))
	 .andExpect(model().attribute("books", new ArgumentMatcher<Object>() {
			@SuppressWarnings("unchecked")
			@Override
			public boolean matches(Object argument) {
				return "tytul".equals(((List<BookTo>) argument).get(0).getTitle())
						&& "Zenon Loska".equals(((List<BookTo>) argument).get(1).getAuthors());
			}
		}));
	
	
	 }

}
