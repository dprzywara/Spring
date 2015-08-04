package pl.spring.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.List;
import java.util.Map;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String bookList(Map<String, Object> params) {
        final List<BookTo> allBooks = bookService.findAllBooks();
        params.put("books", allBooks);
        return "bookList";
    }
    @RequestMapping(value = "/booksTable", method = RequestMethod.GET)
    public String listOfBooks(Map<String, Object> params) {
    	final List<BookTo> allBooks = bookService.findAllBooks();
    	params.put("books", allBooks);
    	return "bookTable";
    }
    @RequestMapping(value = "/booksTable/del2", method = RequestMethod.POST)
    public String deletedBook(@RequestParam("id") Long id,Map<String, Object> params) {
    	//final List<BookTo> book = bookService.findBooksByTitle(title);
    	System.out.println("Controler id = "+id);
    	bookService.deleteBook(id);
    	params.put("id", id);
    	return "deleteConfirm";
    }
    
   
}
