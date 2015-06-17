package in.co.sunrays.hca.controller;

import in.co.sunrays.common.controller.BaseCtl;

import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.hca.exception.ApplicationException;
import in.co.sunrays.hca.model.AppRole;
import in.co.sunrays.hca.model.AppointmentModel;
import in.co.sunrays.hca.model.DoctorModel;
import in.co.sunrays.util.AccessUtility;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Station List functionality Controller. Performs operation for list, search
 * and delete operations of Student
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */

public class AppointmentListCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(AppointmentListCtl.class);

	@Override
	protected AppointmentModel populateModel(HttpServletRequest request) {

		AppointmentModel model = new AppointmentModel();

		model.setDate(DataUtility.getDate(request.getParameter("date")));
		model.setTime(DataUtility.getTimestamp(request.getParameter("time")));
		model.setClinicName(DataUtility.getString(request
				.getParameter("clinicName")));
		model.setClinicAddress(DataUtility.getString(request
				.getParameter("clinicAddress")));
		model.setClinicContact(DataUtility.getString(request.getParameter("clinicContact")));
		model.setDate(DataUtility.getDate(request.getParameter("dob")));
		model.setPatientName(DataUtility.getString(request.getParameter("patientName")));
		model.setPatientAge(DataUtility.getInt(request.getParameter("patientAge")));
		model.setSymptoms(DataUtility.getString(request.getParameter("symptoms")));
		model.setWeight(DataUtility.getInt(request
				.getParameter("weight")));
		model.setHeight(DataUtility.getInt(request
				.getParameter("height")));
	

		return model;
	}
	/**
	 * Handles GET request.
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("DoctorListCtl doGet Start");

		List list = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
				.getValue("page.size")) : pageSize;

		AppointmentModel model = (AppointmentModel) populateModel(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		// get the selected checkbox ids array for delete list
		String[] ids = request.getParameterValues("ids");

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
					|| "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					ServletUtility.redirect(ORSView.APPOINTMENT_LIST_CTL, request,
							response);
					return;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op)) {
					ServletUtility.redirect(ORSView.APPOINTMENT_LIST_CTL, request,
							response);
					return;
				}

			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.APPOINTMENT_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					AppointmentModel deletemodel = new AppointmentModel();
					for (String id : ids) {
						deletemodel.setId(DataUtility.getInt(id));
						deletemodel.delete();
					}
				} else {
					ServletUtility.setErrorMessage(
							"Select at least one record", request);
				}
			}
			list = model.search(pageNo, pageSize);
			ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);

			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forwardView(ORSView.APPOINTMENT_LIST_VIEW, request,
					response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}

		log.debug("DoctorListCtl doGet End");
	}

	@Override
	protected String getView() {
		return ORSView.APPOINTMENT_LIST_VIEW;
	}

	@Override
	protected void setAccess(HttpServletRequest request) {
		super.setAccess(request);
		AccessUtility.setAddAccess(""+ AppRole.ADMIN,
				request);
		AccessUtility.setWriteAccess(""+ AppRole.ADMIN,
				request);
	}
	
	
}