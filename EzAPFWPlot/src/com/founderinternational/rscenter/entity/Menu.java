package com.founderinternational.rscenter.entity;

import java.io.Serializable;

public class Menu  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 惟一标识
	 */
	private Integer id;
	/**
	 * 父ID
	 */
	private Integer parentId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 对应的地址
	 */
	private String url;
	/**
	 * 是否显示在左侧
	 */
	private Integer isShowLeft;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getIsShowLeft() {
		return isShowLeft;
	}
	public void setIsShowLeft(Integer isShowLeft) {
		this.isShowLeft = isShowLeft;
	}
}
