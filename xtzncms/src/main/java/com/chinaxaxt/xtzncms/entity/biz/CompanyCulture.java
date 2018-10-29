package com.chinaxaxt.xtzncms.entity.biz;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业文化实体类
 * @author 吴佳涛
 * 2018-7-4
 */
public class CompanyCulture implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
        /**
         *  企业文化
         */
        private String uid;
        /**
         *  名称
         */
        private String name;
        /**
         *  摘要
         */
        private String remark;
        /**
         *  图片
         */
        private String image;
        /**
         *  类型
         */
        private String type;
        /**
         *  详情
         */
        private String detail;
        /**
         *  上传时间
         */
        private Date createTime;

        public String getUid() {
            return uid;
        }

        public CompanyCulture setUid(String uid) {
            this.uid = uid;
            return this;
        }
        public String getName() {
            return name;
        }

        public CompanyCulture setName(String name) {
            this.name = name;
            return this;
        }
        public String getRemark() {
            return remark;
        }

        public CompanyCulture setRemark(String remark) {
            this.remark = remark;
            return this;
        }
        public String getImage() {
            return image;
        }

        public CompanyCulture setImage(String image) {
            this.image = image;
            return this;
        }
        public String getType() {
            return type;
        }

        public CompanyCulture setType(String type) {
            this.type = type;
            return this;
        }
        public String getDetail() {
            return detail;
        }

        public CompanyCulture setDetail(String detail) {
            this.detail = detail;
            return this;
        }

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
        
}
