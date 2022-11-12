package com.codingdojo.logandreg.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.logandreg.models.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
	List<Book> findAll();
	List<Book> findBythoughtsContaining(String search);
	Long countByTitleContaining(String search);
	Long deleteByTitleStartingWith(String search);
	List<Book> findByBorrowerId(Long userId);
	List<Book> findByBorrowerIdIsOrUserId(Long borrowerID, Long userId);
	}


