package com.faculty.ust;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;
@Entity
public class BatchDTO implements Serializable {
	@Id
	@GenericGenerator(name="auto",strategy="increment")
	@GeneratedValue(generator="auto")
	private int bid;
	private String bname;
	private String fname;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(referencedColumnName="bid")
	private List<StudEntDTO> slist;

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public List<StudEntDTO> getSlist() {
		return slist;
	}

	public void setSlist(List<StudEntDTO> slist) {
		this.slist = slist;
	}

	
	
	
	

}

