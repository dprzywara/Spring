package pl.spring.demo.service;

import java.util.List;

import pl.spring.demo.to.AuthorTo;

public interface AuthorService {

	
	 List<AuthorTo> getAllAuthors();
}
