<%@page import="in.co.sunrays.util.AccessUtility"%>
<%@page import="in.co.sunrays.hca.controller.ORSView"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="java.io.File"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="in.co.sunrays.hca.model.PatientModel"%>
<%@page import="in.co.sunrays.hca.controller.PatientListCtl"%>
<%@page import="in.co.sunrays.hca.controller.PatientCtl"%>
<%@page import="in.co.sunrays.common.controller.UserListCtl"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<p class="st-title">Patient List</p>

<form action="<%=ORSView.PATIENT_LIST_CTL%>">

			<table width="100%">
				<tr>
					<td align="center">
						<label>Patient_Name:</label> <input type="text"
						name="name"
						value="<%=ServletUtility.getParameter("name", request)%>">
						<label>Patient Address:</label> <input type="text"
						name="address"
						value="<%=ServletUtility.getParameter("address3", request)%>">
						
						<input type="submit" name="operation"
						value="<%=PatientListCtl.OP_SEARCH%>"></td>
				</tr>
			</table>
			<br>
			<table border="1" width="100%">
				<tr>
					<th>S.No</th>
					<th>Patient Name</th>
					<th>Patient Address</th>
					<th>Patient ContactNo</th>
					<th>Patient DOB</th>
					<th>Patient Weight</th>
					<th>Patient Height</th>
					<th>Patient State</th>
					<th>Patient City</th>
					<th>Patient EmailId</th>
					<th>Edit</th>

				</tr>
				<tr>
					<td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
				</tr>
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator<PatientModel> it = list.iterator();

					while (it.hasNext()) {

						PatientModel model = it.next();
				%>
				<tr>
				<td><input type="checkbox" name="ids" value="<%=model.getId()%>"></td>
					
					<td><%=model.getName()%></td>
					<td><%=model.getAddress()%></td>
					<td><%=model.getContactNo()%></td>
					<td><%=model.getDob()%></td>
					<td><%=model.getWeight()%></td>
					<td><%=model.getHeight()%></td>
					<td><%=model.getState()%></td>
					<td><%=model.getCity()%></td>
					<td><%=model.getEmailAddress()%></td>
					<td>
				<%
					String label = (AccessUtility.canWrite(request)) ? "Edit"
								: "View";
				%> <a href="<%=ORSView.PATIENT_CTL%>?id=<%=model.getId()%>"><%=label%></a>

			</td>
				</tr>
				<%
					}
				%>
			</table>
			<table width="100%">
		<tr>
			<td align="left"><input type="submit" name="operation"
				value="<%=PatientListCtl.OP_PREVIOUS%>"></td>

			<td colspan="3" align="center"><%=HTMLUtility.getSubmitButton(BaseCtl.OP_NEW,
					AccessUtility.canAdd(request), request)%><%=HTMLUtility.getSubmitButton(BaseCtl.OP_DELETE,
					AccessUtility.canDelete(request), request)%></td>
			<td align="right"><input type="submit" name="operation"
				value="<%=PatientListCtl.OP_NEXT%>"></td>
		</tr>
	</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"><input
				type="hidden" name="pageSize" value="<%=pageSize%>">


		</form>
	</center>
</body>
</html>