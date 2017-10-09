package org.esoftflow.assignment.rest.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

public class UserDaoJPA2Impl implements UserDao {

	@PersistenceContext(unitName="demoRestPersistence")
	private EntityManager entityManager;

	
	public List<UserEntity> getUsers(String orderByInsertionDate) {
		String sqlString = null;
		if(orderByInsertionDate != null){
			sqlString = "SELECT p FROM UserEntity p" + " ORDER BY p.insertionDate " + orderByInsertionDate;
		} else {
			sqlString = "SELECT p FROM UserEntity p";
		}		 
		TypedQuery<UserEntity> query = entityManager.createQuery(sqlString, UserEntity.class);		

		return query.getResultList();
	}

	public List<UserEntity> getRecentUsers(int numberOfDaysToLookBack) {
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -numberOfDaysToLookBack);//substract the number of days to look back 
		Date dateToLookBackAfter = calendar.getTime();
		
		String qlString = "SELECT p FROM UserEntity p where p.insertionDate > :dateToLookBackAfter ORDER BY p.insertionDate DESC";
		TypedQuery<UserEntity> query = entityManager.createQuery(qlString, UserEntity.class);		
		query.setParameter("dateToLookBackAfter", dateToLookBackAfter, TemporalType.DATE);

		return query.getResultList();
	}
	
	public UserEntity getUserById(Long id) {
		
		try {
			String qlString = "SELECT p FROM UserEntity p WHERE p.id = ?1";
			TypedQuery<UserEntity> query = entityManager.createQuery(qlString, UserEntity.class);		
			query.setParameter(1, id);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public UserEntity getUserByEmail(String email) {
		
		try {
			String qlString = "SELECT p FROM UserEntity p WHERE p.email = ?1";
			TypedQuery<UserEntity> query = entityManager.createQuery(qlString, UserEntity.class);		
			query.setParameter(1, email);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	

	public void deleteUserById(Long id) {
		
		UserEntity user = entityManager.find(UserEntity.class, id);
		entityManager.remove(user);
		
	}

	public Long createUser(UserEntity user) {
		
		user.setInsertionDate(new Date());
		entityManager.merge(user);
		entityManager.flush();
		
		return user.getId();
	}

	public void updateUser(UserEntity user) {
		user.setInsertionDate(new Date());
		entityManager.merge(user);		
	}
	
	public void deleteUsers() {
		Query query = entityManager.createNativeQuery("TRUNCATE TABLE users");		
		query.executeUpdate();
	}



}
