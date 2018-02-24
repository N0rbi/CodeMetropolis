package codemetropolis.toolchain.gui.mapping.attribute;

import codemetropolis.toolchain.gui.mapping.MetricDataType;

class MushroomRatio extends BuildingAttribute {

	public MushroomRatio() {
		this.name = "mushroom-ratio";
		this.dataType = MetricDataType.getMetricDataType("FLOAT");
		this.description = "quantity of mushrooms, bigger value means smaller spaces";
	}

}
