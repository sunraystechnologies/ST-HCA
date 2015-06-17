<%@page import="in.co.sunrays.util.AccessUtility"%>
<%@page import="in.co.sunrays.hca.model.ChatModel"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.hca.controller.ChatCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.hca.controller.ORSView"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="java.util.LinkedHashMap"%>

<jsp:useBean id="model" class="in.co.sunrays.hca.model.ChatModel"
	scope="request" />
	
<jsp:useBean id="doctorList" class="java.util.ArrayList"
	scope="request" />	
<jsp:useBean id="patientList" class="java.util.ArrayList"
	scope="request" />		

<p class="st-title">Chat</p>

<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>

<form action="<%=ORSView.CHAT_CTL%>" method="POST">
	<input type="hidden" name="id" value="<%=model.getId()%>">

	<table>
		<tr>
			<th>Doctor Id*</th>
			<td><%=HTMLUtility.getList("doctorId", model.getDoctorId()+"",
					doctorList)%></td>
		</tr>
		<tr>
			<th>Patient Id*</th>
			<td><%=HTMLUtility.getList("patientId", model.getPatientId()+"",
					patientList)%></td>
		</tr>
		<tr>
			<th>Message*</th>
			<td><textarea name="message" cols="22"><%=DataUtility.getStringData(model.getMessage())%></textarea>
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
			<th></th>
			<td colspan="2">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
				<%=HTMLUtility.getSubmitButton(BaseCtl.OP_SAVE,
					AccessUtility.canAdd(request), request)%>
			</td>
		</tr>
	</table>
</form>

