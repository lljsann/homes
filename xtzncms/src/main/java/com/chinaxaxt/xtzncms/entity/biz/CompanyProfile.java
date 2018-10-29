package com.chinaxaxt.xtzncms.entity.biz;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司简介实体类
 * @author 吴佳涛
 * 2018-6-29
 */
public class CompanyProfile implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
        /**
         *  公司简介uid
         */
        private String uid;
        /**
         *  简介
         */
        private String content;
        /**
         *  标语
         */
        private String slogan;
        /**
         *  图片
         */
        private String image;
        /**
         *  添加时间
         */
        private Date createTime;

        public String getUid() {
            return uid;
        }

        public CompanyProfile setUid(String uid) {
            this.uid = uid;
            return this;
        }
        public String getContent() {
            return content;
        }

        public CompanyProfile setContent(String content) {
            this.content = content;
            return this;
        }
        public String getSlogan() {
            return slogan;
        }

        public CompanyProfile setSlogan(String slogan) {
            this.slogan = slogan;
            return this;
        }
        public String getImage() {
            return image;
        }

        public CompanyProfile setImage(String image) {
            this.image = image;
            return this;
        }

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		
        
}
