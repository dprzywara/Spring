package pl.spring.demo.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;
//import pl.spring.demo.entity.QBookEntity;
import pl.spring.demo.entity.QAuthorEntity;
import pl.spring.demo.entity.QBookEntity;
import pl.spring.demo.repository.BookCriteriaSearch;

public class BookRepositoryImpl implements BookCriteriaSearch {
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public List<BookEntity> findBooksByCriteria(BookSearchCriteria bsc) {
		
		QAuthorEntity author = QAuthorEntity.authorEntity;
		QBookEntity book = QBookEntity.bookEntity;
		//QLibraryEntity library = QLibraryEntity.libraryEntity;
		
		//entityManager.createQuery(arg0)
		
		JPQLQuery query = new JPAQuery (entityManager).from(book);
		BooleanBuilder restrictions = new BooleanBuilder();
		
		if(bsc.getTitle()!=null){
			restrictions.and(book.title.startsWithIgnoreCase(bsc.getTitle()));
		}
		if(bsc.getLibraryName()!=null){
			restrictions.and(book.library.name.startsWithIgnoreCase(bsc.getLibraryName()));
		}
		if(bsc.getAuthor()!=null){
			String[] authorDetails=bsc.getAuthor().split(" ");
			//query.join(author);
			if(authorDetails.length>1){
				restrictions.and(book.authors.any().firstName.startsWithIgnoreCase(authorDetails[0]).and(book.authors.any().lastName.startsWithIgnoreCase(authorDetails[1])));
			}
			else{
				restrictions.and(book.authors.any().firstName.startsWithIgnoreCase(bsc.getAuthor()).or(book.authors.any().lastName.startsWithIgnoreCase(bsc.getAuthor())));
			}
		}
		
		
		return query.where(restrictions).listResults(book).getResults();
	}
	
	
	
	

}