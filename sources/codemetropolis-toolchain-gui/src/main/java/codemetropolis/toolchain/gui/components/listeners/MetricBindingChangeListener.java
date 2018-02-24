package codemetropolis.toolchain.gui.components.listeners;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import codemetropolis.toolchain.gui.components.CMDnDElement;
import codemetropolis.toolchain.gui.mapping.MetricItemType;

public class MetricBindingChangeListener implements PropertyChangeListener {
		
	private CMDnDElement element;
	
	public MetricBindingChangeListener(CMDnDElement element) {
		this.element = element;
	}
	
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		MetricItemType metric = (MetricItemType)evt.getNewValue();
		String newValue = "";
		if(metric == null){
			newValue = "";
			element.setColorFree();
			element.setAcceptDrop(true);
		}else {
			newValue = metric.getName();
		}
		element.setText(newValue);
		
	}

}
