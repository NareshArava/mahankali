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

import com.stackroute.RecommendationServiceApplication;
import com.stackroute.model.RecommendationBook;
import com.stackroute.repository.RecommendationBookRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.*;


@ContextConfiguration(classes=RecommendationServiceApplication.class)
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class RepositoryTest {
	@Autowired
	private transient RecommendationBookRepository repo;

	public void setRepo(RecommendationBookRepository repo) {
		this.repo = repo;
	}
	
	@After
	public void tearDown() {
		repo.deleteAll();
	  }
	
	@Test
	public void testSaveBook() throws Exception {
		final RecommendationBook book = repo.save(new RecommendationBook(500,"GOT-1","RR Martin","96325874","JohnSnow"));
		assertThat(book.getTitle()).isEqualTo("GOT-1");
	}
	
	@Test
	public void testDeleteBook() throws Exception {
		final RecommendationBook book = repo.save(new RecommendationBook(500,"GOT-1","RR Martin","96325874","JohnSnow"));
		assertEquals("GOT-1", book.getTitle());
		repo.delete(book);
		assertEquals(Optional.empty(), repo.findById(1));
	}

	@Test
	public void testGetBook() throws Exception {
		final RecommendationBook book = repo.save(new RecommendationBook(500,"GOT-1","RR Martin","96325874","JohnSnow"));
		assertEquals("GOT-1", book.getTitle());
	}

	@Test
	public void testGetAllBooks() throws Exception {
		repo.save(new RecommendationBook(500,"GOT-1","RR Martin","96325874","JohnSnow"));
		repo.save(new RecommendationBook(600,"GOT-2","RR Martin","65897412","JohnSnow"));
		repo.save(new RecommendationBook(700,"GOT-3","RR Martin","36521497","JohnSnow"));
		List<RecommendationBook> myBooks = repo.findByUserId("JohnSnow");
		assertEquals(myBooks.size(),3);
	}

}


