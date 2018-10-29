package com.chinaxaxt.xtzncms.entity.biz;

import java.util.Date;
import java.io.Serializable;

/**
 * 公司新闻实体类
 * @author 吴佳涛
 * 2018-6-25
 */
public class CompanyNews implements Serializable{
	
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
         * 资讯类型
         */
        private String type;
        
//        private static final int COMPANY_NEWS=1;
//        
//        private static final int INDUSTRY_NEWS=2;
        /**
         *  图片url
         */
        private String imageUrl;
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
        private Date createTime;
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

        public CompanyNews setUid(String uid) {
            this.uid = uid;
            return this;
        }
        public String getTitle() {
            return title;
        }

        public CompanyNews setTitle(String title) {
            this.title = title;
            return this;
        }
        public String getRemark() {
            return remark;
        }

        public CompanyNews setRemark(String remark) {
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

        public CompanyNews setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }
        public String getAuthor() {
            return author;
        }

        public CompanyNews setAuthor(String author) {
            this.author = author;
            return this;
        }
        public String getContext() {
            return context;
        }

        public CompanyNews setContext(String context) {
            this.context = context;
            return this;
        }
        public Date getCreateTime() {
            return createTime;
        }

        public CompanyNews setCreateTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }
        public Integer getHitCount() {
            return hitCount;
        }

        public CompanyNews setHitCount(Integer hitCount) {
            this.hitCount = hitCount;
            return this;
        }
        public Integer getApplaudCount() {
            return applaudCount;
        }

        public CompanyNews setApplaudCount(Integer applaudCount) {
            this.applaudCount = applaudCount;
            return this;
        }
}
