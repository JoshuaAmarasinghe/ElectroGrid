package com;

import model.Mynewbill;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Mynewbill")

public class MynewbillServices {
	
	
	
	Mynewbill MynewbillObj = new Mynewbill();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readMynewbill()
	 {
		return MynewbillObj.readMynewbill(); 
	 } 
	
	// insert user details
		@POST
		@Path("/insert")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertMynewbill(
		
		@FormParam("billid") String billid,
		@FormParam("consumerid") String consumerid,
		@FormParam("expiredate") String expiredate,
		@FormParam("issuingdate") String issuingdate,
		@FormParam("unitnumber") String unitnumber,
		@FormParam("billamount") String billamount
		)
		

		
				{
					 String output = MynewbillObj.insertMynewbill(billid,consumerid,expiredate,issuingdate,unitnumber,billamount);
					 return output;
				}
						
						
					
	//update user details
		@PUT
		@Path("/update")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateMynewbill(String MynewbillData)
		{
		   //Convert the input string to a JSON object
			JsonObject MynewbillObject = new JsonParser().parse(MynewbillData).getAsJsonObject();
							 
		    //Read the values from the JSON object
				String billid = MynewbillObject.get("billid").getAsString();			
				String consumerid = MynewbillObject.get("consumerid").getAsString();
				String expiredate = MynewbillObject.get("expiredate").getAsString();
				String issuingdate = MynewbillObject.get("issuingdate").getAsString();
			    String unitnumber = MynewbillObject.get("unitnumber").getAsString();
			    String billamount= MynewbillObject.get("billamount").getAsString();
			   
			   
				
				String output = MynewbillObj.updateMynewbill(billid,consumerid,expiredate,issuingdate,unitnumber,billamount);
				return output;
		}
						
		
		
	//delete user details
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteMynewbill(String MynewbillData)
		{
		 //Convert the input string to an XML document
		 Document doc = Jsoup.parse(MynewbillData, "", Parser.xmlParser());
					
		//Read the value from the element <idUnit>
		 String billid = doc.select("billid").text();
		 String output = MynewbillObj.deleteBill(billid);
		 return output;
		 
		}
	
	

}
