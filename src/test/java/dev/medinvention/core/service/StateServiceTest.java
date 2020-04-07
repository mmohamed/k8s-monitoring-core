package dev.medinvention.core.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.medinvention.core.api.ExtendedCoreV1Api;
import dev.medinvention.core.config.Config;
import dev.medinvention.core.model.State;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Node;
import io.kubernetes.client.openapi.models.V1NodeCondition;
import io.kubernetes.client.openapi.models.V1NodeList;
import io.kubernetes.client.openapi.models.V1NodeSpec;
import io.kubernetes.client.openapi.models.V1NodeStatus;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import io.kubernetes.client.openapi.models.V1Taint;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { Config.class })
public class StateServiceTest {

	@Autowired
	private StateService stateService;

	@Mock
	ExtendedCoreV1Api api;

	@Test
	public void testGet() throws ApiException {
		V1NodeList data = new V1NodeList();

		Mockito.when(api.listNode(null, null, null, null, null, null, null, null, null)).thenReturn(data);

		stateService.setAPI(api);

		State state = stateService.get();

		assertFalse(state.getIsRunning());

		V1ObjectMeta metadata = new V1ObjectMeta();
		metadata.setName("MasterNode");
		
		V1Taint taint = new V1Taint();
		taint.setKey("node-role.kubernetes.io/master");

		V1NodeSpec spec = new V1NodeSpec();
		spec.addTaintsItem(taint);
		
		V1NodeCondition condition = new V1NodeCondition();
		condition.setType("Ready");
		condition.setStatus("True");
		
		V1NodeStatus status = new V1NodeStatus();
		status.addConditionsItem(condition);
		
		V1Node node = new V1Node();
		node.setStatus(status);
		node.setSpec(spec);
		node.setMetadata(metadata);

		data.addItemsItem(node);

		Mockito.when(api.listNode(null, null, null, null, null, null, null, null, null)).thenReturn(data);

		state = stateService.get();
		
		assertTrue(state.getIsRunning());
	}
}
