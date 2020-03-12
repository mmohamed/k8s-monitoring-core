package dev.medinvention.core.service;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.medinvention.core.config.Config;
import dev.medinvention.core.model.Node;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Node;
import io.kubernetes.client.openapi.models.V1NodeAddress;
import io.kubernetes.client.openapi.models.V1NodeCondition;
import io.kubernetes.client.openapi.models.V1NodeList;
import io.kubernetes.client.openapi.models.V1NodeSpec;
import io.kubernetes.client.openapi.models.V1NodeStatus;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import io.kubernetes.client.openapi.models.V1Taint;
import io.kubernetes.client.custom.Quantity;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { Config.class })
public class NodeServiceTest {

	@Autowired
	private NodeService nodeService;

	@Mock
	CoreV1Api api;

	@Test
	public void testGet() throws ApiException {
		V1NodeList data = new V1NodeList();

		Mockito.when(api.listNode(null, null, null, null, null, null, null, null, null)).thenReturn(data);

		nodeService.setAPI(api);

		List<Node> list = nodeService.get();

		assertTrue(list.isEmpty());

		V1ObjectMeta metadata = new V1ObjectMeta();
		metadata.setName("FirstNode");

		V1NodeAddress addresse = new V1NodeAddress();
		addresse.setAddress("127.0.0.1");
		addresse.setType("Internal");

		Map<String, Quantity> capacity = new HashMap<String, Quantity>();
		capacity.put("cpu", new Quantity("12"));

		V1Taint taint = new V1Taint();
		taint.setKey("node-role.kubernetes.io/master");

		V1NodeSpec spec = new V1NodeSpec();
		spec.addTaintsItem(taint);
		
		V1NodeCondition condition = new V1NodeCondition();
		condition.setType("Ready");
		condition.setStatus("True");
		
		V1NodeStatus status = new V1NodeStatus();
		status.addConditionsItem(condition);
		status.addAddressesItem(addresse);
		status.setAllocatable(capacity);

		V1Node node = new V1Node();
		node.setStatus(status);
		node.setMetadata(metadata);
		node.setSpec(spec);
		
		data.addItemsItem(node);

		Mockito.when(api.listNode(null, null, null, null, null, null, null, null, null)).thenReturn(data);

		list = nodeService.get();

		assertEquals(1, list.size());
		assertSame("FirstNode", list.get(0).getName());
		assertTrue(list.get(0).getIsMaster());
	}
}
