package org.esoftflow.assignment.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.esoftflow.assignment.rest.dao.UserDao;
import org.esoftflow.assignment.rest.dao.UserEntity;
import org.esoftflow.assignment.rest.errorhandling.AppException;
import org.esoftflow.assignment.rest.errorhandling.CustomReasonPhraseException;
import org.esoftflow.assignment.rest.filters.AppConstants;
import org.esoftflow.assignment.rest.resource.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserServiceDbAccessImpl implements UserService {

	@Autowired
	UserDao userDao;
		
	/********************* Create related methods implementation ***********************/
	@Transactional("transactionManager")
	public Long createUser(User user) throws AppException {
		
		validateInputForCreation(user);
		
		//verify existence of resource in the db (email must be unique)
		UserEntity userByEmail = userDao.getUserByEmail(user.getEmail());
		if(userByEmail != null){
			throw new AppException(Response.Status.CONFLICT.getStatusCode(), 409, "User with same email already existing in the database with the id " + userByEmail.getId(),
					"Please verify email properly", AppConstants.BLOG_POST_URL);
		}
		
		return userDao.createUser(new UserEntity(user));
	}

	private void validateInputForCreation(User user) throws AppException {
		if(user.getEmail() == null){
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "Provided data not sufficient for insertion",
					"Please verify that the email is properly set", AppConstants.BLOG_POST_URL);
		}
		if(user.getUsername() == null){
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "Provided data not sufficient for insertion",
					"Please verify that the username is properly set", AppConstants.BLOG_POST_URL);
		}
		
	}
	
	@Transactional("transactionManager")
	public void createUsers(List<User> users) throws AppException {
		for (User user : users) {
			createUser(user);
		}		
	}	
	
	
	 // ******************** Read related methods implementation **********************		
	public List<User> getUsers(String orderByInsertionDate, Integer numberDaysToLookBack) throws AppException {
		
		
		if(numberDaysToLookBack!=null){
			List<UserEntity> recentUsers = userDao.getRecentUsers(numberDaysToLookBack);			
			return getUsersFromEntities(recentUsers);			
		}
		
		if(isOrderByInsertionDateParameterValid(orderByInsertionDate)){
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "Please set either ASC or DESC for the orderByInsertionDate parameter", null , null);
		}			
		List<UserEntity> users = userDao.getUsers(orderByInsertionDate);
		
		return getUsersFromEntities(users);
	}

	private boolean isOrderByInsertionDateParameterValid(
			String orderByInsertionDate) {
		return orderByInsertionDate!=null 
				&& !("ASC".equalsIgnoreCase(orderByInsertionDate) || "DESC".equalsIgnoreCase(orderByInsertionDate));
	}
	
	public User getUserById(Long id) throws AppException {		
		UserEntity userById = userDao.getUserById(id);
		if(userById == null){
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(), 
					404, 
					"The user you requested with id " + id + " was not found in the database",
					"Verify the existence of the user with the id " + id + " in the database",
					null);			
		}
		
		return new User(userDao.getUserById(id));
	}	

	private List<User> getUsersFromEntities(List<UserEntity> userEntities) {
		List<User> response = new ArrayList<User>();
		for(UserEntity userEntity : userEntities){
			response.add(new User(userEntity));					
		}
		
		return response;
	}

	public List<User> getRecentUsers(int numberOfDaysToLookBack) {
		List<UserEntity> recentUsers = userDao.getRecentUsers(numberOfDaysToLookBack);
		
		return getUsersFromEntities(recentUsers);
	}	
	
	
	/********************* UPDATE-related methods implementation ***********************/	
	@Transactional("transactionManager")
	public void updateUser(User user) throws AppException {
				
		User verifyUserExistenceById = verifyUserExistenceById(user.getId());
		if(verifyUserExistenceById == null){
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(), 
					404, 
					"The resource you are trying to update does not exist in the database",
					"Please verify existence of data in the database for the id - " + user.getId(),
					null);				
		}
				
		userDao.updateUser(new UserEntity(user));
	}
	
	
	/********************* DELETE-related methods implementation ***********************/
	@Transactional("transactionManager")
	public void deleteUserById(Long id) {
		userDao.deleteUserById(id);
	}
	
	@Transactional("transactionManager")	
	public void deleteUsers() {
		userDao.deleteUsers();		
	}

	public User verifyUserExistenceById(Long id) {
		UserEntity userById = userDao.getUserById(id);
		if(userById == null){
			return null;
		} else {
			return new User(userById);			
		}
	}
	

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void generateCustomReasonPhraseException() throws CustomReasonPhraseException {
		// TODO Auto-generated method stub
		
	}

	
		
}
