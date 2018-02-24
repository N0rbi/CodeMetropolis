package codemetropolis.toolchain.gui.mapping.attribute;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import codemetropolis.toolchain.gui.mapping.MetricDataType;
import codemetropolis.toolchain.gui.mapping.MetricItemType;

public abstract class BuildingAttribute {
	
	protected String name;
	protected String description;
	protected MetricDataType dataType;
	protected MetricItemType metricType;
	
	private PropertyChangeSupport change = new PropertyChangeSupport(this);
	
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

	public void setMetricType(MetricItemType metric) throws ConversionMismatchException {
		if(!metric.getDataType().equals(dataType)){
			throw new ConversionMismatchException("Cannot implicitly cast from " + metric.getDataType().getName() + " to " + dataType.getName());
		}
		change.firePropertyChange("metricType", this.metricType, metric);
		this.metricType = metric;
	}
	
	public void removeMetric() {
		change.firePropertyChange("metricType", this.metricType, null);
		this.metricType = null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof BuildingAttribute){
			this.name.equals(((BuildingAttribute)obj).name);
		}
		return super.equals(obj);
	}
	
	public boolean hasNullFields() {
		if(name == null || description == null || dataType == null || metricType == null){
			return true;
		}
		return dataType.hasNullFields() || metricType.hasNullFields();
	}


	@Override
	public String toString() {
		return "BuildingAttribute [name=" + name + ", description=" + description + ", dataType=" + dataType
				+ ", metricType=" + metricType + "]";
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		change.addPropertyChangeListener(listener);
	}
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		change.removePropertyChangeListener(listener);
	}
	
	
	
}