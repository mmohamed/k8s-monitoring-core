package dev.medinvention.core.service;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.medinvention.core.config.Config;
import dev.medinvention.core.model.Pod;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Container;
import io.kubernetes.client.openapi.models.V1ContainerStatus;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.openapi.models.V1PodSpec;
import io.kubernetes.client.openapi.models.V1PodStatus;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { Config.class })
public class PodServiceTest {

	@Autowired
	private PodService podService;

	@Mock
	CoreV1Api api;

	@Test
	public void testGet() throws ApiException {
		V1PodList data = new V1PodList();

		Mockito.when(api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null))
				.thenReturn(data);

		podService.setAPI(api);

		List<Pod> list = podService.get();

		assertTrue(list.isEmpty());

		V1ObjectMeta metadata = new V1ObjectMeta();
		metadata.setName("FirstPod");
		metadata.setNamespace("FirstNamespace");

		V1ContainerStatus containerStatus = new V1ContainerStatus();
		containerStatus.setReady(false);
		containerStatus.setRestartCount(3);

		V1PodStatus runningSt = new V1PodStatus();
		runningSt.setPhase("Running");
		runningSt.setStartTime(DateTime.now());
		runningSt.addContainerStatusesItem(containerStatus);

		V1Container container = new V1Container();
		container.setImage("First-image");

		List<V1Container> containers = new ArrayList<V1Container>();
		containers.add(container);

		V1PodSpec spec = new V1PodSpec();
		spec.setNodeName("FirstNode");
		spec.setContainers(containers);

		V1Pod pod = new V1Pod();
		pod.setStatus(runningSt);
		pod.setSpec(spec);
		pod.setMetadata(metadata);

		data.addItemsItem(pod);

		Mockito.when(api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null))
				.thenReturn(data);

		list = podService.get();

		assertEquals(1, list.size());
		assertSame("FirstPod", list.get(0).getName());
		assertEquals(3, list.get(0).getRestartCount());
		assertEquals(0, list.get(0).getReadyContainers());
		assertEquals(1, list.get(0).getAllContainers());
		assertSame("First-image", list.get(0).getImages().get(0));
	}
}
