package com.stackroute.controllerTest;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.controller.FavouriteBookController;
import com.stackroute.model.FavouriteBook;
import com.stackroute.service.FavouriteBookService;

@RunWith(SpringRunner.class)
@WebMvcTest(FavouriteBookController.class)
public class BookControllerTest {
	@Autowired
	private MockMvc bookMockMvc;
	
	@MockBean
	private FavouriteBookService bookservice;
	
	@InjectMocks
	private FavouriteBookController bookController;
	
	private FavouriteBook book;
	List<FavouriteBook> books;
	
	@Before
	public void setUp()throws Exception{
		MockitoAnnotations.initMocks(this);
		books=new ArrayList<>();
		book=new FavouriteBook(500,"GOT-1","RR Martin","96325874","JohnSnow");
		books.add(book);
        book=new FavouriteBook(600,"GOT-2","RR Martin","98745216","JohnSnow");
        books.add(book);
		bookMockMvc=MockMvcBuilders.standaloneSetup(bookController).build();
		
	}
	

	
	@Test
	public void testSaveBook()throws Exception{
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsImlhdCI6MTU1MzA2OTcwNX0.PK-py6MXywTauPyHu-iG7BBN46E7O65JRA5bsVbsTzI";
		when(bookservice.saveFavouriteBook(book)).thenReturn(true);
		bookMockMvc.perform(post("/api/bookservice/book").header("authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(book))).andExpect(status().isCreated());
		verify(bookservice,times(1)).saveFavouriteBook(Mockito.any(FavouriteBook.class));
	}
	
	@Test
	public void testDeleteBookById() throws Exception {
		when(bookservice.deleteFavouriteBookById(1)).thenReturn(true);
		bookMockMvc.perform(delete("/api/bookservice/book/{id}", 1)).andExpect(status().isOk());
		verify(bookservice, times(1)).deleteFavouriteBookById(1);
		verifyNoMoreInteractions(bookservice);
	}
	
	@Test
	public void testGetBookById() throws Exception {
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsImlhdCI6MTU1MzA2OTcwNX0.PK-py6MXywTauPyHu-iG7BBN46E7O65JRA5bsVbsTzI";
		when(bookservice.getFavouriteBookById(1)).thenReturn(book);
		bookMockMvc.perform(get("/api/bookservice/book/{id}", 1).header("autorization", "Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(book))).andExpect(status().isOk());
		verify(bookservice, times(1)).getFavouriteBookById(1);
		verifyNoMoreInteractions(bookservice);
	}

	@Test
	public void testGetAllBooks() throws Exception {
		List <FavouriteBook> books=new ArrayList<>();
		FavouriteBook book1=new FavouriteBook(700, "HP-1","JK Rowling","65478932","Harry");
		books.add(book1);
		FavouriteBook book2=new FavouriteBook(800,"HP-2","JK Rowling","85214796","Harry");
		books.add(book2);
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsImlhdCI6MTU1MzA2OTcwNX0.PK-py6MXywTauPyHu-iG7BBN46E7O65JRA5bsVbsTzI";
		when(bookservice.getMyFavouriteBooks("aa")).thenReturn(books);
		bookMockMvc.perform(get("/api/bookservice/books").header("authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(book))).andExpect(status().isOk());
		verify(bookservice, times(1)).getMyFavouriteBooks("aa");
		verifyNoMoreInteractions(bookservice);
	}



	private String jsonToString(final Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
			}
	}

}
