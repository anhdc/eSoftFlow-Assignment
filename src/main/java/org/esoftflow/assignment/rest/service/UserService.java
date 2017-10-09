package org.esoftflow.assignment.rest.service;

import java.util.List;

import org.esoftflow.assignment.rest.errorhandling.AppException;
import org.esoftflow.assignment.rest.errorhandling.CustomReasonPhraseException;
import org.esoftflow.assignment.rest.resource.User;

public interface UserService {
	
	
	public Long createUser(User user) throws AppException;
	public void createUsers(List<User> users) throws AppException;

		
	public List<User> getUsers(String orderByInsertionDate, Integer numberDaysToLookBack) throws AppException;	
	
	public User getUserById(Long id) throws AppException;
	
	public void updateUser(User user) throws AppException;
	
	public void deleteUserById(Long id);	
	
	public void deleteUsers();
	
	public User verifyUserExistenceById(Long id);
	
	public void generateCustomReasonPhraseException() throws CustomReasonPhraseException;

}
