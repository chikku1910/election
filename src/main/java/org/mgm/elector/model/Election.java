package org.mgm.elector.model;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Election implements Comparable<Election> ,Serializable{
 /**
	 * 
	 */
	private static final long serialVersionUID = 4643392154515950100L;

public	enum Status{
		VOTE,COUNT,RESULT;
	}
	@Id
	private String ElectionId;
	@Column(nullable = false)
	private String year;
	@Column(nullable = false, unique = true)
	private String electionName;
	private int VotersCount;
	private int TotalVoters;
	private Date electionDate;
	private Date nominationDate;
	private Date resultDate;
	private Status status=Status.VOTE;
	
	public Election() {
		super();
	}
	
	public int getVotersCount() {
		return VotersCount;
	}

	public void setVotersCount(int votersCount) {
		VotersCount = votersCount;
	}

	public int getTotalVoters() {
		return TotalVoters;
	}

	public void setTotalVoters(int totalVoters) {
		TotalVoters = totalVoters;
	}

	public String getElectionId() {
		return ElectionId;
	}
	public void setElectionId(String electionId) {
		if (electionId==null || electionId.isBlank())
			throw new IllegalArgumentException(" election id  cannot be null");
		ElectionId = electionId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		if (year==null || year.isBlank())
			throw new IllegalArgumentException(" year name cannot be null");
		this.year = year;
	}

	public Date getElectionDate() {
		return electionDate;
	}

	public void setElectionDate(Date electionDate) {
		this.electionDate = electionDate;
	}

	public Date getNominationDate() {
		return nominationDate;
	}

	public void setNominationDate(Date nominationDate) {
		this.nominationDate = nominationDate;
	}

	public Date getResultDate() {
		return resultDate;
	}

	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getElectionName() {
		return electionName;
	}

	public void setElectionName(String electionName) {
		if (electionName==null || electionName.isBlank())
			throw new IllegalArgumentException(" year name cannot be null");
		this.electionName = electionName;
	}

	@Override
	public int compareTo(Election o) {
		// TODO Auto-generated method stub
		return Integer.parseInt(year)-Integer.parseInt(o.year);
	}

	@Override
	public String toString() {
		return "Election [ElectionId=" + ElectionId + ", year=" + year + ", electionName=" + electionName
				+ ", VotersCount=" + VotersCount + ", TotalVoters=" + TotalVoters + ", electionDate=" + electionDate
				+ ", nominationDate=" + nominationDate + ", resultDate=" + resultDate + ", status=" + status + "]";
	}
	
	
}
