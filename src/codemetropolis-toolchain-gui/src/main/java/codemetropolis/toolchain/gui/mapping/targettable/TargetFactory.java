package codemetropolis.toolchain.gui.mapping.targettable;

import codemetropolis.toolchain.gui.mapping.attribute.BuildingAttribute;

public class TargetFactory {
	
	public BuildingTargettable getTarget(String s, BuildingAttribute... attributes) throws 
				AttributeDuplicateException, WrongAttributeException, UnknownTargetException {
		switch(s) {
			case "CELLAR": return new CellarTarget(attributes);
			case "FLOOR": return new FloorTarget(attributes);
			case "GARDEN": return new GardenTarget(attributes);
			default: throw new UnknownTargetException("The given target does not exist.");
		}
	}

}
