package com.db.app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LoginTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIsValidUser() {
		assertFalse(!Login.isValidUser("alison", "gradprog2016@07"));
	}

}
