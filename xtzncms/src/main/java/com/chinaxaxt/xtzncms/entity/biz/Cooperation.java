package com.chinaxaxt.xtzncms.entity.biz;

import java.io.Serializable;
import java.util.Date;

/**
 * 合作单位实体类
 * @author 吴佳涛
 * 2018-7-4
 */
public class Cooperation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
        /**
         *  合作单位
         */
        private String uid;
        /**
         *  单位名称
         */
        private String name;
        /**
         *  单位图片
         */
        private String photo;
        /**
         *  单位链接
         */
        private String url;
        /**
         *  创建时间
         */
        private Date createTime;

		public String getUid() {
            return uid;
        }

        public Cooperation setUid(String uid) {
            this.uid = uid;
            return this;
        }
        public String getName() {
            return name;
        }

        public Cooperation setName(String name) {
            this.name = name;
            return this;
        }
        public String getPhoto() {
            return photo;
        }

        public Cooperation setPhoto(String photo) {
            this.photo = photo;
            return this;
        }
        public String getUrl() {
            return url;
        }

        public Cooperation setUrl(String url) {
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
