package com.plant.dto;

public class Page {
	private int startPage;
	private int endPage;
	private int total;
	private Criteria cri;
	private boolean prev;
	private boolean next;
	
	
	public Page(int total, Criteria cri) {
		super();
		this.total = total;
		this.cri = cri;

		this.endPage = (int) (Math.ceil(cri.getCurrentPage()/(cri.getRowPerpage()*1.0))*cri.getRowPerpage());
		this.startPage = this.endPage - (cri.getRowPerpage()-1);
		
		int realEnd = (int)(Math.ceil(total * 1.0/cri.getRowPerpage()));
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
		
		
	}


	public int getStartPage() {
		return startPage;
	}


	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}


	public int getEndPage() {
		return endPage;
	}


	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public Criteria getCri() {
		return cri;
	}


	public void setCri(Criteria cri) {
		this.cri = cri;
	}


	public boolean isPrev() {
		return prev;
	}


	public void setPrev(boolean prev) {
		this.prev = prev;
	}


	public boolean isNext() {
		return next;
	}


	public void setNext(boolean next) {
		this.next = next;
	}
	
	
}
