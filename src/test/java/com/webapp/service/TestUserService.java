package com.webapp.service;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;
import com.webapp.entity.Role;
import com.webapp.entity.User;

public class TestUserService {

	private static UserService userService = null;
	private static User user = null;
	private static Role role = null;
	private static String userPassword = null;

	@BeforeClass
	public static void setUp() {
		userService = mock(UserService.class);
		user = new User();
		user.setEmail("email@gmail.com");
		user.setName("Name");
		user.setSurname("Surname");
		user.setPhone("0987654321");
		user.setUserId(1);
		role = new Role();
		role.setId(2);
		role.setRolename("admin");
		user.setRole(role);

		when(userService.checkRole(user)).thenReturn("admin");
		when(userService.loginUser("0987654321", userPassword)).thenReturn(user);
		when(userService.registerUser("email@gmail.com", userPassword, "0987654321")).thenReturn(user.getUserId());
	}

	@Test
	public void testCheckRole() {
		String role = userService.checkRole(user);
		assertNotNull(role);
		assertEquals("admin", role);
	}
	
	@Test
	public void testRegisterUser(){
		Integer userId = userService.registerUser("email@gmail.com", userPassword, "0987654321");
		assertNotNull(userId);
	}

	@Test
	public void testLoginUser() {
		User user1 = userService.loginUser(user.getPhone(), userPassword);
		assertNotNull(user1);
	}

}
