package codemetropolis.toolchain.gui.mapping.targettable;

import java.util.HashSet;

import codemetropolis.toolchain.gui.mapping.attribute.BuildingAttribute;

class CellarTarget extends BuildingTargettable {
	
	private static final HashSet<String> allowedAttributes;
	
	static {
		allowedAttributes = new HashSet<>();
		allowedAttributes.add("width");
		allowedAttributes.add("height");
		allowedAttributes.add("length");
		allowedAttributes.add("character");
		allowedAttributes.add("external_character");
		allowedAttributes.add("torches");
	}
	

	public CellarTarget(BuildingAttribute[] attributes) throws AttributeDuplicateException, WrongAttributeException {
		super(attributes);
		this.name="cellar";
		if (!areAllAttributesAllowed(allowedAttributes)){
			throw new WrongAttributeException();
		}
	}

}
