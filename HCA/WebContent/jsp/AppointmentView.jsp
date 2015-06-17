<%@page import="in.co.sunrays.hca.model.AppointmentModel"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.hca.controller.AppointmentCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.hca.controller.ORSView"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.sunrays.util.AccessUtility"%>

<jsp:useBean id="model"
		class="in.co.sunrays.hca.model.AppointmentModel" scope="request"></jsp:useBean>


<p class="st-title">Add Appointment</p>
	
	<%=HTMLUtility.getSuccessMessage(request)%>
	<%=HTMLUtility.getErrorMessage(request)%>


<form action="<%=ORSView.APPOINTMENT_CTL%>" method="POST">
<input type="hidden" name="id" value="<%=model.getId()%>">

	
	<table>

		<tr>
			<th>Date*</th>
			<td><input type="text" name="date" readonly="readonly"
				value="<%=DataUtility.getDateString(model.getDate())%>"> <a
				href="javascript:getCalendar(document.forms[0].date);"> <img
					src="<%=ORSView.IMG_FOLDER%>/cal.jpg" width="16" height="15" border="0"
					alt="Calender">
			</a><font color="red"> <%=ServletUtility.getErrorMessage("date", request)%></font></td>
		</tr>
		<tr>
			<th>Time*</th>
			<td><input type="text" name="time"
				value="<%=DataUtility.getStringData(model.getTime())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("time", request)%></font></td>
		</tr>
		<tr>
			<th>ClinicName*</th>
			<td><input type="text" name="clinicName"
				value="<%=DataUtility.getStringData(model.getClinicName())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("clinicName", request)%></font></td>
		</tr>
		<tr>
			<th>ClinicAddress*</th>
			<td><textarea name="clinicAddress" cols="22"><%=DataUtility.getStringData(model.getClinicAddress())%></textarea>
		</tr>
		<tr>
			<th>ClinicContact*</th>
			<td><input type="text" name="clinicContact"
				value="<%=DataUtility.getStringData(model.getClinicContact())%>"><font
				color="red"><%=ServletUtility.getErrorMessage("clinicContact", request)%></font></td>
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
			<th>PatientName*</th>
			<td><input type="text" name="patientName"
				value="<%=DataUtility.getStringData(model.getPatientName())%>"><font
				color="red"><%=ServletUtility.getErrorMessage("patientName", request)%></font></td>
		</tr>
		<tr>
			<th>PatientAge*</th>
			<td><input type="text" name="patientAge"
				value="<%=DataUtility.getStringData(model.getPatientAge())%>"><font
				color="red"><%=ServletUtility.getErrorMessage("patientAge", request)%></font></td>
		</tr>
		</tr>
		<tr>
			<th>Symptoms*</th>
			<td><textarea name="symptoms" cols="22"><%=DataUtility.getStringData(model.getSymptoms())%></textarea>
		</tr>
		<tr>
			<th>Weight*</th>
			<td><input type="text" name="weight"
				value="<%=DataUtility.getStringData(model.getWeight())%>"><font
				color="red"><%=ServletUtility.getErrorMessage("weight", request)%></font></td>
		</tr>
		<tr>
			<th>Height*</th>
			<td><input type="text" name="height"
				value="<%=DataUtility.getStringData(model.getHeight())%>"><font
				color="red"><%=ServletUtility.getErrorMessage("height", request)%></font></td>
		</tr>

		<tr>
			<th></th>
			<td colspan="2">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
				<%=HTMLUtility.getSubmitButton(BaseCtl.OP_SAVE,
					AccessUtility.canAdd(request), request)%>
			</table>
	</form>

	

