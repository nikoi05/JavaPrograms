package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.*;

public class billingsystem extends JFrame {
    // Class member variables
    JTextField itemnamefield;
    JTextField quantityfield;
    JTextField pricefield;
    JTextArea billarea;
    JTextField discountfield;
    JTextField changefield;
    JTextField AmountTendered;
    JTextField totalamount;
    JTextField taxfield;
    double  finaltotalamount;
    double  finaldiscount;
    private mainbillsystem mbs;
    public billingsystem() {
    	this.mbs = new mainbillsystem();
    }
    public void startAPP() {
    	
        JFrame f = new JFrame("Nico's Billing System");
        JLabel title = new JLabel("Nico's Billing System");
        title.setHorizontalAlignment(JLabel.CENTER);
        f.setSize(800, 500); // Increased width for better layout
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Set layout for the main frame
        f.setLayout(new BorderLayout());
        f.add(title, BorderLayout.NORTH);
        
        // Create the input panel & layouts
        JPanel inputpanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputpanel.setBackground(Color.GRAY);
        inputpanel.setPreferredSize(new Dimension(300, 250));   
        
        // Field for item name
        inputpanel.add(new JLabel("Item Name: "));
        itemnamefield = new JTextField(7);
        inputpanel.add(itemnamefield);
       
        // Field for item quantity
        inputpanel.add(new JLabel("Quantity: "));
        quantityfield = new JTextField(7); // Initialize quantityfield
        inputpanel.add(quantityfield);
        
        // Field for item price
        inputpanel.add(new JLabel("Price: "));
        pricefield = new JTextField(7); // Initialize pricefield
        inputpanel.add(pricefield);
        

        
        JButton addbutton = new JButton("Add Item");
        inputpanel.add(addbutton);
        JButton clearbutton= new JButton("Clear Fields");
        inputpanel.add(clearbutton);
        
        // Summary fields panel
        JPanel summaryfields = new JPanel();
        summaryfields.setLayout(new GridLayout(6, 2, 5, 5));
        summaryfields.setPreferredSize(new Dimension(300, 250));
        summaryfields.setBackground(Color.CYAN);
        summaryfields.add(new JLabel("Total Amount:"));
        totalamount = new JTextField();
        totalamount.setEditable(false);
        summaryfields.add(totalamount);
       
        summaryfields.add(new JLabel("Discount(%):"));
        discountfield = new JTextField(7);
        summaryfields.add(discountfield);
        summaryfields.add(new JLabel("Tax:"));
        taxfield = new JTextField(7);
        summaryfields.add(taxfield);
        summaryfields.add(new JLabel("Amount Tendered:"));
        AmountTendered = new JTextField();
        summaryfields.add(AmountTendered);
        
        summaryfields.add(new JLabel("Change:"));
        changefield = new JTextField();
        changefield.setEditable(false);
        summaryfields.add(changefield);
        summaryfields.add(new JLabel(" "));
        JButton purchasebutton = new JButton("Purchase");
        summaryfields.add(purchasebutton);
        
        // Summary panel
        JPanel summarypanel = new JPanel();
        summarypanel.setLayout(new FlowLayout()); 
        summarypanel.setPreferredSize(new Dimension(300, 100));
        summarypanel.setBounds(500, 800,300,100);
        JButton computebutton = new JButton("Compute");
        summarypanel.add(computebutton);
        JButton Cancelbutton = new JButton("Cancel Order");
        summarypanel.add(Cancelbutton);
        JButton Clearitems = new JButton("Clear");
        summarypanel.add(Clearitems);
       
        //lowerleft panel { cashier, date}
     JPanel lowerleft= new JPanel();
     lowerleft.setLayout(new BoxLayout(lowerleft,BoxLayout.Y_AXIS));
     JPanel cashiernamedate = new JPanel();
     cashiernamedate.setLayout(new GridLayout(2,1,5,5));
     cashiernamedate.add(new JLabel("Cashier Name:"));
     JTextField cashiername = new JTextField();
     cashiername.setText("Nicolas Obra");
     cashiername.setEditable(false);
     cashiernamedate.add(cashiername);
     cashiernamedate.add(new JLabel("Date:"));
     JTextField date = new JTextField();
     date.setEditable(false);
     LocalDate today = LocalDate.now();
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
     date.setText(today.format(formatter));
     cashiernamedate.add(date);
     lowerleft.add(summarypanel);
     lowerleft.add(cashiernamedate);
  ;
  
  		//billarea view of items added
        billarea = new JTextArea(10, 20);
        billarea.setEditable(false);
        JScrollPane scroll = new JScrollPane(billarea);
        scroll.setPreferredSize(new Dimension(200, 200)); 
        
        //CenterPanel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); 
        centerPanel.add(inputpanel);
        centerPanel.add(summaryfields);
        
        //LeftPanel
        JPanel leftpanel = new JPanel();
        leftpanel.setLayout(new BoxLayout(leftpanel,BoxLayout.Y_AXIS));
        leftpanel.add(scroll);
        leftpanel.add(lowerleft);
        f.add(centerPanel, BorderLayout.CENTER); 
        f.add(leftpanel,BorderLayout.EAST);
        
        f.setVisible(true); //for frame to be visible
        
        // ACTIONLISTENERS FOR THE BUTTONS
        addbutton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		additemfrominput();
        	}
        
        });
        clearbutton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		itemnamefield.setText("");
            	quantityfield.setText("");
            	pricefield.setText("");
            	
            	
        	}
        });
        computebutton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		taxfield.setText(String.format("%.2f",mbs.getTaxrate()));
        		computetotalamount();
        	}
        });
       purchasebutton.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent e) {
    		  changefield();
    	   }
       });
       Cancelbutton.addActionListener(new ActionListener() {;
       @Override
       public void actionPerformed(ActionEvent e) {
    		   removeitem();}
       });
       Clearitems.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent e) {
    		  mbs.clearitems();
    		  billarea.setText("");
    		  taxfield.setText("");
    		  discountfield.setText("");
    		  changefield.setText("");
    		  totalamount.setText("");
    		  AmountTendered.setText("");
    		   
    	   }
       });
        
    }
    
    
    // METHODS FOR THE FUNCTIONS
    
    //compute total amount
    public void computetotalamount() {
    	discountfrominput();
    	 double discount=mbs.computeItems()*finaldiscount;
    	finaltotalamount = mbs.computeItems()+mbs.computeTaxadditions()-discount;
    	totalamount.setText(String.format("%.2f",finaltotalamount));
    }
    // get the discount value from imput
   public void discountfrominput() {
	   double discount=0;
	   String discountrate=discountfield.getText().trim();
	   if(discountrate.isEmpty()) {
		   JOptionPane.showMessageDialog(this,"Please fill the Discount field","Input Error",JOptionPane.ERROR_MESSAGE);
		   return;
	   }
	   try {
	  discount=Double.parseDouble(discountrate);
	   }catch(NumberFormatException ne) {
		   JOptionPane.showMessageDialog(this, "Invalid Discount Percentage","Input Error",JOptionPane.ERROR_MESSAGE);
		   return;
	   }
	  
	finaldiscount = discount/100;
	  
   }   
   
   //method for change and amount tendered
   public void changefield() {
	   String amountpaying =AmountTendered.getText().trim();
	   if(amountpaying.isEmpty()) {
		   JOptionPane.showMessageDialog(this,"Please fill the field","Input Error",JOptionPane.ERROR_MESSAGE);
		   return;
	   }
	   try {
	  double amount = Double.parseDouble(amountpaying);
	   double change = amount-finaltotalamount;
	   if(change<0) {
		   JOptionPane.showMessageDialog(this,"Insufficience amount tendered","Input Error",JOptionPane.ERROR_MESSAGE);
		   return;
	   }
	   changefield.setText(String.format("%.2f",change));
	   }catch(NumberFormatException nfe) {
		   JOptionPane.showMessageDialog(this,"Please input sufficient amount tendered","Input Error",JOptionPane.ERROR_MESSAGE);
	   }
	   	  mbs.clearitems();
		  
   }
   //remove last added item
    public void removeitem() {
   int lastline =billarea.getText().lastIndexOf("\n");
   if(lastline>-1) {
	   billarea.setText(billarea.getText().substring(0,lastline));
	   mbs.removeLastItem();
   }else {
	   billarea.setText("");
   }
    }
    //additem from input method
    public void additemfrominput() {
    	String name = itemnamefield.getText().trim();
    	String quantity =quantityfield.getText().trim();
    	String price =pricefield.getText().trim();
    	
    	if(name.isEmpty() || quantity.isEmpty() || price.isEmpty()) {
    		JOptionPane.showMessageDialog(this,"Please fill all item fields","Input Error",JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	double pricez;
    	int quantityz;
    	double subtotal;
    	try {
    		pricez=Double.parseDouble(price);
    		quantityz=Integer.parseInt(quantity);
    		 subtotal = pricez*quantityz;
    	}catch(NumberFormatException ne) {
    		JOptionPane.showMessageDialog(this,"Invalid price or quantity","Input Error", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	mbs.addItems(name, quantityz, pricez);
    	billarea.append(name+"-$"+pricez +"-qty " + quantityz +"-subtotal $"+subtotal+"\n");
    	
    }
    
   public static void main(String[] args) {
        billingsystem bs = new billingsystem();
        bs.startAPP();
    }
}