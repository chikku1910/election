package org.mgm.elector.model;

import java.util.List;

public interface ElectionDao {
	public void persistElection(Election election);
	public List<Election> getElections();
	public void deleteElection(String id);

}
