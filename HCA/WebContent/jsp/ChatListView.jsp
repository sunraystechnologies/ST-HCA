<%@page import="in.co.sunrays.hca.controller.ChatListCtl"%>
<%@page import="in.co.sunrays.hca.controller.ChatCtl"%>
<%@page import="in.co.sunrays.hca.controller.ORSView"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.hca.model.ChatModel"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.util.AccessUtility"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>

<jsp:useBean id="model" class="in.co.sunrays.hca.model.ChatModel"
	scope="request"/>

<p class="st-title">Chat List</p>

	<form action="<%=ORSView.CHAT_LIST_CTL%>">

		<table width="100%">
			<tr>
				<td align="center"><label>Doctor Id:</label> <input
					type="text" name="doctorId"
					value="<%=ServletUtility.getParameter("doctorId", request)%>">&emsp;
					<label>Patient Id:</label> <input type="text" name="patientId"
					value="<%=ServletUtility.getParameter("patientId", request)%>">&emsp;
					<input type="submit" name="operation"
					value="<%=ChatListCtl.OP_SEARCH%>"></td>
			</tr>
		</table>
		<br>
		<table border="1" width="100%">
			<tr>
				<th>Select</th>
				<th>Doctor Id</th>
				<th>Patient Id</th>
				<th>Message</th>
				<th>Date</th>
				<th>Edit</th>
			</tr>
			<tr>
				<td colspan="6"><%=HTMLUtility.getErrorMessage(request)%></td>
			</tr>
			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List list = ServletUtility.getList(request);
				Iterator<ChatModel> it = list.iterator();

				while (it.hasNext()) {

					ChatModel bean = it.next();
			%>
			<tr>
				<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
				<td><%=bean.getDoctorId()%></td>
				<td><%=bean.getPatientId()%></td>
				<td><%=bean.getMessage()%></td>
				<td><%=bean.getDate()%></td>
				<td>
				<%
					String label = (AccessUtility.canWrite(request)) ? "Edit"
								: "View";
				%> <a href="<%=ORSView.CHAT_CTL%>?id=<%=bean.getId()%>"><%=label%></a>

			</tr>

			<%
				}
			%>
		</table>
		<table width="100%">
			<tr>
				<td><input type="submit" name="operation"
					value="<%=ChatListCtl.OP_PREVIOUS%>"></td>
					
				<td><%=HTMLUtility.getSubmitButton(BaseCtl.OP_NEW,
					AccessUtility.canAdd(request), request)%><%=HTMLUtility.getSubmitButton(BaseCtl.OP_DELETE,
					AccessUtility.canDelete(request), request)%></td>
			
					
				<td align="right"><input type="submit" name="operation"
					value="<%=ChatListCtl.OP_NEXT%>"></td>
			</tr>
		</table>
		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>