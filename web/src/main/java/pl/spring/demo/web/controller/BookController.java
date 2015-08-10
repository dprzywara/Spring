package pl.spring.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

//	@RequestMapping(value = "/booksTable", method = RequestMethod.POST)
//	public String deletedBook(@RequestParam("id") Long id, Map<String, Object> params) {
//		BookTo book = bookService.findBookById(id);
//		bookService.deleteBookById(id);
//		params.put("title", book.getTitle());
//		return "deleteConfirm";
//	}
	@RequestMapping(value = "/booksTable", method = RequestMethod.POST)
	public String deletedBook(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		BookTo book = bookService.findBookById(id);
		redirectAttributes.addFlashAttribute("title", book.getTitle());
		bookService.deleteBookById(id);
		return "redirect:/deleteConfirm";
	}
	
	@RequestMapping(value = "/booksTable/add", method = RequestMethod.GET)
	public String addBook(Model model) {
		model.addAttribute("book", new BookTo());
		return "addBook";
	}

	@RequestMapping(value = "/booksTable/add2", method = RequestMethod.GET)
	public String addBook() {
		return "addBook";
	}

	@RequestMapping(value = "/booksTable/add2", method = RequestMethod.POST)
	public String addBook(@RequestParam("id") Long id, @RequestParam("title") String title,
			@RequestParam("authors") String authors) {
		bookService.saveBook(new BookTo(id, title, authors));
		return "redirect:/booksTable";
	}
	

	@RequestMapping(value = "/booksTable/add", method = RequestMethod.POST)
	public String addBook(@ModelAttribute BookTo book, Model model) {
		bookService.saveBook(book);
		return "redirect:/booksTable";
	}

	@RequestMapping(value = "/booksTable/up", method = RequestMethod.POST)
	public String updateBook(@RequestParam("id") Long id, @RequestParam("title") String title,
			@RequestParam("authors") String authors) {
		bookService.saveBook(new BookTo(id, title, authors));
		return "redirect:/booksTable";
	}

	@RequestMapping(value = "/deleteConfirm", method = RequestMethod.GET)
	public String confirm(){//(@RequestParam("title") String title, Map<String, Object> params) {
		//params.put("title", title);
		return "deleteConfirm";
	}

}
