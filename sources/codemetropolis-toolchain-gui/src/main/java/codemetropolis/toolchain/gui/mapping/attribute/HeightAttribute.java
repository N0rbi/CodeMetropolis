package codemetropolis.toolchain.gui.mapping.attribute;

import codemetropolis.toolchain.gui.mapping.MetricDataType;

class HeightAttribute extends BuildingAttribute{

	public HeightAttribute() {
		this.name = "height";
		this.dataType = MetricDataType.getMetricDataType("INT");
		this.description = "size near Y";
	}

}
