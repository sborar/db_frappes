package com.db.connector;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ConnectorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetConnection() {
		assertNotNull(Connector.getConnection());
	}

}
