package dev.medinvention.core.api.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import io.kubernetes.client.openapi.models.V1ListMeta;

public class V1Beta1NodeMetricsList {

	public static final String SERIALIZED_NAME_API_VERSION = "apiVersion";
	@SerializedName(SERIALIZED_NAME_API_VERSION)
	private String apiVersion;

	public static final String SERIALIZED_NAME_ITEMS = "items";
	@SerializedName(SERIALIZED_NAME_ITEMS)
	private List<V1Beta1NodeMetrics> items = new ArrayList<V1Beta1NodeMetrics>();

	public static final String SERIALIZED_NAME_KIND = "kind";
	@SerializedName(SERIALIZED_NAME_KIND)
	private String kind;

	public static final String SERIALIZED_NAME_METADATA = "metadata";
	@SerializedName(SERIALIZED_NAME_METADATA)
	private V1ListMeta metadata;

	public V1Beta1NodeMetricsList apiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
		return this;
	}

	@javax.annotation.Nullable
	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public V1Beta1NodeMetricsList items(List<V1Beta1NodeMetrics> items) {
		this.items = items;
		return this;
	}

	public V1Beta1NodeMetricsList addItemsItem(V1Beta1NodeMetrics itemsItem) {
		this.items.add(itemsItem);
		return this;
	}

	public List<V1Beta1NodeMetrics> getItems() {
		return items;
	}

	public void setItems(List<V1Beta1NodeMetrics> items) {
		this.items = items;
	}

	public V1Beta1NodeMetricsList kind(String kind) {
		this.kind = kind;
		return this;
	}

	@javax.annotation.Nullable
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public V1Beta1NodeMetricsList metadata(V1ListMeta metadata) {
		this.metadata = metadata;
		return this;
	}

	@javax.annotation.Nullable
	public V1ListMeta getMetadata() {
		return metadata;
	}

	public void setMetadata(V1ListMeta metadata) {
		this.metadata = metadata;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		V1Beta1NodeMetricsList v1Beta1NodeMetricsList = (V1Beta1NodeMetricsList) o;
		
		return Objects.equals(this.apiVersion, v1Beta1NodeMetricsList.apiVersion)
				&& Objects.equals(this.items, v1Beta1NodeMetricsList.items)
				&& Objects.equals(this.kind, v1Beta1NodeMetricsList.kind)
				&& Objects.equals(this.metadata, v1Beta1NodeMetricsList.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(apiVersion, items, kind, metadata);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class V1Beta1NodeMetricsList {\n");
		sb.append("    apiVersion: ").append(toIndentedString(apiVersion)).append("\n");
		sb.append("    items: ").append(toIndentedString(items)).append("\n");
		sb.append("    kind: ").append(toIndentedString(kind)).append("\n");
		sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	private String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
