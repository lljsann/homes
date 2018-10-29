package com.chinaxaxt.xtzncms.entity.biz;

import java.io.Serializable;

/**
 * 公司通讯地址实体类
 * @author 吴佳涛
 * 2018-6-27
 */
public class Communication implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
        /**
         *  通讯uid
         */
        private String uid;
        /**
         *  公司名称
         */
        private String name;
        /**
         *  LOGO
         */
        private String logo;
        /**
         *  二维码
         */
        private String qrCode;
        /**
         *  公司地址
         */
        private String address;
        /**
         *  电话
         */
        private String phone;
        /**
         *  传真
         */
        private String fax;
        /**
         *  邮政编码
         */
        private String postCode;
        /**
         *  邮箱
         */
        private String email;

        public String getUid() {
            return uid;
        }

        public Communication setUid(String uid) {
            this.uid = uid;
            return this;
        }
        public String getName() {
            return name;
        }

        public Communication setName(String name) {
            this.name = name;
            return this;
        }
        public String getLogo() {
            return logo;
        }

        public Communication setLogo(String logo) {
            this.logo = logo;
            return this;
        }
        public String getQrCode() {
            return qrCode;
        }

        public Communication setQrCode(String qrCode) {
            this.qrCode = qrCode;
            return this;
        }
        public String getAddress() {
            return address;
        }

        public Communication setAddress(String address) {
            this.address = address;
            return this;
        }
        public String getPhone() {
            return phone;
        }

        public Communication setPhone(String phone) {
            this.phone = phone;
            return this;
        }
        public String getFax() {
            return fax;
        }

        public Communication setFax(String fax) {
            this.fax = fax;
            return this;
        }
        public String getPostCode() {
            return postCode;
        }

        public Communication setPostCode(String postCode) {
            this.postCode = postCode;
            return this;
        }
        public String getEmail() {
            return email;
        }

        public Communication setEmail(String email) {
            this.email = email;
            return this;
        }
}
