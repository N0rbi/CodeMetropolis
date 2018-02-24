package codemetropolis.toolchain.gui.mapping;


public class MetricDataType {
	
	public static MetricDataType getMetricDataType(String s){
		switch(s) {
		case "INT":
			return new MetricDataType("integer");
		case "FLOAT":
			return new MetricDataType("float");
		case "STRING":
			return new MetricDataType("string");
		}
		return null;
	}
	
	private String name;
	
	private MetricDataType(String s) {
		this.name = s;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MetricDataType) {
			MetricDataType other = (MetricDataType) obj;
			return this.name.equals(other.name);
		}
		return super.equals(obj);
	}

	public boolean hasNullFields() {
		return name == null;
	}

	@Override
	public String toString() {
		return "MetricDataType [name=" + name + "]";
	}
}
