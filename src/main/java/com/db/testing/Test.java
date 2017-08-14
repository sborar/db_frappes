package com.db.testing;

import com.db.app.JSONRetriever;
import com.db.dto.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Test {

	public static void main(String[] args) throws Exception {
		/*try (Connection con = Connector.getConnection()) {
			if (con != null) {
				System.out.println("Connection is established!!");
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from counterparty;");
				while (rs.next())
					System.out.println(
							rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getDate(4));
			} else
				System.out.println("connection failed!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
*/
		//System.out.println(Login.isValidUser("alison", "gradprog2016@07"));
		/*Users users = new Users();
		users.setUserId("Raman");
		users.setUserPwd("tuyioup");
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(users);*/
		//String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(users);
		System.out.println(JSONRetriever.getConuterpartyIdJSON(703));
	}
	
}
