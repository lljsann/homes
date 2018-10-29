package com.chinaxaxt.xtzncms.entity.biz;

import java.util.Date;
import java.io.Serializable;

/**
 * 公司架构实体类
 * @author 吴佳涛
 * 2018-7-4
 */
public class CompanyStruct implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
        /**
         *  公司架构
         */
        private String uid;
        /**
         *  架构图
         */
        private String photo;
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

        public CompanyStruct setUid(String uid) {
            this.uid = uid;
            return this;
        }
        public String getPhoto() {
            return photo;
        }

        public CompanyStruct setPhoto(String photo) {
            this.photo = photo;
            return this;
        }
        public String getImage() {
            return image;
        }

        public CompanyStruct setImage(String image) {
            this.image = image;
            return this;
        }
        public String getAuthor() {
            return author;
        }

        public CompanyStruct setAuthor(String author) {
            this.author = author;
            return this;
        }
        public Date getCreateTime() {
            return createTime;
        }

        public CompanyStruct setCreateTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }
}
