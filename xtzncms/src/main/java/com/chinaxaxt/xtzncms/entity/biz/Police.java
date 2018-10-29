package com.chinaxaxt.xtzncms.entity.biz;

import java.io.Serializable;
import java.util.Date;

/**
 * 智慧警务实体类
 * @author 吴佳涛
 * 2018-6-29
 */
public class Police implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
        /**
         *  智慧警务项目uid
         */
        private String uid;
        /**
         *  项目名称
         */
        private String name;
        /**
         *  项目简介
         */
        private String synopsis;
        /**
         *  项目详情
         */
        private String content;
        /**
         *  项目优势
         */
        private String virtue;
        /**
         * 展示图名称
         */
        private String imageName;
        /**
         *  产品图
         */
        private String image;
        /**
         *  产品链接
         */
        private String url;
        /**
         *  发布时间
         */
        private Date createTime;

        public String getUid() {
            return uid;
        }

        public Police setUid(String uid) {
            this.uid = uid;
            return this;
        }
        public String getName() {
            return name;
        }

        public Police setName(String name) {
            this.name = name;
            return this;
        }
        public String getSynopsis() {
            return synopsis;
        }

        public Police setSynopsis(String synopsis) {
            this.synopsis = synopsis;
            return this;
        }
        public String getContent() {
            return content;
        }

        public Police setContent(String content) {
            this.content = content;
            return this;
        }
        public String getVirtue() {
            return virtue;
        }

        public Police setVirtue(String virtue) {
            this.virtue = virtue;
            return this;
        }
        
        public String getImageName() {
			return imageName;
		}

		public void setImageName(String imageName) {
			this.imageName = imageName;
		}

		public String getImage() {
            return image;
        }

        public Police setImage(String image) {
            this.image = image;
            return this;
        }
        public String getUrl() {
            return url;
        }

        public Police setUrl(String url) {
            this.url = url;
            return this;
        }

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
        
}
