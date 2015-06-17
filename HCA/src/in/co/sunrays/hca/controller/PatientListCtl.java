package in.co.sunrays.hca.controller;

import in.co.sunrays.common.controller.BaseCtl;

import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.hca.exception.ApplicationException;
import in.co.sunrays.hca.model.AppRole;
import in.co.sunrays.hca.model.DoctorModel;
import in.co.sunrays.hca.model.PatientModel;
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

public class PatientListCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(PatientListCtl.class);

	@Override
	protected PatientModel populateModel(HttpServletRequest request) {

		PatientModel model = new PatientModel();

		model.setName(DataUtility.getString(request.getParameter("name")));
		model.setAddress(DataUtility.getString(request.getParameter("address")));
		model.setContactNo(DataUtility.getString(request
				.getParameter("contactno")));
		model.setDob(DataUtility.getDate(request.getParameter("dob")));
		model.setWeight(DataUtility.getInt(request.getParameter("weight")));
		model.setHeight(DataUtility.getInt(request.getParameter("height")));
		model.setState(DataUtility.getString(request.getParameter("state")));
		model.setCity(DataUtility.getString(request.getParameter("city")));
		model.setEmailAddress(DataUtility.getString(request
				.getParameter("emailId")));
		
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

		PatientModel model = (PatientModel) populateModel(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		// get the selected checkbox ids array for delete list
		String[] ids = request.getParameterValues("ids");

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
					|| "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					ServletUtility.redirect(ORSView.PATIENT_LIST_CTL, request,
							response);
					return;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op)) {
					ServletUtility.redirect(ORSView.PATIENT_LIST_CTL, request,
							response);
					return;
				}

			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.PATIENT_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					PatientModel deletemodel = new PatientModel();
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
			ServletUtility.forwardView(ORSView.PATIENT_LIST_VIEW, request,
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
		return ORSView.PATIENT_LIST_VIEW;
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