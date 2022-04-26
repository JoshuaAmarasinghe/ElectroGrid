package model;

import java.sql.Connection;
import javafx.util.Pair;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

@SuppressWarnings("restriction")
public class PaymentModel {
	public Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid",
					"root", "");
			//For testing
			System.out.print("Successfully connected to db");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public String addPayment(String CardType,String CardNumber,String CardHolderName,String CVC,String CardExpireDate,String PaymentDate,int BillID )
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			
			
			String insertQuery = "insert into payment values (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmnt = con.prepareStatement(insertQuery);
			double TaxAmount = this.calculateTaxAmount(BillID);
			double TotalAmount = this.calculateSubAmount(BillID);
			String Status = "pending";
			pstmnt.setString(1, CardType);
			pstmnt.setString(2, CardNumber);
			pstmnt.setString(3, CardHolderName);
			pstmnt.setString(4, CVC);
			pstmnt.setString(5, CardExpireDate);
			pstmnt.setString(6, Status);
			pstmnt.setDouble(7, TaxAmount);
			pstmnt.setDouble(8, TotalAmount);
			pstmnt.setString(9, PaymentDate);
			pstmnt.setInt(10, BillID);
			
			// execute the statement3
						pstmnt.execute();
						con.close();
						output = "Payment Added successfully";
			
			
		}
		catch (Exception e)
		{
			output = "Error while inserting the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String getAllPayment() {
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1' style=\"font-family: Arial, Helvetica, sans-serif; border-collapse: collapse; width: 100%; radius: 10px\">" +
                    "<tr style=\"border: 1px solid #ddd; padding: 8px;\">" +
					"<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #1E90FF; color: white;\">PaymentID</th>" +
                    "<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #1E90FF; color: white;\">CardType</th>" +
					"<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #1E90FF; color: white;\">CardNumber</th>" +
					"<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #1E90FF; color: white;\">CardHolderName</th>" +
					"<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #1E90FF; color: white;\">CVC</th>" +
					"<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #1E90FF; color: white;\">CardExpireDate</th>" +
					"<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #1E90FF; color: white;\">Status</th>" +
					"<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #1E90FF; color: white;\">TaxAmount</th>" +
                    "<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #1E90FF; color: white;\">TotalAmount</th>" +
					"<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #1E90FF; color: white;\">PaymentDate</th>" +
					"<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #1E90FF; color: white;\">BillID</th>";
						String query = "select * from payment";
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query);
						// iterate through the rows in the result set
						while (rs.next())
						{
							int PaymentID = rs.getInt("PaymentID");
							String CardType = rs.getString("CardType");
							String CardNumber = rs.getString("CardNumber");
							String CardHolderName = rs.getString("CardHolderName");
							String CVC = rs.getString("CVC");
							String CardExpireDate = rs.getString("CardExpireDate");
							String Status = rs.getString("Status");
							float TaxAmount = rs.getFloat("TaxAmount");
							float TotalAmount = rs.getFloat("TotalAmount");
							String PaymentDate = rs.getString("PaymentDate");
							int BillID = rs.getInt("BillID");

							output += "<tr style=\"border: 1px solid #ddd; padding: 8px;\"><td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + PaymentID + "</td>";
							output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + CardType + "</td>";
							output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + CardNumber + "</td>";							
							output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + CardHolderName + "</td>";
							output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + CVC + "</td>";
							output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + CardExpireDate + "</td>";
							output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + Status + "</td>";
							output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #191919; font-weight: 600\">" + TaxAmount + "</td>";
							output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #191919; font-weight: 600\">" + TotalAmount + "</td>";
							output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + PaymentDate + "</td>";
							output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + BillID + "</td></tr>";
						}
						con.close();
						// Complete the html table
						output += "</table>";
	}
		catch (Exception e)
		{
			output = "Error while reading the Payment.";
			System.err.println(e.getMessage());
		}
		return output;

    }
	
	
	public String getPaymentByUser(int UserID) {
		try(Connection con = connect()) {
			String getQuery = "select py.PaymentID, o.BillID, c.name, py.PaymentDate,o.NoOfUnits,py.Status, py.TotalAmount from billing o \n"
					+ "join user c on o.UserID = c.UserID \n"
					+ "join payment py on o.BillID = py.BillID \n" 
					+ "where c.UserID = ?;";
			PreparedStatement pstmnt = con.prepareStatement(getQuery);
			pstmnt.setInt(1, UserID);
			
			String output = "<table border='1' style=\"font-family: Arial, Helvetica, sans-serif; border-collapse: collapse; width: 100%; radius: 10px\">" + 
					"<tr style=\"border: 1px solid #ddd; padding: 8px;\">" 
					+ "<th>PaymentID</th>" 
					+ "<th>Bill ID</th>"
					+ "<th>Full Name</th>"
					+ "<th>Payment Date</th>" 
					+ "<th>No Of Units per month</th>"
					+ "<th>Status</th>"
					+ "<th>Total Amount(Include TAX)</th>";
	       ResultSet rs = pstmnt.executeQuery();
	       
	       while (rs.next()) {
				int PaymentID = rs.getInt("PaymentID");
				int BillID = rs.getInt("BillID");
				String name = rs.getString("name");
				String PaymentDate = rs.getString("PaymentDate");
				int NoOfUnits = rs.getInt("NoOfUnits");
				String Status = rs.getString("Status");
				double TotalAmount = rs.getDouble("TotalAmount");
				

				output += "<tr><td>" + PaymentID + "</td>";
				output += "<td>" + BillID + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + PaymentDate + "</td>";
				output += "<td>" + NoOfUnits + "</td>";
				output += "<td>" + Status + "</td>";
				output += "<td>" + TotalAmount + "</td>";
				

			}
	       output += "</table>";
			return output;
			
		}
		catch(Exception e) {
			return "Error occur during retrieving \n" + e.getMessage();
		}
		
	}
	
	
	
	/*public float calculateTaxAmount(int BillID, int NoOfUnit) {
		float TaxAmount = 0;
		try(Connection con = connect()) {
			String getQuery = "select o.NoOfUnits\n" 
					+ "from billing o\n"
					+ "where o.BillID = ?;";
		     
			PreparedStatement pstmt = con.prepareStatement(getQuery);
			pstmt.setInt(1, NoOfUnit);
			ResultSet rs = pstmt.executeQuery();
			
			
			if (NoOfUnit <= 100)
			{
				TaxAmount = NoOfUnit * 3;
			}	
			else if (NoOfUnit <= 200)
			{
				TaxAmount = 100 * 3 + (NoOfUnit-100)*4;
			}
			else
			{
				TaxAmount = 100 * 3 + 100 * 4 + (NoOfUnit-200) * 5;
			}
			
            while (rs.next()) {
				
            	TaxAmount = rs.getFloat("TaxAmount");
			}
            con.close();
             
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return TaxAmount;
		
	}*/
	
	
	public double calculateTaxAmount(int BillID) {
		double TaxAmount = 0;
		try(Connection con = connect()) {
			String getQuery = "select o.Amount, o.NoOfUnits\n" 
					+ "from billing o\n"
					+ "where o.BillID = ?;";
		     
			PreparedStatement pstmt = con.prepareStatement(getQuery);
			pstmt.setInt(1, BillID);
			ResultSet rs = pstmt.executeQuery();
			
			int NoOfUnits = 0;
			
			
            while (rs.next()) {
				
            	NoOfUnits = rs.getInt("NoOfUnits");
	
				
				if(NoOfUnits < 100)
				{
					TaxAmount = NoOfUnits*3;
				}
				else if(NoOfUnits < 200)
				{
					TaxAmount = 100*3+(NoOfUnits-100)*4;
				}
				else
				{
					TaxAmount = 100*3 + 100*4 + (NoOfUnits-200)*5;
				}
			}
            con.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return TaxAmount;
		
	}
	
	
	
	
	
	
	public double calculateSubAmount(int BillID) {
		double TotalAmount = 0;
		
		try(Connection con = connect()) {
			String getQuery = "select o.Amount, o.NoOfUnits\n" 
					+ "from billing o\n"
					+ "where o.BillID = ?;";
		     
			PreparedStatement pstmt = con.prepareStatement(getQuery);
			pstmt.setInt(1, BillID);
			ResultSet rs = pstmt.executeQuery();
			
			
			float Amount = 0;
			double TaxAmount = calculateTaxAmount(BillID);
			
            while (rs.next()) {
				
            	
				Amount = rs.getFloat("Amount");
				
				
			}
            con.close();
            TotalAmount = Amount + TaxAmount;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return TotalAmount;
		
	}
	
	public String updatePayment(int PaymentID,
			String CardType,
			String CardNumber,
			String CardHolderName,
			String CVC,
			String CardExpireDate,
			String PaymentDate,
			int BillID) {
		
		try(Connection con = connect()) {
			
			String updateQuery = "update payment set CardType=?,CardNumber=?,CardHolderName=?,CVC=?,CardExpireDate=?,Status=?,TaxAmount=? ,TotalAmount=? ,PaymentDate=?,BillID=? where PaymentID =?" ;
			
			PreparedStatement pstmt = con.prepareStatement(updateQuery);
			double TaxAmount = this.calculateTaxAmount(BillID);
			double TotalAmount = this.calculateSubAmount(BillID);
			String Status = "pending";
			pstmt.setString(1,CardType);
			pstmt.setString(2,CardNumber);
			pstmt.setString(3,CardHolderName);
			pstmt.setString(4,CVC);
			pstmt.setString(5,CardExpireDate);
			pstmt.setString(6,Status);
			pstmt.setDouble(7, TaxAmount);
			pstmt.setDouble(8,TotalAmount);
			pstmt.setString(9,PaymentDate);
			pstmt.setInt(10,BillID);
			pstmt.setInt(11,PaymentID);
			pstmt.execute();
			con.close();
			System.out.println(PaymentID);
	
			return "Payment updated successfully";
			
			
		}
		catch(Exception e) {
			return "Error occur during updating \n" +
					e.getMessage();
		}
		
		
	}
	
	public String DeletePayment(int PaymentID) 
	{ 
		String output = ""; 
		try
		{  
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for deleting."; } 
			// create a prepared statement
			String query = "delete from payment where PaymentID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1,PaymentID); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Deleted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while deleting the payment."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	
	

    
}
