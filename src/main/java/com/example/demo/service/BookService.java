package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

//public  class service {
//
//}
@Service
public class BookService {
	@Autowired
	private BookRepository repository;

	public List<Book> findallBooks() {
		return (List<Book>) repository.findAll();
	}

	public Book findBookById(int id) {
		Optional<Book> result = repository.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		return new Book();
	}

	public Book addBook(Book book) {
		return repository.save(book);
	}

	public Book updateBook(Book book) {
		Optional<Book> result = repository.findById(book.getId());
		Book existing = result.get();
		existing.setAuthor(book.getAuthor());
		existing.setName(book.getName());
		existing.setNoOfPages(book.getNoOfPages());
		existing.setPublication(book.getPublication());
		return repository.save(existing);
	}

	public void deleteById(int id) {
		repository.deleteById(id);
	}

}