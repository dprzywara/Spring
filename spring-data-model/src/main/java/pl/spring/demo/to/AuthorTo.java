package pl.spring.demo.to;

public class AuthorTo {
	private Long id;
	private String firstName;
	private String lastName;

	public AuthorTo() {
	}

	public AuthorTo(Long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
 
	public AuthorTo(String authorData) {
		if(authorData!=null){
		String[] author = authorData.split(" ");
		if(author.length==0){
		this.lastName=authorData;}
		
		this.firstName = author[0];
		this.lastName = author[1];
		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
