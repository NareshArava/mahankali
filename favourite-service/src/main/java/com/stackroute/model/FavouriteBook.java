package com.stackroute.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="favourite")
public class FavouriteBook {

	
	@Transient
	
	public static final String SEQUENCE_NAME= "users_sequence";
	
	@Id
	private long id;
	
	private String title;
	private String author_name;
	private String isbn;
	private String userId;

	public FavouriteBook() {
		super();
	}

	public FavouriteBook(int id, String title, String author_name, String isbn,  String userId) {
		super();
		this.id = id;
		this.title = title;
		this.author_name = author_name;
		this.isbn = isbn;
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long l) {
		this.id = l;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
