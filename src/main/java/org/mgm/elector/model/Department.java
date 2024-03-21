package org.mgm.elector.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Department implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3028727308951284430L;
	@Id
	private String deptId;
	@Column(nullable = false)
	private String deptName;
	public Department(String deptId, String deptName) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
	}
	public Department() {
		super();
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		if (deptId==null || !deptId.startsWith("D"))
			throw new IllegalArgumentException("street cannot be null and starts with D");
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		if (deptName==null)
			throw new IllegalArgumentException("district cannot be null");
		this.deptName = deptName;
	}
	@Override
	public String toString() {
		return  deptName ;
	}

}
