package com;

import model.inquiry;

//for REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//for JSON
import com.google.gson.*;

//for XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Inquiry")

public class inquiryService {
	
	inquiry inqObj = new inquiry();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readInquiry() {
		
		return inqObj.readInquiry();
		
	}
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertInquiry(@FormParam("accNo") String accNo,
							@FormParam("cusName") String cusName,
							@FormParam("date") String date,
							@FormParam("complain") String complain)
	{
		
		String output = inqObj.insertInquiry(accNo, cusName, date, complain);
		return output;
		
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateInquiry(String inquiryData)
	{
		//Convert input string to a JSON object
		JsonObject inqObject = new JsonParser().parse(inquiryData).getAsJsonObject();
		
		//Read values from JSON object
		String inqID = inqObject.get("inqID").getAsString();
		String accNo = inqObject.get("accNo").getAsString();
		String cusName = inqObject.get("cusName").getAsString();
		String date = inqObject.get("date").getAsString();
		String complain = inqObject.get("complain").getAsString();
		
		String output = inqObj.updateInquiry(inqID, accNo, cusName, date, complain);
		return output;
		
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInquiry(String inquiryData)
	{
		//Convert input string to a JSON object
		Document doc = Jsoup.parse(inquiryData, "", Parser.xmlParser());
		
		//Read values from JSON object
		String inqID = doc.select("inqID").text();
		
		String output = inqObj.deleteInquiry(inqID);
		return output;
		
	}

}
