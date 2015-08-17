package pl.spring.demo.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.QBookEntity;

public class BookDaoImpl extends AbstractDao<BookEntity, Long>implements BookDao {

	@Override
	public List<BookEntity> findBookByTitle(String title) {
		TypedQuery<BookEntity> query = entityManager.createQuery(
				"select book from BookEntity book where upper(book.title) like concat(upper(:title), '%')",
				BookEntity.class);
		query.setParameter("title", title);
		return query.getResultList();
	}

	@Override
	public List<BookEntity> findBooksByCriteria(BookSearchCriteria bsc) {
		QBookEntity book = QBookEntity.bookEntity;
		JPQLQuery query = new JPAQuery(entityManager).from(book);
		BooleanBuilder restrictions = new BooleanBuilder();

		if (bsc.getTitle() != null) {
			restrictions.and(book.title.startsWithIgnoreCase(bsc.getTitle()));
		}
		if (bsc.getLibraryName() != null) {
			restrictions.and(book.library.name.startsWithIgnoreCase(bsc.getLibraryName()));
		}
		if (bsc.getAuthor() != null) {
			String[] authorDetails = bsc.getAuthor().split(" ");
			if (authorDetails.length > 1) {
				restrictions.and(book.authors.any().firstName.startsWithIgnoreCase(authorDetails[0])
						.and(book.authors.any().lastName.startsWithIgnoreCase(authorDetails[1])));
			}
			if (authorDetails.length <= 1) {
				restrictions.and(book.authors.any().firstName.startsWithIgnoreCase(bsc.getAuthor())
						.or(book.authors.any().lastName.startsWithIgnoreCase(bsc.getAuthor())));
			}
		}

		return query.where(restrictions).listResults(book).getResults();

	}

}
