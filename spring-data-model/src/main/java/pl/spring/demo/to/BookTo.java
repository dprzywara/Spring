package pl.spring.demo.to;

public class BookTo {
    private Long id;
    private String title;
    private String authors;
    private Long libraryId;

    public Long getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(Long libraryId) {
		this.libraryId = libraryId;
	}

	public BookTo() {
    }

    public BookTo(Long id, String title, String authors, Long libId) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.libraryId=libId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }
}
