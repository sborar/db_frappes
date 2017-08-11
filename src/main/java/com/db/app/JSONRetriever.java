package com.db.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.db.connector.Connector;
import com.db.dto.Deal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONRetriever {

	public static String getDealJSON() {
		String jsonString = null;
		ArrayList<Deal> dealist = new ArrayList<>();
		try(Connection conn = Connector.getConnection()){
			if(conn != null){
				String sql = "SELECT * FROM deal;";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Deal deal = new Deal();
					deal.setDealId(rs.getInt(1));
					deal.setDealTime(rs.getString(2));
					deal.setDealCounterpartyId(rs.getInt(3));
					deal.setDealInstrumentId(rs.getInt(4));
					deal.setDealType(rs.getString(5).charAt(0));
					deal.setDealAmount(rs.getDouble(6));
					deal.setDealQuantity(rs.getInt(7));
					dealist.add(deal);
				}
				ObjectMapper mapper = new ObjectMapper();
				jsonString = mapper.writeValueAsString(dealist);
			}
			else{
				throw new Exception("Connection not established!!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}
	
	public static String getConuterpartyIdJSON(int counterpartyId) {
		String jsonString = null;
		ArrayList<Deal> dealist = new ArrayList<>();
		try(Connection conn = Connector.getConnection()){
			if(conn != null){
				String sql = "SELECT * FROM deal where deal_counterparty_id = ? ;";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, counterpartyId);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Deal deal = new Deal();
					deal.setDealId(rs.getInt(1));
					deal.setDealTime(rs.getString(2));
					deal.setDealCounterpartyId(rs.getInt(3));
					deal.setDealInstrumentId(rs.getInt(4));
					deal.setDealType(rs.getString(5).charAt(0));
					deal.setDealAmount(rs.getDouble(6));
					deal.setDealQuantity(rs.getInt(7));
					dealist.add(deal);
				}
				ObjectMapper mapper = new ObjectMapper();
				jsonString = mapper.writeValueAsString(dealist);
			}
			else{
				throw new Exception("Connection not established!!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}
	
}
