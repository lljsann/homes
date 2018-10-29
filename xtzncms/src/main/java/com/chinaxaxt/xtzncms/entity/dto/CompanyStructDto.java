package com.chinaxaxt.xtzncms.entity.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司架构接口实体类 
 * @author 吴佳涛
 * 2018-7-16
 */
public class CompanyStructDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 公司架构
	 */
	private String uid;
	/**
	 * 架构图
	 */
	private String photo;
	/**
	 * 展示图
	 */
	private String[] image;
	/**
	 * 上传人
	 */
	private String author;
	/**
	 * 上传时间
	 */
	private Date createTime;

	public String getUid() {
		return uid;
	}

	public CompanyStructDto setUid(String uid) {
		this.uid = uid;
		return this;
	}

	public String getPhoto() {
		return photo;
	}

	public CompanyStructDto setPhoto(String photo) {
		this.photo = photo;
		return this;
	}

	public String[] getImage() {
		return image;
	}

	public CompanyStructDto setImage(String[] image) {
		this.image = image;
		return this;
	}

	public String getAuthor() {
		return author;
	}

	public CompanyStructDto setAuthor(String author) {
		this.author = author;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public CompanyStructDto setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

}
