package dev.medinvention.core.client;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.util.ClientBuilder;

import java.io.IOException;

abstract public class AbstractClient {

	private CoreV1Api api;

	private boolean initialized = false;

	public CoreV1Api getAPI() {
		if (null == api) {
			if (!this.initialized) {
				try {
					ApiClient client = ClientBuilder.cluster().build();
					Configuration.setDefaultApiClient(client);
					this.initialized = true;
				} catch (IOException e) {
				}
				
			}
			api = new CoreV1Api();
		}
		return api;
	}

	public void setAPI(CoreV1Api api) {
		this.api = api;
	}
}