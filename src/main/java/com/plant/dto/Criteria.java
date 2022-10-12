package com.plant.dto;

public class Criteria {
	private int currentPage;
	private int rowPerpage;
	private String searchField;
	private String searchText;
	private String filename;
	
	public Criteria() {
		super();
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Criteria(int currentPage, int rowPerpage) {
		super();
		this.currentPage = currentPage;
		this.rowPerpage = rowPerpage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRowPerpage() {
		return rowPerpage;
	}

	public void setRowPerpage(int rowPerpage) {
		this.rowPerpage = rowPerpage;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
}
