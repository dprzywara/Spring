package pl.spring.demo.criteria;

import org.springframework.stereotype.Service;

@Service
public class BookSearchCriteria {
	
	private String title;
	private String author;
	private String libraryName;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getLibraryName() {
		return libraryName;
	}
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	
	
}
