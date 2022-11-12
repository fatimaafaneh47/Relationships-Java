package com.codingdojo.logandreg.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.codingdojo.logandreg.models.Book;
import com.codingdojo.logandreg.models.User;
import com.codingdojo.logandreg.repositories.BookRepository;

@Service
public class BookService {
	  	private final BookRepository bookRepository;
	    
	    public BookService(BookRepository bookRepository) {
	        this.bookRepository = bookRepository;
	    }
	    
	    public List<Book> allBooks() {
	        return bookRepository.findAll();
	    }
	 
	    public Book createBook(Book b) {
	        return bookRepository.save(b);
	    }
	
	    public Book findBook(Long id) {
	        Optional<Book> optionalBook = bookRepository.findById(id);
	        if(optionalBook.isPresent()) {
	            return optionalBook.get();
	        } else {
	            return null;
	        }
	    }
	    public Book updateBook(Book book) {
	    	return bookRepository.save(book);
	    }
	    public void deleteBook(Long id) {
			Optional<Book> optionalBook = bookRepository.findById(id);
			if(optionalBook.isPresent()) {
				bookRepository.deleteById(id);
			}
	    }
	    public List<Book> unborrowedBooks(User user){
			return bookRepository.findByBorrowerIdIsOrUserId(null, user.getId());
		}
		
		public List<Book> borrowedBooks(User user){
			return bookRepository.findByBorrowerId(user.getId());
		}
		public void removeBorrower(Book book) {
			book.setBorrower(null);
			bookRepository.save(book);
		}
		
		public void addBorrower(Book book, User user) {
			book.setBorrower(user);
			bookRepository.save(book);
		}
	
			
	    
}
