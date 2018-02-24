package codemetropolis.toolchain.gui.mapping.attribute;

import codemetropolis.toolchain.gui.mapping.MetricDataType;

class ExternalCharacterAttribute extends BuildingAttribute {

	public ExternalCharacterAttribute() {
		this.name = "external character";
		this.dataType = MetricDataType.getMetricDataType("STRING");
		this.description = "secondary material the structure is made of";
	}

}
