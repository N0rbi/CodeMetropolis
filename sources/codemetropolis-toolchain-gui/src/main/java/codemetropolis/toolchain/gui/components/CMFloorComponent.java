package codemetropolis.toolchain.gui.components;

import codemetropolis.toolchain.gui.CustomMapperController;

public class CMFloorComponent extends CMBuildingTargetComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6330494425662343863L;
	
	public CMFloorComponent(CustomMapperController controller, CMTextArea infoPanel) {
		super(new CMDnDElement(controller.getFloor().getAttribute("width"),20,20,100,100, controller, infoPanel),
				new CMDnDElement(controller.getFloor().getAttribute("height"),140,20,100,100, controller, infoPanel),
				new CMDnDElement(controller.getFloor().getAttribute("length"),260,20,100,100, controller, infoPanel),
				new CMDnDElement(controller.getFloor().getAttribute("character"),20,150,100,100, controller, infoPanel),
				new CMDnDElement(controller.getFloor().getAttribute("external character"),140,140,100,100, controller, infoPanel),
				new CMDnDElement(controller.getFloor().getAttribute("torches"),320,180,100,100, controller, infoPanel));
	}
}
