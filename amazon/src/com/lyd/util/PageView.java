package com.lyd.util;

import java.util.List;

public class PageView
{
    public static final int PAGE_SIZE = 12;
	private int currentPage = 1;// 当前页
	public final int pageSize = PAGE_SIZE;// 每页显示的记录数

	private int recordCount; // 总记录数
	private List recordList; // 本页的数据列表

	private int pageCount; // 总页数
	private int startPageIndex; // 页码列表开始索引
	private int endPageIndex; // 页码列表结束索引

	public PageView()
	{
	}

	public PageView(int currentPage, int recordCount, List recordList)
	{
		// 给变量赋值
		this.currentPage = currentPage;
		this.recordCount = recordCount;
		this.recordList = recordList;
		// 总页数
		pageCount = (recordCount + pageSize - 1) / pageSize;
	}

	public PageView(int currentPage, int recordCount)
	{
		// 给变量赋值
		this.currentPage = currentPage;
		// this.pageSize = pageSize;
		this.recordCount = recordCount;
		// 总页数
		pageCount = (recordCount + pageSize - 1) / pageSize;
	}
	
	/**
	 * 计算总页数
	 * @param recordCount
	 * @return
	 */
	public int getAllPageCount(int recordCount)
	{
		return (recordCount + pageSize - 1) / pageSize;
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	// public void setPageSize(int pageSize) {
	// this.pageSize = pageSize;
	// }
	public int getRecordCount()
	{
		return recordCount;
	}

	public void setRecordCount(int recordCount)
	{
		this.recordCount = recordCount;
	}

	public List getRecordList()
	{
		return recordList;
	}

	public void setRecordList(List recordList)
	{
		this.recordList = recordList;
	}

	public int getPageCount()
	{
		return pageCount;
	}

	public void setPageCount(int pageCount)
	{
		this.pageCount = pageCount;
	}

	public int getStartPageIndex()
	{
		return startPageIndex;
	}

	public void setStartPageIndex(int startPageIndex)
	{
		this.startPageIndex = startPageIndex;
	}

	public int getEndPageIndex()
	{
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex)
	{
		this.endPageIndex = endPageIndex;
	}

}
