package codemetropolis.toolchain.gui.components.listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import codemetropolis.toolchain.gui.CustomMapperController;
import codemetropolis.toolchain.gui.mapping.targettable.BuildingTargettable;

public class SourceChangeListener implements ItemListener {
	
	private BuildingTargettable targettable; 
	private CustomMapperController controller;

	public SourceChangeListener(BuildingTargettable targettable, CustomMapperController controller) {
		this.targettable = targettable;
		this.controller = controller;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED){
			String source = e.getItem().toString();
			source = source.equals("<<unset>>") ? null : source;
			targettable.setSource(source);
			controller.addOrReplaceSources(source, targettable);
		}

	}

}
