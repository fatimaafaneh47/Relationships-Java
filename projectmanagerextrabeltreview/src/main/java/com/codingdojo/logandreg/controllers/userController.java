package com.codingdojo.logandreg.controllers;

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
import com.codingdojo.logandreg.models.LoginUser;
import com.codingdojo.logandreg.models.Project;
import com.codingdojo.logandreg.models.User;
import com.codingdojo.logandreg.services.ProjectService;
import com.codingdojo.logandreg.services.UserService;

@Controller
public class userController {
	
	@Autowired
    private UserService userServ;
	
	@Autowired
    private ProjectService projectServ;
   
   @GetMapping("/")
   public String index(Model model, HttpSession session) {
   	
   	if (session.getAttribute("userId")!= null){
   		return "redirect:/dashboard";
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
       return "redirect:/dashboard";
     
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
       return "redirect:/dashboard";
   }
   
   @GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
	 
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userServ.findUserById(userId);
		model.addAttribute("user", user);
		
		model.addAttribute("unassignedProjects", projectServ.getUnassignedUsers(user));
		model.addAttribute("assignedProjects", projectServ.getAssignedUsers(user));
		model.addAttribute("createdProjects", projectServ.getLeadProjects(user));
		 
		return "success.jsp";
	}
   
   @GetMapping ("/logout")
   public String logout(HttpSession session){
   	session.invalidate();
   	return "redirect:/";		
   }
   @GetMapping ("/projects/new")
   public String createformpage(@ModelAttribute("projects")Project project ,Model model, HttpSession session){
  		Long userId = (Long) session.getAttribute("userId");
  		User user = userServ.findUserById(userId);
  		model.addAttribute("user", user);
   		return "create.jsp";		
   }
   @PostMapping("/projects/new")
  	public String create(@Valid @ModelAttribute("projects") Project project, BindingResult result, Model model) {
  		if(result.hasErrors()) {
  			return "create.jsp";
  		}else {
  			projectServ.createProject(project);
  			return "redirect:/dashboard";
  		}
  	}
   @GetMapping("/projects/edit/{id}")
  	public String editpage(@PathVariable("id") Long id, Model model) {	
  		Project project = projectServ.findProject(id);	
  		model.addAttribute("project", project);
  		return "edit.jsp";
  	}
   @PutMapping("/projects/edit/{id}")
  	public String update(@Valid @ModelAttribute("project") Project project, BindingResult result) {
  		if(result.hasErrors()) {
  			return "edit.jsp";
  		}else {
  			projectServ.updateProject(project);
  			return "redirect:/dashboard";
  		}
     }
   @GetMapping("/projects/{id}")
  	public String show(@PathVariable(value="id") Long id ,Model model, HttpSession session ) {
  		Project project = projectServ.findProject(id);
  		Long userId = (Long) session.getAttribute("userId");
     		User user = userServ.findUserById(userId);
     		model.addAttribute("user", user);	
  		model.addAttribute("project", project);
  		return "show.jsp";
  		}
   @DeleteMapping("/projects/{id}/delete")
   public String delete(@PathVariable("id") Long id) {
	   projectServ.deleteProject(id);
       return "redirect:/dashboard";
   }
   @RequestMapping("/dashboard/join/{id}")
	public String joinTeam(@PathVariable("id") Long id, HttpSession session) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		
		Project project = projectServ.findProject(id);
		User user = userServ.findUserById(userId);
		
		user.getProjects().add(project);
		userServ.updateUser(user);

		
		return "redirect:/dashboard";
	}
   @RequestMapping("/dashboard/leave/{id}")
	public String leaveTeam(@PathVariable("id") Long id, HttpSession session, Model model) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		
		Project project = projectServ.findProject(id);
		User user = userServ.findUserById(userId);
		
		user.getProjects().remove(project);
		userServ.updateUser(user);
		return "redirect:/dashboard";
	}


  
}
