package org.mgm.elector.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;


@Entity
public class Nominee implements Serializable
{
/**
	 * 
	 */
	private static final long serialVersionUID = -4903422133741978168L;
@Id
private String nomineeId;
@ManyToOne
private Student nominee;
@ManyToOne
private Designation designation;
@Lob( )
@Column(columnDefinition = "MEDIUMBLOB")
private byte[] symbol;
private int vote;
public String getNomineeId() {
	return nomineeId;
}
public void setNomineeId(String nomineeId) {
	this.nomineeId = nomineeId;
}
public Student getNominee() {
	return nominee;
}
public void setNominee(Student nominee) {
	this.nominee = nominee;
}

public byte[] getSymbol() {
	return symbol;
}
public void setSymbol(byte[] symbol) {
	this.symbol = symbol;
}
public Designation getDesignation() {
	return designation;
}
public void setDesignation(Designation designation) {
	this.designation = designation;
}
public int getVote() {
	return vote;
}
public void setVote(int vote) {
	this.vote = vote;
}



}
