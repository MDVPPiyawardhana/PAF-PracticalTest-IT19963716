<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="model.inquiry"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inquiry Management</title>
	<link rel="stylesheet" href="Views/bootstrap.css">
</head>
<body>
	<div class="container"><div class="row"><div class="col-6">
		<h1>Inquiry Management</h1>
		<form id="formInq" name="formInq">
 			Account No:
 			<input id="accNo" name="accNo" type="text"
 				class="form-control form-control-sm">
 			<br> Customer name:
		 	<input id="cusName" name="cusName" type="text"
 				class="form-control form-control-sm">
 			<br> Date:
 			<input id="date" name="date" type="text"
 				class="form-control form-control-sm">
 			<br> Complain:
 			<input id="complain" name="complain" type="text"
 				class="form-control form-control-sm">
 			<br>
 			<input id="btnSave" name="btnSave" type="button" value="Save"
 				class="btn btn-primary">
 			<input type="hidden" id="hidInqIDSave"
 				name="hidInqIDSave" value="">
		</form>
		<br>
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
	<br>
		<div id="divInqGrid">
 		<%
 			inquiry inqObj = new inquiry();
 			out.print(inqObj.readInquiry());
 		%>
		</div>
		</div> </div> </div>
</body>
	<script src="Components/jquery.min.js"></script>
	<script src="Components/inquiries.js"></script>
</html>