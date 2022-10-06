package com.plant.dto;

import java.util.List;

public class Mplant {
	
	private int rn;
	private String mplant_seqno;
	private String name;
	private String water;
	private String place;
	private String temp;
	private String moist;
	private String wdate;
	private String edit;
	private String isdel;
	private String id;
	private String etc;
	private String cate;
	private String plevel;
	private String count;
	private String searchName;
	private List<MplantImg> mpimg;
	private MplantThumb mplant_thumb;
	
	
	

	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	public List<MplantImg> getMpimg() {
		return mpimg;
	}
	public void setMpimg(List<MplantImg> mpimg) {
		this.mpimg = mpimg;
	}
	public String getWater() {
		return water;
	}
	public void setWater(String water) {
		this.water = water;
	}
	public String getMplant_seqno() {
		return mplant_seqno;
	}
	public void setMplant_seqno(String mplant_seqno) {
		this.mplant_seqno = mplant_seqno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getEdit() {
		return edit;
	}
	public void setEdit(String edit) {
		this.edit = edit;
	}
	public String getIsdel() {
		return isdel;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public String getPlevel() {
		return plevel;
	}
	public void setPlevel(String plevel) {
		this.plevel = plevel;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public MplantThumb getMplant_thumb() {
		return mplant_thumb;
	}
	public void setMplant_thumb(MplantThumb mplant_thumb) {
		this.mplant_thumb = mplant_thumb;
	}
	
	
}
