<%@page import="in.co.sunrays.util.AccessUtility"%>
<%@page import="in.co.sunrays.hca.model.RequestMasterModel"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.hca.controller.RequestMasterCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.hca.controller.ORSView"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="java.util.LinkedHashMap"%>

<jsp:useBean id="model" class="in.co.sunrays.hca.model.RequestMasterModel"
	scope="request" />


<p class="st-title">Add Request</p>

<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>

<form action="<%=ORSView.REQUESTMASTER_CTL%>" method="POST">
	<input type="hidden" name="id" value="<%=model.getId()%>">

	<table>
		<tr>
			<th>Patient Id*</th>
			<td><input type="text" name="patientId"
				value="<%=DataUtility.getStringData(model.getPatientId())%>"></input><font
				color="red"> <%=ServletUtility.getErrorMessage("patientId", request)%></font></td>
		</tr>
		<tr>
			<th>Patient Name*</th>
			<td><input type="text" name="patientName"
				value="<%=DataUtility.getStringData(model.getPatientName())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("patientName", request)%></font></td>
		</tr>
		<tr>
			<th>Doctor Id*</th>
			<td><input type="text" name="doctorId"
				value="<%=DataUtility.getStringData(model.getDoctorId())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("doctorId", request)%></font></td>
		</tr>
		<tr>
			<th>Doctor Name*</th>
			<td><input type="text" name="doctorName"
				value="<%=DataUtility.getStringData(model.getDoctorName())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("doctorName", request)%></font></td>
		</tr>
		
		<tr>
			<th>Date (mm/dd/yyyy)*</th>
			<td><input type="text" name="date" readonly="readonly"
				value="<%=DataUtility.getDateString(model.getDate())%>"> <a
				href="javascript:getCalendar(document.forms[0].date);"> <img
					src="<%=ORSView.IMG_FOLDER%>/cal.jpg" width="16" height="15" border="0"
					alt="Calender">
			</a><font color="red"> <%=ServletUtility.getErrorMessage("date", request)%></font></td>
		</tr>

		<tr>
			<th>Time(HH:MM)*</th>
			<td><input type="text" name="time"
				value="<%=DataUtility.getStringData(model.getTime())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("time", request)%></font></td>
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

