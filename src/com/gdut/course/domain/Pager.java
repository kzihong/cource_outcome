package com.gdut.course.domain;

import java.util.List;

public class Pager<T> {
	/**
	 * 当前页（起始页)
	 */
	private Integer currentPage;
	/**
	 * 页面大小
	 */
	private Integer pageSize;
	/**
	 * 总数据大小
	 */
	private Long totalAmount;
	/**
	 * 总页面
	 */
	private Long totalPages;
	/**
	 * 页面数据
	 */
	private List<T> datas;
	/**
	 * 页码索引(开始)
	 */
	private Integer beginPageIndex;
	/**
	 * 页码索引(结束)
	 */
	private Integer endPageIndex;
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	public Integer getBeginPageIndex() {
		return beginPageIndex;
	}
	public void setBeginPageIndex(Integer beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}
	public Integer getEndPageIndex() {
		return endPageIndex;
	}
	public void setEndPageIndex(Integer endPageIndex) {
		this.endPageIndex = endPageIndex;
	}
	
}
