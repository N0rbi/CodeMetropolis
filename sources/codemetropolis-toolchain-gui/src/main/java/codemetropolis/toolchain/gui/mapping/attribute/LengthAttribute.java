package codemetropolis.toolchain.gui.mapping.attribute;

import codemetropolis.toolchain.gui.mapping.MetricDataType;
import codemetropolis.toolchain.gui.mapping.MetricItemType;

class LengthAttribute extends BuildingAttribute{

	public LengthAttribute(MetricItemType metricType) throws ConversionMismatchException {
		super(metricType);
		this.name = "length";
		this.dataType = MetricDataType.integer;
		this.description = "size near Z";
	}

}
