package com.stackroute.repositoryTest;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.FavouriteServiceApplication;
import com.stackroute.model.FavouriteBook;
import com.stackroute.repository.FavouriteBookRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.*;

import javax.transaction.Transactional;




@ContextConfiguration(classes=FavouriteServiceApplication.class)
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Transactional

public class RepositoryTest {
	@Autowired
	private transient FavouriteBookRepository repo;

	public void setRepo(FavouriteBookRepository repo) {
		this.repo = repo;
	}
	
	@After
	public void tearDown() {
		repo.deleteAll();
	  }
	
	@Test
	public void testSaveBook() throws Exception {
		final FavouriteBook book = repo.save(new FavouriteBook(500,"GOT-1","RR Martin","96325874","JohnSnow"));
		assertThat(book.getTitle()).isEqualTo("GOT-1");
	}
	
	@Test
	public void testDeleteBook() throws Exception {
		final FavouriteBook book = repo.save(new FavouriteBook(500,"GOT-1","RR Martin","96325874","JohnSnow"));
		assertEquals("GOT-1", book.getTitle());
		repo.delete(book);
		assertEquals(Optional.empty(), repo.findById(1));
	}

	@Test
	public void testGetBook() throws Exception {
		final FavouriteBook book = repo.save(new FavouriteBook(500,"GOT-1","RR Martin","96325874","JohnSnow"));
		assertEquals("GOT-1", book.getTitle());
	}

	@Test
	public void testGetAllBooks() throws Exception {
		repo.save(new FavouriteBook(500,"GOT-1","RR Martin","96325874","JohnSnow"));
		repo.save(new FavouriteBook(600,"GOT-2","RR Martin","65897412","JohnSnow"));
		repo.save(new FavouriteBook(700,"GOT-3","RR Martin","36521497","JohnSnow"));
		List<FavouriteBook> myBooks = repo.findByUserId("JohnSnow");
		assertEquals(myBooks.size(),3);
	}

}
