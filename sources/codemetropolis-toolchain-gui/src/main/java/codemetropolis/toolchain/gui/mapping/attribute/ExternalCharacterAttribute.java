package codemetropolis.toolchain.gui.mapping.attribute;

import codemetropolis.toolchain.gui.mapping.MetricDataType;
import codemetropolis.toolchain.gui.mapping.MetricItemType;

class ExternalCharacterAttribute extends BuildingAttribute {

	public ExternalCharacterAttribute(MetricItemType metricType) throws ConversionMismatchException {
		super(metricType);
		this.name = "external_character";
		this.dataType = MetricDataType.string;
		this.description = "secondary material the structure is made of";
	}

}
