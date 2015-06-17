<%@page import="in.co.sunrays.hca.model.DoctorModel"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.hca.controller.DoctorCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.hca.controller.ORSView"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.sunrays.util.AccessUtility"%>

<jsp:useBean id="model" class="in.co.sunrays.hca.model.DoctorModel"
	scope="request"></jsp:useBean>

<p class="st-title">Add Doctor</p>

<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>

<form action="<%=ORSView.DOCTOR_CTL%>" method="POST">
	<input type="hidden" name="id" value="<%=model.getId()%>">
<input type="hidden" name="password" value="<%=model.getPassword()%>">

	<table>
		<tr>
			<th>Doctor Name*</th>
			<td><input type="text" name="doctorName"
				value="<%=DataUtility.getStringData(model.getDoctorName())%>"></input><font
				color="red"> <%=ServletUtility.getErrorMessage("doctorName", request)%></font></td>
		</tr>
		<tr>
			<th>Doctor Address*</th>
			<td><input type="text" name="doctorAddress"
				value="<%=DataUtility.getStringData(model.getDoctorAddress())%>"></input><font
				color="red"> <%=ServletUtility.getErrorMessage("doctorAddress", request)%></font></td>
		</tr>
		<tr>
			<th>Date Of Birth(mm/dd/yyyy)*</th>
			<td><input type="text" name="dob" readonly="readonly"
				value="<%=DataUtility.getDateString(model.getDob())%>"> <a
				href="javascript:getCalendar(document.forms[0].dob);"> <img
					src="<%=ORSView.IMG_FOLDER%>/cal.jpg" width="16" height="15" border="0"
					alt="Calender">
			</a><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
		</tr>
		<tr>
			<th>Age*</th>
			<td><input type="text" name="age"
				value="<%=DataUtility.getStringData(model.getAge())%>"></input><font
				color="red"> <%=ServletUtility.getErrorMessage("age", request)%></font></td>
		</tr>
		
		
		<tr>
			<th>State*</th>
			<td><input type="text" name="state"
				value="<%=DataUtility.getStringData(model.getDoctorName())%>"></input><font
				color="red"> <%=ServletUtility.getErrorMessage("state", request)%></font></td>
		</tr>
		<tr>
			<th>City*</th>
			<td><input type="text" name="city"
				value="<%=DataUtility.getStringData(model.getCity())%>"></input><font
				color="red"> <%=ServletUtility.getErrorMessage("city", request)%></font></td>
		</tr>
		<tr>
			<th>Email Id*</th>
			<td><input type="text" name="emailId"
				value="<%=DataUtility.getStringData(model.getEmailId())%>"></input><font
				color="red"> <%=ServletUtility.getErrorMessage("emailId", request)%></font></td>
		</tr>
		<tr>
			<th>Password*</th>
			<td><input type="password" name="password"
				value="<%=DataUtility.getStringData(model.getPassword())%>"></input><font
				color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
		</tr>
		<tr>
			<th>Qualification*</th>
			<td><input type="text" name="qualification"
				value="<%=DataUtility.getStringData(model.getQualification())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("qualification", request)%></font></td>
		</tr>
		<tr>
			<th>University*</th>
			<td><input type="text" name="university"
				value="<%=DataUtility.getStringData(model.getUniversity())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("university", request)%></font></td>
		</tr>
		<tr>
			<th>Year Of Passing*</th>
			<td><input type="text" name="yearOfPassing" readonly="readonly"
				value="<%=DataUtility.getDateString(model.getYearOfpassing())%>"> <a
				href="javascript:getCalendar(document.forms[0].yearOfPassing);"> <img
					src="<%=ORSView.IMG_FOLDER%>/cal.jpg" width="16" height="15" border="0"
					alt="Calender">
			</a><font color="red"> <%=ServletUtility.getErrorMessage("yearOfPassing", request)%></font></td>
		</tr>
		<tr>
			<th>Experience*</th>
			<td><input type="text" name="experience"
				value="<%=DataUtility.getStringData(model.getExperience())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("experience", request)%></font></td>
		</tr>
		<tr>
			<th>Clinic Name*</th>
			<td><input type="text" name="clinicName"
				value="<%=DataUtility.getStringData(model.getClinicName())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("clinicName", request)%></font></td>
		</tr>
		<tr>
			<th>Clinic Address*</th>
			<td><input type="text" name="clinicAddress"
				value="<%=DataUtility.getStringData(model.getClinicAddress())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("clinicAddress", request)%></font></td>
		</tr>
		<tr>
			<th>Clinic ContactNO*</th>
			<td><input type="text" name="clinicContact"
				value="<%=DataUtility.getStringData(model.getClinicContact())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("clinicContact", request)%></font></td>
		</tr>
		
		<tr>
			<th></th>
			<td colspan="2">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
				<%=HTMLUtility.getSubmitButton(BaseCtl.OP_SAVE,
					AccessUtility.canAdd(request), request)%>
			</td>
		</tr>
	</table>
</form>