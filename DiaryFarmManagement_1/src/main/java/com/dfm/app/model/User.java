package com.dfm.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints={@UniqueConstraint(columnNames = {"email"})})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String email;
	private String username;
	private String password;
	private String usertype;
	private String firstname;
	private String lastname;
	private String mobileNumber;
	private String doorNo;
	private String street;
	private String city;
	private String zipCode;
	private String securityQuestion;
	private String securityAnswer;
	public String getUsertype() {
		return usertype;
	}
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}
	

}
