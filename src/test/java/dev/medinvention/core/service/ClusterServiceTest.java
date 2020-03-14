package dev.medinvention.core.service;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.medinvention.core.api.ExtendedCoreV1Api;
import dev.medinvention.core.api.type.V1Beta1NodeMetrics;
import dev.medinvention.core.api.type.V1Beta1NodeMetricsList;
import dev.medinvention.core.api.type.V1Beta1NodeMetricsUsage;
import dev.medinvention.core.config.Config;
import dev.medinvention.core.model.Metrics;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import io.kubernetes.client.custom.Quantity;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { Config.class })
public class ClusterServiceTest {

	@Autowired
	private ClusterService clusterService;

	@Mock
	ExtendedCoreV1Api api;

	@Test
	public void testGet() throws ApiException {
		V1Beta1NodeMetricsList data = new V1Beta1NodeMetricsList();

		Mockito.when(api.clusterMetrics()).thenReturn(data);

		clusterService.setAPI(api);

		List<Metrics> list = clusterService.metrics();

		assertTrue(list.isEmpty());

		V1ObjectMeta metadata = new V1ObjectMeta();
		metadata.setName("FirstNode");

		V1Beta1NodeMetricsUsage usage = new V1Beta1NodeMetricsUsage();
		usage.setCpu(new Quantity("1233234"));

		V1Beta1NodeMetrics metrics = new V1Beta1NodeMetrics();
		metrics.setMetadata(metadata);
		metrics.setTimestamp(new DateTime());
		metrics.setWindow("30s");
		metrics.setUsage(usage);

		data.addItemsItem(metrics);

		Mockito.when(api.clusterMetrics()).thenReturn(data);

		list = clusterService.metrics();

		assertEquals(1, list.size());
		assertSame("FirstNode", list.get(0).getNode());
		assertEquals(new BigDecimal(1233234), list.get(0).getCpu());
	}
}
