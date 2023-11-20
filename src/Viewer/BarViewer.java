package Viewer;

import javax.swing.JPanel;

import warehouseServerVisualizer.gui.MainServerUI;

public class BarViewer implements Viewer{
	
	private static BarViewer instance = null;
	
	private BarViewer() {}
	
	public static BarViewer getInstance() {
		if (instance == null) {
			instance = new BarViewer();
		}
		
		return instance;
	}
	

	@Override
	public void displayOrder() {
		MainServerUI.getInstance().createBar();
		
	}
	
	public void update() {
		MainServerUI.getInstance().updateBar();
	}
	
}
