package com.db.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.connector.Connector;

public class Login {

	public static boolean isValidUser(String name,String password) {
		boolean isValidUser = false;
		try(Connection con = Connector.getConnection()){
			if(con != null){
				String sql = " SELECT EXISTS ( SELECT * FROM users WHERE user_id = ? AND user_pwd = ?);" ;
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1,name);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery() ;
				while(rs.next()){
					if(rs.getInt(1) == 1)
						isValidUser = true;
					else
						isValidUser = false;
				}
			}
			else{
				throw new Exception("Connection not established!!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isValidUser;
	}
	
}
