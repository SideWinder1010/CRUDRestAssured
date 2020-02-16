package com.qa.furniture.dto;


public class PutListDTO {
private String availability;
private double categoryId;
private String color;
private String createdOn;
private String description;
private double discount;
private String materialDescription;
private String name;
private double price;
private String updateOn;
private double warranty;

public PutListDTO() {
	
	
}

public PutListDTO(String availability,String color,String createdOn,String description,double discount,String materialDescription,String name,double price,String updateOn,double warranty) {
	
this.availability = 	availability;
this.categoryId = categoryId;
this.color = color;
this.createdOn = createdOn;
this.description = description;
this.discount = discount;
this.materialDescription = materialDescription;
this.name = name;
this.price = price;
this.updateOn = updateOn;
this.warranty = warranty;

	
}

public double getCategoryId() {
	return categoryId;
}

public void setCategoryId(double categoryId) {
	this.categoryId = categoryId;
}

public String getColor() {
	return color;
}

public void setColor(String color) {
	this.color = color;
}

public String getCreatedOn() {
	return createdOn;
}

public void setCreatedOn(String createdOn) {
	this.createdOn = createdOn;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public int getDiscount() {
	return (int) discount;
}

public void setDiscount(int discount) {
	this.discount = discount;
}

public String getMaterialDescription() {
	return materialDescription;
}

public void setMaterialDescription(String materialDescription) {
	this.materialDescription = materialDescription;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public String getUpdateOn() {
	return updateOn;
}

public void setUpdateOn(String updateOn) {
	this.updateOn = updateOn;
}

public double getWarranty() {
	return warranty;
}

public void setWarranty(double warranty) {
	this.warranty = warranty;
}

/*
 * requestParams.put("availability",true); requestParams.put("categoryId",2);
 * requestParams.put("color", "blue"); requestParams.put("createdOn", "");
 * requestParams.put("description", ""); requestParams.put("discount",0);
 * requestParams.put("materialDescription", ""); requestParams.put("name",
 * "chair"); requestParams.put("price",100); requestParams.put("updateOn", "");
 * requestParams.put("warranty",0);
 */

public void setAvailibility (String availability) {
	System.out.println("In set "+availability);
	this.availability= availability;
}

public String getAvailibility() {
	System.out.println("In get  "+availability);
	return availability;
}




}