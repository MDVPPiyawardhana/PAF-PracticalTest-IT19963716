package model;

import java.sql.*;

public class inquiry {
	
	public String insertInquiry(String accNo, String cusName, String date, String complain) {
		
		String output = ""; 
		try
		{ 
			
			connection con = new connection();
			Connection con1 = con.connect(); 
			if (con1 == null) 
			{return "Error while connecting to the database for inserting."; } 
			// create a prepared statement
			String query = " insert into inquiry(`inqID`,`accNo`,`cusName`,`date`,`complain`)"
					+ " values (?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con1.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, accNo); 
			preparedStmt.setString(3, cusName); 
			preparedStmt.setString(4, date); 
			preparedStmt.setString(5, complain); 
			// execute the statement
			preparedStmt.execute(); 
			con1.close(); 
			String newInq = readInquiry();
			
			System.out.println(newInq);

			output = "{\"status\":\"success\", \"data\": \"" + newInq + "\"}";

		} 
		catch (Exception e) 
		{ 
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the inquiry.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output;
		
	}
	
	public String readInquiry() {
		
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect();
			if (con1 == null) 
			{return "Error while connecting to the database for reading."; } 
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Account No</th><th>Customer Name</th>" +
					"<th>Date</th>" + 
					"<th>Complain</th>" +
					"<th>Update</th><th>Remove</th></tr>"; 

			String query = "select * from inquiry"; 
			Statement stmt = con1.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String inqID = Integer.toString(rs.getInt("inqID")); 
				String accNo = rs.getString("accNo"); 
				String cusName = rs.getString("cusName"); 
				String date = rs.getString("date"); 
				String complain = rs.getString("complain"); 
				// Add into the html table
				output += "<tr><td><input type='hidden' name='hidInqIDUpdate' id='hidInqIDUpdate' value='"+ inqID +"'>" + accNo + "</td>"; 
				output += "<td>" + cusName + "</td>"; 
				output += "<td>" + date + "</td>"; 
				output += "<td>" + complain + "</td>"; 
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-inqid='"+inqID+"'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-secondary' data-inqid='"+inqID+"'></td></tr>"; 
			} 
			con1.close(); 
			// Complete the html table
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the inquiries."; 
			System.err.println(e.getMessage()); 
		} 
		return output;
		
	}
	
	public String updateInquiry(String id, String accNo, String cusName, String date, String complain) {
		
		System.out.println("came here as well");
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect();
			
			if (con1 == null) 
			{return "Error while connecting to the database for updating."; } 
			// create a prepared statement
			String query = "UPDATE inquiry SET accNo=?,cusName=?,date=?,complain=? WHERE inqID=?"; 
			PreparedStatement preparedStmt = con1.prepareStatement(query); 
			// binding values
			preparedStmt.setString(1, accNo); 
			preparedStmt.setString(2, cusName); 
			preparedStmt.setString(3, date); 
			preparedStmt.setString(4, complain); 
			preparedStmt.setString(5, id); 
			// execute the statement
			preparedStmt.execute(); 
			con1.close(); 
			String newInq = readInquiry();

			output = "{\"status\":\"success\", \"data\": \"" + newInq + "\"}";
		} 
		catch (Exception e) 
		{ 
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the inquiry.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output;
		
	}
	
	public String deleteInquiry(String inqID) {
		
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect();
			
			if (con1 == null) 
			{return "Error while connecting to the database for deleting."; } 
			// create a prepared statement
			String query = "delete from inquiry where inqID=?"; 
			PreparedStatement preparedStmt = con1.prepareStatement(query); 
			// binding values
			preparedStmt.setString(1, inqID); 
			// execute the statement
			preparedStmt.execute(); 
			con1.close(); 
			String newInq = readInquiry();

			output = "{\"status\":\"success\", \"data\": \"" + newInq + "\"}";
		} 
		catch (Exception e) 
		{ 
			output =  "{\"status\":\"error\", \"data\": \"Error while deleting the inquiry.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output;
		
	}

}

