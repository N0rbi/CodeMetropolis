package codemetropolis.toolchain.gui.mapping.targettable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Set;

import codemetropolis.toolchain.gui.mapping.attribute.BuildingAttribute;

public abstract class BuildingTargettable {
	
	protected String name;
	protected String source;
	protected ArrayList<BuildingAttribute> attributes;
	
	private PropertyChangeSupport change = new PropertyChangeSupport(this);

	public BuildingTargettable(){
		attributes = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setSource(String source) {
		change.firePropertyChange("source", this.source, source);
		this.source = source;
	}

	public String getSource() {
		return source;
	}

	public ArrayList<BuildingAttribute> getAttributes() {
		return attributes;
	}
	
	public void addBuildingAttribute(BuildingAttribute ba) throws AttributeDuplicateException, WrongAttributeException{
		if(attributes.contains(ba)){
			throw new AttributeDuplicateException();
		}
		attributes.add(ba);
	}
	
	protected boolean isAttributeAllowed(Set<String> criteria, BuildingAttribute ba) {
		if (!criteria.contains(ba.getName())){
			return false;
		}
		return true;
	}
	
	public BuildingAttribute getAttribute(String s) {
		for(BuildingAttribute ba : attributes) {
			if(ba.getName().equals(s)){
				return ba;
			}
		}
		return null;
	}
	
	public boolean hasNullFields() {
		if(name == null || source == null || attributes == null) {
			return true;
		}
		for(BuildingAttribute a : attributes){
			if(a.hasNullFields()){
				return true;
			}
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "BuildingTargettable [name=" + name + ", source=" + source + ", attributes=" + attributes + "]";
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		change.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		change.removePropertyChangeListener(listener);
	}
}
