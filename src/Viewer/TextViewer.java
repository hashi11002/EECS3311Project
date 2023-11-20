package Viewer;

import javax.swing.JPanel;

import warehouseServerVisualizer.gui.MainServerUI;

public class TextViewer implements Viewer {
	
	private static TextViewer instance = null;
	
	private TextViewer() {}
	
	public static TextViewer getInstance() {
		if (instance == null) {
			instance = new TextViewer();
		}
		
		return instance;
	}
	
	public void displayOrder() {
		MainServerUI.getInstance().createNewReport();
	}
	
	public void update() {
		MainServerUI.getInstance().updateReport();
	}
	
	public void displayRejected() {
		MainServerUI.getInstance().displayRejected();
	}
	
	public void displayRestockBegun() {
		MainServerUI.getInstance().displayRestockBegun();
	}
	
	public void displayRestockEnded() {
		MainServerUI.getInstance().displayRestockEnded();
	}
	
	public void displayPending() {
		MainServerUI.getInstance().displayPending();
	}

}
