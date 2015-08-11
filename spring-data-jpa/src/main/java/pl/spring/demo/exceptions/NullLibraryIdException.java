package pl.spring.demo.exceptions;

public class NullLibraryIdException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public NullLibraryIdException() {
		super("library in Book has null id");
	}

}
