package codemetropolis.toolchain.gui.mapping.attribute;

import codemetropolis.toolchain.gui.mapping.MetricDataType;
import codemetropolis.toolchain.gui.mapping.MetricItemType;

class TourchesAttribute extends BuildingAttribute {

	public TourchesAttribute(MetricItemType metricType) throws ConversionMismatchException {
		super(metricType);
		this.name = "torches";
		this.dataType = MetricDataType.integer;
		this.description = "quantity of torches, bigger value means smaller spaces between torches";
	}

}
