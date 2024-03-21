package org.mgm.elector.model;

import java.util.List;

public interface DesignationDAO {

	public void adddesignation(Designation designation);
	public List<Designation> getAllDesignation(Election election);
	public void deleteDesignation(Designation designation);
}
