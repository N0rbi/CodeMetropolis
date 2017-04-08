package codemetropolis.toolchain.gui.mapping.attribute;

import codemetropolis.toolchain.gui.mapping.MetricDataType;
import codemetropolis.toolchain.gui.mapping.MetricItemType;

class HeightAttribute extends BuildingAttribute{

	public HeightAttribute(MetricItemType metricType) throws ConversionMismatchException {
		super(metricType);
		this.name = "height";
		this.dataType = MetricDataType.getMetricDataType("INTEGER");
		this.description = "size near Y";
	}

}
