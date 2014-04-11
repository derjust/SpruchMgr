/**
 * Copyright (c) 2007-2009 RÖPERWEISE Systems GmbH, Gehrden - GERMANY. All rights reserved.
 */
package de.zeeman.sayingmgr_ejb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Authors")
public class Author implements Serializable {
	private static final long serialVersionUID = 1983995244909773103L;
	private int id;
	private String firstname;
	private String lastname;
	private Date birthday;
	private Date obit;
	private String work;
	private Picture picture;

	public Author() {
		super();
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @return the obit
	 */
	public Date getObit() {
		return obit;
	}

	/**
	 * @return the picture
	 */
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="PictureId")
	public Picture getPicture() {
		return picture;
	}

	/**
	 * @return the work
	 */
	public String getWork() {
		return work;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @param obit the obit to set
	 */
	public void setObit(Date obit) {
		this.obit = obit;
	}

	/**
	 * @param picture the picture to set
	 */
	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	/**
	 * @param work the work to set
	 */
	public void setWork(String work) {
		this.work = work;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(getFirstname()).append(" ").append(getLastname());
		return sb.toString();
	}
}
