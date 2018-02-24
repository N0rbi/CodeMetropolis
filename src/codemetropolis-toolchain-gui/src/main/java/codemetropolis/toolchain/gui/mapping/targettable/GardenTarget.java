package codemetropolis.toolchain.gui.mapping.targettable;

import java.util.HashSet;

import codemetropolis.toolchain.gui.mapping.attribute.BuildingAttribute;

class GardenTarget extends BuildingTargettable {
	
	private static final HashSet<String> allowedAttributes;
	
	static {
		allowedAttributes = new HashSet<>();
		allowedAttributes.add("flower-ratio");
		allowedAttributes.add("tree-ratio");
		allowedAttributes.add("mushroom-ratio");
	}

	public GardenTarget(BuildingAttribute... attributes) throws AttributeDuplicateException, WrongAttributeException {
		super(attributes);
		this.name="garden";
		if (!areAllAttributesAllowed(allowedAttributes)){
			throw new WrongAttributeException();
		}
	}

}
