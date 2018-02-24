package codemetropolis.toolchain.gui.components.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.TransferHandler;

import codemetropolis.toolchain.gui.components.CMButton;

public class DragAndDropListener extends MouseAdapter implements MouseListener{
	
	@Override
    public void mouseDragged(MouseEvent e) {
        CMButton button = (CMButton) e.getSource();
        TransferHandler handle = button.getTransferHandler();
        handle.exportAsDrag(button, e, TransferHandler.COPY);
    }
}
