package pl.spring.demo.to;

import java.util.List;

public class BookEntity implements IdAware {
	private Long id;
	private String title;
	private List<AthorTo> authors;
	
	
	public BookEntity() {
    }
	public BookEntity(Long id, String title, List<AthorTo> authors) {
		super();
		this.id = id;
		this.title = title;
		this.authors = authors;
	}

	@Override
	public Long getId() {
		 return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<AthorTo> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AthorTo> authors) {
		this.authors = authors;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
