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

import com.stackroute.exception.RecommendationBookAlreadyExistsException;
import com.stackroute.exception.RecommendationBookNotFoundException;
import com.stackroute.model.RecommendationBook;
import com.stackroute.repository.RecommendationBookRepository;
import com.stackroute.service.RecommendationBookService;


public class BookServiceTest {
	private RecommendationBook book;
	Optional<RecommendationBook> options;
	
	@Mock
	private RecommendationBookRepository bookRepo;
	
	@InjectMocks
	private RecommendationBookService bookService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		book=new RecommendationBook(500,"GOT-1","RR Martin","96325874","JohnSnow");
		options=Optional.of(book);
	}
	
	@Test
	public void testMockCreation(){
		assertNotNull(book);
		assertNotNull("jpa repository creation fails: use@injectMocks on movieService",bookRepo);
	}
	
	@Test
	public void testSaveBookSuccess()throws RecommendationBookAlreadyExistsException{
		when(bookRepo.save(book)).thenReturn(book);
		boolean flag =bookService.saveRecommendationBook(book);
		assertEquals("saving book",true,flag);
		verify(bookRepo, times(1)).save(book);
		verify(bookRepo, times(1)).findById(book.getId());
	}
	
	@Test(expected=RecommendationBookAlreadyExistsException.class)
	public void testSaveBookFail()throws RecommendationBookAlreadyExistsException{
		when(bookRepo.findById(500)).thenReturn(options);
		when(bookRepo.save(book)).thenReturn(book);
		boolean flag =bookService.saveRecommendationBook(book);
	}

	@Test
	public void testDeleteById() throws RecommendationBookNotFoundException{
		when(bookRepo.findById(500)).thenReturn(options);
		doNothing().when(bookRepo).delete(book);
		boolean flag=bookService.deleteRecommendationBookById(500);
		assertEquals("Deleting book", true, flag);
		verify(bookRepo,times(1)).findById(book.getId());
	}
	
	@Test
	public void testGetBookById()throws RecommendationBookNotFoundException{
		when(bookRepo.findById(500)).thenReturn(options);
		RecommendationBook book1=bookService.getRecommendationBookById(500);
		assertEquals("fetching book",book1,book);
		verify(bookRepo,times(1)).findById(book.getId());
	}
	
	@Test
	public void testGetMyMovies(){
		List<RecommendationBook> bookList=new ArrayList<>();
		bookList.add(book);
		when(bookRepo.findByUserId("aa")).thenReturn(bookList);
		List<RecommendationBook> book1=bookService.getMyRecommendationBooks("aa");
		assertEquals(bookList,book1);
		verify(bookRepo,times(1)).findByUserId("aa");
	}
	

}


