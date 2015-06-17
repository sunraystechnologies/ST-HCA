package in.co.sunrays.hca.controller;
import in.co.sunrays.common.controller.BaseCtl;
import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.hca.exception.ApplicationException;
import in.co.sunrays.hca.exception.DuplicateRecordException;
import in.co.sunrays.hca.model.AppRole;
import in.co.sunrays.hca.model.PatientModel;
import in.co.sunrays.util.AccessUtility;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.DataValidator;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
/**
 * Station functionality Controller. Performs operation for add, update, delete
 * and get Station
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */

public class PatientCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(PatientCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		// PatientModel model = new PatientModel();
		/*
		 * try { // List l = model.list(); //
		 * request.setAttribute("PatientList", l); } catch (ApplicationException
		 * e) { log.error(e); }
		 */

	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("PatientCtl Method validate Started");

		boolean pass = true;
		
		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name",
					PropertyReader.getValue("error.require", "Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address",
					PropertyReader.getValue("error.require", "Address"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("contactNo"))) {
			request.setAttribute("contactNo",
					PropertyReader.getValue("error.require", "ContactNo"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.require", "Dob"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("weight"))) {
			request.setAttribute("weight",
					PropertyReader.getValue("error.require", "Weight"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("height"))) {
			request.setAttribute("height",
					PropertyReader.getValue("error.require", "height"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("state"))) {
			request.setAttribute("state",
					PropertyReader.getValue("error.require", "State"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city",
					PropertyReader.getValue("error.require", "City"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("emailId"))) {
			request.setAttribute("emailId",
					PropertyReader.getValue("error.require", "EmailId"));
			pass = false;
		}

		log.debug("PatientCtl Method validate Ended");
		System.out.println("flag " + pass);
		return pass;

	}

	@Override
	protected PatientModel populateModel(HttpServletRequest request) {

		log.debug("PatientCtl Method populatemodel Started");

		PatientModel pmodel = new PatientModel();

		pmodel.setId(DataUtility.getLong(request.getParameter("id")));

		pmodel.setName(DataUtility.getString(request.getParameter("name")));

		pmodel.setAddress(DataUtility.getString(request.getParameter("address")));
		pmodel.setContactNo(DataUtility.getString(request
				.getParameter("contactNo")));
		pmodel.setDob(DataUtility.getDate(request.getParameter("dob")));
		pmodel.setWeight(DataUtility.getInt(request.getParameter("weight")));
		pmodel.setHeight(DataUtility.getInt(request.getParameter("height")));
		pmodel.setState(DataUtility.getString(request.getParameter("state")));
		pmodel.setCity(DataUtility.getString(request.getParameter("city")));
		pmodel.setEmailAddress(DataUtility.getString(request
				.getParameter("emailId")));
		
		populateModel(pmodel, request);

		log.debug("PatientCtl Method populatemodel Ended");

		return pmodel;
	}
	/**
	 * Contains Display Logic
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long id = DataUtility.getLong(request.getParameter("id"));

		PatientModel model = new PatientModel();

		if (id > 0) {
			try {
				model = model.findByPK(id);
				ServletUtility.setModel(model, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forwardView(ORSView.PATIENT_VIEW, request, response);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("PatientCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		PatientModel model = (PatientModel) populateModel(request);

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
				ServletUtility.redirect(ORSView.PATIENT_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else {
			if (id > 0 || op != null) {
				PatientModel model1;
				try {
					model1 = model.findByPK(id);
					ServletUtility.setModel(model1, request);
				} catch (ApplicationException e) {

					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}
		ServletUtility.forwardView(ORSView.PATIENT_VIEW, request, response);
		log.debug("PatientCtl Method doGet Ended");
	}
	
	

	@Override
	protected String getView() {
		return ORSView.PATIENT_VIEW;
	}
	
	@Override
	protected void setAccess(HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.setAccess(request);
		
		AccessUtility.setAddAccess("" + AppRole.ADMIN, request);
		
		AccessUtility.setWriteAccess("" + AppRole.ADMIN, request);
		
	}
}
