package in.co.sunrays.hca.model;

import in.co.sunrays.util.JDBCDataSource;
import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.hca.exception.ApplicationException;
import in.co.sunrays.hca.exception.DatabaseException;
import in.co.sunrays.hca.exception.DuplicateRecordException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Time;

import org.apache.log4j.Logger;

import com.sun.org.apache.xerces.internal.impl.dv.xs.YearMonthDV;


/**
 * JDBC Implementation of Appointment Model
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public class AppointmentModel extends BaseModel {

	private static Logger log = Logger.getLogger(AppointmentModel.class);

	private static String columnNames = "ID,DATE,TIME,CLINIC_NAME,"
			+ "CLINIC_ADDRESS,CLINIC_CONTACT_NO,"
			+ "DOB,PATIENT_NAME,PATIENT_AGE,SYMPTOMS,WEIGHT,HEIGHT";

	/**
	 * First Name of policeStation
	 */
	private Date date;
	private Timestamp time;
	private String clinicName;
	private String clinicAddress;
	private String clinicContact;
	private Date dob;
	private String patientName;
	private int patientAge;
	private String symptoms;
	private double weight;
	private double height;

	/**
	 * accessor
	 */
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public String getClinicAddress() {
		return clinicAddress;
	}

	public void setClinicAddress(String clinicAddress) {
		this.clinicAddress = clinicAddress;
	}

	public String getClinicContact() {
		return clinicContact;
	}

	public void setClinicContact(String clinicContact) {
		this.clinicContact = clinicContact;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public int getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * Add a Appointment
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public long add() throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		System.out.println("model " + getClinicName() + " "
				+ getClinicContact());
		// get ClinicName
		// AppointmentModel aModel = new AppointmentModel();

		AppointmentModel duplicatecode = findByPK(id);
		long pk = 0;

		if (duplicatecode != null) {
			throw new DuplicateRecordException("Id already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO st_appointment ("
							+ columnNames
							+ ") values (?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setDate(2, new java.sql.Date(date.getTime()));
			pstmt.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
			pstmt.setString(4, clinicName);
			pstmt.setString(5, clinicAddress);
			pstmt.setString(6, clinicContact);
			pstmt.setDate(7, new java.sql.Date(dob.getTime()));
			pstmt.setString(8, patientName);
			pstmt.setInt(9, patientAge);
			pstmt.setString(10, symptoms);
			pstmt.setDouble(11, weight);
			System.out.println("height..........abbbbbbb"+height);
			pstmt.setDouble(12, height);

			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
			updateCreatedInfo();
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException(
					"Exception : Exception in add Appointment");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	/**
	 * Delete a Appointment
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */
	public void delete() throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM st_appointment  WHERE ID=?");
					
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : Delete rollback exception "
								+ ex.getMessage());
			}
			throw new ApplicationException(
					"Exception : Exception in delete Appointment");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	/**
	 * Find Appointment by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public AppointmentModel findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM st_appointment  WHERE ID=?");
				
		AppointmentModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new AppointmentModel();
				model.setId(rs.getLong(1));
				model.setDate(rs.getDate(2));
				model.setTime(rs.getTimestamp(3));
				model.setClinicName(rs.getString(4));
				model.setClinicAddress(rs.getString(5));
				model.setClinicContact(rs.getString(6));
				model.setDob(rs.getDate(7));
				model.setPatientName(rs.getString(8));
				model.setPatientAge(rs.getInt(9));
				model.setSymptoms(rs.getString(10));
				model.setWeight(rs.getDouble(11));
				model.setHeight(rs.getDouble(12));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting Appointment by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return model;
	}

	/**
	 * Update a Appointment
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */

	public void update() throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		AppointmentModel beanExist = findByPK(id);

		// Check if updated Roll no already exist
		if (beanExist != null && beanExist.getId() != id) {
			throw new DuplicateRecordException("Id already exist");
		}

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE st_appointment SET DATE=?,TIME=?,"
							+ "CLINIC_NAME=?,CLINIC_ADDRESS=?,CLINIC_CONTACT_NO=?,"
							+ "DOB=?,PATIENT_NAME=?,PATIENT_AGE=?,SYMPTOMS=?,"
							+ "WEIGHT=?,HEIGHT=? WHERE ID=?");
			System.out.println("Date................"+dob);				
			pstmt.setDate(1, new java.sql.Date(date.getTime()));
			pstmt.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
			pstmt.setString(3, clinicName);
			pstmt.setString(4, clinicAddress);
			pstmt.setString(5, clinicContact);
			
			pstmt.setDate(6, new java.sql.Date(dob.getTime()));
			pstmt.setString(7, patientName);
			pstmt.setInt(8, patientAge);
			pstmt.setString(9, symptoms);
			pstmt.setDouble(10, weight);
			pstmt.setDouble(11, height);
			pstmt.setLong(12, id);

			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
			updateModifiedInfo();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : Delete rollback exception "
								+ ex.getMessage());
			}
			throw new ApplicationException(
					"Exception in updating st_appointment ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Search appointment
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws DatabaseException
	 */

	public List search() throws ApplicationException {
		return search(0, 0);
	}

	/**
	 * Search Station with pagination
	 * 
	 * @return list : List of Station
	 * @param model
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */

	public List search(int pageNo, int pageSize)
			throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM st_appointment WHERE 1=1");
				

		if (id > 0) {
			sql.append(" AND id = " + id);
		}

			if (clinicName != null
					&& clinicName.length() > 0) {
				sql.append(" AND CLINIC_NAME like '" + clinicName+ "%'");
			}
			if (clinicAddress != null
					&& clinicAddress.length() > 0) {
				sql.append(" AND CLINIC_ADDRESS like '" + clinicAddress + "%'");
			}



		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList<AppointmentModel> list = new ArrayList<AppointmentModel>();
		Connection conn = null;
		try {
			System.out.println("SQL is " + sql);
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				AppointmentModel model = new AppointmentModel();
				model.setId(rs.getLong(1));
				model.setDate(rs.getDate(2));
				model.setTime(rs.getTimestamp(3));
				model.setClinicName(rs.getString(4));
				model.setClinicAddress(rs.getString(5));
				model.setClinicContact(rs.getString(6));
				model.setDob(rs.getDate(7));
				model.setPatientName(rs.getString(8));
				model.setPatientAge(rs.getInt(9));
				model.setSymptoms(rs.getString(10));
				model.setWeight(rs.getDouble(11));
				model.setHeight(rs.getDouble(12));
				list.add(model);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in searching Appointment");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return patientName + " " + clinicName;
	}

	@Override
	public String getTableName() {
		return "st_appointment";
	}
}
