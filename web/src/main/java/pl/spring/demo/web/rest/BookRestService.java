package pl.spring.demo.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.repository.BookRepository;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.List;

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
    
    //delete book
    @RequestMapping(value="booksTable/del/{id}", method = RequestMethod.GET)
    public String delete (@PathVariable Long id) {
    	 bookService.deleteBook(id);
       // return "redirect:/users";
    	 return "deleteConfirm";
    }
}
