package codemetropolis.toolchain.gui.mapping.attribute;

import codemetropolis.toolchain.gui.mapping.MetricDataType;
import codemetropolis.toolchain.gui.mapping.MetricItemType;

class CharacterAttribute extends BuildingAttribute {

	public CharacterAttribute(MetricItemType metricType) throws ConversionMismatchException {
		super(metricType);
		this.name = "character";
		this.dataType = MetricDataType.string;
		this.description = "primary material the structure is made of";
	}

}
