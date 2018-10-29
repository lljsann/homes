package com.chinaxaxt.xtzncms.entity.biz;

import java.util.Date;
import java.io.Serializable;

/**
 * 行业资讯实体类
 * @author 吴佳涛
 * 2018-6-25
 */
public class IndustryNews implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
        /**
         *  行业资讯UID
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

        public IndustryNews setUid(String uid) {
            this.uid = uid;
            return this;
        }
        public String getTitle() {
            return title;
        }

        public IndustryNews setTitle(String title) {
            this.title = title;
            return this;
        }
        public String getRemark() {
            return remark;
        }

        public IndustryNews setRemark(String remark) {
            this.remark = remark;
            return this;
        }
        public String getImageUrl() {
            return imageUrl;
        }

        public IndustryNews setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }
        public String getAuthor() {
            return author;
        }

        public IndustryNews setAuthor(String author) {
            this.author = author;
            return this;
        }
        public String getContext() {
            return context;
        }

        public IndustryNews setContext(String context) {
            this.context = context;
            return this;
        }
        public Date getCreateTime() {
            return createTime;
        }

        public IndustryNews setCreateTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }
        public Integer getHitCount() {
            return hitCount;
        }

        public IndustryNews setHitCount(Integer hitCount) {
            this.hitCount = hitCount;
            return this;
        }
        public Integer getApplaudCount() {
            return applaudCount;
        }

        public IndustryNews setApplaudCount(Integer applaudCount) {
            this.applaudCount = applaudCount;
            return this;
        }
}
