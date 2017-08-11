package com.db.app;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.db.connector.Connector;
import com.db.dto.Deal;
import com.db.dto.DealCounterInt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONRetriever {

	public static String getDealJSON() {
		String jsonString = null;
		ArrayList<Deal> dealist = new ArrayList<>();
		try (Connection conn = Connector.getConnection()) {
			if (conn != null) {
				String sql = "SELECT * FROM deal;";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
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
			} else {
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

	public static boolean getConuterpartyIdJSON(int counterpartyId) {
		boolean file_test = false;
		String filename = "deal_"+counterpartyId+".json";
		ArrayList<DealCounterInt> dealist_buy = new ArrayList<>();
		ArrayList<DealCounterInt> dealist_sell = new ArrayList<>();
		ArrayList<DealCounterInt> dealist_value = new ArrayList<>();
		try(Connection conn = Connector.getConnection()){
			if(conn != null){
			String sql_buy = "SELECT sum(deal_amount * deal_quantity), deal_instrument_id"+ 
			" FROM deal where deal_counterparty_id = ?"+
			" and deal_type = 'B' group by deal_instrument_id;";
				PreparedStatement ps_buy = conn.prepareStatement(sql_buy);
				ps_buy.setInt(1, counterpartyId);
				ResultSet rs_buy = ps_buy.executeQuery();
				while(rs_buy.next()){
					DealCounterInt counterIntbuy = new DealCounterInt();
					counterIntbuy.setDealValue(rs_buy.getDouble(1));
					counterIntbuy.setDealInstrumentId(rs_buy.getInt(2));
					dealist_buy.add(counterIntbuy);
				}
				
				String sql_sell = "SELECT sum(deal_amount * deal_quantity), deal_instrument_id"+ 
						" FROM deal where deal_counterparty_id = ?"+
						" and deal_type = 'S' group by deal_instrument_id;";
							PreparedStatement ps_sell = conn.prepareStatement(sql_sell);
							ps_sell.setInt(1, counterpartyId);
							ResultSet rs_sell = ps_sell.executeQuery();
							while(rs_sell.next()){
								DealCounterInt counterIntsell = new DealCounterInt();
								counterIntsell.setDealValue(rs_sell.getDouble(1));
								counterIntsell.setDealInstrumentId(rs_sell.getInt(2));
								dealist_sell.add(counterIntsell);
							}
							
				for(DealCounterInt dcib : dealist_buy){
					for(DealCounterInt dcis : dealist_sell){
						int inst_id = dcib.getDealInstrumentId();
						if(dcis.getDealInstrumentId() == inst_id ){
							DealCounterInt counterIntvalue = new DealCounterInt();
							counterIntvalue.setDealInstrumentId(inst_id);
							counterIntvalue.setDealValue(dcib.getDealValue() - dcis.getDealValue());
							dealist_value.add(counterIntvalue);
							break;
							}
						else
							continue;
					}
				}
				ObjectMapper mapper = new ObjectMapper();
				//jsonString = mapper.writeValueAsString(dealist_value);
				File file = new File(".//Json_CID//"+filename);
				mapper.writeValue(file, dealist_value);
				file_test =true;
				
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
		return file_test;
	}

}
