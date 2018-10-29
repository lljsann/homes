package com.chinaxaxt.xtzncms.entity.biz;

import java.io.Serializable;
import java.util.Date;

/**
 * 荣誉资质实体类
 * @author 吴佳涛
 * 2018-6-29
 */
public class CompanyAptitude implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
        /**
         *  荣誉资质
         */
        private String uid;
        /**
         *  标题
         */
        private String title;
        /**
         *  图片
         */
        private String image;
        /**
         *  详情
         */
        private String detail;
        /**
         *  类型
         */
        private String type;
        /**
         *  添加时间
         */
        private Date createTime;

        public String getUid() {
            return uid;
        }

        public CompanyAptitude setUid(String uid) {
            this.uid = uid;
            return this;
        }
        public String getTitle() {
            return title;
        }

        public CompanyAptitude setTitle(String title) {
            this.title = title;
            return this;
        }
        public String getImage() {
            return image;
        }

        public CompanyAptitude setImage(String image) {
            this.image = image;
            return this;
        }
        public String getDetail() {
            return detail;
        }

        public CompanyAptitude setDetail(String detail) {
            this.detail = detail;
            return this;
        }

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
        
}
