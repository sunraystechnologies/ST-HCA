package in.co.sunrays.hca.model;
import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.hca.exception.ApplicationException;
import in.co.sunrays.hca.exception.DatabaseException;
import in.co.sunrays.util.JDBCDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;


/**
 * JDBC Implementation of RequestMaster Model
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */



public class RequestMasterModel extends BaseModel
{
	private static Logger log = Logger.getLogger(RequestMasterModel.class);

	private String patientId;
	private String patientName;
	private String doctorId;
	private String doctorName;
	private Timestamp time;
	private Date date;
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
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
	
	/**
	 * Add a RequestMaster
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public long add() throws ApplicationException {
		log.debug("Model add Started");

		Connection conn = null;

		long pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO ST_REQUEST_MASTER (ID,PATIENT_ID,PATIENT_NAME,"
							+ "DOCTOR_ID,DOCTOR_NAME,DATE,TIME) VALUES(?,?,?,?,?,?,?)");
							
			pstmt.setLong(1, pk);
			pstmt.setString(2, patientId);
			pstmt.setString(3, patientName);
			pstmt.setString(4, doctorId);
			pstmt.setString(5, doctorName);
			pstmt.setDate(6, new java.sql.Date(date.getTime()));
			pstmt.setTimestamp(7, new java.sql.Timestamp(new Date().getTime()));
		
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
			this.setId(pk);
			updateCreatedInfo();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			JDBCDataSource.trnRollback(conn);
			throw new ApplicationException(e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	/**
	 * Delete a RequestMaster
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
			PreparedStatement pstmt = conn
					.prepareStatement("DELETE FROM ST_REQUEST_MASTER WHERE ID=?");

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
					"Exception : Exception in delete Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	/**
	 * Find RequestMaster by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public RequestMasterModel  findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_REQUEST_MASTER WHERE ID=?");

		RequestMasterModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new RequestMasterModel();
				model.setId(rs.getLong(1));
				model.setPatientId(rs.getString(2));
				model.setPatientName(rs.getString(3));
				model.setDoctorId(rs.getString(4));
				model.setDoctorName(rs.getString(5));
				model.setDate(rs.getDate(6));
				model.setTime(rs.getTimestamp(7));
			
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return model;
	}

	
	public RequestMasterModel findByUserId(long userId) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_REQUEST_MASTER WHERE USER_ID=?");

		RequestMasterModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new RequestMasterModel();
				model = new RequestMasterModel();
				model.setId(rs.getLong(1));
				model.setPatientId(rs.getString(2));
				model.setPatientName(rs.getString(3));
				model.setDoctorId(rs.getString(4));
				model.setDoctorName(rs.getString(5));
				model.setDate(rs.getDate(6));
				model.setTime(rs.getTimestamp(7));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return model;
	}
	
	/**
	 * Update a RequestMaster
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */

	public void update() throws ApplicationException {
		log.debug("Model update Started");
		Connection conn = null;
		
		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE ST_REQUEST_MASTER  SET PATIENT_ID=?,"
							+ "PATIENT_NAME=?,DOCTOR_ID=?,DOCTOR_NAME=?,DATE=?,TIME=? WHERE ID=?");
							
							

			pstmt.setString(1, patientId);
			pstmt.setString(2, patientName);
			pstmt.setString(3, doctorId);
			pstmt.setString(4, doctorName);
			pstmt.setDate(5, new java.sql.Date(date.getTime()));
			pstmt.setTimestamp(6, new java.sql.Timestamp(new Date().getTime()));
			pstmt.setLong(7, id);
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
			updateModifiedInfo();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			JDBCDataSource.trnRollback(conn);
			throw new ApplicationException(e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Search RequestMaster
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws DatabaseException
	 */

	public List search() throws ApplicationException {
		return search(0, 0);
	}

	/**
	 * Search RequestMaster with pagination
	 * 
	 * @return list : List of Students
	 * @param bean
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
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_REQUEST_MASTER WHERE 1=1");
				 

		if (id > 0) {
			sql.append(" AND id = " + id);
		}
			if (patientId != null
					&& patientId.length() > 0) {
				sql.append(" AND PATIENT_ID like '" + patientId
						+ "%'");
			}
			if (patientName != null && patientName.length() > 0) {
				sql.append(" AND PATIENT_NAME like '" + patientName + "%'");
			}
			if (doctorId != null
					&& doctorId.length() > 0) {
				sql.append(" AND DOCTOR_ID like '" + doctorId
						+ "%'");
			}
			if (doctorName != null
					&& doctorName.length() > 0) {
				sql.append(" AND DOCTOR_NAME like '" + doctorName
						+ "%'");
			}
			
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RequestMasterModel	model = new RequestMasterModel();
				model.setId(rs.getLong(1));
				model.setPatientId(rs.getString(2));
				model.setPatientName(rs.getString(3));
				model.setDoctorId(rs.getString(4));
				model.setDoctorName(rs.getString(5));
				model.setDate(rs.getDate(6));
				model.setTime(rs.getTimestamp(7));
		
				list.add(model);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in search Student");
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
		return patientName + " " + doctorName;
	}

	@Override
	public String getTableName() {
		return "ST_REQUEST_MASTER";
	}
	
}
	
	
	

