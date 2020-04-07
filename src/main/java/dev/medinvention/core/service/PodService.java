package dev.medinvention.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dev.medinvention.core.client.AbstractClient;
import dev.medinvention.core.model.Pod;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Container;
import io.kubernetes.client.openapi.models.V1ContainerStatus;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;

@Component
public class PodService extends AbstractClient {

	public List<Pod> get() throws ApiException {
		List<Pod> pods = new ArrayList<Pod>();

		V1PodList list = this.getAPI().listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);

		for (V1Pod item : list.getItems()) {
			Pod pod = new Pod();

			List<String> images = new ArrayList<String>();

			if (item.getSpec() != null && item.getSpec().getContainers() != null) {
				for (V1Container container : item.getSpec().getContainers()) {
					images.add(container.getImage());
				}

				pod.setNode(item.getSpec().getNodeName());
			}

			int ready = 0;
			int all = 0;
			int restart = 0;
			if (item.getStatus() != null && item.getStatus().getContainerStatuses() != null) {
				for (V1ContainerStatus status : item.getStatus().getContainerStatuses()) {
					if (status.getReady()) {
						ready++;
					}
					all++;
					restart = Math.max(restart, status.getRestartCount() != null ? status.getRestartCount() : 0);
				}

				pod.setStatus(item.getStatus().getPhase());
				pod.setAddress(item.getStatus().getPodIP());
				pod.setStartedAt(item.getStatus().getStartTime() != null ? item.getStatus().getStartTime().toDate() : null);
			}

			if (item.getMetadata() != null) {
				pod.setName(item.getMetadata().getName());
				pod.setNamespace(item.getMetadata().getNamespace());
			}

			pod.setImages(images);
			pod.setReadyContainers(ready);
			pod.setAllContainers(all);
			pod.setRestartCount(restart);

			pods.add(pod);
		}

		return pods;
	}
}
