package com;

import model.feedback;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/feedback")

public class feedbackService {
	
	feedback feedbackObj = new feedback();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readfeedback()
	 {
		return feedbackObj.readFeedback(); 
	 } 
	
	


	// insert user details
		@POST
		@Path("/insert")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String inserFeedback(
		
		@FormParam("userid") String userid,
		@FormParam("previousbillno") String previousbillno,
		@FormParam("amount") String amount,
		@FormParam("payedamount") String payedamount,
		@FormParam("balance") String balance,
		@FormParam("newbillno") String newbillno,
		@FormParam("newamount") String newamount,
		@FormParam("total") String total,
		@FormParam("comment") String comment)
		

		
				{
					 String output = feedbackObj.inserFeedback(userid,previousbillno,amount,payedamount,balance,newbillno,newamount,total,comment);
					 return output;
				}
						
						
						
	//update user details
		@PUT
		@Path("/update")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateFeedback(String feedbackData)
		{
		   //Convert the input string to a JSON object
			JsonObject feedbackObject = new JsonParser().parse(feedbackData).getAsJsonObject();
							 
		    //Read the values from the JSON object
				String id = feedbackObject.get("id").getAsString();			
				String userid = feedbackObject.get("userid").getAsString();
				String previousbillno = feedbackObject.get("previousbillno").getAsString();
				String amount = feedbackObject.get("amount").getAsString();
			    String payedamount = feedbackObject.get("payedamount").getAsString();
			    String balance = feedbackObject.get("balance").getAsString();
			    String newbillno = feedbackObject.get("newbillno").getAsString();
			    String newamount = feedbackObject.get("newamount").getAsString();
			    String total = feedbackObject.get("total").getAsString();
			    String comment = feedbackObject.get("comment").getAsString();
				
				String output = feedbackObj.updateFeedback(id,userid,previousbillno,amount,payedamount,balance,newbillno,newamount,total,comment);
				return output;
		}
						
		
		
	//delete user details
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFeedback(String feedbackData)
		{
		 //Convert the input string to an XML document
		 Document doc = Jsoup.parse(feedbackData, "", Parser.xmlParser());
					
		//Read the value from the element <idUnit>
		 String userid = doc.select("cid").text();
		 String output = feedbackObj.deleteFeedback(userid);
		 return output;
		 
		}

}
