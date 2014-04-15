package de.zeeman.spruchmgr;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "saying")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "Sayings")
public class Saying implements Serializable {

	private static final long serialVersionUID = 4781908991470834341L;

	@XmlElement(required = true)
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;

	@XmlElement(required = true)
	@Column(name = "Saying", columnDefinition = "TEXT NOT NULL")
	@Lob
	protected String saying;

	@XmlElement(required = true)
	@ManyToOne
	@JoinColumn(name = "AuthorId")
	protected Author author;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSaying() {
		return saying;
	}

	public void setSaying(String saying) {
		this.saying = saying;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Saying [id=" + id + ", saying=" + saying + ", author=" + author
				+ "]";
	}

}
