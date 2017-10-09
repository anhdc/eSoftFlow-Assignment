package org.esoftflow.assignment.rest.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.beanutils.BeanUtils;
import org.esoftflow.assignment.rest.resource.User;


@Entity
@Table(name="users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -8039686696076337053L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	
	@Column(name="username")
	private String username;
		
	
	@Column(name="email")
	private String email;
	
	
	@Column(name="address")
	private String address;
	
	
	/** insertion date in the database */
	@Column(name="insertion_date")
	private Date insertionDate;

	public UserEntity(){}
	
	public UserEntity(String name, String email, String address) {
		
		this.username = name;
		this.email = email;
		this.address = address;
		
	}
	
	public UserEntity(User user){
		try {
			BeanUtils.copyProperties(this, user);
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getInsertionDate() {
		return insertionDate;
	}

	public void setInsertionDate(Date insertionDate) {
		this.insertionDate = insertionDate;
	}	
	
		
}
