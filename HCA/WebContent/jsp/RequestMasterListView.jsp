<%@page import="in.co.sunrays.util.AccessUtility"%>
<%@page import="in.co.sunrays.hca.controller.ORSView"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="java.io.File"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="in.co.sunrays.hca.model.RequestMasterModel"%>
<%@page import="in.co.sunrays.hca.controller.RequestMasterListCtl"%>
<%@page import="in.co.sunrays.hca.controller.RequestMasterCtl"%>
<%@page import="in.co.sunrays.common.controller.UserListCtl"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<p class="st-title">Request List</p>

<form action="<%=ORSView.REQUESTMASTER_LIST_CTL%>">

	<table width="100%">
		<tr>
			<td align="center"><label>Patient Name:</label> <input type="text"
				name="patientName"
				value="<%=ServletUtility.getParameter("patientName", request)%>">
				&nbsp; <label>Doctor Name:</label> <input type="text" name="doctorName"
				value="<%=ServletUtility.getParameter("doctorName", request)%>">&nbsp;
				<input type="submit" name="operation"
				value="<%=RequestMasterListCtl.OP_SEARCH%>"></td>
		</tr>
	</table>
	<br>
	<table border="1" width="100%">
		<tr>
			<th>Select</th>
			<th>Patient Id</th>
			<th>Patient Name</th>
			<th>Doctor Id</th>
			<th>Doctor Name</th>
			<th>Date</th>
			<th>Time</th>
			<th>Edit</th>
		</tr>
		<%
			if (HTMLUtility.getErrorMessage(request).length() > 0) {
		%>
		<tr>
			<td colspan="8"><%=HTMLUtility.getErrorMessage(request)%></td>
		</tr>
		<%
			}
		%>
		<%
			int i = 1;
			List list = ServletUtility.getList(request);
			Iterator<RequestMasterModel> it = list.iterator();

			while (it.hasNext()) {

				RequestMasterModel bean = it.next();
		%>
		<tr>
			<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
			<td><%=bean.getPatientId()%></td>
			<td><%=bean.getPatientName()%></td>
			<td><%=bean.getDoctorId()%></td>
			<td><%=bean.getDoctorName()%></td>
			<td><%=bean.getDate()%></td>
			<td><%=bean.getTime()%></td>
			<td>
				<%
					String label = (AccessUtility.canWrite(request)) ? "Edit"
								: "View";
				%> <a href="<%=ORSView.REQUESTMASTER_CTL%>?id=<%=bean.getId()%>"><%=label%></a>

			</td>
		</tr>
		<%
			}
		%>
	</table>
	<table width="100%">
		<tr>
			<td align="left"><input type="submit" name="operation"
				value="<%=RequestMasterListCtl.OP_PREVIOUS%>"></td>

			<td colspan="3" align="center"><%=HTMLUtility.getSubmitButton(BaseCtl.OP_NEW,
					AccessUtility.canAdd(request), request)%><%=HTMLUtility.getSubmitButton(BaseCtl.OP_DELETE,
					AccessUtility.canDelete(request), request)%></td>
			<td align="right"><input type="submit" name="operation"
				value="<%=RequestMasterListCtl.OP_NEXT%>"></td>
		</tr>
	</table>
	<%
		int pageNo = ServletUtility.getPageNo(request);
		int pageSize = ServletUtility.getPageSize(request);
		int index = ((pageNo - 1) * pageSize) + 1;
	%>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">
</form>

