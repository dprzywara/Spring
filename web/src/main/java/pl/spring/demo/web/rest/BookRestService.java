package pl.spring.demo.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.repository.BookRepository;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
public class BookRestService {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books-by-title", method = RequestMethod.GET)
    public List<BookTo> findBooksByTitle(@RequestParam("titlePrefix") String titlePrefix) {
        return bookService.findBooksByTitle(titlePrefix);
    }
//    @RequestMapping(value = "/books-by-title/{title}")
//    public List<BookTo> findBooksByTitle(@PathVariable("title") String titlePrefix) {
//    	return bookService.findBooksByTitle(titlePrefix);
//    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public BookTo saveBook(@RequestBody BookTo book) {
        return bookService.saveBook(book);
    }
    @RequestMapping(value = "/book", method = RequestMethod.PUT)
    public BookTo updateBook(@RequestBody BookTo book) {
    	return bookService.saveBook(book);
    }
    @RequestMapping(value = "/bookId", method = RequestMethod.DELETE)
    public void delBook(@RequestBody long id) {
    	if(bookService.findBookById(id)!=null){
    	 bookService.deleteBookById(id);
    	}
    }
    @RequestMapping(value = "/book", method = RequestMethod.DELETE)
    public void delBook(@RequestBody BookTo book) {
    	bookService.deleteBook(book);
    	
    }
    
    @RequestMapping(value="book/{id}", method = RequestMethod.DELETE)
    public BookTo delete (@PathVariable Long id,Map<String, Object> params) {
    	BookTo book ;
    	if ((book= bookService.findBookById(id))!=null) {
    		bookService.deleteBookById(id);
			return book;
		}	
    	return null;

    }
}
