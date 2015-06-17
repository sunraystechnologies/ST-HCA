<%@page import="in.co.sunrays.hca.model.AppRole"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.sunrays.common.controller.LoginCtl"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="in.co.sunrays.hca.controller.ORSView"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.common.controller.UserRegistrationCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.util.AccessUtility"%>


<jsp:useBean id="model" class="in.co.sunrays.common.model.UserModel"
	scope="request"></jsp:useBean>

	
<p class="st-title">
	User Registration Form
</p>

<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>

<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="POST">

<input type="hidden" name="createdBy" value="<%=model.getCreatedBy()%>">
	<input type="hidden" name="modifiedBy"
		value="<%=model.getModifiedBy()%>"> <input type="hidden"
		name="createdDatetime"
		value="<%=DataUtility.getTimestamp(model.getCreatedDatetime())%>">
	<input type="hidden" name="modifiedDatetime"
		value="<%=DataUtility.getTimestamp(model.getModifiedDatetime())%>">
	
	<table>
	<tr>
			<th>Register As*</th>
			<td><input type="radio"
				name="roleId" value="<%=AppRole.PATIENT%>"> Patient <input
				type="radio" name="roleId" value="<%=AppRole.DOCTOR%>">
				Doctor</td>
		</tr>
		<tr>
			<th>First Name*</th>
			<td><input type="text" name="firstName"
				value="<%=DataUtility.getStringData(model.getFirstName())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
		</tr>
		<tr>
			<th>Last Name*</th>
			<td><input type="text" name="lastName"
				value="<%=DataUtility.getStringData(model.getLastName())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
		</tr>
		<tr>
			<th>LoginId*</th>
			<td><input type="text" name="login"
				placeholder="Must be Email ID"
				value="<%=DataUtility.getStringData(model.getLogin())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
		</tr>
		<tr>
			<th>Password*</th>
			<td><input type="password" name="password"
				value="<%=DataUtility.getStringData(model.getPassword())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
		</tr>
		<tr>
			<th>Confirm Password*</th>
			<td><input type="password" name="confirmPassword"
				value="<%=DataUtility.getStringData(model.getConfirmPassword())%>"><font
				color="red"> <%=ServletUtility
					.getErrorMessage("confirmPassword", request)%></font></td>
		</tr>
		<tr>
			<th>Gender *</th>
			<td>
				<%
					HashMap map = new HashMap();
					map.put("M", "Male");
					map.put("F", "Female");

					String htmlList = HTMLUtility.getList("gender", model.getGender(),
							map);
				%> <%=htmlList%>

			</td>
		</tr>
		<tr>
			<th>Mobile No *</th>
			<td><input type="text" name="mobileNo"
				value="<%=DataUtility.getStringData(model.getMobileNo())%>">
				</a><font color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
		</tr>

		<tr>
			<th>Date Of Birth (mm/dd/yyyy) *</th>
			<td><input type="text" name="dob" readonly="readonly"
				value="<%=DataUtility.getDateString(model.getDob())%>"> <a
				href="javascript:getCalendar(document.forms[0].dob);"> <img
					src="<%=ORSView.IMG_FOLDER%>/cal.jpg" width="16" height="15" border="0"
					alt="Calender">
			</a><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
		</tr>
		<tr>
			<tr>
			<th></th>
			<td colspan="2" align="center"><input type="submit"
				name="operation" value="<%=UserRegistrationCtl.OP_SIGN_UP%>">
			</td>
		</tr>
	</table>
</form>
