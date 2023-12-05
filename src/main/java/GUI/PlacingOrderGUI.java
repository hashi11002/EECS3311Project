package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.*;

import Model.Fetcher;
import Model.Product.Product;
import httpClient.httpClient;

public class PlacingOrderGUI extends JFrame {
	private JComboBox<String> productComboBox;
	private JComboBox<Integer> quantitydropdown;
	private JComboBox<String> productdropdown;
	
	public static void main(String[] args) {
		
		PlacingOrderGUI placingOrder = new PlacingOrderGUI();
	}
	
	public PlacingOrderGUI() {
		Fetcher selection = new Fetcher();
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
			Fetcher selection = new Fetcher();
			String selectedProduct = (String) productdropdown.getSelectedItem();
			Product product =  selection.getProductObj(selectedProduct);
			//Store this product into the context.
	                int selectedQuantity = (int) quantitydropdown.getSelectedItem();
	                //store this quantity into the context.
	
	                LocalDateTime timestamp = java.time.LocalDateTime.now();
	                String date = timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	               
	                String message = "Order Details:\n" +  "Product: " + selectedProduct + "\n" + "Quantity: " + selectedQuantity + "\n" +  "Timestamp: " + date;
	                JOptionPane.showMessageDialog(PlacingOrderGUI.this, message, "Order Placed", JOptionPane.INFORMATION_MESSAGE);
	                httpClient client = new httpClient();
	                JOptionPane.showMessageDialog(PlacingOrderGUI.this, 
	                		client.doThehttpCalltest1(selectedProduct, String.valueOf(selectedQuantity), date), "Server Response", JOptionPane.INFORMATION_MESSAGE);
	        }
				

        });

        add(panel);
        setLocationRelativeTo(null);
		
	}


}
