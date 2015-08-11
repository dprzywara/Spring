package pl.spring.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.spring.demo.entity.LibraryEntity;
import pl.spring.demo.repository.LibraryRepository;
import pl.spring.demo.service.LibraryService;

@Service
@Transactional(readOnly = true)
public class LibraryServiceImpl implements LibraryService{
	
	@Autowired
	private LibraryRepository libraryRepository;

	@Override
    @Transactional(readOnly = false)
	public LibraryEntity removeLibrary(Long id) {
		LibraryEntity libEntity = libraryRepository.findOne(id);
		if(libEntity!=null){
		libraryRepository.delete(id);
		return libEntity;
		}
		return null;
	}
	
	
	

}
