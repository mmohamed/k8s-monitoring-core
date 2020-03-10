package dev.medinvention.core.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import dev.medinvention.core.client.AbstractClient;
import dev.medinvention.core.model.Node;
import io.kubernetes.client.custom.Quantity;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Node;
import io.kubernetes.client.openapi.models.V1NodeCondition;
import io.kubernetes.client.openapi.models.V1NodeList;

@Component
public class NodeService extends AbstractClient {

	public List<Node> get() throws ApiException {
		List<Node> nodes = new ArrayList<Node>();

		V1NodeList list = this.getAPI().listNode(null, null, null, null, null, null, null, null, null);

		for (V1Node item : list.getItems()) {

			Map<String, Quantity> capacity = item.getStatus().getCapacity();

			String status = "NotReady";

			for (V1NodeCondition condition : item.getStatus().getConditions()) {
				if (condition.getType().contentEquals("Ready") && condition.getStatus().contentEquals("True")) {
					status = "Ready";
				}
			}

			Node node = new Node();

			node.setName(item.getMetadata().getName());
			node.setStatus(status);
			node.setMemory(capacity.containsKey("memory") ? capacity.get("memory").getNumber() : new BigDecimal(0));
			node.setCpu(capacity.containsKey("cpu") ? capacity.get("cpu").getNumber() : new BigDecimal(0));
			node.setAddresse(item.getStatus().getAddresses().get(0).getAddress());

			nodes.add(node);
		}

		return nodes;
	}

}
