package com.chinaxaxt.xtzncms.common.vo;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;

/**
 * 返回前端的响应
 * @author 吴佳涛
 *
 */
public class ResponseVO implements Serializable{
	
	private static final long serialVersionUID = -5718054283517931016L;

	private Integer rspCode;
	
	private String msg;
	
	private Object data;

	public static ResponseVO success(Object data) {
		return new ResponseVO(800, "success", data);
	}
	
	public static ResponseVO success(String msg,Object data) {
		return new ResponseVO(800, msg, data);
	}	
	
	public static ResponseVO successPageVO(List<?> li,Page<Object> pa) {
    	return ResponseVO.success(new PageVO(pa.getTotal(), pa.getPageNum(), li));
	}
	
	public static ResponseVO error(String msg) {
		return new ResponseVO(850, msg);
	}
	
	
	public ResponseVO(int rspCode, String msg) {
		super();
		this.rspCode = rspCode;
		this.msg = msg;
	}

	public ResponseVO(int rspCode, String msg, Object data) {
		super();
		this.rspCode = rspCode;
		this.msg = msg;
		this.data = data;
	}

	public int getRspCode() {
		return rspCode;
	}

	public void setRspCode(int rspCode) {
		this.rspCode = rspCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseVO [rspCode=" + rspCode + ", msg=" + msg + ", data=" + data + ", toString()=" + super.toString()
				+ "]";
	}
	
}
