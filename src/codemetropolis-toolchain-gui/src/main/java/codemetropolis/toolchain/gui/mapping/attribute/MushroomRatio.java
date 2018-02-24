package codemetropolis.toolchain.gui.mapping.attribute;

import codemetropolis.toolchain.gui.mapping.MetricDataType;
import codemetropolis.toolchain.gui.mapping.MetricItemType;

class MushroomRatio extends BuildingAttribute {

	public MushroomRatio(MetricItemType metricType) throws ConversionMismatchException {
		super(metricType);
		this.name = "mushroom-ratio";
		this.dataType = MetricDataType.getMetricDataType("FLOAT");
		this.description = "quantity of mushrooms, bigger value means smaller spaces";
	}

}
