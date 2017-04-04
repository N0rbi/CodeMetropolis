package codemetropolis.toolchain.gui.mapping.attribute;

import codemetropolis.toolchain.gui.mapping.MetricItemType;

public class AttributeFactory {
	
	// http://geryxyz.github.io/CodeMetropolis/toolchain/mapping/
	
	public BuildingAttribute getBuildingAttribute(String name, MetricItemType metricType) throws UnknownAttributeException, ConversionMismatchException {
		switch (name) {
			case "WIDTH": return new WidthAttribute(metricType);
			case "HEIGHT": return new HeightAttribute(metricType);
			case "LENGTH": return new LengthAttribute(metricType);
			case "CHARACTER": return new CharacterAttribute(metricType);
			case "EXTERNAL_CHARACTER": return new ExternalCharacterAttribute(metricType);
			case "TORCHES": return new TourchesAttribute(metricType);
			case "FLOWER-RATIO": return new FlowerRatioAttribute(metricType);
			case "TREE-RATIO": return new TreeRatioAttribute(metricType);
			case "MUSHROOM-RATIO": return new MushroomRatio(metricType);
			default: throw new UnknownAttributeException();
		}
	}

}
