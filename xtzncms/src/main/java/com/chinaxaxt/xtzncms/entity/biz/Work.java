package com.chinaxaxt.xtzncms.entity.biz;

import java.util.Date;
import java.io.Serializable;

/**
 * 智能板块实体类
 * @author 吴佳涛
 * 2018-7-4
 */
public class Work implements Serializable{
	
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
        private String image;
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

        public Work setUid(String uid) {
            this.uid = uid;
            return this;
        }
        public String getName() {
            return name;
        }

        public Work setName(String name) {
            this.name = name;
            return this;
        }
        public String getRemark() {
            return remark;
        }

        public Work setRemark(String remark) {
            this.remark = remark;
            return this;
        }
        public String getImage() {
            return image;
        }

        public Work setImage(String image) {
            this.image = image;
            return this;
        }
        public String getAuthor() {
            return author;
        }

        public Work setAuthor(String author) {
            this.author = author;
            return this;
        }
        public Date getCreateTime() {
            return createTime;
        }

        public Work setCreateTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }
}
