package in.co.sunrays.hca.controller;
import in.co.sunrays.common.controller.BaseCtl;


import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.hca.exception.ApplicationException;
import in.co.sunrays.hca.model.AppRole;
import in.co.sunrays.hca.model.RequestMasterModel;
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


public class RequestMasterCtl extends BaseCtl {
	
	/**
	 * Logger to log the messages.
	 */
	
	private static Logger log = Logger.getLogger(RequestMasterCtl.class);     
	/**
	 * Loads pre-loaded data like Dropdown List.
	 */
	@Override
	protected void preload(HttpServletRequest request) {

    }

	/**
	 * Validates Input data
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("RequestMasterCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("patientId"))) {
			request.setAttribute("patientId",
					PropertyReader.getValue("error.require", "Patient Id"));
			
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("patientName"))) {
			request.setAttribute("patientName",
					PropertyReader.getValue("error.require", "Patient Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("doctorId"))) {
			request.setAttribute("doctorId",
					PropertyReader.getValue("error.require", "Doctor Id"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("doctorName"))) {
			request.setAttribute("doctorName",
					PropertyReader.getValue("error.require", "Doctor Name"));
			
			pass = false;
			
		}
		if (DataValidator.isNull(request.getParameter("date"))) {
			request.setAttribute("date",
					PropertyReader.getValue("error.require", "Date"));
			
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("time"))) {
			request.setAttribute("time",
					PropertyReader.getValue("error.require", "Time"));
			pass = false;
		}
		
		log.debug("RequestMasterCtl Method validate Ended    " +pass);

		return pass;
	}

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {

		log.debug("RequestMasterCtl Method populatebean Started");

		RequestMasterModel model = new RequestMasterModel();

		model.setId(DataUtility.getLong(request.getParameter("id")));

		// bean.setRoleId(RoleBean.STUDENT);
		model.setPatientId(DataUtility.getString(request
				.getParameter("patientId")));
		model.setPatientName(DataUtility.getString(request
				.getParameter("patientName")));
		model.setDoctorId(DataUtility.getString(request
				.getParameter("doctorId")));
		model.setDoctorName(DataUtility.getString(request
				.getParameter("doctorName")));
		model.setDate(DataUtility.getDate(request.getParameter("date")));
		
		model.setTime(DataUtility.getTimestamp(request
				.getParameter("time")));
		
		populateModel(model, request);

		log.debug("RequestMasterCtl Method populatemodel Ended");

		return model;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("RequestMasterCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		RequestMasterModel model = (RequestMasterModel) populateModel(request);

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
				ServletUtility.redirect(ORSView.REQUESTMASTER_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else {
			if (id > 0 || op != null) {
				RequestMasterModel model1;
				try {
					model1 = model.findByPK(id);
					ServletUtility.setModel(model1, request);
				} catch (ApplicationException e) {

					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}
		ServletUtility.forwardView(ORSView.REQUSTMASTER_VIEW, request, response);
		log.debug("RequestMasterCtl Method doGet Ended");
	}
	
	/**
	 * Contains Display Logic
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long id = DataUtility.getLong(request.getParameter("id"));

		RequestMasterModel model = new RequestMasterModel();

		if (id > 0) {
			try {
				model = model.findByPK(id);
				ServletUtility.setModel(model, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forwardView(ORSView.REQUSTMASTER_VIEW, request, response);
	}

	@Override
	protected String getView() {
		return ORSView.REQUSTMASTER_VIEW;
	}
	
	@Override
	protected void setAccess(HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.setAccess(request);
		
		AccessUtility.setAddAccess("" + AppRole.ADMIN, request);
		
		AccessUtility.setWriteAccess("" + AppRole.ADMIN, request);
		
	}
}