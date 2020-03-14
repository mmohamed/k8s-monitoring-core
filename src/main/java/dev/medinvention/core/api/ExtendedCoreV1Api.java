package dev.medinvention.core.api;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import dev.medinvention.core.api.type.V1Beta1NodeMetricsList;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.ApiResponse;
import io.kubernetes.client.openapi.Pair;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import okhttp3.Call;

public class ExtendedCoreV1Api extends CoreV1Api {

	public V1Beta1NodeMetricsList clusterMetrics() throws ApiException {
		Object localVarPostBody = null;

		// path
		String localVarPath = "/apis/metrics.k8s.io/v1beta1/nodes";

		List<Pair> localVarQueryParams = new ArrayList<Pair>();
		List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
		Map<String, String> localVarHeaderParams = new HashMap<String, String>();
		Map<String, String> localVarCookieParams = new HashMap<String, String>();
		Map<String, Object> localVarFormParams = new HashMap<String, Object>();

		final String[] localVarAccepts = {};
		final String localVarAccept = this.getApiClient().selectHeaderAccept(localVarAccepts);
		if (localVarAccept != null) {
			localVarHeaderParams.put("Accept", localVarAccept);
		}

		final String[] localVarContentTypes = { "application/json" };
		final String localVarContentType = this.getApiClient().selectHeaderContentType(localVarContentTypes);
		localVarHeaderParams.put("Content-Type", localVarContentType);

		String[] localVarAuthNames = new String[] { "BearerToken" };

		Call call = this.getApiClient().buildCall(localVarPath, "GET", localVarQueryParams,
				localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams,
				localVarFormParams, localVarAuthNames, null);

		Type localVarReturnType = new TypeToken<V1Beta1NodeMetricsList>() {
		}.getType();

		ApiResponse<V1Beta1NodeMetricsList> response = this.getApiClient().execute(call, localVarReturnType);

		return response.getData();
	}
}
