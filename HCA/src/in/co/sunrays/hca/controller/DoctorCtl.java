package in.co.sunrays.hca.controller;
import in.co.sunrays.common.controller.BaseCtl;

import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.hca.exception.ApplicationException;
import in.co.sunrays.hca.exception.DuplicateRecordException;
import in.co.sunrays.hca.model.AppRole;
import in.co.sunrays.hca.model.DoctorModel;
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

public class DoctorCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(DoctorCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		// DoctorModel model = new DoctorModel();
		/*
		 * try { // List l = model.list(); // request.setAttribute("PoliceList",
		 * l); } catch (ApplicationException e) { log.error(e); }
		 */

	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("DoctorCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("doctorName"))) {
			request.setAttribute("doctorName",
					PropertyReader.getValue("error.require", "Doctor Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("doctorAddress"))) {
			request.setAttribute("doctorAddress",
					PropertyReader.getValue("error.require", "Doctor Address"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.require", "Dob"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("age"))) {
			request.setAttribute("age",
					PropertyReader.getValue("error.require", "Age"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("state"))) {
			request.setAttribute("state", PropertyReader.getValue(
					"error.require", "State"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city", PropertyReader.getValue(
					"error.require", "Sity"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("emailId"))) {
			request.setAttribute("emailId", PropertyReader.getValue(
					"error.require", "Email Id"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.require", "Password"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("qualification"))) {
			request.setAttribute("qualification",
					PropertyReader.getValue("error.require", "Qualification"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("university"))) {
			request.setAttribute("university",
					PropertyReader.getValue("error.require", "University"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("yearOfPassing"))) {
			request.setAttribute("yearOfPassing",
					PropertyReader.getValue("error.require", "Year Of Passing"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("experience"))) {
			request.setAttribute("experience",
					PropertyReader.getValue("error.require", "Experience"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("clinicName"))) {
			request.setAttribute("clinicName",
					PropertyReader.getValue("error.require", "Clinic Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("clinicAddress"))) {
			request.setAttribute("clinicAddress",
					PropertyReader.getValue("error.require", "Clinic Address"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("clinicContact"))) {
			request.setAttribute("clinicContact",
					PropertyReader.getValue("error.require", "Clinic Contact"));
			pass = false;
		}
		
		log.debug("DoctorCtl Method validate Ended");
		System.out.println("flag " + pass);
		return pass;

	}

	@Override
	protected DoctorModel populateModel(HttpServletRequest request) {

		log.debug("DoctorCtl Method populatemodel Started");

		DoctorModel pmodel = new DoctorModel();

		pmodel.setId(DataUtility.getLong(request.getParameter("id")));

		pmodel.setDoctorName(DataUtility.getString(request.getParameter("doctorName")));

		pmodel.setDoctorAddress(DataUtility.getString(request.getParameter("doctorAddress")));

		pmodel.setDob(DataUtility.getDate(request.getParameter("dob")));

		pmodel.setAge(DataUtility.getInt(request
				.getParameter("age")));
		pmodel.setState(DataUtility.getString(request
				.getParameter("state")));
		pmodel.setCity(DataUtility.getString(request
				.getParameter("city")));
		pmodel.setEmailId(DataUtility.getString(request
				.getParameter("emailId")));
		pmodel.setPassword(DataUtility.getString(request.getParameter("password")));
		pmodel.setQualification(DataUtility.getString(request
				.getParameter("qualification")));
		pmodel.setUniversity(DataUtility.getString(request.getParameter("university")));
		pmodel.setYearOfPassing(DataUtility.getDate(request
				.getParameter("yearOfPassing")));
		pmodel.setExperience(DataUtility.getFloat(request
				.getParameter("experience")));
		pmodel.setClinicName(DataUtility.getString(request
				.getParameter("clinicName")));
		pmodel.setClinicAddress(DataUtility.getString(request
				.getParameter("clinicAddress")));
		pmodel.setClinicContact(DataUtility.getString(request
				.getParameter("clinicContact")));

		populateModel(pmodel, request);

		log.debug("DoctorCtl Method populatemodel Ended");

		return pmodel;
	}
	
	/**
	 * Contains Display Logic
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long id = DataUtility.getLong(request.getParameter("id"));

		DoctorModel model = new DoctorModel();

		if (id > 0) {
			try {
				model = model.findByPK(id);
				ServletUtility.setModel(model, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forwardView(ORSView.DOCTOR_VIEW, request, response);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("DoctorCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		DoctorModel model = (DoctorModel) populateModel(request);

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
				ServletUtility.redirect(ORSView.DOCTOR_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else {
			if (id > 0 || op != null) {
				DoctorModel model1;
				try {
					model1 = model.findByPK(id);
					ServletUtility.setModel(model1, request);
				} catch (ApplicationException e) {

					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}
		ServletUtility.forwardView(ORSView.DOCTOR_VIEW, request, response);
		log.debug("DoctorCtl Method doGet Ended");
	}
	

	@Override
	protected String getView() {
		return ORSView.DOCTOR_VIEW;
	}
	
	
}
