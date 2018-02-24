package codemetropolis.toolchain.gui.mapping.attribute;

import codemetropolis.toolchain.gui.mapping.MetricDataType;

class WidthAttribute extends BuildingAttribute{

	public WidthAttribute() {
		this.name = "width";
		this.dataType = MetricDataType.getMetricDataType("INT");
		this.description = "size near X";
	}

}
