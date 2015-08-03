package pl.spring.demo.aop;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/pl/spring/demo/service/CommonServiceTest-context.xml")


public class AspectSetIdTest {

	@Autowired
    private BookService bookService;
	
	@Test
	public void testShouldSetId() {
		// given
		BookTo bookToSave = new BookTo(null, "title", "author nazw;");
        // when
        BookTo result =bookService.saveBook(bookToSave);
        // then
		assertNotNull(result.getId());
	
	}
}