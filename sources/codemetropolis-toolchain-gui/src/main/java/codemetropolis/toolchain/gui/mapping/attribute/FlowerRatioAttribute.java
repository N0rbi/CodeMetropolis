package codemetropolis.toolchain.gui.mapping.attribute;

import codemetropolis.toolchain.gui.mapping.MetricDataType;

class FlowerRatioAttribute extends BuildingAttribute {

	public FlowerRatioAttribute() {
		this.name = "flower-ratio";
		this.dataType = MetricDataType.getMetricDataType("FLOAT");
		this.description = "quantity of torches, bigger value means smaller spaces";
	}

}
