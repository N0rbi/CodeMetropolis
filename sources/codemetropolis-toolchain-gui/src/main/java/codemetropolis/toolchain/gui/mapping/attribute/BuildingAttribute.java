package codemetropolis.toolchain.gui.mapping.attribute;

import codemetropolis.toolchain.gui.mapping.MetricDataType;
import codemetropolis.toolchain.gui.mapping.MetricItemType;

public abstract class BuildingAttribute {
	
	protected String name;
	protected String description;
	protected MetricDataType dataType;
	protected MetricItemType metricType;
	
	public BuildingAttribute(MetricItemType metricType) throws ConversionMismatchException {
		this.metricType = metricType;
		if (!this.dataType.equals(this.metricType.getDataType())){
			throw new ConversionMismatchException();
		}
	}
	
	
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public MetricDataType getDataType() {
		return dataType;
	}
	public MetricItemType getMetricType() {
		return metricType;
	}
}