package codemetropolis.toolchain.gui.mapping.attribute;

import codemetropolis.toolchain.gui.mapping.MetricDataType;

class CharacterAttribute extends BuildingAttribute {

	public CharacterAttribute() {
		this.name = "character";
		this.dataType = MetricDataType.getMetricDataType("STRING");
		this.description = "primary material the structure is made of";
	}

}
