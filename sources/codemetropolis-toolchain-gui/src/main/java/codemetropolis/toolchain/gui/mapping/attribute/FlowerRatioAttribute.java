package codemetropolis.toolchain.gui.mapping.attribute;

import codemetropolis.toolchain.gui.mapping.MetricDataType;
import codemetropolis.toolchain.gui.mapping.MetricItemType;

class FlowerRatioAttribute extends BuildingAttribute {

	public FlowerRatioAttribute(MetricItemType metricType) throws ConversionMismatchException {
		super(metricType);
		this.name = "flower-ratio";
		this.dataType = MetricDataType.normalizedFloat;
		this.description = "quantity of torches, bigger value means smaller spaces";
	}

}
