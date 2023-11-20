package GUI;
import java.awt.Component;
import Orders.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.*;

import Orders.Product;

public class PlacingOrderGUI extends JFrame {
	private JComboBox<String> productComboBox;
	private JComboBox<Integer> quantitydropdown;
	private JComboBox<String> productdropdown;
	
	public static void main(String[] args) {
		
		PlacingOrderGUI placingOrder = new PlacingOrderGUI();
		}
	  public PlacingOrderGUI() {
		  
		  OrderSelection selection = new OrderSelection();
	   
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(300, 200);
	       
	        ArrayList<String> products = new ArrayList<>(selection.getProductNames());

	        createWindow(products);
	        setVisible(true);
	    }
	  private void createWindow(ArrayList<String> products) {
	        JPanel panel = new JPanel();
	        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	        // dropdown menue 
	        JLabel chooseproduct = new JLabel("Select Product:");
	        panel.add(chooseproduct); 
	    

	        String[] productsArray = products.toArray(new String[0]);
	        productdropdown = new JComboBox<>(productsArray);
	        panel.add(productdropdown);

	        // Quantity input
	        JLabel choosequantity = new JLabel("Quantity Selected:");
	        Integer[] quantities = {1, 50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900, 950, 1000, 1050, 1100};
	        panel.add(choosequantity);

	        quantitydropdown = new JComboBox<>(quantities);
	        panel.add(quantitydropdown);

	        // Order button
	        JButton orderButton = new JButton("Place Order");
	        panel.add(orderButton);
	        orderButton.addActionListener(new ActionListener() {
	           
	        	@Override
	            public void actionPerformed(ActionEvent e) {
	                placeOrder();
	            }

				private void placeOrder() {
					OrderSelection selection = new OrderSelection();
					String selectedProduct = (String) productdropdown.getSelectedItem();
					Product product =  selection.getProductByName(selectedProduct);
					//Store this product into the context.
	                int selectedQuantity = (int) quantitydropdown.getSelectedItem();
	                //store this quantity into the context.

	           
	                java.util.Date date = new java.util.Date();
	                Timestamp timestamp = new Timestamp(date.getTime());

	               
	                String message = "Order Details:\n" +  "Product: " + selectedProduct + "\n" + "Quantity: " + selectedQuantity + "\n" +  "Timestamp: " + timestamp;
	                JOptionPane.showMessageDialog(PlacingOrderGUI.this, message, "Order Placed", JOptionPane.INFORMATION_MESSAGE);
	            }
					

	        });

	        add(panel);
	        setLocationRelativeTo(null);
	    }

}
