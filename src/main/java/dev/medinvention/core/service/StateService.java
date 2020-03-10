package dev.medinvention.core.service;

import org.springframework.stereotype.Component;

import dev.medinvention.core.client.AbstractClient;
import dev.medinvention.core.model.State;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Node;
import io.kubernetes.client.openapi.models.V1NodeCondition;
import io.kubernetes.client.openapi.models.V1NodeList;
import io.kubernetes.client.openapi.models.V1Taint;

@Component
public class StateService extends AbstractClient {

	public State get() throws ApiException {

		State state = new State();

		state.setIsRunning(false);

		V1NodeList list = this.getAPI().listNode(null, null, null, null, null, null, null, null, null);

		for (V1Node item : list.getItems()) {
			if(item.getSpec() == null) {
				continue;
			}
			for (V1Taint taint : item.getSpec().getTaints()) {
				if (taint.getKey().contentEquals("node-role.kubernetes.io/master")) {
					if(item.getStatus() == null) {
						continue;
					}
					for (V1NodeCondition condition : item.getStatus().getConditions()) {
						if (condition.getType().contentEquals("Ready") && condition.getStatus().contentEquals("True")) {
							state.setIsRunning(true);
						}
					}
					break;
				}
			}
		}
		return state;
	}
}
