package org.comviva.jdbc.h2.domain;

public class Comviva {

	private int id;
	private String fileName;
	private double fileValue;
	private double processData;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public double getProcessData() {
		return processData;
	}
	public void setProcessData(double processData) {
		this.processData = processData;
	}
	public double getFileValue() {
		return fileValue;
	}
	public void setFileValue(double fileValue) {
		this.fileValue = fileValue;
	}
	@Override
	public String toString() {
		return "Comviva [id=" + id + ", fileName=" + fileName + ", fileValue=" + fileValue + ", processData="
				+ processData + "]";
	}
	
	
	
	
	
	
	
	
}
