package pl.spring.demo.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.BookMapper;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookDaoImpl implements BookDao {

	private final Set<BookEntity> ALL_BOOKS = new HashSet<>();
	private final Sequence sequence;
	private final BookMapper mapper;

	@Autowired
	public BookDaoImpl(Sequence sequence, BookMapper mapper) {
		this.sequence = sequence;
		this.mapper = mapper;
		addTestBooks(mapper);
	}

	@Override
	public List<BookEntity> findAll() {
		return new ArrayList<>(ALL_BOOKS);
	}

	@Override
	public List<BookEntity> findBookByTitle(String title) {
		List<BookEntity> lisfOfBookEntity = new ArrayList<>();
		for (BookEntity bookEntity : ALL_BOOKS) {
			if (bookEntity.getTitle().toLowerCase().startsWith(title.toLowerCase())) {
				lisfOfBookEntity.add(bookEntity);
			}
		}
		return lisfOfBookEntity;
	}

	@Override
	public List<BookEntity> findBooksByAuthor(String author) {
		if (author != null) {
			List<BookEntity> lisfOfBookEntity = new ArrayList<>();
			String[] details = author.split(" ");
			for (BookEntity bookEntity : ALL_BOOKS) {
				for (AuthorTo a : bookEntity.getAuthors()) {
					if (isAuthorMatched(details, a)) {
						lisfOfBookEntity.add(bookEntity);
					}
				}
			}
			return lisfOfBookEntity;
		}
		return null;
	}

	private boolean isMatchedToFirstNameOrLastName(String[] details, AuthorTo a) {
		return ((a.getFirstName().toLowerCase().startsWith(details[0].toLowerCase()))
				|| (a.getLastName().toLowerCase().startsWith(details[0].toLowerCase())));
	}

	private boolean isMatchedToFirstNameAndLastName(String[] details, AuthorTo a) {
		return ((a.getFirstName().toLowerCase().startsWith(details[0].toLowerCase()))
				&& (a.getLastName().toLowerCase().startsWith(details[1].toLowerCase())));

	}

	private boolean isAuthorMatched(String[] details, AuthorTo a) {
		switch (details.length) {
		case 2:
			return (isMatchedToFirstNameAndLastName(details, a));
		case 1:
			return (isMatchedToFirstNameOrLastName(details, a));
		}
		return false;
	}

	public long getNextSequenceValue() {
		return sequence.nextValue(ALL_BOOKS);
	}

	@Override
	@NullableId
	public BookEntity save(BookEntity book) {
		ALL_BOOKS.add(book);
		return book;
	}

	private void addTestBooks(BookMapper mapper) {
		ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia", Arrays.asList(new AuthorTo("Wiliam Szekspir"))));
		ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole", Arrays.asList(new AuthorTo("Hanna Ożogowska"))));
		ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza", Arrays.asList(new AuthorTo("Jan Parandowski"))));
		ALL_BOOKS.add(new BookEntity(4L, "Awantura w Niekłaju", Arrays.asList(new AuthorTo("Edmund Niziurski"))));
		ALL_BOOKS.add(
				new BookEntity(5L, "Pan Samochodzik i Fantomas", Arrays.asList(new AuthorTo("Zbigniew Nienacki"))));
		ALL_BOOKS.add(mapper.mapToBookEntity(new BookTo(6L, "Zemsta", "Aleksander Fredro")));
	}
}
