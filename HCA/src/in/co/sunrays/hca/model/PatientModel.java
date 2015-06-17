package in.co.sunrays.hca.model;

import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.hca.exception.ApplicationException;
import in.co.sunrays.hca.exception.DatabaseException;
import in.co.sunrays.hca.exception.DuplicateRecordException;
import in.co.sunrays.util.JDBCDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * JDBC Implementation of Patient Model
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public class PatientModel extends BaseModel {

	private static Logger log = Logger.getLogger(PatientModel.class);

	private static String columnNames = " ID,NAME,ADDRESS,CONTACT_NO,DOB,WEIGHT,HEIGHT,STATE,CITY,EMAIL_ID ";

	/**
	 * First Name of patient
	 */
	private String name;
	private String address;
	private String contactNo;
	private Date dob;
	private int weight;
	private int height;
	private String state;
	private String city;
	private String emailAddress;
	

	/**
	 * accessor
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * Add a patient
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public long add() throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		System.out.println("model " + getName() + " " + getAddress());

		// get patient Name
		// PatientModel pModel = new PatientModel();

		PatientModel duplicatecode = findByPK(id);
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
					.prepareStatement("INSERT INTO st_patient (" + columnNames
							+ ") values (?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, getName());
			pstmt.setString(3, getAddress());
			pstmt.setString(4, getContactNo());
			pstmt.setDate(5, new java.sql.Date(dob.getTime()));
			pstmt.setDouble(6, getWeight());
			pstmt.setDouble(7, getHeight());
			pstmt.setString(8, getState());
			pstmt.setString(9, getCity());
			pstmt.setString(10, getEmailAddress());
		
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
					"Exception : Exception in add Patient");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	/**
	 * Delete a patient
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
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM "
					+ getTableName() + " WHERE ID=?");
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
					"Exception : Exception in delete Patient");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	/**
	 * Find patient by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public PatientModel findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT " + columnNames + " FROM "
				+ getTableName() + " WHERE ID=?");
		PatientModel pmodel = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pmodel = new PatientModel();
				pmodel.setId(rs.getLong(1));
				pmodel.setName(rs.getString(2));
				pmodel.setAddress(rs.getString(3));
				pmodel.setContactNo(rs.getString(4));
				pmodel.setDob(rs.getDate(5));
				pmodel.setWeight(rs.getInt(6));
				pmodel.setHeight(rs.getInt(7));
				pmodel.setState(rs.getString(8));
				pmodel.setCity(rs.getString(9));
				pmodel.setEmailAddress(rs.getString(10));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting Station by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return pmodel;
	}

	/**
	 * Update a Patient
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */

	public void update() throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		PatientModel beanExist = findByPK(id);

		// Check if updated Roll no already exist
		if (beanExist != null && beanExist.getId() != id) {
			throw new DuplicateRecordException("Id is already exist");
		}

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE "
							+ getTableName()
							+ " SET NAME=?,ADDRESS=?,CONTACT_NO=?,DOB=?,WEIGHT=?,HEIGHT=?,STATE=?,CITY=?,EMAIL_ID=? WHERE ID=?");
			pstmt.setString(1, getName());
			pstmt.setString(2, getAddress());
			pstmt.setString(3, getContactNo());
			pstmt.setDate(4, new java.sql.Date(dob.getTime()));
			pstmt.setDouble(5, getWeight());
			pstmt.setDouble(6, getHeight());
			pstmt.setString(7, getState());
			pstmt.setString(8, getCity());
			pstmt.setString(9, getEmailAddress());
			pstmt.setLong(10, id);

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
			throw new ApplicationException("Exception in updating St_patient ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Search Patient
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws DatabaseException
	 */

	public List search() throws ApplicationException {
		return search(0, 0);
	}

	/**
	 * Search Patient with pagination
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
		StringBuffer sql = new StringBuffer("SELECT " + columnNames + " FROM "
				+ getTableName() + " WHERE 1=1");

		if (id > 0) {
			sql.append(" AND ID = " + id);
		}
			
			if (name != null && name.length() > 0) {
				sql.append(" AND NAME like '" + name + "%'");
			}
			if (address != null
					&& address.length() > 0) {
				sql.append(" AND EMAIL_ID like '" + address
						+ "%'");
			}
		
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList<PatientModel> list = new ArrayList<PatientModel>();
		Connection conn = null;
		try {
			System.out.println("SQL is " + sql);
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				PatientModel model = new PatientModel();
				model.setId(rs.getLong(1));
				model.setName(rs.getString(2));
				model.setAddress(rs.getString(3));
				model.setContactNo(rs.getString(4));
				model.setDob(rs.getDate(5));
				model.setWeight(rs.getInt(6));
				model.setHeight(rs.getInt(7));
				model.setState(rs.getString(8));
				model.setCity(rs.getString(9));
				model.setEmailAddress(rs.getString(10));
				list.add(model);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in search Patient");
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
		return name + " " + id;
	}

	@Override
	public String getTableName() {
		return "st_patient";
	}
}
