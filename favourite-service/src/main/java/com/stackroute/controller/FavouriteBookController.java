package com.stackroute.controller;

import java.util.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.stackroute.exception.FavouriteBookAlreadyExistsException;
import com.stackroute.exception.FavouriteBookNotFoundException;
import com.stackroute.model.FavouriteBook;
import com.stackroute.service.FavouriteBookService;
import com.stackroute.service.SequenceGeneratorService;

import io.jsonwebtoken.Jwts;

@CrossOrigin
@RestController
@RequestMapping("/api/favouritebookservice")
public class FavouriteBookController {
	
	@Autowired
	private FavouriteBookService favouritebookservice;
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	//< - - Save Method - - >
	@PostMapping("/book")
	public ResponseEntity<?> saveNewMovie(@RequestBody FavouriteBook favouritebook,
			HttpServletRequest request,HttpServletRequest response){
		System.out.println("Saving book");
		String authHeader=request.getHeader("authorization");
		String token=authHeader.substring(7);
		String userId=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		System.out.println("userId: "+userId);	
		try{
			favouritebook.setUserId(userId);
			favouritebook.setId(sequenceGeneratorService.generateSequence(FavouriteBook.SEQUENCE_NAME));
			favouritebookservice.saveFavouriteBook(favouritebook);
		}
		catch(FavouriteBookAlreadyExistsException e){
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
		}
		catch(Exception e){
			return new ResponseEntity<String>("{\"message\": \""+e.getMessage()+"\"}",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		return new ResponseEntity<FavouriteBook>(favouritebook,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/book/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable("id") int id){
		try{
			favouritebookservice.deleteFavouriteBookById(id);
		}
		catch(FavouriteBookNotFoundException e){
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		catch(Exception e){
			return new ResponseEntity<String>("{\"message\": \""+e.getMessage()+"\"}",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		return new ResponseEntity<String>("Book deleted successfully",HttpStatus.OK);
	}
	
	
	@GetMapping("/book/{id}")
	public ResponseEntity<?> getBook(@PathVariable("id") int id){
		FavouriteBook getfavouritebook;
		try{
			getfavouritebook=favouritebookservice.getFavouriteBookById(id);
		}
		catch(FavouriteBookNotFoundException e){
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		catch(Exception e){
			return new ResponseEntity<String>("{\"message\": \""+e.getMessage()+"\"}",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		return new ResponseEntity<FavouriteBook>(getfavouritebook, HttpStatus.OK);
	}
	
	
	@GetMapping("/books")
	public @ResponseBody ResponseEntity<List<FavouriteBook>> getMyBooks(ServletRequest req, ServletResponse res){
		HttpServletRequest request=(HttpServletRequest) req;
		String authHeader=request.getHeader("authorization");
		String token=authHeader.substring(7);
		String userId=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		return new ResponseEntity<List<FavouriteBook>>(favouritebookservice.getMyFavouriteBooks(userId),HttpStatus.OK);
	}
	
}
