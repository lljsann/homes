package com.chinaxaxt.xtzncms.entity.dto;

import java.io.Serializable;

/**
 * 资讯接口实体类
 * @author 吴佳涛
 * 2018-7-17
 */
public class CompanyNewsDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
        /**
         *  公司新闻UID
         */
        private String uid;
        /**
         *  标题
         */
        private String title;
        /**
         *  摘要
         */
        private String remark;
        /**
         *  图片url
         */
        private String imageUrl;
        /**
         * 资讯类型
         */
        private String type;
        
        /**
         *  发布人
         */
        private String author;
		/**
         *  内容
         */
        private String context;
        /**
         *  发布时间
         */
        private String createTime;
        /**
         *  阅读次数
         */
        private Integer hitCount;
        /**
         *  鼓掌次数
         */
        private Integer applaudCount;

        public String getUid() {
            return uid;
        }

        public CompanyNewsDto setUid(String uid) {
            this.uid = uid;
            return this;
        }
        public String getTitle() {
            return title;
        }

        public CompanyNewsDto setTitle(String title) {
            this.title = title;
            return this;
        }
        public String getRemark() {
            return remark;
        }

        public CompanyNewsDto setRemark(String remark) {
            this.remark = remark;
            return this;
        }
        
        public String getType() {
 			return type;
 		}

 		public void setType(String type) {
 			this.type = type;
 		}
        public String getImageUrl() {
            return imageUrl;
        }

        public CompanyNewsDto setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }
        public String getAuthor() {
            return author;
        }

        public CompanyNewsDto setAuthor(String author) {
            this.author = author;
            return this;
        }
        public String getContext() {
            return context;
        }

        public CompanyNewsDto setContext(String context) {
            this.context = context;
            return this;
        }
        public String getCreateTime() {
            return createTime;
        }

        public CompanyNewsDto setCreateTime(String createTime) {
            this.createTime = createTime;
            return this;
        }
        public Integer getHitCount() {
            return hitCount;
        }

        public CompanyNewsDto setHitCount(Integer hitCount) {
            this.hitCount = hitCount;
            return this;
        }
        public Integer getApplaudCount() {
            return applaudCount;
        }

        public CompanyNewsDto setApplaudCount(Integer applaudCount) {
            this.applaudCount = applaudCount;
            return this;
        }
}
