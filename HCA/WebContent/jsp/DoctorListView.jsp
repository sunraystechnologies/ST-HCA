<%@page import="in.co.sunrays.util.AccessUtility"%>
<%@page import="in.co.sunrays.hca.controller.ORSView"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="java.io.File"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="in.co.sunrays.hca.model.DoctorModel"%>
<%@page import="in.co.sunrays.hca.controller.DoctorListCtl"%>
<%@page import="in.co.sunrays.hca.controller.DoctorCtl"%>
<%@page import="in.co.sunrays.common.controller.UserListCtl"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<style type="text/css">

</style>


<p class="st-title">Doctor List</p>

<form action="<%=ORSView.DOCTOR_LIST_CTL%>">

	<table width="100%">
		<tr>
			<td align="center"><label>Doctor Name:</label> <input type="text"
				name="doctorName"
				value="<%=ServletUtility.getParameter("doctorName", request)%>">
				&nbsp; <label>Doctor Address:</label> <input type="text" name="doctorAddress"
				value="<%=ServletUtility.getParameter("doctorAddress", request)%>">&nbsp;
				<input type="submit" name="operation"
				value="<%=DoctorListCtl.OP_SEARCH%>"></td>
		</tr>
	</table>
	<br>
	<div class="scroll">
	<table border="1" >
		<tr>
			<th>Select</th>
			<th>Doctor Name</th>
			<th>Doctor Address</th>
			<th>Date Of Birth</th>
			<th>Age</th>
			<th>State</th>
			<th>City</th>
			<th>Email ID</th>
			<th>Qualification</th>
			<th>University</th>
			<th>Year Of Passing</th>
			<th>Experience</th>
			<th>Clinic Name</th>
			<th>Clinic Address</th>
			<th>Clinic ContactNo</th>
			<th>Edit</th>
		</tr>
		<%
			if (HTMLUtility.getErrorMessage(request).length() > 0) {
		%>
		<tr>
			<td colspan="15"><%=HTMLUtility.getErrorMessage(request)%></td>
		</tr>
		<%
			}
		%>
		<%
			int i = 1;
			List list = ServletUtility.getList(request);
			Iterator<DoctorModel> it = list.iterator();

			while (it.hasNext()) {

				DoctorModel bean = it.next();
		%>
		<tr>
			<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
			<td><%=bean.getDoctorName()%></td>
			<td><%=bean.getDoctorAddress()%></td>
			<td><%=bean.getDob()%></td>
			<td><%=bean.getAge()%></td>
			<td><%=bean.getState()%></td>
			<td><%=bean.getCity()%></td>
			<td><%=bean.getEmailId()%></td>
			<td><%=bean.getPassword()%></td>
			<td><%=bean.getQualification()%></td>
			<td><%=bean.getUniversity()%></td>
			<td><%=bean.getYearOfpassing()%></td>
			<td><%=bean.getExperience()%></td>
			<td><%=bean.getClinicName()%></td>
			<td><%=bean.getClinicAddress()%></td>
			<td><%=bean.getClinicContact()%></td>
			<td>
				<%
					String label = (AccessUtility.canWrite(request)) ? "Edit"
								: "View";
				%> <a href="<%=ORSView.DOCTOR_CTL%>?id=<%=bean.getId()%>"><%=label%></a>

			</td>
		</tr>
		<%
			}
		%>
	</table>
	</div>
	<table width="100%">
		<tr>
			<td align="left"><input type="submit" name="operation"
				value="<%=DoctorListCtl.OP_PREVIOUS%>"></td>

			<td colspan="3" align="center"><%=HTMLUtility.getSubmitButton(BaseCtl.OP_NEW,
					AccessUtility.canAdd(request), request)%><%=HTMLUtility.getSubmitButton(BaseCtl.OP_DELETE,
					AccessUtility.canDelete(request), request)%></td>
			<td align="right"><input type="submit" name="operation"
				value="<%=DoctorListCtl.OP_NEXT%>"></td>
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

</html>