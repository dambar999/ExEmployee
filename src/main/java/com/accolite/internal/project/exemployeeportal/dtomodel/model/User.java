package com.accolite.internal.project.exemployeeportal.dtomodel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="user", schema = "public")
public class User {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false)
	    private String name;

	    @Email
	    @Column(nullable = false)
	    private String email;
	    
	    @Column
	    private String primary_contact_num;

	    private String imageUrl;

	    @Column(nullable = false)
	    private Boolean emailVerified = false;

	    @JsonIgnore
	    private String hashed_password;

	    @NotNull
	    @Enumerated(EnumType.STRING)
	    private AuthProvider provider;

	    private String salt;

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getImageUrl() {
	        return imageUrl;
	    }

	    public void setImageUrl(String imageUrl) {
	        this.imageUrl = imageUrl;
	    }

	    public Boolean getEmailVerified() {
	        return emailVerified;
	    }

	    public void setEmailVerified(Boolean emailVerified) {
	        this.emailVerified = emailVerified;
	    }

	    public AuthProvider getProvider() {
	        return provider;
	    }

	    public void setProvider(AuthProvider provider) {
	        this.provider = provider;
	    }

		public String getPrimary_contact_num() {
			return primary_contact_num;
		}

		public void setPrimary_contact_num(String primary_contact_num) {
			this.primary_contact_num = primary_contact_num;
		}

		public String getHashed_password() {
			return hashed_password;
		}

		public void setHashed_password(String hashed_password) {
			this.hashed_password = hashed_password;
		}

		public String getSalt() {
			return salt;
		}

		public void setSalt(String salt) {
			this.salt = salt;
		}

}
