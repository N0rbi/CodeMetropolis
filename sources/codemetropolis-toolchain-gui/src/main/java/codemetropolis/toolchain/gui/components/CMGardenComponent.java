package codemetropolis.toolchain.gui.components;


import codemetropolis.toolchain.gui.CustomMapperController;

public class CMGardenComponent extends CMBuildingTargetComponent{

	public CMGardenComponent(CustomMapperController controller, CMTextArea infoPanel) {
		super(new CMDnDElement(controller.getGarden().getAttribute("flower-ratio"),20,20,100,100, controller, infoPanel),
				new CMDnDElement(controller.getGarden().getAttribute("tree-ratio"),260,20,100,100, controller, infoPanel),
				new CMDnDElement(controller.getGarden().getAttribute("mushroom-ratio"),320,180,100,100, controller, infoPanel));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4504456538256324222L;
	


}
