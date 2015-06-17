package in.co.sunrays.hca.controller;

/**
 * Contains ORS View and Controller URI
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */

public interface ORSView {

	public String APP_CONTEXT = "/HCA";

	public String PAGE_FOLDER = "/jsp";
	public String COMMON_FOLDER = "/common";
	public String USER_FOLDER = "/user";
	
	public String IMG_FOLDER = APP_CONTEXT + "/images";
	public String CSS_FOLDER = APP_CONTEXT + "/css";
	public String JS_FOLDER = APP_CONTEXT + "/js";
	
	// generic Views
	public String ERROR_VIEW = COMMON_FOLDER + "/Error.jsp";
	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";
	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public String COMMON_ATTRIBUTES = COMMON_FOLDER + "/CommonAttributes.jsp";

	// User Folder
	public String USER_VIEW = USER_FOLDER + "/UserView.jsp";
	public String USER_LIST_VIEW = USER_FOLDER + "/UserListView.jsp";
	public String USER_REGISTRATION_VIEW = USER_FOLDER
			+ "/UserRegistrationView.jsp";
	public String MY_PROFILE_VIEW = USER_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = USER_FOLDER
			+ "/ForgetPasswordView.jsp";
	public String CHANGE_PASSWORD_VIEW = USER_FOLDER
			+ "/ChangePasswordView.jsp";
	public String LOGIN_VIEW = USER_FOLDER + "/LoginView.jsp";
	
	
	//Common View
	public String WELCOME_VIEW = COMMON_FOLDER + "/Welcome.jsp";
	
  // Role View
	public String ROLE_VIEW = USER_FOLDER + "/RoleView.jsp";
	public String ROLE_LIST_VIEW = USER_FOLDER + "/RoleListView.jsp";

	// Marksheet Views

	public String ERESOURCE_VIEW = PAGE_FOLDER + "/EResourceView.jsp";
	public String ERESOURCE_List_VIEW = PAGE_FOLDER + "/EResourceListView.jsp";
	public String COMMENT_VIEW = PAGE_FOLDER + "/CommentView.jsp";
	public String COMMENT_LIST_VIEW = PAGE_FOLDER + "/CommentListView.jsp";
	

	public String GETATTENDENCE_VIEW = PAGE_FOLDER + "/GetAttendenceView.jsp";
	public String ERESOURCE_LINK_VIEW = PAGE_FOLDER + "/EResourceLinkView.jsp";
	
	public String DOCTOR_VIEW = PAGE_FOLDER + "/DoctorView.jsp";
	public String DOCTOR_LIST_VIEW = PAGE_FOLDER + "/DoctorListView.jsp";
	public String PATIENT_VIEW = PAGE_FOLDER + "/PatientView.jsp";
	public String PATIENT_LIST_VIEW = PAGE_FOLDER + "/PatientListView.jsp";
	public String APPOINTMENT_VIEW = PAGE_FOLDER + "/AppointmentView.jsp";
	public String APPOINTMENT_LIST_VIEW = PAGE_FOLDER + "/AppointmentListView.jsp";
	public String CHAT_VIEW = PAGE_FOLDER + "/ChatView.jsp";
	public String CHAT_LIST_VIEW = PAGE_FOLDER + "/ChatListView.jsp";
	public String REQUSTMASTER_VIEW = PAGE_FOLDER + "/RequestMasterView.jsp";
	public String REQUSTMASTER_LIST_VIEW = PAGE_FOLDER + "/RequestMasterListView.jsp";
	
	// generic Controller
	public String ERROR_CTL = "/ErrorCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/LoginCtl";

	// User Controller

	// Without Login
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ChangePasswordCtl";

	// After Login
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";

	// Role Controller
	public String ROLE_CTL = APP_CONTEXT + "/ctl/RoleCtl";
	public String ROLE_LIST_CTL = APP_CONTEXT + "/ctl/RoleListCtl";

	
	public String COMMENT_CTL = APP_CONTEXT + "/ctl/CommentCtl";
	public String COMMENT_LIST_CTL = APP_CONTEXT + "/ctl/CommentListCtl";
	public String ERESOURCE_CTL = APP_CONTEXT + "/ctl/EResourceCtl";
	public String ERESOURCE_LIST_CTL = APP_CONTEXT + "/ctl/EResourceListCtl";
		
	public String GETATTENDENCE_CTL = APP_CONTEXT + "/ctl/GetAttendenceCtl";
	public String ERESOURCE_LINK_CTL = APP_CONTEXT + "/ctl/EResourceLinkCtl";
	
	public String Download_CTL = APP_CONTEXT + "/ctl/DownloadCtl";
	public String DOCTOR_CTL = APP_CONTEXT + "/ctl/DoctorCtl";
	public String DOCTOR_LIST_CTL = APP_CONTEXT + "/ctl/DoctorListCtl";
	public String PATIENT_CTL = APP_CONTEXT + "/ctl/PatientCtl";
	public String PATIENT_LIST_CTL = APP_CONTEXT + "/ctl/PatientListCtl";
	public String APPOINTMENT_CTL = APP_CONTEXT + "/ctl/AppointmentCtl";
	public String APPOINTMENT_LIST_CTL = APP_CONTEXT + "/ctl/AppointmentListCtl";
	public String CHAT_CTL = APP_CONTEXT + "/ctl/ChatCtl";
	public String CHAT_LIST_CTL = APP_CONTEXT + "/ctl/ChatListCtl";
	public String REQUESTMASTER_CTL = APP_CONTEXT + "/ctl/RequestMasterCtl";
	public String REQUESTMASTER_LIST_CTL = APP_CONTEXT + "/ctl/RequestMasterListCtl";
}