package com.chinaxaxt.xtzncms.entity.dto;

import java.util.Date;
import java.io.Serializable;

/**
 * 智能板块接口实体类
 * @author 吴佳涛
 * 2018-7-16
 */
public class WorkDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
        /**
         *  智能板块
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
         *  展示图
         */
        private String[] image;
        /**
         *  上传人
         */
        private String author;
        /**
         *  上传时间
         */
        private Date createTime;

        public String getUid() {
            return uid;
        }

        public WorkDto setUid(String uid) {
            this.uid = uid;
            return this;
        }
        public String getName() {
            return name;
        }

        public WorkDto setName(String name) {
            this.name = name;
            return this;
        }
        public String getRemark() {
            return remark;
        }

        public WorkDto setRemark(String remark) {
            this.remark = remark;
            return this;
        }
        public String[] getImage() {
            return image;
        }

        public WorkDto setImage(String[] image) {
            this.image = image;
            return this;
        }
        public String getAuthor() {
            return author;
        }

        public WorkDto setAuthor(String author) {
            this.author = author;
            return this;
        }
        public Date getCreateTime() {
            return createTime;
        }

        public WorkDto setCreateTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }
        
}
