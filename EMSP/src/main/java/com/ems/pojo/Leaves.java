/**
 * 
 */
package com.ems.pojo;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author Christopher Dsouza
 *
 */
@Entity
@Table(name="leaves")
public class Leaves {
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="empID")
	private Employee employee;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="leaveID", nullable=false, unique=true)
	private int leaveID;

	@Column(name="leaveStartDate")
	private Date leaveStartDate;
	
	@Column(name="leaveEndDate")
	private Date leaveEndDate;
	
	@Column(name="approvalStatus")
	private char approvalStatus;
	
	@Column(name="rejectReason")
	private String rejectReason;

	public Leaves(Employee employee, Date leaveStartDate, Date leaveEndDate, char approvalStatus, String rejectReason) {
		this.employee = employee;
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.approvalStatus = approvalStatus;
		this.rejectReason = rejectReason;
	}

    public Leaves() {

    }

    /**
	 * @return the leaveID
	 */
	public int getLeaveID() {
		return leaveID;
	}

	/**
	 * @param leaveID the leaveID to set
	 */
	public void setLeaveID(int leaveID) {
		this.leaveID = leaveID;
	}

	/**
	 * @return the leaveStartDate
	 */
	public Date getLeaveStartDate() {
		return leaveStartDate;
	}

	/**
	 * @param leaveStartDate the leaveStartDate to set
	 */
	public void setLeaveStartDate(Date leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}

	/**
	 * @return the leaveEndDate
	 */
	public Date getLeaveEndDate() {
		return leaveEndDate;
	}

	/**
	 * @param leaveEndDate the leaveEndDate to set
	 */
	public void setLeaveEndDate(Date leaveEndDate) {
		this.leaveEndDate = leaveEndDate;
	}

	/**
	 * @return the approvalStatus
	 */
	public char getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * @param approvalStatus the approvalStatus to set
	 */
	public void setApprovalStatus(char approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * @return the rejectReason
	 */
	public String getRejectReason() {
		return rejectReason;
	}

	/**
	 * @param rejectReason the rejectReason to set
	 */
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}