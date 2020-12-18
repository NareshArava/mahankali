package com.stackroute.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.exception.FavouriteBookAlreadyExistsException;
import com.stackroute.exception.FavouriteBookNotFoundException;
import com.stackroute.model.FavouriteBook;
import com.stackroute.repository.FavouriteBookRepository;


@Service
public class FavouriteBookServiceImpl implements FavouriteBookService {
	
	@Autowired
	private FavouriteBookRepository favouritebookrepository;

	@Override
	public boolean saveFavouriteBook(FavouriteBook favouritebook) throws FavouriteBookAlreadyExistsException {
		System.out.println(favouritebook.getId());
		Optional<FavouriteBook> obj=favouritebookrepository.findById(favouritebook.getId());
		if(obj.isPresent()){
		throw new FavouriteBookAlreadyExistsException("Book already exists, cannot be saved");
		}
		favouritebookrepository.save(favouritebook);
		return true;
	}

	@Override
	public boolean deleteFavouriteBookById(int id) throws FavouriteBookNotFoundException {
		FavouriteBook obj = favouritebookrepository.findById(id).orElse(null);
		if (obj==null){
			throw new FavouriteBookNotFoundException("Book not found, cannot be deleted.");
		}
		favouritebookrepository.delete(obj);
		return true;
	}

	@Override
	public FavouriteBook getFavouriteBookById(int id) throws FavouriteBookNotFoundException {
		FavouriteBook obj=favouritebookrepository.findById(id).get();
		if(obj==null){
			throw new FavouriteBookNotFoundException("Book not found");
		}
		return obj;
	}

	@Override
	public List<FavouriteBook> getMyFavouriteBooks(String userId) {
		return favouritebookrepository.findByUserId(userId);
	}

}
