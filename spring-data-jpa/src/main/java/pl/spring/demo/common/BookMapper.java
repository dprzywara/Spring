package pl.spring.demo.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

@Component
public class BookMapper {
	
	public BookEntity mapToBookEntity(BookTo book){
		
		List<AuthorTo> authors = new ArrayList<>();
		String[] authorsTab = book.getAuthors().split(";");
		for(int i=0;i<authorsTab.length;i++){
			authors.add(new AuthorTo(authorsTab[i]));
		}
		
		BookEntity bookEntity = new BookEntity(book.getId(),book.getTitle(),authors);
		return bookEntity;
	}
	
	public BookTo mapToBookTo(BookEntity bookentity){
		
		String authors ="";
		for (AuthorTo author : bookentity.getAuthors()) {
			authors+=author.getFirstName()+" "+author.getLastName()+";";
		}
		BookTo bookTo = new BookTo(bookentity.getId(),bookentity.getTitle(),authors);
		return bookTo;
	}

	public List<BookTo> mapToBookToList(List<BookEntity> bookentityList){
		
		List<BookTo> listOffBookTo = new ArrayList<>();
		for (BookEntity bookEntity : bookentityList) {
			listOffBookTo.add(mapToBookTo(bookEntity));
		}
		return listOffBookTo;
	}
	

}
