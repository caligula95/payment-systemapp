package com.webapp.dao;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import com.webapp.entity.User;
import com.webapp.persistence.dao.UserDao;
import com.webapp.persistence.dao.impl.UserDaoImpl;

public class TestUserDao {

	private static UserDao userDao = null;
	private static User user1 = null;
	private static User user2 = null;

	@BeforeClass
	public static void setUp() {
		userDao = mock(UserDaoImpl.class);
		user1 = new User();
		user1.setUserId(1);
		user1.setSurname("Surname");
		user1.setEmail("email@gmail.com");
		user1.setPhone("1234567890");
		user1.setPassword("password");
		
		user2 = new User();
		user2.setUserId(2);
		user2.setSurname("Surname2");
		user2.setPhone("1111111111");
		when(userDao.findAll()).thenReturn(Arrays.asList(user1, user2));
		when(userDao.findByPhone("1234567890")).thenReturn(user1);
		when(userDao.findById(2)).thenReturn(user2);
		when(userDao.findByLoginAndPassword("1234567890", "password")).thenReturn(user1);
		when(userDao.create(user1)).thenReturn(user1.getUserId());
	}
	
	@Test
	public void testFindAllUsers(){
		List<User> allUsers = userDao.findAll();
		assertEquals(2, allUsers.size());
		assertEquals("1111111111", allUsers.get(1).getPhone());
		assertEquals("email@gmail.com", allUsers.get(0).getEmail());
	}
	
	@Test
	public void testFindByPhone(){
		User user = userDao.findByPhone("1234567890");
		assertEquals("Surname", user.getSurname());
	}
	
	@Test
	public void testFindById(){
		User user = userDao.findById(2);
		String phone = user2.getPhone();
		assertEquals(phone, user.getPhone());
		assertNotNull(user);
	}
	
	@Test
	public void testFindByLoginAndPassword(){
		User user = userDao.findByLoginAndPassword("1234567890", "password");
		User user3 = userDao.findByLoginAndPassword("1234567890", "pas");
		assertNull(user3);
		String surname = user1.getSurname();
		assertEquals(surname, user.getSurname());
	}
	
	@Test
	public void testCreateUser(){
		Integer userId = userDao.create(user1);
		Integer id = user1.getUserId();
		assertNotNull(userId);
		assertEquals(id, userId);
	}
}
