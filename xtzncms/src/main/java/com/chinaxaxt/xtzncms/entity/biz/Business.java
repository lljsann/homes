package com.chinaxaxt.xtzncms.entity.biz;

import java.util.Date;
import java.io.Serializable;

/**
 * 办公环境实体类
 * @author 吴佳涛
 * 2018-7-4
 */
public class Business implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    /**
     *  办公环境
     */
    private String uid;
    /**
     *  名称
     */
    private String name;
    /**
     *  简介
     */
    private String remark;
    /**
     *  地址
     */
    private String content;
    /**
     *  上传时间
     */
    private Date createTime;
    /**
     *  上传人
     */
    private String author;

    public String getUid() {
        return uid;
    }

    public Business setUid(String uid) {
        this.uid = uid;
        return this;
    }
    public String getName() {
        return name;
    }

    public Business setName(String name) {
        this.name = name;
        return this;
    }
    public String getRemark() {
        return remark;
    }

    public Business setRemark(String remark) {
        this.remark = remark;
        return this;
    }
    public String getContent() {
        return content;
    }

    public Business setContent(String content) {
        this.content = content;
        return this;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public Business setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
    public String getAuthor() {
        return author;
    }

    public Business setAuthor(String author) {
        this.author = author;
        return this;
    }
}
