package com.chinaxaxt.xtzncms.entity.biz;

import java.util.Date;
import java.io.Serializable;

/**
 * 新闻资讯实体类
 * @author 吴佳涛
 * 2018-6-29
 */
public class Info implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
        /**
         *  新闻资讯UID
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

        public Info setUid(String uid) {
            this.uid = uid;
            return this;
        }
        public String getTitle() {
            return title;
        }

        public Info setTitle(String title) {
            this.title = title;
            return this;
        }
        public String getRemark() {
            return remark;
        }

        public Info setRemark(String remark) {
            this.remark = remark;
            return this;
        }
        public String getImageUrl() {
            return imageUrl;
        }

        public Info setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }
        public String getAuthor() {
            return author;
        }

        public Info setAuthor(String author) {
            this.author = author;
            return this;
        }
        public String getContext() {
            return context;
        }

        public Info setContext(String context) {
            this.context = context;
            return this;
        }
        public Date getCreateTime() {
            return createTime;
        }

        public Info setCreateTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }
        public Integer getHitCount() {
            return hitCount;
        }

        public Info setHitCount(Integer hitCount) {
            this.hitCount = hitCount;
            return this;
        }
        public Integer getApplaudCount() {
            return applaudCount;
        }

        public Info setApplaudCount(Integer applaudCount) {
            this.applaudCount = applaudCount;
            return this;
        }
}
