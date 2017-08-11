package com.db.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.db.app.Login;
import com.db.connector.Connector;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Servlet implementation class ServletDemo
 */

public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			try {
		        StringBuilder jsonBuff = new StringBuilder();
		        String line = null;
		        try {
		            BufferedReader reader = request.getReader();
		            while ((line = reader.readLine()) != null)
		                jsonBuff.append(line);
		        } catch (Exception e) { /*error*/ }

		        System.out.println("Request JSON string :" + jsonBuff.toString());
		        //write the response here by getting JSON from jasonBuff.toString()

		        try {
		            JSONObject jsonObject = new JSONObject(jsonBuff.toString());
		            String user=jsonObject.get("username").toString();
		            String password=jsonObject.get("password").toString();
		            System.out.println(user); 
		            Boolean userExists = Login.isValidUser(user, password);
		            System.out.println(userExists); 
		            PrintWriter out = response.getWriter();
		            if(userExists){
		            	out.print("index.html");
						return;
		            }
		            else{
		            	out.print("/error.html");
						return;
		            }
		          

		        } catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       
	        } finally {            
	            
	        }
		}


//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
}


