package com.plant.dto;

public class Myboard {
	private int currentPage;
	private int rowPerpage;
	private String searchField;
	private String searchText;
	private String id;
	public Myboard() {
		super();
	}

	public Myboard(int currentPage, int rowPerpage) {
		super();
		this.currentPage = currentPage;
		this.rowPerpage = rowPerpage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
