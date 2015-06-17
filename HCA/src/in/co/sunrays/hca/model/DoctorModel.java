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
 * JDBC Implementation of Doctor Model
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public class DoctorModel extends BaseModel {

	private static Logger log = Logger.getLogger(DoctorModel.class);

	private static String columnNames = " ID,DOCTOR_NAME,DOCTOR_ADDRESS,DOB,AGE,STATE,CITY,EMAIL_ID,PASSWORD,QUALIFICATION,UNIVERSITY,YEAR_OF_PASSING,EXPERIENCE,CLINIC_NAME,CLINIC_ADDRESS,CLINIC_CONTACT_NO ";

	/**
	 * First Name of policeStation
	 */
	private String doctorName;
	private String doctorAddress;
	private Date dob;
	private int age;
	private String state;
	private String city;
	private String emailId;
	private String password;
	private String qualification;
	private String university;
	private Date yearOfPassing;
	private float experience;
	private String clinicName;
	private String clinicAddress;
	private String clinicContact;

	/**
	 * accessor
	 */

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorAddress() {
		return doctorAddress;
	}

	public void setDoctorAddress(String doctorAddress) {
		this.doctorAddress = doctorAddress;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public Date getYearOfpassing() {
		return yearOfPassing;
	}

	public void setYearOfPassing(Date yearOfPassing) {
		this.yearOfPassing = yearOfPassing;
	}

	public float getExperience() {
		return experience;
	}

	public void setExperience(float experience) {
		this.experience = experience;
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

	/**
	 * Add a
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public long add() throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		System.out.println("model " + getDoctorName() + " " + getClinicName());
		// get DoctorName
		// DoctorModel aModel = new DoctorModel();

		DoctorModel duplicatecode = findByPK(id);
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
					.prepareStatement("INSERT INTO ST_DOCTOR (ID,DOCTOR_NAME,DOCTOR_ADDRESS,DOB,"
							+ "AGE,STATE,CITY,EMAIL_ID,PASSWORD,QUALIFICATION,UNIVERSITY,YEAR_OF_PASSING,"
							+ "EXPERIENCE,CLINIC_NAME,CLINIC_ADDRESS,"
							+ "CLINIC_CONTACT_NO) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, doctorName);
			pstmt.setString(3, doctorAddress);
			pstmt.setDate(4, new java.sql.Date(dob.getDate()));
			pstmt.setInt(5, getAge());
			pstmt.setString(6, getState());
			pstmt.setString(7, getCity());
			pstmt.setString(8, getEmailId());
			pstmt.setString(9, getPassword());
			pstmt.setString(10, getQualification());
			pstmt.setString(11, getUniversity());
			pstmt.setDate(12, new java.sql.Date(yearOfPassing.getYear()));
			pstmt.setFloat(13, getExperience());
			pstmt.setString(14, getClinicName());
			pstmt.setString(15, getClinicAddress());
			pstmt.setString(16, getClinicContact());

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
					"Exception : Exception in adding Doctor");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	/**
	 * Delete a Doctor
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
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_DOCTOR WHERE ID=?");
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
					"Exception : Exception in delete Doctor");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	/**
	 * Find Student by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public DoctorModel findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_DOCTOR WHERE ID=?");
		DoctorModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new DoctorModel();
				model.setId(rs.getLong(1));
				model.setDoctorName(rs.getString(2));
				model.setDoctorAddress(rs.getString(3));
				model.setDob(rs.getDate(4));
				model.setAge(rs.getInt(5));
				model.setState(rs.getString(6));
				model.setCity(rs.getString(7));
				model.setEmailId(rs.getString(8));
				model.setPassword(rs.getString(9));
				model.setQualification(rs.getString(10));
				model.setUniversity(rs.getString(11));
				model.setYearOfPassing(rs.getDate(12));
				model.setExperience(rs.getFloat(13));
				model.setClinicName(rs.getString(14));
				model.setClinicAddress(rs.getString(15));
				model.setClinicContact(rs.getString(16));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting Doctor by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return model;
	}

	/**
	 * Update a Doctor
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */

	public void update() throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		DoctorModel beanExist = findByPK(id);

		// Check if updated Roll no already exist
		if (beanExist != null && beanExist.getId() != id) {
			throw new DuplicateRecordException("Id already exist");
		}

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE ST_DOCTOR SET DOCTOR_NAME=?,DOCTOR_ADDRESS =?,"
							+ "DOB=?,AGE=?,STATE=?,CITY=?,EMAIL_ID=?,PASSWORD=?,"
							+ "QUALIFICATION=?,UNIVERSITY=?,YEAR_OF_PASSING=?,"
							+ "EXPERIENCE=?,CLINIC_NAME=?,CLINIC_ADDRESS=?,"
							+ "CLINIC_CONTACT_NO=? WHERE ID=?");
						
			pstmt.setString(1, getDoctorName());
			pstmt.setString(2, getDoctorAddress());
			pstmt.setDate(3, new java.sql.Date(dob.getDate()));
			pstmt.setInt(4, getAge());
			pstmt.setString(5, getState());
			pstmt.setString(6, getCity());
			pstmt.setString(7, getEmailId());
			pstmt.setString(8, getPassword());
			pstmt.setString(9, getQualification());
			pstmt.setString(10, getUniversity());
			pstmt.setDate(11, new java.sql.Date(yearOfPassing.getYear()));
			pstmt.setFloat(12, getExperience());
			pstmt.setString(13, getClinicName());
			pstmt.setString(14, getClinicAddress());
			pstmt.setString(15, getClinicContact());
			pstmt.setLong(16, getId());

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
			throw new ApplicationException("Exception in updating st_doctor ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Search Doctor
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
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_DOCTOR WHERE 1=1");

			if (id > 0) {
				sql.append(" AND id = " + id);
			}
			if (clinicName != null
					&& clinicName.length() > 0) {
				sql.append(" AND CLINIC_NAME like '" + clinicName
						+ "%'");
			}
			if (doctorName != null
					&& doctorName.length() > 0) {
				sql.append(" AND DOCTOR_NAME like '" + doctorName
						+ "%'");
			}
			if (doctorAddress != null
					&& doctorAddress.length() > 0) {
				sql.append(" AND DOCTOR_ADDRESS like '"
						+ doctorAddress + "%'");
			}


		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList<DoctorModel> list = new ArrayList<DoctorModel>();
		Connection conn = null;
		try {
			System.out.println("SQL is " + sql);
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				DoctorModel	model = new DoctorModel();
				model.setId(rs.getLong(1));
				model.setDoctorName(rs.getString(2));
				model.setDoctorAddress(rs.getString(3));
				model.setDob(rs.getDate(4));
				model.setAge(rs.getInt(5));
				model.setState(rs.getString(6));
				model.setCity(rs.getString(7));
				model.setEmailId(rs.getString(8));
				model.setPassword(rs.getString(9));
				model.setQualification(rs.getString(10));
				model.setUniversity(rs.getString(11));
				model.setYearOfPassing(rs.getDate(12));
				model.setExperience(rs.getFloat(13));
				model.setClinicName(rs.getString(14));
				model.setClinicAddress(rs.getString(15));
				model.setClinicContact(rs.getString(16));
				list.add(model);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in searching Doctor");
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
		return doctorName + " " + clinicName;
	}

	@Override
	public String getTableName() {
		return "st_doctor";
	}
}
