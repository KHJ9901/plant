package com.plant.dto;

import java.util.List;

public class Adopt {
	
	private String no;
	private String seqno;
	private String count;
	private String content;
	private String wdate;
	private String id;
	private String station;
	private String edit;
	private String water;
	private String place;
	private String temp;
	private String moist;
	private String sdate;
	private String pname;
	private String mplant_seqno;
	
	private List<AdoptReply> adoptreply;
	
	private List<AdoptFile> adoptFile;
	
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getMplant_seqno() {
		return mplant_seqno;
	}
	public void setMplant_seqno(String mplant_seqno) {
		this.mplant_seqno = mplant_seqno;
	}
	public String getSeqno() {
		return seqno;
	}
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getEdit() {
		return edit;
	}
	public void setEdit(String edit) {
		this.edit = edit;
	}
	public String getWater() {
		return water;
	}
	public void setWater(String water) {
		this.water = water;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getMoist() {
		return moist;
	}
	public void setMoist(String moist) {
		this.moist = moist;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	public List<AdoptReply> getAdoptreply() {
		return adoptreply;
	}
	public void setAdoptreply(List<AdoptReply> adoptreply) {
		this.adoptreply = adoptreply;
	}
	public List<AdoptFile> getAdoptFile() {
		return adoptFile;
	}
	public void setAdoptFile(List<AdoptFile> adoptFile) {
		this.adoptFile = adoptFile;
	}



}
