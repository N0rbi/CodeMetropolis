package codemetropolis.toolchain.gui.mapping.attribute;

public class AttributeFactory {
	
	// http://geryxyz.github.io/CodeMetropolis/toolchain/mapping/
	public BuildingAttribute getBuildingAttribute(String name) throws UnknownAttributeException {
		switch (name) {
			case "WIDTH": return new WidthAttribute();
			case "HEIGHT": return new HeightAttribute();
			case "LENGTH": return new LengthAttribute();
			case "CHARACTER": return new CharacterAttribute();
			case "EXTERNAL_CHARACTER": return new ExternalCharacterAttribute();
			case "TORCHES": return new TourchesAttribute();
			case "FLOWER-RATIO": return new FlowerRatioAttribute();
			case "TREE-RATIO": return new TreeRatioAttribute();
			case "MUSHROOM-RATIO": return new MushroomRatio();
			default: throw new UnknownAttributeException("The given attribute does not exist.");
		}
	}

}
