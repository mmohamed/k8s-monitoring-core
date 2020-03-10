package dev.medinvention.core.model;

import java.math.BigDecimal;

public class Node {

	private String name;

	private String status;

	private BigDecimal cpu;

	private BigDecimal memory;

	private String addresse;

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

}
