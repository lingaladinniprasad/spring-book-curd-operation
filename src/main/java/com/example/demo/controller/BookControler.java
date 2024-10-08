package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;

@Controller
public class BookControler {
	@Autowired
    private	BookService service;

	@GetMapping("/")
	public String findAll(Model model) {
		model.addAttribute("books", service.findallBooks());
		return "all-books";
	}
	@GetMapping("/add")
	@PreAuthorize("hasRole('USER')")  
	public String lunchAddBookPage(Model model) {
		model.addAttribute("book", new Book());
		return "add-book";
	}
	


	@PostMapping("/addbook")
    // @PreAuthorize("hasRole('ADMIN')")
	public String createBook(@RequestParam Book book) {
		service.addBook(book);
		return "redirect:/";

	}

	@GetMapping("/edit/{id}")
	 @PreAuthorize("hasRole('ADMIN')")
	public String lunchEditPage(Model model, @PathVariable("id") int id) {
		model.addAttribute("book", service.findBookById(id));
		return "edit-book";

	}

	@PostMapping("/updatebook")
	public String upadteBook(Book book) {
		service.updateBook(book);
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		service.deleteById(id);
		return "redirect:/";
	}

	@GetMapping("/test")
	public String test() {
		Book st = new Book();
		st.setId(1);
		st.setAuthor("Rajanikanta");
		st.setName("Java");
		st.setNoOfPages(299);
		st.setPublication("Ptradhan");
		service.addBook(st);
		return "index";

	}

}