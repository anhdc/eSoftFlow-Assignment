package org.esoftflow.assignment.rest.resource;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.beanutils.BeanUtils;
import org.esoftflow.assignment.rest.dao.UserEntity;
import org.esoftflow.assignment.rest.helpers.DateISO8601Adapter;

@SuppressWarnings("restriction")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {

	private static final long serialVersionUID = -8039686696076337053L;

	
	@XmlElement(name = "id")	
	private Long id;
	
	
	@XmlElement(name = "username")	
	private String username;
		
	
	@XmlElement(name = "email")	
	private String email;
	
	
	@XmlElement(name = "address")		
	private String address; 
		
	@XmlElement(name = "insertionDate")
	@XmlJavaTypeAdapter(DateISO8601Adapter.class)		
	private Date insertionDate;

	public User(UserEntity userEntity){
		try {
			BeanUtils.copyProperties(this, userEntity);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public User(String username, String email, String address) {
		
		this.username = username;		
		this.email = email;
		this.address = address;
		
	}
	
	public User(){}

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
