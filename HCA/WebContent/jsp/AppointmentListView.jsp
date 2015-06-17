<%@page import="in.co.sunrays.hca.controller.AppointmentCtl"%>
<%@page import="in.co.sunrays.hca.controller.AppointmentListCtl"%>
<%@page import="in.co.sunrays.hca.controller.ORSView"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.hca.model.AppointmentModel"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.util.AccessUtility"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>

		<p class="st-title">Appointment List</p>

		<form action="<%=ORSView.APPOINTMENT_LIST_CTL%>">
			<table width="100%">
				<tr>
					<td align="center"><label>ClinicName :</label> <input type="text"
						name="clinicName"
						value="<%=ServletUtility.getParameter("clinicName", request)%>">
						<label>ClinicAddress :</label> <input type="text" name="clinicAddress"
						value="<%=ServletUtility.getParameter("clinicAddress", request)%>">
						<input type="submit" name="operation"
						value="<%=AppointmentListCtl.OP_SEARCH%>"></td>
				</tr>
			</table>
			<br>
			<div class="scroll">
			<table border="1">
				<tr>
					<th>S.No.</th>
					<th>Date.</th>
					<th>Time.</th>
					<th>ClinicName.</th>
					<th>ClinicAddress.</th>
					<th>ClinicContact.</th>
					<th>DOB.</th>
					<th>PatientName.</th>
					<th>PatientAge.</th>
					<th>Symptoms.</th>
					<th>Weight.</th>
					<th>Height.</th>
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
					Iterator<AppointmentModel> it = list.iterator();

					while (it.hasNext()) {

						AppointmentModel model = it.next();
				%>
				<tr>
				<td><input type="checkbox" name="ids" value="<%=model.getId()%>"></td>
					<td><%=model.getDate()%></td>
					<td><%=model.getTime()%></td>
					<td><%=model.getClinicName()%></td>
					<td><%=model.getClinicAddress()%></td>
					<td><%=model.getClinicContact()%></td>
					<td><%=model.getDob()%></td>
					<td><%=model.getPatientName()%></td>
					<td><%=model.getPatientAge()%></td>
					<td><%=model.getSymptoms()%></td>
					<td><%=model.getWeight()%></td>
					<td><%=model.getHeight()%></td>
					<td>
				<%
					String label = (AccessUtility.canWrite(request)) ? "Edit"
								: "View";
				%> <a href="<%=ORSView.APPOINTMENT_CTL%>?id=<%=model.getId()%>"><%=label%></a>

			</td>
				</tr>
				<%
					}
				%>
			</table>
			</div>
			<table width="100%">
				<tr>
					<td><input type="submit" name="operation"
						value="<%=AppointmentListCtl.OP_PREVIOUS%>"></td>
						
							<td><%=HTMLUtility.getSubmitButton(BaseCtl.OP_NEW,
					AccessUtility.canAdd(request), request)%><%=HTMLUtility.getSubmitButton(BaseCtl.OP_DELETE,
					AccessUtility.canDelete(request), request)%></td>
						
					<td align="right"><input type="submit" name="operation"
						value="<%=AppointmentListCtl.OP_NEXT%>"></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"><input
				type="hidden" name="pageSize" value="<%=pageSize%>">


		</form>
	</center>
</body>
</html>