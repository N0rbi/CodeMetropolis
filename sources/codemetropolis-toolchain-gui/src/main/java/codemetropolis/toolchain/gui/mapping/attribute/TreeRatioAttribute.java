package codemetropolis.toolchain.gui.mapping.attribute;

import codemetropolis.toolchain.gui.mapping.MetricDataType;
import codemetropolis.toolchain.gui.mapping.MetricItemType;

class TreeRatioAttribute extends BuildingAttribute {

	public TreeRatioAttribute(MetricItemType metricType) throws ConversionMismatchException {
		super(metricType);
		this.name = "tree-ratio";
		this.dataType = MetricDataType.normalizedFloat;
		this.description = "quantity of trees, bigger value means smaller spaces";
	}

}
