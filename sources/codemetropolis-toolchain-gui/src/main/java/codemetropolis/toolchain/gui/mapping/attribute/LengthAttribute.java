package codemetropolis.toolchain.gui.mapping.attribute;

import codemetropolis.toolchain.gui.mapping.MetricDataType;

class LengthAttribute extends BuildingAttribute{

	public LengthAttribute() {
		this.name = "length";
		this.dataType = MetricDataType.getMetricDataType("INT");
		this.description = "size near Z";
	}

}
