package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Sample {
	private String globalId;
	private String name;
	private String description;
	private String createdBy; 
	private String modifiedBy; 
	private String ownedBy;
	private String type;
	private String sampleSource;
	private double storageTempMax;
	private int storageTempMaxUnit;
	private double storageTempMin;
	private int storageTempMinUnit;
	private double quantityVal;
	private int quantityUnit;
	private LocalDate expiryDate;
	private LocalDateTime createDateTime;
	private LocalDateTime modifiedDateTime;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getOwnedBy() {
		return ownedBy;
	}

	public void setOwnedBy(String owner) {
		this.ownedBy = owner;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSampleSource() {
		return sampleSource;
	}

	public void setSampleSource(String sampleSource) {
		this.sampleSource = sampleSource;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getGlobalId() {
		return globalId;
	}

	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}
	
	public double getStorageTempMax() {
		return storageTempMax;
	}

	public void setStorageTempMax(double storageTempMax) {
		this.storageTempMax = storageTempMax;
	}

	public int getStorageTempMaxUnit() {
		return storageTempMaxUnit;
	}

	public void setStorageTempMaxUnit(int storageTempMaxUnit) {
		this.storageTempMaxUnit = storageTempMaxUnit;
	}

	public double getStorageTempMin() {
		return storageTempMin;
	}

	public void setStorageTempMin(double storageTempMin) {
		this.storageTempMin = storageTempMin;
	}

	public int getStorageTempMinUnit() {
		return storageTempMinUnit;
	}

	public void setStorageTempMinUnit(int storageTempMinUnit) {
		this.storageTempMinUnit = storageTempMinUnit;
	}

	public LocalDateTime getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(LocalDateTime modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public double getQuantityVal() {
		return quantityVal;
	}

	public void setQuantityVal(double quantityVal) {
		this.quantityVal = quantityVal;
	}

	public int getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(int quantityUnit) {
		this.quantityUnit = quantityUnit;
	}
}
