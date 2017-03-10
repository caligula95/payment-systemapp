package com.webapp.service;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

import com.webapp.entity.Account;

public class TestAccountService {

	private static AccountService accountService = null;
	private static Account account = null;
	private static BigDecimal sum = null;

	@BeforeClass
	public static void setUp() {
		sum = new BigDecimal(34);
		accountService = mock(AccountService.class);
		account = new Account();
		account.setAccountId(1);
		account.setBalance(new BigDecimal(100));
		account.setIsBlocked(false);
		account.setUserId(1);
		account.setNumber("12345678900987654321");
		when(accountService.addFunds(account.getAccountId(), sum)).thenReturn(account.getAccountId());
		when(accountService.blockAccont(account.getAccountId())).thenReturn(account.getAccountId());
		when(accountService.unblockAccount(account.getAccountId())).thenReturn(account.getAccountId());
	}

	@Test
	public void testAddFunds() {
		Integer accountId = accountService.addFunds(account.getAccountId(), sum);
		assertNotNull(accountId);
		assertEquals(accountId, account.getAccountId());
	}
	
	@Test
	public void testBlockAccount(){
		Integer accountId = accountService.blockAccont(1);
		assertEquals(accountId, account.getAccountId());
	}
	
	@Test
	public void testUnblockAccount(){
		Integer accountId = accountService.unblockAccount(1);
		assertEquals(accountId, account.getAccountId());
	}
}
