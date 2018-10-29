package com.chinaxaxt.xtzncms.entity.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 招聘岗位接口实体类
 * @author 吴佳涛
 * 2018-7-16
 */
public class JobDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
        /**
         *  岗位表uid
         */
        private String uid;
        /**
         *  岗位名称
         */
        private String name;
        /**
         *  任职地点
         */
        private String address;
        /**
         *  岗位职责
         */
        private String[] duty;
        /**
         *  任职要求
         */
        private String[] demand;
        /**
         *  创建时间
         */
        private Date createTime;

        public String getUid() {
            return uid;
        }

        public JobDto setUid(String uid) {
            this.uid = uid;
            return this;
        }
        public String getName() {
            return name;
        }

        public JobDto setName(String name) {
            this.name = name;
            return this;
        }
        public String getAddress() {
            return address;
        }

        public JobDto setAddress(String address) {
            this.address = address;
            return this;
        }
        public String[] getDuty() {
            return duty;
        }

        public JobDto setDuty(String[] duty) {
            this.duty = duty;
            return this;
        }
        public String[] getDemand() {
            return demand;
        }

        public JobDto setDemand(String[] demand) {
            this.demand = demand;
            return this;
        }

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
        
}
