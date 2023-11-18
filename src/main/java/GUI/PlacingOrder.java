package GUI;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PlacingOrder extends JFrame {
	private JComboBox<String> productComboBox;
	private JTextField quantitydropdown;
	private JComboBox<String> productdropdown;
	
	public static void main(String[] args) {
		
		PlacingOrder placingOrder = new PlacingOrder();
		}
	  public PlacingOrder() {
	   
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(300, 200);
	        String[] products = {"Product A", "Product B", "Product C"};
	        createWindow(products);
	        setVisible(true);
	    }
	  private void createWindow(String[] products) {
	        JPanel panel = new JPanel();
	        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	        // dropdown menue 
	        JLabel chooseproduct = new JLabel("Select Product:");
	        panel.add(chooseproduct);

	        productdropdown = new JComboBox<>(products);
	        panel.add(productdropdown);

	        // Quantity input
	        JLabel choosequantity = new JLabel("Quantity Selected:");
	        panel.add(choosequantity);

	        quantitydropdown = new JTextField();
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
					// TODO Auto-generated method stub
					
				}
	        });

	        // Add panel to the frame
	        add(panel);

	        // Center the frame on the screen
	        setLocationRelativeTo(null);
	    }

}
