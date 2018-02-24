package codemetropolis.toolchain.gui.components;

import codemetropolis.toolchain.gui.CustomMapperController;

public class CMCellarComponent extends CMBuildingTargetComponent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7090917388460232703L;

	public CMCellarComponent(CustomMapperController controller, CMTextArea infoPanel) {
		super(new CMDnDElement(controller.getCellar().getAttribute("width"),20,20,100,100, controller, infoPanel),
				new CMDnDElement(controller.getCellar().getAttribute("height"),140,20,100,100, controller, infoPanel),
				new CMDnDElement(controller.getCellar().getAttribute("length"),260,20,100,100, controller, infoPanel),
				new CMDnDElement(controller.getCellar().getAttribute("character"),20,150,100,100, controller, infoPanel),
				new CMDnDElement(controller.getCellar().getAttribute("external character"),140,140,100,100, controller, infoPanel),
				new CMDnDElement(controller.getCellar().getAttribute("torches"),320,180,100,100, controller, infoPanel));
	}


}
