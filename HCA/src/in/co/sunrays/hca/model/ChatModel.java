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
 * JDBC Implementation of Chat Model
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */

public class ChatModel extends BaseModel
{
	private static Logger log = Logger.getLogger(ChatModel.class);
	
	private String doctorId;
	private String patientId;
	private String message;
	private Date date;
	

	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * Add a Chat
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
					.prepareStatement("INSERT INTO ST_CHAT(ID,DOCTOR_ID,PATIENT_ID,MESSAGE,DATE) VALUES(?,?,?,?,?)");
										
			pstmt.setLong(1, pk);
			pstmt.setString(2, doctorId);
			pstmt.setString(3, patientId);
			pstmt.setString(4, message);
			pstmt.setDate(5, new java.sql.Date(date.getTime()));
			
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
	 * Delete a Student Chat
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
					.prepareStatement("DELETE FROM ST_CHAT WHERE ID=?");

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
	 * Find Chat by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public ChatModel findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_CHAT  WHERE ID=?");

		ChatModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new ChatModel();
				model.setId(rs.getLong(1));
				model.setDoctorId(rs.getString(2));
				model.setPatientId(rs.getString(3));
				model.setMessage(rs.getString(4));
				model.setDate(rs.getDate(5));
	
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

	public ChatModel findByUserId(long userId) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_CHAT  WHERE USER_ID=?");

		ChatModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new ChatModel();
				model.setId(rs.getLong(1));
				model.setDoctorId(rs.getString(2));
				model.setPatientId(rs.getString(3));
				model.setMessage(rs.getString(4));
				model.setDate(rs.getDate(5));
	

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
	 * Update a Chat
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
					.prepareStatement("UPDATE ST_CHAT SET DOCTOR_ID=?,"
							+ "PATIENT_ID=?,MESSAGE=?,DATE=? WHERE ID=?");
							 
						

			pstmt.setString(1, doctorId);
			pstmt.setString(2, patientId);
			pstmt.setString(3, message);
			pstmt.setDate(4, new java.sql.Date(date.getTime()));
			pstmt.setLong(5, id);
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
	 * Search Chat
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws DatabaseException
	 */

	public List search() throws ApplicationException {
		return search(0, 0);
	}

	/**
	 * Search Chat with pagination
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

	public List search(int pageNo, int pageSize) throws ApplicationException {

		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_CHAT WHERE 1=1");

		if (id > 0) {
			sql.append(" AND id = " + id);
		}
		if (doctorId != null && doctorId.length() > 0) {
			sql.append(" AND DOCTOR_ID like '" + doctorId + "%'");
		}
		if (patientId != null && patientId.length() > 0) {
			sql.append(" AND PATIENT_ID like '" + patientId + "%'");
		}
		if (message != null && message.length() > 0) {
			sql.append(" AND FATHER_NAME like '" + message + "%'");
		}
		
		if (date != null && date.getDate() > 0) {
			sql.append(" AND DATE = " + date);
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
				ChatModel model = new ChatModel();
				model.setId(rs.getLong(1));
				model.setDoctorId(rs.getString(2));
				model.setPatientId(rs.getString(3));
				model.setMessage(rs.getString(4));
				model.setDate(rs.getDate(5));
				//model.setUserId(rs.getLong(14));

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
		return message ;
	}

	@Override
	public String getTableName() {
		return "ST_CHAT";
	}
}

