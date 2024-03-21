package org.mgm.elector.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Designation implements Comparable<Designation> ,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7975117634709114413L;
	public enum Post{
		CHAIRMAN,VICE_CHAIRMAN,REPRESENTATIVE_Y1,REPRESENTATIVE_Y2,REPRESENTATIVE_Y3,WOMEN_REPRESENTATIVE1,
		WOMEN_REPRESENTATIVE2,MAGAZINE_EDITOR,UNIVERSITY_UNION_COUNCILER1,UNIVERSITY_UNION_COUNCILER2,
		CIVIL_REPRESENTATIVE,MECH_REPRESENTATIVE,AUTO_REPRESENTATIVE,CT_REPRESENTATIVE,AI_REPRESENTATIVE,
		EC_REPRESENTATIVE,ARTS_CLUB_SECRETARY,GENERAL_SECRETARY;
	}
	@Id
	private String DesignationId;
	@Column(nullable = false)
	private String DesignationName;

	private int NomineeCount;
	private Post post;
	@ManyToOne
	private Election election;
	@OneToOne
	private Nominee winner;
	
	
	public Designation() {
		super();
	}
	public Designation(String designationId, String designationName, int nomineeCount) {
		super();
		DesignationId = designationId;
		DesignationName = designationName;
		NomineeCount = nomineeCount;
	}
	public String getDesignationId() {
		return DesignationId;
	}
	public void setDesignationId(String designationId) {
		if (designationId==null)
			throw new IllegalArgumentException("designation cannot be null");
		DesignationId = designationId;
	}
	
	public int getNomineeCount() {
		return NomineeCount;
	}
	public void setNomineeCount(int nomineeCount) {
		NomineeCount = nomineeCount;
	}
	public String getDesignationName() {
		return DesignationName;
	}
	public void setDesignationName(String designationName) {
		if (designationName==null)
			throw new IllegalArgumentException("designation name cannot be null");
		DesignationName = designationName;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public Election getElection() {
		return election;
	}
	public void setElection(Election election) {
		this.election = election;
	}
	public Nominee getWinner() {
		return winner;
	}
	public void setWinner(Nominee winner) {
		this.winner = winner;
	}
	@Override
	public int compareTo(Designation o) {
		// TODO Auto-generated method stub
		return post.compareTo(o.post);
	}

}
