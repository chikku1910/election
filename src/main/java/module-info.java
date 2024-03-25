open module elector {
	
	exports org.mgm.elector.control;
	exports org.mgm.elector.model;
	exports org.mgm.elector.gui;

	requires jakarta.persistence;
	requires java.desktop;
	requires java.naming;
	requires java.sql;
	requires java.xml;
	requires jcalendar;
	requires org.hibernate.orm.core;
	requires spring.beans;
	requires spring.context;
	requires spring.core;
	
}