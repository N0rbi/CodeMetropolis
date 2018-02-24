package codemetropolis.toolchain.gui.mapping.targettable;

public class TargetFactory {
	
	public BuildingTargettable getTarget(String s) throws UnknownTargetException {
		switch(s) {
			case "CELLAR": return new CellarTarget();
			case "FLOOR": return new FloorTarget();
			case "GARDEN": return new GardenTarget();
			default: throw new UnknownTargetException("The given target does not exist.");
		}
	}

}
