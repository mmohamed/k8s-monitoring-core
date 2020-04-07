package dev.medinvention.core.client;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.util.ClientBuilder;

import java.io.IOException;

import dev.medinvention.core.api.ExtendedCoreV1Api;

abstract public class AbstractClient {

	private ExtendedCoreV1Api api;

	private boolean initialized = false;

	public ExtendedCoreV1Api getAPI() throws ApiException {
		if (null == api) {
			if (!this.initialized) {
				try {
					ApiClient client = ClientBuilder.cluster().build();
					Configuration.setDefaultApiClient(client);
					this.initialized = true;
				} catch (IOException e) {
				}

			}
			api = new ExtendedCoreV1Api();
		}
		return api;
	}

	public void setAPI(ExtendedCoreV1Api api) {
		this.api = api;
	}
}