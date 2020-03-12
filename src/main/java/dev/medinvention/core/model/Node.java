package dev.medinvention.core.model;

import java.math.BigDecimal;

public class Node {

	private String name;

	private String status;

	private BigDecimal cpu;

	private BigDecimal memory;
	
	private BigDecimal pods;
	
	private String addresse;
	
	private Boolean isMaster = false;
	
	public String getAddresse() {
		return addresse;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getCpu() {
		return cpu;
	}

	public void setCpu(BigDecimal cpu) {
		this.cpu = cpu;
	}

	public BigDecimal getMemory() {
		return memory;
	}

	public void setMemory(BigDecimal memory) {
		this.memory = memory;
	}

	public BigDecimal getPods() {
		return pods;
	}

	public void setPods(BigDecimal pods) {
		this.pods = pods;
	}

	public Boolean getIsMaster() {
		return isMaster;
	}

	public void setIsMaster(Boolean isMaster) {
		this.isMaster = isMaster;
	}
	
}
