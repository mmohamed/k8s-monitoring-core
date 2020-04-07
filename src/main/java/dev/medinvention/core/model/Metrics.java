package dev.medinvention.core.model;

import java.math.BigDecimal;

public class Metrics {

	private String node;

	private BigDecimal cpu;

	private BigDecimal memory;

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
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
