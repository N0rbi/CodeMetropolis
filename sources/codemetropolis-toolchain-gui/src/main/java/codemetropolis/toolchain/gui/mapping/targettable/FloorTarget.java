package codemetropolis.toolchain.gui.mapping.targettable;

import java.util.HashSet;

import codemetropolis.toolchain.gui.mapping.attribute.BuildingAttribute;

class FloorTarget extends BuildingTargettable {
	
	private static final HashSet<String> allowedAttributes;
	
	static {
		allowedAttributes = new HashSet<>();
		allowedAttributes.add("width");
		allowedAttributes.add("height");
		allowedAttributes.add("length");
		allowedAttributes.add("character");
		allowedAttributes.add("external character");
		allowedAttributes.add("torches");
	}

	public FloorTarget() {
		this.name="floor";
	}
	
	@Override
	public void addBuildingAttribute(BuildingAttribute ba) throws AttributeDuplicateException, WrongAttributeException {
		if(!isAttributeAllowed(allowedAttributes, ba)){
			throw new WrongAttributeException();
		}
		super.addBuildingAttribute(ba);
	}

}
