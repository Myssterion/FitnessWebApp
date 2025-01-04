package com.springboot.app.dto;

public class FilterDto {

	private Integer categoryId;
	private Integer difficultyId;
	private Integer attributeId;
	private Integer priceFrom;
	private Integer priceTo;
	private Integer durationFrom;
	private Integer durationTo;

	public FilterDto() {
		// TODO Auto-generated constructor stub
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getDifficultyId() {
		return difficultyId;
	}

	public void setDifficultyId(Integer difficultyId) {
		this.difficultyId = difficultyId;
	}

	public Integer getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(Integer priceFrom) {
		this.priceFrom = priceFrom;
	}

	public Integer getPriceTo() {
		return priceTo;
	}

	public void setPriceTo(Integer priceTo) {
		this.priceTo = priceTo;
	}

	public Integer getDurationFrom() {
		return durationFrom;
	}

	public void setDurationFrom(Integer durationFrom) {
		this.durationFrom = durationFrom;
	}

	public Integer getDurationTo() {
		return durationTo;
	}

	public void setDurationTo(Integer durationTo) {
		this.durationTo = durationTo;
	}

	public Integer getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}

}
