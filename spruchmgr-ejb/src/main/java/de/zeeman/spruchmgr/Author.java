package de.zeeman.spruchmgr;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "author")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "Authors")
public class Author implements Serializable {

	@XmlElement(required = true)
	@Id @Column(name="Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;
	@XmlElement(required = true)
	@Basic
	@Column(name = "Lastname", length = 128)
	@Size(max = 128)
	protected String lastname;
	@XmlElement(required = true)
	@Basic
	@Column(name = "Firstname", length = 128)
	@Size(max = 128)
	protected String firstname;
	@XmlElement(required = false)
	@Basic
	@Column(name = "Work", length = 90, nullable = true)
	@Size(max = 90)
	protected String work;
	@XmlElement(required = false)
	@Temporal(TemporalType.DATE)
	@Column(name = "Birthday", nullable = true)
	protected Date birthday;
	@XmlElement(required = false)
	@Temporal(TemporalType.DATE)
	@Column(name = "Obit", nullable = true)
	protected Date obit;
//	@OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
//	@XmlTransient
//	protected List<Saying> sayings;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getObit() {
		return obit;
	}

	public void setObit(Date obit) {
		this.obit = obit;
	}
/*
	public List<Saying> getSayings() {
		return sayings;
	}

	public void setSayings(List<Saying> sayings) {
		this.sayings = sayings;
	}
*/
	@Override
	public String toString() {
		return "Author [id=" + id + ", lastname=" + lastname + ", firstname="
				+ firstname + ", work=" + work + ", birthday=" + birthday
				+ ", obit=" + obit + 
				//", sayings=" + sayings + 
				"]";
	}
	
}
