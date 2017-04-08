package codemetropolis.toolchain.gui.mapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Bean for the parsed metrics.
 * 
 * @author N0rbi
 *
 */
public class MetricItemType {
	
	private static HashMap<String, List<String>> stringValuesByMetricType;

	private String name;
	private MetricDataType dataType;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MetricDataType getDataType() {
		return dataType;
	}
	public void setDataType(MetricDataType dataType) {
		this.dataType = dataType;
	}
	
	public void addStringValueToMetric(String metric, String value) {
		if(!stringValuesByMetricType.containsKey(metric)){
			stringValuesByMetricType.put(metric, new ArrayList<String>());
		}
		
		stringValuesByMetricType.get(metric).add(value);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MetricItemType){
			MetricItemType other = (MetricItemType) obj;
			return other.name.equals(name) && other.dataType.equals(dataType);
		}
		return super.equals(obj);
	}
	@Override
	public String toString() {
		return "MetricItemType [name=" + name + ", dataType=" + dataType + "]";
	}
	
	
	
}
