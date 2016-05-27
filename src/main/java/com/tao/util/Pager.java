package com.tao.util;

public class Pager {
	private int totalRows; //总记录数
	  private int pageSize; //页最大记录数
	  private int currentPage; //当前页
	  private int totalPages; //总页数
	  private int startRow; //起始记录
	  public Pager() {
	  }
	  public Pager(int _totalRows, int _pageSize) {
	    totalRows = _totalRows;
	    pageSize = _pageSize;
	    totalPages=(int) Math.ceil((double)totalRows/pageSize);
	    currentPage = 1;
	    startRow = 0;
	  }
	  public int getStartRow() {
	    return startRow;
	  }
	  public int getTotalPages() {
	    return totalPages;
	  }
	  public int getCurrentPage() {
	    return currentPage;
	  }
	  public int getPageSize() {
	    return pageSize;
	  }
	  public void setTotalRows(int totalRows) {
	    this.totalRows = totalRows;
	  }
	  public void setStartRow(int startRow) {
	    this.startRow = startRow;
	  }
	  public void setTotalPages(int totalPages) {
	    this.totalPages = totalPages;
	  }
	  public void setCurrentPage(int currentPage) {
	    this.currentPage = currentPage;
	  }
	  public void setPageSize(int pageSize) {
	    this.pageSize = pageSize;
	  }
	  public int getTotalRows() {
	    return totalRows;
	  }
	  public void first() {  //第一页
	    currentPage = 1;
	    startRow = 0;
	  }
	  public void previous() {  //前一页
	    if (currentPage == 1) {
	      return;
	    }
	    currentPage--;
	    startRow = (currentPage - 1) * pageSize;
	  }
	  public void next() {   //下一页
	    if (currentPage < totalPages) {
	      currentPage++;
	    }
	    startRow = (currentPage - 1) * pageSize;
	  }
	  public void last() {  //尾页
	    currentPage = totalPages;
	    startRow = (currentPage - 1) * pageSize;
	  }
	  public void refresh(int _currentPage) {
	    currentPage = _currentPage;
	    if (currentPage > totalPages) {
	      last();
	    }
	    else if(currentPage < 1){
	    	first();
	    }
	    else{
	    startRow = (currentPage - 1) * pageSize;
	    }
	  }
}
