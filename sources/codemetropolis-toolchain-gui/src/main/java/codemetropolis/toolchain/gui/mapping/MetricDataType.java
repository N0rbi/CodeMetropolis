package codemetropolis.toolchain.gui.mapping;

public enum MetricDataType {
	integer("int"), string("string"), normalizedFloat("float"), unknown("none");
	
	@SuppressWarnings("unused")
	private String s;
	MetricDataType(String s) {
		if (s.equals("int") || s.equals("string") || s.equals("float")){
			this.s = s;
		}else {
			this.s = "none";
		}
	}
}
