package in.co.sunrays.hca.controller;
import in.co.sunrays.common.controller.BaseCtl;

import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.hca.exception.ApplicationException;
import in.co.sunrays.hca.model.AppRole;
import in.co.sunrays.hca.model.ChatModel;
import in.co.sunrays.hca.model.DoctorModel;
import in.co.sunrays.hca.model.PatientModel;
import in.co.sunrays.util.AccessUtility;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.DataValidator;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
/**
 * Student functionality Controller. Performs operation for add, update, delete
 * and get Student
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */


public class ChatCtl extends BaseCtl
{
	/**
	 * Logger to log the messages.
	 */
	
	private static Logger log = Logger.getLogger(ChatCtl.class);
	
	/**
	 * Loads pre-loaded data like Dropdown List.
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		DoctorModel model = new DoctorModel();

		try {
			List list = model.search();
			request.setAttribute("doctorList", list);
		} catch (ApplicationException e) {
			log.error(e);
		}
		
		PatientModel model1 = new PatientModel();

		try {
			List list = model1.search();
			request.setAttribute("patientList", list);
		} catch (ApplicationException e) {
			log.error(e);
		}
	}
	/**
	 * Validates Input data
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("ChatCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("doctorId"))) {
			request.setAttribute("doctorId",
					PropertyReader.getValue("error.require","Doctor Id"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("patientId"))) {
			request.setAttribute("patientId",
					PropertyReader.getValue("error.require", "Patient Id"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("message"))) {
			request.setAttribute("message",
					PropertyReader.getValue("error.require", "Message"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("date"))) {
			request.setAttribute("date",
					PropertyReader.getValue("error.require", "Date"));
			pass = false;
		}
		
           log.debug("ChatCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {

		log.debug("ChatCtl Method populatebean Started");

		ChatModel model = new ChatModel();

		model.setId(DataUtility.getLong(request.getParameter("id")));

		// bean.setRoleId(RoleBean.STUDENT);
		model.setDoctorId(DataUtility.getString(request
				.getParameter("doctorId")));
		model.setPatientId(DataUtility.getString(request
				.getParameter("patientId")));
		model.setMessage(DataUtility.getString(request
				.getParameter("message")));
		model.setDate(DataUtility.getDate(request.getParameter("date")));
	
		populateModel(model, request);

		log.debug("ChatCtl Method populatemodel Ended");

		return model;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("ChatCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		ChatModel model = (ChatModel) populateModel(request);

		long id = model.getId();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			try {
				if (id > 0) {
					model.update();
				} else {
					long pk = model.add();
					model.setId(pk);
				}
				ServletUtility.setModel(model, request);
				ServletUtility.setSuccessMessage("Data is successfully saved",
						request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			try {
				model.delete();
				ServletUtility.redirect(ORSView.CHAT_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else {
			if (id > 0 || op != null) {
				ChatModel model1;
				try {
					model1 = model.findByPK(id);
					ServletUtility.setModel(model1, request);
				} catch (ApplicationException e) {

					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}
		ServletUtility.forwardView(ORSView.CHAT_VIEW, request, response);
		log.debug("ChatCtl Method doGet Ended");
	}

	/**
	 * Contains Display Logic
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long id = DataUtility.getLong(request.getParameter("id"));

		ChatModel model = new ChatModel();

		if (id > 0) {
			try {
				model = model.findByPK(id);
				ServletUtility.setModel(model, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forwardView(ORSView.CHAT_VIEW, request, response);
	}

	@Override
	protected String getView() {
		return ORSView.CHAT_VIEW;
	}
	
	@Override
	protected void setAccess(HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.setAccess(request);
		
		AccessUtility.setAddAccess("" + AppRole.PATIENT + AppRole.ADMIN, request);
		
		AccessUtility.setWriteAccess("" + AppRole.ADMIN, request);
		
	}
}
