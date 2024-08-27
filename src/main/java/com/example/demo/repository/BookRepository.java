package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Book;

//public class BookRepository {
//}
public interface BookRepository extends CrudRepository<Book, Integer> {

	
	
}
