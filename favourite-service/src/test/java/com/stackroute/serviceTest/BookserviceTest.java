package com.stackroute.serviceTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.model.FavouriteBook;
import com.stackroute.exception.FavouriteBookAlreadyExistsException;
import com.stackroute.exception.FavouriteBookNotFoundException;
import com.stackroute.repository.FavouriteBookRepository;
import com.stackroute.service.FavouriteBookService;
public class BookserviceTest {
	private FavouriteBook book;
	Optional<FavouriteBook> options;
	
	@Mock
	private FavouriteBookRepository bookRepo;
	
	@InjectMocks
	private FavouriteBookService bookService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		book=new FavouriteBook(500,"GOT-1","RR Martin","96325874","JohnSnow");
		options=Optional.of(book);
	}
	
	@Test
	public void testMockCreation(){
		assertNotNull(book);
		assertNotNull("jpa repository creation fails: use@injectMocks on movieService",bookRepo);
	}
	
	@Test
	public void testSaveBookSuccess()throws FavouriteBookAlreadyExistsException{
		when(bookRepo.save(book)).thenReturn(book);
		boolean flag =bookService.saveFavouriteBook(book);
		assertEquals("saving book",true,flag);
		verify(bookRepo, times(1)).save(book);
		verify(bookRepo, times(1)).findById(book.getId());
	}
	
	@Test(expected=FavouriteBookAlreadyExistsException.class)
	public void testSaveBookFail()throws FavouriteBookAlreadyExistsException{
		when(bookRepo.findById(500)).thenReturn(options);
		when(bookRepo.save(book)).thenReturn(book);
		boolean flag =bookService.saveFavouriteBook(book);
	}

	@Test
	public void testDeleteById() throws FavouriteBookNotFoundException{
		when(bookRepo.findById(500)).thenReturn(options);
		doNothing().when(bookRepo).delete(book);
		boolean flag=bookService.deleteFavouriteBookById(500);
		assertEquals("Deleting book", true, flag);
		verify(bookRepo,times(1)).findById(book.getId());
	}
	
	@Test
	public void testGetBookById()throws FavouriteBookNotFoundException{
		when(bookRepo.findById(500)).thenReturn(options);
		FavouriteBook book1=bookService.getFavouriteBookById(500);
		assertEquals("fetching book",book1,book);
		verify(bookRepo,times(1)).findById(book.getId());
	}
	
	@Test
	public void testGetMyMovies(){
		List<FavouriteBook> bookList=new ArrayList<>();
		bookList.add(book);
		when(bookRepo.findByUserId("aa")).thenReturn(bookList);
		List<FavouriteBook> book1=bookService.getMyFavouriteBooks("aa");
		assertEquals(bookList,book1);
		verify(bookRepo,times(1)).findByUserId("aa");
	}
	

}
