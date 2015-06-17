<%@page import="in.co.sunrays.hca.model.PatientModel"%>
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

<jsp:useBean id="model" class="in.co.sunrays.hca.model.PatientModel"
	scope="request"></jsp:useBean>

<p class="st-title">Add Patient</p>

<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>

<form action="<%=ORSView.PATIENT_CTL%>" method="POST">
	<input type="hidden" name="id" value="<%=model.getId()%>">

			<table>

				<tr>
					<th>Name*</th>
					<td><input type="text" name="name"
						value="<%=DataUtility.getStringData(model.getName())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>
				<tr>
					<th>Address*</th>
					<td><input type="text" name="address"
						value="<%=DataUtility.getStringData(model.getAddress())%>"><font
						color="red"><%=ServletUtility.getErrorMessage("address", request)%></font>
					</td>
				</tr>
				<tr>
					<th>ContactNo*</th>
					<td><input type="text" name="contactNo"
						value="<%=DataUtility.getStringData(model.getContactNo())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("contactNo", request)%></font></td>
				</tr>
				<tr>
			<th>Date Of Birth(mm/dd/yyyy)*</th>
			<td><input type="text" name="dob" readonly="readonly"
				value="<%=DataUtility.getDateString(model.getDob())%>"><a
				href="javascript:getCalendar(document.forms[0].dob);"> <img
					src="<%=ORSView.IMG_FOLDER%>/cal.jpg" width="16" height="15" border="0"
					alt="Calender">
			</a><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
		</tr>
				<tr>
					<th>Weight*</th>
					<td><input type="text" name="weight"
						value="<%=DataUtility.getStringData(model.getWeight())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("weight", request)%></font></td>
				</tr>
				<tr>
					<th>Height*</th>
					<td><input type="text" name="height"
						value="<%=DataUtility.getStringData(model.getHeight())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("height", request)%></font></td>
				</tr>
				<tr>
					<th>State*</th>
					<td><input type="text" name="state"
						value="<%=DataUtility.getStringData(model.getState())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("state", request)%></font></td>
				</tr>
				<tr>
					<th>City*</th>
					<td><input type="text" name="city"
						value="<%=DataUtility.getStringData(model.getCity())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("city", request)%></font></td>
				</tr>
				<tr>
					<th>Email_Id*</th>
					<td><input type="text" name="emailId"
						value="<%=DataUtility.getStringData(model.getEmailAddress())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("emailId", request)%></font></td>
				</tr>
				<tr>
			<th></th>
			<td colspan="2">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
				<%=HTMLUtility.getSubmitButton(BaseCtl.OP_SAVE,
					AccessUtility.canAdd(request), request)%>
			</table>
	</form>

	
