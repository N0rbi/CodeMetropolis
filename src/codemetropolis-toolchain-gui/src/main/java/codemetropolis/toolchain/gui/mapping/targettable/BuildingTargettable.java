package codemetropolis.toolchain.gui.mapping.targettable;

import java.util.HashSet;
import java.util.Set;

import codemetropolis.toolchain.gui.mapping.attribute.BuildingAttribute;

public abstract class BuildingTargettable {
	
	protected String name;
	protected String source;
	protected BuildingAttribute[] attributes;

	public BuildingTargettable(BuildingAttribute... attributes) throws AttributeDuplicateException{
		this.attributes = attributes;
		
		HashSet<String> attributeNameSet = new HashSet<>();
		
		for (BuildingAttribute a : this.attributes) {
			if (attributeNameSet.contains(a.getName())){
				throw new AttributeDuplicateException();
			}
		}
	}
	
	protected boolean areAllAttributesAllowed(Set<String> criteria) {
		for (BuildingAttribute ba : attributes) {
			if (!criteria.contains(ba.getName())){
				return false;
			}
		}
		return true;
	}
	
	public String getName() {
		return name;
	}

	public String getSource() {
		return source;
	}

	public BuildingAttribute[] getAttributes() {
		return attributes;
	}

}
