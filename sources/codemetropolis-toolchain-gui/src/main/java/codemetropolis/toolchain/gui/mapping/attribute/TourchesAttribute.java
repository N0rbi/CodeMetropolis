package codemetropolis.toolchain.gui.mapping.attribute;

import codemetropolis.toolchain.gui.mapping.MetricDataType;

class TourchesAttribute extends BuildingAttribute {

	public TourchesAttribute() {
		this.name = "torches";
		this.dataType = MetricDataType.getMetricDataType("INT");
		this.description = "quantity of torches, bigger value means smaller spaces between torches";
	}

}
