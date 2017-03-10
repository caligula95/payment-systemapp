package com.webapp.dao;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


import static org.junit.Assert.*;

import com.webapp.entity.Account;
import com.webapp.persistence.dao.AccountDao;
import com.webapp.persistence.dao.impl.AccountDaoImpl;

public class TestAccountDao {

	private static AccountDao accountDao = null;
	private static Account account = null;
	
	@BeforeClass
	public static void setUp(){
		accountDao = mock(AccountDaoImpl.class);
		account = new Account();
		account.setAccountId(1);
		account.setBalance(new BigDecimal(123));
		account.setUserId(1);
		account.setNumber("1234567890123456");
		
		when(accountDao.create(account)).thenReturn(account.getAccountId());
		when(accountDao.findAccountById(account.getAccountId())).thenReturn(account);
		when(accountDao.findAllAccountsOfUser(account.getUserId())).thenReturn(Arrays.asList(account));
		when(accountDao.update(account)).thenReturn(account.getAccountId());
	}
	
	@Test
	public void testCreate(){
		Integer accountId = accountDao.create(account);
		assertNotNull(accountId);
		assertEquals(new Integer(1), accountId);
	}
	
	@Test
	public void testFindAccountById(){
		Account account1 = accountDao.findAccountById(account.getAccountId());
		Account account2 = accountDao.findAccountById(3);
		assertNull(account2);
		assertEquals(new BigDecimal(123), account1.getBalance());
	}
	
	@Test
	public void testFindAllAccountsOfUser(){
		List<Account> accounts = accountDao.findAllAccountsOfUser(1);
		assertEquals(1, accounts.size());
		assertEquals("1234567890123456", accounts.get(0).getNumber());
	}
	
	@Test
	public void testUpdate(){
		Integer accountId = accountDao.update(account);
		assertNotNull(accountId);
		assertEquals(new Integer(1), accountId);
	}
}
