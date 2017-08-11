package com.db.dto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AnonymousUsersTest {
	AnonymousUsers anonymoususers = new AnonymousUsers();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetAnonymousUserId() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetAnonymousUserId() {
		anonymoususers.setAnonymousUserId("testUID");
		assertEquals("testUID", anonymoususers.getAnonymousUserId());
	}

	@Test
	public void testGetAnonymousUserPwd() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetAnonymousUserPwd() {
		anonymoususers.setAnonymousUserPwd("testPWD");
		assertEquals("testPWD", anonymoususers.getAnonymousUserPwd());
	}

}
