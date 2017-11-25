package com.brucex.modules.sys.entity;

/**
 * @description PageParam.java 查询页数据对象
 * @author xiong
 * @date 2017年7月3日下午11:50:38
 */
public class PageParam {

	private Integer draw; // 请求次数
	private Integer start;// 查询开始页数
	private Integer length;// 查询记录数
	private Integer recordsTotal;// 总记录数
	private Integer recordsFiltered;// 过滤后记录数
	
	private String orderColumn;// 需排序的列
	private String orderDir = "asc";// 排序符， 默认asc 升序排序  可选 desc 降序
	private String searchValue; // 搜索值
	
	
	//#################     constranstor       ################
	
	
	public PageParam() {
		super();
	}
	
	/**
	 * @param draw
	 * @param start
	 * @param length
	 * @param recordsTotal
	 * @param recordsFiltered
	 * @param orderColumn
	 * @param orderDir
	 * @param searchValue
	 */
	public PageParam(Integer draw, Integer start, Integer length, Integer recordsTotal, Integer recordsFiltered,
			String orderColumn, String orderDir, String searchValue) {
		super();
		this.draw = draw;
		this.start = start;
		this.length = length;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.orderColumn = orderColumn;
		this.orderDir = orderDir;
		this.searchValue = searchValue;
	}
	
	
	//##############  getter and setter   #################
	
	/**
	 * @return the draw
	 */
	public Integer getDraw() {
		return draw;
	}
	
	/**
	 * @param draw the draw to set
	 */
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	/**
	 * @return the start
	 */
	public Integer getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(Integer start) {
		this.start = start;
	}
	/**
	 * @return the length
	 */
	public Integer getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(Integer length) {
		this.length = length;
	}
	/**
	 * @return the recordsTotal
	 */
	public Integer getRecordsTotal() {
		return recordsTotal;
	}
	/**
	 * @param recordsTotal the recordsTotal to set
	 */
	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	/**
	 * @return the recordsFiltered
	 */
	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}
	/**
	 * @param recordsFiltered the recordsFiltered to set
	 */
	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	/**
	 * @return the orderColumn
	 */
	public String getOrderColumn() {
		return orderColumn;
	}
	/**
	 * @param orderColumn the orderColumn to set
	 */
	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}
	/**
	 * @return the orderDir
	 */
	public String getOrderDir() {
		return orderDir;
	}
	/**
	 * @param orderDir the orderDir to set
	 */
	public void setOrderDir(String orderDir) {
		this.orderDir = orderDir;
	}
	/**
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}
	/**
	 * @param searchValue the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PageParam [draw=" + draw + ", start=" + start + ", length=" + length + ", recordsTotal=" + recordsTotal
				+ ", recordsFiltered=" + recordsFiltered + ", orderColumn=" + orderColumn + ", orderDir=" + orderDir
				+ ", searchValue=" + searchValue + "]";
	}
	
}
