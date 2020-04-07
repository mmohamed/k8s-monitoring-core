package dev.medinvention.core.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dev.medinvention.core.api.type.V1Beta1NodeMetrics;
import dev.medinvention.core.api.type.V1Beta1NodeMetricsList;
import dev.medinvention.core.client.AbstractClient;
import dev.medinvention.core.model.Metrics;
import io.kubernetes.client.openapi.ApiException;

@Component
public class ClusterService extends AbstractClient {

	public List<Metrics> metrics() throws ApiException {

		List<Metrics> metricss = new ArrayList<Metrics>();

		V1Beta1NodeMetricsList list = this.getAPI().clusterMetrics();

		for (V1Beta1NodeMetrics item : list.getItems()) {

			Metrics metrics = new Metrics();

			metrics.setNode(item.getMetadata().getName());
			metrics.setMemory(null != item.getUsage() && null != item.getUsage().getMemory()
					? item.getUsage().getMemory().getNumber()
					: new BigDecimal(0));
			metrics.setCpu(
					null != item.getUsage() && null != item.getUsage().getCpu() ? item.getUsage().getCpu().getNumber()
							: new BigDecimal(0));

			metricss.add(metrics);
		}
		return metricss;
	}

}
