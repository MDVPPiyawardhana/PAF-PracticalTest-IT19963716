
//Hide the alters on page load
$(document).ready(function()
{
	$("#alertSuccess").hide();
 	$("#alertError").hide();
});


//Add an event handler for the click event of the save button
$(document).on("click", "#btnSave", function(event)
{
 	// Clear alerts---------------------
 	$("#alertSuccess").text("");
 	$("#alertSuccess").hide();
 	$("#alertError").text("");
 	$("#alertError").hide();


// Form validation-------------------
	var status = validateInqForm();
	if (status != true)
 	{
 		$("#alertError").text(status);
 		$("#alertError").show();
 		return;
 	}
// If valid------------------------
	var type = ($("#hidInqIDSave").val() == "") ? "POST" : "PUT";
	console.log(type);
 	$.ajax(
 	{
		url : "inquiriesAPI",
		type : type,
 		data : $("#formInq").serialize(),
 		dataType : "text",
 		complete : function(response, status)
 		{
 			onInqSaveComplete(response.responseText, status);
 		}
 	}); 
}); 


//implement validation
function validateInqForm()
{
// ACCOUNT NO
	if ($("#accNo").val().trim() == "")
 	{
 		return "Insert Account Number.";
 	}
// CUSTOMER NAME
	if ($("#cusName").val().trim() == "")
 	{
 		return "Insert Customer Name.";
 	}
// DATE-------------------------------
	if ($("#date").val().trim() == "")
 	{
		return "Insert Date.";
 	}
// COMPLAIN------------------------
	if ($("#complain").val().trim() == "")
 	{
 		return "Insert Complain.";
 	}
	return true;
}


//Update
$(document).on("click", ".btnUpdate", function(event)
{
 //$("#hidInqIDSave").val($(this).closest("tr").find('#hidInqIDUpdate').val());
 $("#hidInqIDSave").val($(this).data("inqid"));
 $("#accNo").val($(this).closest("tr").find('td:eq(0)').text());
 $("#cusName").val($(this).closest("tr").find('td:eq(1)').text());
 $("#date").val($(this).closest("tr").find('td:eq(2)').text());
 $("#complain").val($(this).closest("tr").find('td:eq(3)').text());
});


function onInqSaveComplete(response, status)
{
	if (status == "success")
 	{
 		var resultSet = JSON.parse(response);
 		if (resultSet.status.trim() == "success")
 		{
 			$("#alertSuccess").text("Successfully saved.");
 			$("#alertSuccess").show();
			$("#divInqGrid").html(resultSet.data);
			
 		} else if (resultSet.status.trim() == "error")
 		{
 			$("#alertError").text(resultSet.data);
 			$("#alertError").show();
 		}
 	} else if (status == "error")
 	{
 		$("#alertError").text("Error while saving.");
 		$("#alertError").show();
 	} else
 	{
 		$("#alertError").text("Unknown error while saving..");
 		$("#alertError").show();
 	}
	$("#hidInqIDSave").val("");
 	$("#formInq")[0].reset();
}


$(document).on("click", ".btnRemove", function(event)
{
	var id = $(this).data("inqid");
	console.log("id is :"+id)
	
	 $.ajax(
	 {
		 url : "inquiriesAPI",
 		 type : "DELETE",
 		 data : "inqID=" + id,
 		 dataType : "text",
  		 complete : function(response, status)
 		 {
 				onInqDeleteComplete(response.responseText, status);
 		 }
 	 });
});


function onInqDeleteComplete(response, status)
{
	if (status == "success")
 	{
 		var resultSet = JSON.parse(response);
 		if (resultSet.status.trim() == "success")
 		{
 			$("#alertSuccess").text("Successfully deleted.");
 			$("#alertSuccess").show();
 			$("#divInqGrid").html(resultSet.data);
 		} else if (resultSet.status.trim() == "error")
 		{
 			$("#alertError").text(resultSet.data);
 			$("#alertError").show();
 		}
 	} else if (status == "error")
 	{
 		$("#alertError").text("Error while deleting.");
 		$("#alertError").show();
 	} else
 	{
 		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
 	}
	$("#hidInqIDSave").val(""); 
	$("#formInq")[0].reset(); 
}