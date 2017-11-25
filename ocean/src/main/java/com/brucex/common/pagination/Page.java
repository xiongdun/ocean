/**
 * 
 */
package com.brucex.common.pagination;

/**
 * @description 分页对象 继承自mybatis分页插件
 * @author xiongdun
 * @datetime 2017年4月8日下午7:16:00
 */
public class Page<T> extends com.github.pagehelper.Page<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8156139414879886410L;

	private Integer first;
	private Integer next;
	private String html;
	
	
	public Integer getFirst() {
		return first;
	}


	public void setFirst(Integer first) {
		this.first = first;
	}


	public Integer getNext() {
		return next;
	}


	public void setNext(Integer next) {
		this.next = next;
	}


	public String getHtml() {
		return html;
	}


	public void setHtml(String html) {
		this.html = html;
	}


	public Page() {
		
	}
}
