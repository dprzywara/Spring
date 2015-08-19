package pl.spring.demo.mapper;
import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookMapper {

    public static BookTo map(BookEntity bookEntity) {
    	Set<AuthorTo> authors= new HashSet<AuthorTo>() ;
    	
        if (bookEntity != null) {
        	for (AuthorEntity author : bookEntity.getAuthors()) {
				authors.add(AuthorMapper.map(author));
			}
            return new BookTo(bookEntity.getId(), bookEntity.getTitle(), authors);
        }
        return null;
    }

    public static BookEntity map(BookTo bookTo) {
    	Set<AuthorEntity> authors= new HashSet<AuthorEntity>() ;
        if (bookTo != null) {
        	for (AuthorTo author : bookTo.getAuthors()) {
				authors.add(AuthorMapper.map(author));
			}
            return new BookEntity(bookTo.getId(), bookTo.getTitle());
        }
        return null;
    }

    public static List<BookTo> map2To(List<BookEntity> bookEntities) {
        return bookEntities.stream().map(BookMapper::map).collect(Collectors.toList());
    }

    public static List<BookEntity> map2Entity(List<BookTo> bookEntities) {
        return bookEntities.stream().map(BookMapper::map).collect(Collectors.toList());
    }

//    private static String mapAuthors(Collection<AuthorEntity> authors) {
//        if (!authors.isEmpty()) {
//            return authors.stream().map(authorEntity -> authorEntity.getFirstName() + " " + authorEntity.getLastName()).collect(Collectors.joining
//                    (", "));
//        }
//        return null;
//    }
}