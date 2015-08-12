package pl.spring.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;
//import pl.spring.demo.entity.QBookEntity;

public class LibraryRepositoryImpl implements MyCriteriaInterface {

	@PersistenceContext(name="hsql")
	private EntityManager entityManager;
	
	
	@Override
	public List<BookEntity> search(BookSearchCriteria bsc) {
		
		//QAuthorEntity author = QAuthorEntity.authorentity;
		
		//JPQLQuery query = new 
		
		
		return null;
	}
	
	
	
	

}
