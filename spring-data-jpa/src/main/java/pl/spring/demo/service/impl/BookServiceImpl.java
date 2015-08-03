package pl.spring.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import pl.spring.demo.common.BookMapper;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;
	@Autowired
	private BookMapper mapper;

	@Override
	public List<BookTo> findAllBooks() {
		return mapper.mapToBookToList(bookDao.findAll());
	}

	@Override
	public List<BookTo> findBooksByTitle(String title) {
		return mapper.mapToBookToList(bookDao.findBookByTitle(title));
	}

	@Override
	public List<BookTo> findBooksByAuthor(String author) {
		return mapper.mapToBookToList(bookDao.findBooksByAuthor(author));
	}

	@Override
	public BookTo saveBook(BookTo book) {
		return mapper.mapToBookTo(bookDao.save(mapper.mapToBookEntity(book)));
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
}
