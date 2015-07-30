package pl.spring.demo.to;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookEntity implements IdAware {
	private Long id;
	private String title;
	private List<AuthorTo> authors;
	
	
	public BookEntity() {
    }
	public BookEntity(Long id, String title, List<AuthorTo> authors) {
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

	public List<AuthorTo> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorTo> authors) {
		this.authors = authors;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public boolean equals(Object object) {
	     boolean result= false;
	    // Set set = new HashSet();
		 
	     
	    if (this == object) return true;
	     
	    if ( !(object instanceof BookEntity) ) return false; 
	     
	    BookEntity entity = (BookEntity) object;
	   // set.addAll(entity.getAuthors());
	    
	    if(this.id==entity.getId()&&this.title==entity.getTitle()){
	    	
	    	return true;
	    }
	    	
	     

	    
	    
	    	
	   return result;
	}
	
	
}
