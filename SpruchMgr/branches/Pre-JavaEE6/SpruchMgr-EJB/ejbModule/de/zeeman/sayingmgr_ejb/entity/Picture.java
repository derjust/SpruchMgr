/**
 * Copyright (c) 2007-2009 R�PERWEISE Systems GmbH, Gehrden - GERMANY. All rights reserved.
 */
package de.zeeman.sayingmgr_ejb.entity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Hibernate;

@Entity
@Table(name = "Pictures")
public class Picture implements Serializable {
	private static final long serialVersionUID = -668060260276212232L;
	private int id;
	private String mime;
	private Long length;
	private byte[] image;

	public Picture() {
		super();
	}

	@SuppressWarnings("unused")
	private Blob getData() {
		return Hibernate.createBlob(this.image);
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	@Transient
	public byte[] getImage() {
		return this.image;
	}

	/**
	 * @return the length
	 */
	public Long getLength() {
		return length;
	}

	/**
	 * @return the mime
	 */
	public String getMime() {
		return mime;
	}

	@SuppressWarnings("unused")
	private void setData(Blob imageBlob) {
		this.image = toByteArray(imageBlob);
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(Long length) {
		this.length = length;
	}

	/**
	 * @param mime the mime to set
	 */
	public void setMime(String mime) {
		this.mime = mime;
	}

	private byte[] toByteArray(Blob fromImageBlob) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			return toByteArrayImpl(fromImageBlob, baos);
		} catch (Exception e) {
		}
		return null;
	}

	private byte[] toByteArrayImpl(Blob fromImageBlob,
			ByteArrayOutputStream baos) throws SQLException, IOException {
		byte buf[] = new byte[4000];
		int dataSize;
		InputStream is = fromImageBlob.getBinaryStream();

		try {
			while ((dataSize = is.read(buf)) != -1) {
				baos.write(buf, 0, dataSize);
			}
		} finally {
			if (is != null) {
				is.close();
			}
		}
		return baos.toByteArray();
	}

}
