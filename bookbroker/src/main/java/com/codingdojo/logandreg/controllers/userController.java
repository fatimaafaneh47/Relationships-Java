package com.codingdojo.logandreg.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.logandreg.models.Book;
import com.codingdojo.logandreg.models.LoginUser;
import com.codingdojo.logandreg.models.User;
import com.codingdojo.logandreg.services.BookService;
import com.codingdojo.logandreg.services.UserService;

@Controller
public class userController {
	
	@Autowired
    private UserService userServ;
	
	@Autowired
    private BookService bookServ;
   
   @GetMapping("/")
   public String index(Model model, HttpSession session) {
   	
   	if (session.getAttribute("userId")!= null){
   		return "redirect:/bookmarket";
   	}
   	
       model.addAttribute("newUser", new User());
       model.addAttribute("newLogin", new LoginUser());
       return "login.jsp";
   }
   
   @PostMapping("/register")
   public String register(@Valid @ModelAttribute("newUser") User newUser, 
           BindingResult result, Model model, HttpSession session) {
       
   	User registeredUser = userServ.register(newUser, result);
       
       if(result.hasErrors()) {
           
           model.addAttribute("newLogin", new LoginUser());
           return "login.jsp";
       }
       session.setAttribute("userId", registeredUser.getId());
       return "redirect:/bookmarket";
     
   }
   
   @PostMapping("/login")
   public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
           BindingResult result, Model model, HttpSession session) {
       
   	
        User user = userServ.login(newLogin, result);
   
       if(result.hasErrors()) {
           model.addAttribute("newUser", new User());
           return "login.jsp";
       }
       
       session.setAttribute("userId", user.getId());
       return "redirect:/bookmarket";
   }
   
   @GetMapping ("/bookmarket")
   public String home(Model model, HttpSession session){
   	if (session.getAttribute("userId")!= null){
   		Long userId = (Long) session.getAttribute("userId");
   		User currentUser = userServ.findUserById(userId);
   		model.addAttribute("currentUser", currentUser);	
   		List<Book> books = bookServ.unborrowedBooks(userServ.findUserById(userId));
   		List<Book> myBooks = bookServ.borrowedBooks(userServ.findUserById(userId));
		model.addAttribute("myBooks", myBooks);
        model.addAttribute("books", books);
   		return "success.jsp";
   	}
   	return "redirect:/";
   	  		
   }
   
   @GetMapping ("/logout")
   public String logout(HttpSession session){
   	session.invalidate();
   	return "redirect:/";		
   }
   @GetMapping ("/books/new")
   public String logout(@ModelAttribute("books")Book book ,Model model, HttpSession session){
  		Long userId = (Long) session.getAttribute("userId");
  		User user = userServ.findUserById(userId);
  		model.addAttribute("user", user);
   		return "form.jsp";		
   }
   @PostMapping("/books/new")
	public String create(@Valid @ModelAttribute("books") Book book, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "form.jsp";
		}else {
			bookServ.createBook(book);
			return "redirect:/bookmarket";
		}
	}
   @GetMapping("/books/{id}")
	public String show(@PathVariable(value="id") Long id ,Model model, HttpSession session ) {
		Book book = bookServ.findBook(id);
		Long userId = (Long) session.getAttribute("userId");
   		User user = userServ.findUserById(userId);
   		model.addAttribute("user", user);	
		model.addAttribute("book", book);
		return "show.jsp";
		}
   @DeleteMapping("/books/{id}")
   public String delete(@PathVariable("id") Long id) {
	   bookServ.deleteBook(id);
       return "redirect:/bookmarket";
   }
   @GetMapping("/books/{id}/edit")
	public String editpage(@PathVariable("id") Long id, Model model) {	
		Book book = bookServ.findBook(id);	
		model.addAttribute("book", book);
		return "edit.jsp";
	}
   @PutMapping("/books/{id}/edit")
	public String update(@Valid @ModelAttribute("book") Book book, BindingResult result) {
		if(result.hasErrors()) {
			return "edit.jsp";
		}else {
			System.out.println(book);
			bookServ.updateBook(book);
			return "redirect:/bookmarket";
		}
   }
   @RequestMapping("/books/{id}/delete")
   public String deleteMybook(@PathVariable("id") Long id) {
	   bookServ.deleteBook(id);
       return "redirect:/bookmarket";
   }
   @RequestMapping("/books/{id}/borrow")
	public String borrowBook(@PathVariable("id") Long bookID, HttpSession session) {
	 
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}
		bookServ.addBorrower(bookServ.findBook(bookID), userServ.findUserById(userId));
		 
		return "redirect:/bookmarket";
	}
	
	@RequestMapping("/books/return/{id}")
	public String returnBook(@PathVariable("id") Long id, HttpSession session) {
	 
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		bookServ.removeBorrower(bookServ.findBook(id));
		return "redirect:/bookmarket"; 
}
}
   
   
   

