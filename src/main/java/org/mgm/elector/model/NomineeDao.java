package org.mgm.elector.model;

import java.util.List;

public interface NomineeDao {
	public List<Nominee> getNominees(Designation designation);
	public void addNominee(Nominee nominee);
	public void deleteNominee(Nominee nominee);
	public void addVote(List<Nominee> nominees);

}
