package com.xuetu.entity;

import java.io.Serializable;
import java.util.Date;

public class JiFenMingXi implements Serializable{

	private String imgUrl = null;
	private int unmpuint = 0;
	private String text = null;
	private Date time = null;
	private int id = 0;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getUnmpuint() {
		return unmpuint;
	}

	public void setUnmpuint(int unmpuint) {
		this.unmpuint = unmpuint;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JiFenMingXi(String imgUrl, int unmpuint, String text, Date time, int id) {
		super();
		this.imgUrl = imgUrl;
		this.unmpuint = unmpuint;
		this.text = text;
		this.time = time;
		this.id = id;
	}

	public JiFenMingXi() {
	}

}
