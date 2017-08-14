package com.db.app;

import java.io.File;
import java.io.IOException;
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
		ArrayList<DealCounterInt> dealist_buy = getValueList(counterpartyId,"B");
		ArrayList<DealCounterInt> dealist_sell = getValueList(counterpartyId, "S");
		ArrayList<DealCounterInt> dealist_value = new ArrayList<>();
							
				for(DealCounterInt dcib : dealist_buy){
					for(DealCounterInt dcis : dealist_sell){
						String inst_name = dcib.getDealInstrumenTName();
						if(dcis.getDealInstrumenTName().equals(inst_name)){
							DealCounterInt counterIntvalue = new DealCounterInt();
							counterIntvalue.setDealInstrumenTName(inst_name);;
							counterIntvalue.setDealValue(dcib.getDealValue() - dcis.getDealValue());
							dealist_value.add(counterIntvalue);
							break;
							}
						else
							continue;
					}
				}
				ObjectMapper mapper = new ObjectMapper();
				File file = new File(".//Json_CID//"+filename);
				try {
					mapper.writeValue(file, dealist_value);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				file_test =true;
				
		return file_test;
	}

	private static ArrayList<DealCounterInt> getValueList (int counterpartyId, String deal_type){
		ArrayList<DealCounterInt> deal_value = new ArrayList<>();
		try(Connection conn = Connector.getConnection()){
			if(conn != null){
			String sql = "select ta.value, tb.instrument_name from ( SELECT sum(deal_amount * deal_quantity) "+
			"value, deal_instrument_id "+
			"FROM deal where deal_counterparty_id = ? " +
			"and deal_type = ? group by deal_instrument_id ) ta" +
            " join (select instrument_id,instrument_name from instrument) tb "+
            "on ta.deal_instrument_id =tb.instrument_id;";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, counterpartyId);
				ps.setString(2, deal_type);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					DealCounterInt counterIntbuy = new DealCounterInt();
					counterIntbuy.setDealValue(rs.getDouble(1));
					counterIntbuy.setDealInstrumenTName(rs.getString(2));
					deal_value.add(counterIntbuy);
				}
			}
				else{
					throw new Exception("Connection not established!!");
				}
			
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return deal_value;
	


}
}