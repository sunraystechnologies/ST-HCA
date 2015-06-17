package in.co.sunrays.hca.controller;
import in.co.sunrays.common.controller.BaseCtl;

import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.hca.exception.ApplicationException;
import in.co.sunrays.hca.exception.DuplicateRecordException;
import in.co.sunrays.hca.model.AppRole;
import in.co.sunrays.hca.model.AppointmentModel;
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

public class AppointmentCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(AppointmentCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		// AppointmentModel model = new AppointmentModel();
		/*
		 * try { // List l = model.list(); //
		 * request.setAttribute("PatientList", l); } catch (ApplicationException
		 * e) { log.error(e); }
		 */

	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("AppointmentCtl Method validate Started");

		boolean pass = true;

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
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("patientName"))) {
			request.setAttribute("patientName",
					PropertyReader.getValue("error.require", "Patient Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("patientAge"))) {
			request.setAttribute("patientAge",
					PropertyReader.getValue("error.require", "Patient Age"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("symptoms"))) {
			request.setAttribute("symptoms",
					PropertyReader.getValue("error.require", "Symptoms"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("weight"))) {
			request.setAttribute("weight",
					PropertyReader.getValue("error.require", "Weight"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("height"))) {
			request.setAttribute("height",
					PropertyReader.getValue("error.require", "Height"));
			pass = false;
		}

		log.debug("AppointmentCtl Method validate Ended");
		System.out.println("flag " + pass);
		return pass;

	}

	@Override
	protected AppointmentModel populateModel(HttpServletRequest request) {

		log.debug("AppointmentCtl Method populatemodel Started");

		AppointmentModel pmodel = new AppointmentModel();

		pmodel.setId(DataUtility.getLong(request.getParameter("id")));

		pmodel.setDate(DataUtility.getDate(request.getParameter("date")));

		pmodel.setTime(DataUtility.getTimestamp(request.getParameter("time")));
		pmodel.setClinicName(DataUtility.getString(request
				.getParameter("clinicName")));
		pmodel.setClinicAddress(DataUtility.getString(request
				.getParameter("clinicAddress")));
		pmodel.setClinicContact(DataUtility.getString(request
				.getParameter("clinicContact")));
		pmodel.setDob(DataUtility.getDate(request.getParameter("dob")));
		pmodel.setPatientName(DataUtility.getString(request
				.getParameter("patientName")));
		pmodel.setPatientAge(DataUtility.getInt(request
				.getParameter("patientAge")));
		pmodel.setSymptoms(DataUtility.getString(request
				.getParameter("symptoms")));
		pmodel.setWeight(DataUtility.getDouble(request.getParameter("weight")));
		pmodel.setHeight(DataUtility.getDouble(request.getParameter("height")));
System.out.println("height..........."+request.getParameter("height"));
		populateModel(pmodel, request);

		log.debug("AppointmentCtl Method populatemodel Ended");

		return pmodel;
	}
	/**
	 * Contains Display Logic
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long id = DataUtility.getLong(request.getParameter("id"));

		AppointmentModel model = new AppointmentModel();

		if (id > 0) {
			try {
				model = model.findByPK(id);
				ServletUtility.setModel(model, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forwardView(ORSView.APPOINTMENT_VIEW, request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("AppointmentCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		AppointmentModel model = (AppointmentModel) populateModel(request);

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
				ServletUtility.redirect(ORSView.APPOINTMENT_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else {
			if (id > 0 || op != null) {
				AppointmentModel model1;
				try {
					model1 = model.findByPK(id);
					ServletUtility.setModel(model1, request);
				} catch (ApplicationException e) {

					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}
		ServletUtility.forwardView(ORSView.APPOINTMENT_VIEW, request, response);
		log.debug("AppointmentCtl Method doGet Ended");
	}
	
	

	@Override
	protected String getView() {
		return ORSView.APPOINTMENT_VIEW;
	}
	
	@Override
	protected void setAccess(HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.setAccess(request);
		
		AccessUtility.setAddAccess("" + AppRole.PATIENT + AppRole.ADMIN, request);
		
		AccessUtility.setWriteAccess("" + AppRole.ADMIN, request);
		
	}
	
	
	
}	