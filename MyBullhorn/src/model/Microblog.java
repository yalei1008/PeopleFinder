package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MICROBLOG database table.
 * 
 */
@Entity
@Table (name="Microblog", schema="testDB")
@NamedQuery(name="Microblog.findAll", query="SELECT m FROM Microblog m")
public class Microblog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String contect;

	public Microblog() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContect() {
		return this.contect;
	}

	public void setContect(String contect) {
		this.contect = contect;
	}

}