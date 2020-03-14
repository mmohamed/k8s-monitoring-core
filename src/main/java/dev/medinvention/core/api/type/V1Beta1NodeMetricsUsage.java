package dev.medinvention.core.api.type;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import io.kubernetes.client.custom.Quantity;

public class V1Beta1NodeMetricsUsage {

	public static final String SERIALIZED_NAME_CPU = "cpu";
	@SerializedName(SERIALIZED_NAME_CPU)
	private Quantity cpu;

	public static final String SERIALIZED_NAME_MEMORY = "memory";
	@SerializedName(SERIALIZED_NAME_MEMORY)
	private Quantity memory;

	public V1Beta1NodeMetricsUsage cpu(Quantity cpu) {
		this.cpu = cpu;
		return this;
	}

	public Quantity getCpu() {
		return cpu;
	}

	public void setCpu(Quantity cpu) {
		this.cpu = cpu;
	}

	public V1Beta1NodeMetricsUsage memory(Quantity memory) {
		this.memory = memory;
		return this;
	}

	public Quantity getMemory() {
		return memory;
	}

	public void setMemory(Quantity memory) {
		this.memory = memory;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		V1Beta1NodeMetricsUsage v1Beta1NodeMetricsUsage = (V1Beta1NodeMetricsUsage) o;

		return Objects.equals(this.cpu, v1Beta1NodeMetricsUsage.cpu)
				&& Objects.equals(this.memory, v1Beta1NodeMetricsUsage.memory);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpu, memory);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class V1Beta1NodeMetricsUsage {\n");
		sb.append("    cpu: ").append(toIndentedString(cpu)).append("\n");
		sb.append("    memory: ").append(toIndentedString(memory)).append("\n");
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
