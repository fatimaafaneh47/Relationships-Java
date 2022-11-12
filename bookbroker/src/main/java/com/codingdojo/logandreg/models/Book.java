package com.codingdojo.logandreg.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="books")
public class Book {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @NotEmpty(message="Title must not be blank")
	    @Size(min = 5, max = 200)
	    private String title;
	    @NotEmpty(message="thoughts is required!")
	    @Size(min = 5, max = 400)
	    private String thoughts;
	    @NotEmpty(message="Author must not be blank")
	    @Size(min = 3, max = 40)
	    private String authorname;
	    @Column(updatable=false)
		private Date createdAt;
		private Date updatedAt;
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="user_id")
		private User user;
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="borrower_id")
		private User borrower;
		
		public Book() {
	    }
	    public Book(String title,String thoughts,String authorname, User user) {
			this.title = title;
			this.thoughts = thoughts;
			this.authorname = authorname;
			this.user = user;
		}

		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
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

		public String getThoughts() {
			return thoughts;
		}

		public void setThoughts(String thoughts) {
			this.thoughts = thoughts;
		}

		public String getAuthorname() {
			return authorname;
		}

		public void setAuthorname(String authorname) {
			this.authorname = authorname;
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
		@PrePersist
		    protected void onCreate(){
		        this.createdAt = new Date();
		}
		@PreUpdate
		    protected void onUpdate(){
		        this.updatedAt = new Date();
		 }
		public User getBorrower() {
			return borrower;
		}
		public void setBorrower(User borrower) {
			this.borrower = borrower;
		}
	    
}
