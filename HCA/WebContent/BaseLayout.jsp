<!DOCTYPE html>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.hca.model.AppRole"%>
<%@page import="in.co.sunrays.hca.controller.ORSView"%>
<html>
<head>
<meta charset="UTF-8">
<title>Health Care Adviser</title>
<meta name="description" content="Description of your site goes here">
<meta name="keywords" content="keyword1, keyword2, keyword3">
<link
	href="http://www.htmltemplates.net/preview/template_1/css/style.css"
	rel="stylesheet" type="text/css">
<script type='text/javascript' src='<%=ORSView.JS_FOLDER%>/calendar.js'></script>
<style type="text/css">
.errorMessage {
	color: red;
	font-size: 16px;
}

.scroll {
 height: 70%;
 width: 100%;
 overflow: auto;
 margin: 0;
 border: 1;
 scrollbar-face-color: #6095C1;
 scrollbar-highlight-color: #C2D7E7;
 scrollbar-3dlight-color: #85AECF;
 scrollbar-darkshadow-color: #427AA8;
 scrollbar-shadow-color: #315B7D;
 scrollbar-arrow-color: #FFFFFF;
 scrollbar-track-color: #4DECF8S;
 text-align: justify;

}

* html .scroll {
 overflow-y: scroll;
 overflow-x: hidden;
}
</style>

</head>
<body>
	<%@page import="java.io.Console"%>
	<%@page import="in.co.sunrays.util.MenuBuilder"%>
	<%@page import="in.co.sunrays.common.model.UserModel"%>
	<%@page import="in.co.sunrays.util.PropertyReader"%>
	<%@page import="in.co.sunrays.common.model.RoleModel"%>
	<%@page import="in.co.sunrays.common.controller.LoginCtl"%>
	<%@page import="in.co.sunrays.hca.controller.ORSView"%>
	<%
		UserModel userModel = ServletUtility.getUserModel(request);

		long roleId = ServletUtility.getRole(request);

		String welcomeMsg = "Hi, Guest ";

		if (ServletUtility.isLoggedIn(request)) {
			RoleModel roleModel = new RoleModel().findByPK(roleId);
			welcomeMsg = "Hi, " + userModel.getFirstName() + " ("
					+ roleModel.getName() + ")";
		}
	%>
	<div class="page-in">
		<div class="page">
			<div class="main">
				<div class="header">
					<div class="header-top">
						<h1>
							Health Care <span>Adviser</span>
						</h1>
					</div>
					<div class="header-bottom">
						<h2>Take the expert Doctor opinion for your Healthy Life.</h2>
					</div>
					<div class="topmenu">
						<ul>
							<li class="active"><a
								href="<%=ORSView.APP_CONTEXT%>/index.html"><span>Home</span></a></li>
							<li><a href="#"><span>Contact</span></a></li>
							<li><a href="#"><span>Resources</span></a></li>
							<%
								if (ServletUtility.isLoggedIn(request)) {
							%>
							<li><a href="<%=ORSView.LOGOUT_CTL%>"><span>LogOut</span></a></li>
							<%
								} else {
							%>
							<li><a href="<%=ORSView.USER_REGISTRATION_CTL%>"><span>SignUp</span></a></li>
							<li><a href="<%=ORSView.LOGIN_CTL%>"><span>Sign
										In</span></a></li>
							<%
								}
							%>
						</ul>
					</div>
				</div>
				<div class="content">
					<div class="content-left">
						<div class="row1">
							<h1 class="title">
								Welcome To <span>HCA</span>
							</h1>
						</div>
						<div class="row2">
							<p>&nbsp;</p>

							<tiles:insertAttribute name="body" />


							<%
									String bodyPage = null;

									bodyPage = (String) request.getAttribute("bodyPage");

									String headerPage = "/jsp/Header.jsp";
								%>


							<jsp:include page="<%=bodyPage%>"></jsp:include>


							<p>&nbsp;</p>
						</div>
					</div>
					<div class="content-right">
							<div class="mainmenu">
								<h2 class="sidebar1">Main Menu</h2>
								<H3><%=welcomeMsg%></H3>
								<div class="box">
									<%=MenuBuilder.getMenu(roleId, MenuBuilder.VERTICAL)%>
									<tiles:insertAttribute name="header" />
								</div>
								<div class="contact">
									<h2 class="sidebar2">Contact</h2>
									<div class="contact-detail">
										<p class="green">
											<strong>Lorem Ipsum is simply dummy text</strong>
										</p>
										<p>
											<strong>Adress :</strong> Lorem Ipsum has been the<br>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;dummy
											text the 1500s,
										</p>
										<p>
											<strong>E-mail :</strong> &nbsp;&nbsp;&nbsp;when an unknown
											printer took a
										</p>
										<p>
											<strong>Phone :</strong> &nbsp;&nbsp;&nbsp;00-0000000000<br>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;000-0000-0000
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
</body>
</html>