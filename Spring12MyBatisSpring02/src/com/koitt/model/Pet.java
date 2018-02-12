package com.koitt.model;

import java.util.Date;

public class Pet {
	
	private Integer petId;
	private String petName;
	private String ownerName;
	private Integer price;
	private Date berthDate;
	
	public Pet() {}

	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getBerthDate() {
		return berthDate;
	}

	public void setBerthDate(Date berthDate) {
		this.berthDate = berthDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pet [petId=");
		builder.append(petId);
		builder.append(", petName=");
		builder.append(petName);
		builder.append(", ownerName=");
		builder.append(ownerName);
		builder.append(", price=");
		builder.append(price);
		builder.append(", berthDate=");
		builder.append(berthDate);
		builder.append("]");
		return builder.toString();
	} 
	
	
	
}
