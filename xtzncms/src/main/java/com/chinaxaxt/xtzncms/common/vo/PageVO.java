package com.chinaxaxt.xtzncms.common.vo;

import java.io.Serializable;

/**
 * 封装的分页工具
 * @author 吴佳涛
 *
 */
public class PageVO implements Serializable{
	
	private static final long serialVersionUID = 127670414564283958L;
	
	/**
	 * 总条数
	 */
	private Long total;
	
	/**
	 * 当前页数
	 */
	private Integer nowPage;
	
	private Object data;
	
	public PageVO() {
		super();
	}
	public PageVO(long total, int nowPage, Object data) {
		super();
		this.total = total;
		this.nowPage = nowPage;
		this.data = data;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "PageVO [toString()=" + super.toString() + "]";
	}
	
}
