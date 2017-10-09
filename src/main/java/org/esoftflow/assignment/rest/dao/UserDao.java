package org.esoftflow.assignment.rest.dao;

import java.util.List;


public interface UserDao {
	
	public List<UserEntity> getUsers(String orderByInsertionDate);

	public List<UserEntity> getRecentUsers(int numberOfDaysToLookBack);	
	
	public UserEntity getUserById(Long id);
	
	public UserEntity getUserByEmail(String email);	

	public void deleteUserById(Long id);

	public Long createUser(UserEntity user);

	public void updateUser(UserEntity user);

	public void deleteUsers();


}
