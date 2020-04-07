package dev.medinvention.core.model;

import java.util.Date;
import java.util.List;

public class Pod {

	private String name;
	
	private String namespace;
	
	private String status;
	
	private List<String> images;
	
	private Date startedAt;
	
	private String node;
	
	private Integer readyContainers;
	
	private Integer allContainers;
	
	private Integer restartCount;
	
	private String address;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public Integer getReadyContainers() {
		return readyContainers;
	}

	public void setReadyContainers(Integer readyContainers) {
		this.readyContainers = readyContainers;
	}

	public Integer getAllContainers() {
		return allContainers;
	}

	public void setAllContainers(Integer allContainers) {
		this.allContainers = allContainers;
	}

	public Integer getRestartCount() {
		return restartCount;
	}

	public void setRestartCount(Integer restartCount) {
		this.restartCount = restartCount;
	}

	public Date getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}	
}
