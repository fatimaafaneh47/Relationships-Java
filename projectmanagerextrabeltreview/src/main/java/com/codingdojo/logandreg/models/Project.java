package com.codingdojo.logandreg.models;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="projects")
public class Project {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@NotEmpty(message="Title must not be blank")
	@Size(min = 5, max = 200)
	private String title;
	@NotEmpty(message="Description must not be blank")
	@Size(min = 5, max = 200)
	private String description;
	@Future
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
	private String ticket;
	private Date createdAt;
	private Date updatedAt;
	 
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="teamLead_id")
    private User lead;
    
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "users_projects",
			joinColumns = @JoinColumn(name = "project_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<User> users;
	
	
	public Project() {
	}
	
	 public Project(String title, Date dueDate, String description, User lead) {
	    	this.title = title;
	    	this.date = dueDate;
	    	this.description = description;
	    	this.lead = lead;
	    }

	public User getLead() {
		return lead;
	}

	public void setLead(User lead) {
		this.lead = lead;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	

}
