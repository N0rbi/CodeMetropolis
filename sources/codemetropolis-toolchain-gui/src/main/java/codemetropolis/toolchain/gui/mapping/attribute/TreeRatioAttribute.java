package codemetropolis.toolchain.gui.mapping.attribute;

import codemetropolis.toolchain.gui.mapping.MetricDataType;

class TreeRatioAttribute extends BuildingAttribute {

	public TreeRatioAttribute() {
		this.name = "tree-ratio";
		this.dataType = MetricDataType.getMetricDataType("FLOAT");
		this.description = "quantity of trees, bigger value means smaller spaces";
	}

}
