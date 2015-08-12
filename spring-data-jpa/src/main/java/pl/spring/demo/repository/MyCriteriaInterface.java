package pl.spring.demo.repository;

import java.util.List;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;

public interface MyCriteriaInterface {
	
	
	List<BookEntity> search(BookSearchCriteria bsc);

}
