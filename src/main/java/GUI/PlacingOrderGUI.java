package GUI;
import java.awt.Component;
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
	   
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(300, 200);
	       
	        ArrayList<String> products = new ArrayList<>();
	        products.add("Product A");
	        products.add("Product B");
	        products.add("Product C");
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
	        Integer[] quantities = {1, 2, 3, 4, 5,6,7,8,9,10};
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
					String selectedProduct = (String) productdropdown.getSelectedItem();
	                int selectedQuantity = (int) quantitydropdown.getSelectedItem();

	           
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
