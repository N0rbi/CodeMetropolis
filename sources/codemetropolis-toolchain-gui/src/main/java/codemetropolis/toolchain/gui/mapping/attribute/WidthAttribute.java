package codemetropolis.toolchain.gui.mapping.attribute;

import codemetropolis.toolchain.gui.mapping.MetricDataType;
import codemetropolis.toolchain.gui.mapping.MetricItemType;

class WidthAttribute extends BuildingAttribute{

	public WidthAttribute(MetricItemType metricType) throws ConversionMismatchException {
		super(metricType);
		this.name = "width";
		this.dataType = MetricDataType.getMetricDataType("INTEGER");
		this.description = "size near X";
	}

}
