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
    @RequestMapping(value = "/booksTable", method = RequestMethod.POST)
    public String deletedBook(@RequestParam("id") Long id,Map<String, Object> params) {
    	BookTo book = bookService.findBookById(id);
    	bookService.deleteBookById(id);
    	params.put("book", book);
    	return "deleteConfirm";
    }
    
   
}
