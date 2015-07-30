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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookDaoImpl implements BookDao {

    private final Set<BookEntity> ALL_BOOKS = new HashSet<>();
    @Autowired
    private Sequence sequence;
    @Autowired
    private BookMapper mapper;
    
    public BookDaoImpl() {
        addTestBooks();
    }

    @Override
    public List<BookEntity> findAll() {
        return new ArrayList<>(ALL_BOOKS);
    }

    @Override
    public List<BookEntity> findBookByTitle(String title) {
    	List<BookEntity> lisfOfBookEntity = new ArrayList<>();
    	for (BookEntity bookEntity : ALL_BOOKS) {
			if(bookEntity.getTitle().equalsIgnoreCase(title)){
				lisfOfBookEntity.add(bookEntity);
			}
		}
        return lisfOfBookEntity;
    }

    @Override
    public List<BookEntity> findBooksByAuthor(String author) {
    	
    	List<BookEntity> lisfOfBookEntity = new ArrayList<>();
    	for (BookEntity bookEntity : ALL_BOOKS) {
    		for (AuthorTo a : bookEntity.getAuthors()) {
    			if(a.getFirstName().toLowerCase().startsWith(author.toLowerCase())|| a.getLastName().toLowerCase().startsWith(author.toLowerCase())){
    				lisfOfBookEntity.add(bookEntity);
    			}
			}
			
		}
        return lisfOfBookEntity;
    }
    

    public long getNextSequenceValue(){
    	return sequence.nextValue(ALL_BOOKS);
    }
    @Override
    @NullableId
    public BookEntity save(BookEntity book) {
        ALL_BOOKS.add(book);
        return book;
    }
    
    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }

    private void addTestBooks() {
        ALL_BOOKS.add(mapper.mapToBookEntity(new BookTo(1L, "Romeo i Julia", "Wiliam Szekspir;")));
        ALL_BOOKS.add(mapper.mapToBookEntity(new BookTo(2L, "Opium w rosole", "Hanna Ożogowska;")));
        ALL_BOOKS.add(mapper.mapToBookEntity(new BookTo(3L, "Przygody Odyseusza", "Jan Parandowski;")));
        ALL_BOOKS.add(mapper.mapToBookEntity(new BookTo(4L, "Awantura w Niekłaju", "Edmund Niziurski;")));
        ALL_BOOKS.add(mapper.mapToBookEntity(new BookTo(5L, "Pan Samochodzik i Fantomas", "Zbigniew Nienacki;")));
        ALL_BOOKS.add(mapper.mapToBookEntity(new BookTo(6L, "Zemsta", "Aleksander Fredro;")));
    }
}
