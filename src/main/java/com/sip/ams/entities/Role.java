package com.sip.ams.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "role")
public class Role {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "role_id")
private  int id;
@Column(name = "role")
//@NotEmpty(message = "*Please provide a role")
private String role;


	public Role() {
	super();
	// TODO Auto-generated constructor stub
}
	
	
	public Role(String role) {
		super();
		this.role = role;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}

