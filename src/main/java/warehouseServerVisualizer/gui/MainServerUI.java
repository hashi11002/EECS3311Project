/*************************************************
 * FALL 2022
 * EECS 3311 GUI SAMPLE CODE
 * ONLT AS A REFERENCE TO SEE THE USE OF THE jFree FRAMEWORK
 * THE CODE BELOW DOES NOT DEPICT THE DESIGN TO BE FOLLOWED 
 */

package warehouseServerVisualizer.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import warehouseServerVisualizer.utils.AvailableProductList;
import warehouseServerVisualizer.utils.LastOrder;

public class MainServerUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map<String, Integer> productData;
	private static LastOrder theLastOrder;
	private static JPanel east;
	private static JPanel west;

	private static MainServerUI instance;
	private static JTextArea report;
	private static DefaultCategoryDataset dataset;
	private static CategoryPlot plot;

	public static MainServerUI getInstance() {
		if (instance == null)
			instance = new MainServerUI();

		return instance;
	}

	private MainServerUI() {
		// Set window title
		super("Warehouse Server UI");

		productData = AvailableProductList.getInstance().getAvailableProductList();
		theLastOrder = LastOrder.getInstance();


		// Set charts region
		west = new JPanel();
		west.setLayout(new GridLayout(2, 0));

		east = new JPanel();
		east.setLayout(new GridLayout(2, 0));

		getContentPane().add(west, BorderLayout.WEST);
		getContentPane().add(east, BorderLayout.EAST);
		
		setSize(800,600);

//		createCharts(west, east);

	}

//	private void createCharts(JPanel west, JPanel east) {
//
//		createBar();
//		createNewReport();
//
//	}

	public void createNewReport() {
		east.removeAll();
		report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 600));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		String reportMessage1, reportMessage2;

		reportMessage1 = "Last Order\n" + "==========================\n";
		reportMessage1 = reportMessage1 + "Product: " + theLastOrder.getProductName() + "\n" 
		                 + "Quantity:" +  theLastOrder.getQuantity() + "\n"
		                 + "TimeStamp:" +  theLastOrder.getDate() + "\n";

		reportMessage2 = "Current Product Quantity in Warehouse\n" + "==============================\n";

		for (Map.Entry<String, Integer> entry : productData.entrySet()) {
			//System.out.println("IN LOOP " + entry.getKey() + " " + entry.getValue());
			reportMessage2 = reportMessage2 + entry.getKey();
			reportMessage2 = reportMessage2 + "\n \t Quantity ==> " + entry.getValue() + "\n";

		}


	

		report.setText(reportMessage1 + reportMessage2);
		JScrollPane outputScrollPane = new JScrollPane(report);
		east.add(outputScrollPane);
	}
	
	public void updateReport() {
		productData = AvailableProductList.getInstance().getAvailableProductList();
		String reportMessage2;

		reportMessage2 = "Current Product Quantity in Warehouse\n" + "==============================\n";

		for (Map.Entry<String, Integer> entry : productData.entrySet()) {
			//System.out.println("IN LOOP " + entry.getKey() + " " + entry.getValue());
			reportMessage2 = reportMessage2 + entry.getKey();
			reportMessage2 = reportMessage2 + "\n \t Quantity ==> " + entry.getValue() + "\n";

		}
		report.setText(report.getText() + "\n" + reportMessage2);
		setVisible(true);
	}
	
	public void displayRejected() {
		
		
		String rejectedMessage = "Order for Product " + theLastOrder.getProductName() + " Quantity " + theLastOrder.getQuantity() + " exceeds the max quantity set for this product and cannot be processed";
		
		report.setText(report.getText() + "\n" + rejectedMessage);
	}
	
	public void displayRestockBegun() {
		String message = "Restocking Operation for Product " + theLastOrder.getProductName() + " initiated";
		
		report.setText(report.getText() + "\n" + message);
	}
	
	public void displayRestockEnded() {
		String message = "Restocking Operation for Product " + theLastOrder.getProductName() + " completed";
		
		report.setText(report.getText() + "\n" + message);
	}
	
	public void displayPending() {
		String message = "Order for Product " + theLastOrder.getProductName() + " Quantity " + theLastOrder.getQuantity() + " is pending – order exceeds available quantity";

		report.setText(report.getText() + "\n" + message);
	}

	public void createBar() {
		west.removeAll();
		dataset = new DefaultCategoryDataset();

		for (Map.Entry<String, Integer> entry : productData.entrySet()) {
			dataset.setValue(entry.getValue(), entry.getKey(), "");

		}


		plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();
		BarRenderer barrenderer2 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new NumberAxis(""));

		plot.setRenderer(1, barrenderer2);

		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis

		JFreeChart barChart = new JFreeChart("Warehouse Product Monitor System",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);


		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(400, 600));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);
		setVisible(true);
	}
	
	public void updateBar() {
		productData = AvailableProductList.getInstance().getAvailableProductList();
		for (Map.Entry<String, Integer> entry : productData.entrySet()) {
			dataset.setValue(entry.getValue(), entry.getKey(), "");

		}


		plot.setDataset(0, dataset);
		setVisible(true);
		
	}

	public static void main(String[] args) {

		JFrame frame = MainServerUI.getInstance();
		frame.setSize(800, 600);
		// frame.pack();
		frame.setVisible(true);
	}
	// TODO Auto-generated method stub

}