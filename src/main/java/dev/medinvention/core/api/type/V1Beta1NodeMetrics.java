package dev.medinvention.core.api.type;

import java.util.Objects;

import org.joda.time.DateTime;

import com.google.gson.annotations.SerializedName;

import io.kubernetes.client.openapi.models.V1ObjectMeta;

public class V1Beta1NodeMetrics {

	public static final String SERIALIZED_NAME_WINDOW = "window";
	@SerializedName(SERIALIZED_NAME_WINDOW)
	private String window;

	public static final String SERIALIZED_NAME_METADATA = "metadata";
	@SerializedName(SERIALIZED_NAME_METADATA)
	private V1ObjectMeta metadata;

	public static final String SERIALIZED_NAME_TIMESTAMP = "timestamp";
	@SerializedName(SERIALIZED_NAME_TIMESTAMP)
	private DateTime timestamp;

	public static final String SERIALIZED_NAME_USAGE = "usage";
	@SerializedName(SERIALIZED_NAME_USAGE)
	private V1Beta1NodeMetricsUsage usage;

	public V1Beta1NodeMetricsUsage getUsage() {
		return usage;
	}

	public void setUsage(V1Beta1NodeMetricsUsage usage) {
		this.usage = usage;
	}

	public V1Beta1NodeMetrics usage(V1Beta1NodeMetricsUsage usage) {
		this.usage = usage;
		return this;
	}

	public V1Beta1NodeMetrics timestamp(DateTime timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	public DateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(DateTime timestamp) {
		this.timestamp = timestamp;
	}

	public V1Beta1NodeMetrics window(String window) {
		this.window = window;
		return this;
	}

	public String getWindow() {
		return window;
	}

	public void setWindow(String window) {
		this.window = window;
	}

	public V1Beta1NodeMetrics metadata(V1ObjectMeta metadata) {
		this.metadata = metadata;
		return this;
	}

	@javax.annotation.Nullable
	public V1ObjectMeta getMetadata() {
		return metadata;
	}

	public void setMetadata(V1ObjectMeta metadata) {
		this.metadata = metadata;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		V1Beta1NodeMetrics v1Beta1NodeMetrics = (V1Beta1NodeMetrics) o;

		return Objects.equals(this.window, v1Beta1NodeMetrics.window)
				&& Objects.equals(this.timestamp, v1Beta1NodeMetrics.timestamp)
				&& Objects.equals(this.usage, v1Beta1NodeMetrics.usage)
				&& Objects.equals(this.metadata, v1Beta1NodeMetrics.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(window, timestamp, usage, metadata);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class V1Beta1NodeMetrics {\n");
		sb.append("    window: ").append(toIndentedString(window)).append("\n");
		sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
		sb.append("    usage: ").append(toIndentedString(usage)).append("\n");
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
