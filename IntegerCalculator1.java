package integerCalculator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

/**
 * <p>
 * Title: IntegerCalculator Class - A component of the Integer Calculator Program
 * </p>
 *
 * <p>
 * Description: A controller object class that implements the calculator's GUI window and computation
 * </p>
 *
 * <p>
 * Copyright: Copyright � 2011
 * </p>
 *
 * @author Amanov Adilbek
 * @version 1.00
 */
public class IntegerCalculator extends JFrame {
	/**
	 * These are the class attributes
	 */
	private static int windowWidth = 600;			// The width of the window in pixels
	private static int windowHeight = 400;			// The height of the window in pixels
	private static Dimension frameSize = new Dimension(windowWidth, windowHeight);

	// This establishes the user's screen size so we can center the window on the screen
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();		

	// These attributes establish the calculator's labels and text input/output fields
	private JLabel msgIntegerCalculator = new JLabel("Floating Point Calculator");
	private JLabel msgOperand1 = new JLabel("First operand");
	private JTextField fldOperand1 = new JTextField();
	private JLabel msgOperand2 = new JLabel("Second operand");
	private JTextField fldOperand2 = new JTextField();
	private JLabel msgResult = new JLabel("Product");
	private JTextField fldResult = new JTextField();
	private JButton btnAdd = new JButton();
	private JButton btnSub = new JButton();
	private JButton btnMpy = new JButton();
	private JButton btnDiv = new JButton();
	private JLabel errOperand1 = new JLabel("");
	private JLabel errOperand2 = new JLabel("");

	// These attributes are the values for the calculator and are tied to the GUI text fields
	private double operand1;
	private double operand2;
	private double result;
	boolean operandError;

	// This listener calls "checkForErrors" any time a change is made to either of the two 
	// operand input fields
	DocumentListener fieldChangeListener = new DocumentListener() {
		public void changedUpdate(DocumentEvent de){ checkForErrors(); }
		public void insertUpdate(DocumentEvent de){ checkForErrors(); }
		public void removeUpdate(DocumentEvent de){ checkForErrors(); }
	};


	/**********
	 * This constructor creates the JFrame, populates the frame with the calculator's fields,
	 * centers it on the user's screen, and makes it visible
	 */
	public IntegerCalculator() {
		
		// Set the window title and terminate on close of the window
		this.setTitle("Adilbek Amanov");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// This is the label at the top of the content portion of the window
		msgIntegerCalculator.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		msgIntegerCalculator.setBounds(new Rectangle(225, 00, 300, 24));

		/***
		 * These initialization commands set the font, size, and location of the field
		 */
		
		/* 
		 * The first operand
		 */
		// The label for the operand
		msgOperand1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		msgOperand1.setBounds(new Rectangle(10, 75, 140, 24));
		msgOperand1.setHorizontalAlignment(JTextField.RIGHT);
		
		// The text field for the operand
		fldOperand1.setText("");
		fldOperand1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		fldOperand1.setBounds(new Rectangle(160, 72, 300, 30));
		fldOperand1.getDocument().addDocumentListener(fieldChangeListener);
		// When a character changes inside of Operand2, the method "fieldChangeListener" is called
		
		// The error message for the operand
		errOperand1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		errOperand1.setBounds(new Rectangle(470, 75, 160, 24));
		errOperand1.setForeground(Color.red);

		/* 
		 * The second operand
		 */
		// The label for the operand
		msgOperand2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		msgOperand2.setBounds(new Rectangle(10, 150, 140, 24));
		msgOperand2.setHorizontalAlignment(JTextField.RIGHT);
		
		// The text field for the operand
		fldOperand2.setText("");
		fldOperand2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		fldOperand2.setBounds(new Rectangle(160, 147, 300, 30));
		fldOperand2.getDocument().addDocumentListener(fieldChangeListener);
		// When a character changes inside of Operand2, the method "fieldChangeListener" is called
		
		// The error message for the operand
		errOperand2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		errOperand2.setBounds(new Rectangle(470, 150, 160, 24));
		errOperand2.setForeground(Color.red);

		/* 
		 * The calculator buttons
		 */
		// Addition "+"
		btnAdd.setText("+");
		btnAdd.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		btnAdd.setBounds(new Rectangle(100, 200, 60, 50));
		btnAdd.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent ae) {addOperands(ae);}});
		// When the add button is pressed, the method "addOperands" is called

		// Subtraction "-"
		btnSub.setText("-");
		btnSub.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		btnSub.setBounds(new Rectangle(220, 200, 60, 50));
		btnSub.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent ae) {subOperands(ae);}});
		// When the add button is pressed, the method "subOperands" is called

		// Multiplication "*"
		btnMpy.setText("*");
		btnMpy.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		btnMpy.setBounds(new Rectangle(340, 200, 60, 50));
		btnMpy.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent ae) {mpyOperands(ae);}});
		// When the add button is pressed, the method "mpyOperands" is called

		// Division "/"
		btnDiv.setText("/");
		btnDiv.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		btnDiv.setBounds(new Rectangle(460, 200, 60, 50));
		btnDiv.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent ae) {divOperands(ae);}});
		// When the add button is pressed, the method "divOperands" is called

		/* 
		 * The result
		 */
		// The label for the result
		msgResult.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		msgResult.setBounds(new Rectangle(10, 300, 140, 24));
		msgResult.setHorizontalAlignment(JTextField.RIGHT);
		
		// The text field for the result - the user cannot directly edit it, but can copy the contents
		fldResult.setText("");						// Clear the contents of the text field
		fldResult.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		fldResult.setBounds(new Rectangle(160, 297, 300, 30));
		fldResult.setEditable(false);				// Allow a copy of but not a change to the field

		/* 
		 * These commands add each GUI element to the layout so they will be displayed
		 */
		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(null);				// No layout manager, we will manually do it
		contentPane.add(msgIntegerCalculator);		// Add in all of the GUI elements to the contentPane
		contentPane.add(msgOperand1);       
		contentPane.add(fldOperand1);
		contentPane.add(errOperand1);
		contentPane.add(msgOperand2);
		contentPane.add(fldOperand2);       
		contentPane.add(errOperand2);       
		contentPane.add(btnAdd);
		contentPane.add(btnSub);
		contentPane.add(btnMpy);
		contentPane.add(btnDiv);
		contentPane.add(msgResult);
		contentPane.add(fldResult);

		/* 
		 * Center the window on the user's screen (and make sure it fits)
		 */
		if (windowHeight > screenSize.height) { frameSize.height = screenSize.height; }
		if (windowWidth > screenSize.width) { frameSize.width = screenSize.width; }
		this.setSize(frameSize.width + 10, frameSize.height + 10); // + 10 for Windows computers
		this.setLocation( (screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

		/* 
		 * Make this window visible
		 */
		this.setVisible(true);
	}

	/**********
	 * During initialization listener methods were established for the four calculation buttons.  The
	 * following routines perform the various calculator operations.
	 */

	/**********
	 * This routine checks the first operand and returns it.  If the value is invalid, a value of zero 
	 * is returned, but more importantly, a red error message is displayed next to the text field.
	 * If the value is valid, it resets the error field and returns the actual value.
	 */
	private double convertOperand1() {
		// Fetch the string the user entered into the text field
		String temp = fldOperand1.getText();	// Get the text from the input field
		if (temp.length() == 0) {				// If there is nothing there,
			errOperand1.setText("");			// reset the error message
			fldResult.setText("");				// clear the result text field
			return 0;							// and return a zero value
		}
               
		// If the first character is a plus sign, ignore it.
                int start = 0;							// Start at character position zero
		boolean negative = false;				// Assume the value is not negative
		if (temp.charAt(start) == '+')			// See if the first character is '+'
			 start++;							// If so, skip it and ignore it
		
		// If the first character is a minus sign, skip over it, but remember it
		else if (temp.charAt(start) == '-'){	// See if the first character is '-'
			start++;							// if so, skip it
			negative = true;					// but do not ignore it
		}
		
		// Verify that the rest of characters in the input is numeric
		for (int i=start; i < temp.length(); i++){					// Loop through the rest of the characters
                        if(temp.charAt(i)=='.'||temp.charAt(i)==','){
                                errOperand1.setText(""); 				// of the valid range, give an error
				operandError = false;
                                continue;
                        }
			// Check each character to see if it is outside of the valid range for digits
			if (temp.charAt(i) < '0' || temp.charAt(i) > '9') {// If the character is outside
				errOperand1.setText("Invalid digit"); 				// of the valid range, give an error
				operandError = true;								// signal the error for the operators
				return 0;											// The input contains an invalid character
			}
                        
                        
                }
		// Convert the user-entered numeric string to a long integer value
		Scanner tempScanner = new Scanner(temp.substring(start));	// Create scanner for the digits
		fldResult.setText("");										// Clear the result field
		errOperand1.setText("");									// Reset the error message

		// Verify that the input has at least one character
		if (temp.substring(start).length()==0)						// If the digit string is empty
			return 0;												// return zero

		// Verify that the sequence of digits 
		else if (tempScanner.hasNextDouble())							// If the digit string is valid
			if (negative)											// return the proper value based
				return -tempScanner.nextDouble();						// on the state of the flag that
			else 													// says whether or not a '-' was
				return tempScanner.nextDouble();						// seen as the first character
		else {
			errOperand1.setText("Invalid value");					// If the digit string is not a valid
			operandError = true;									// value, signal the error and set
			return 0;												// an error message and
		}															// return a value of zero
	}

	/**********
	 * This routine checks the second operand and returns it.  If the value is invalid, a value of zero 
	 * is returned, but more importantly, a red error message is displayed next to the text field.
	 * If the value is valid, it resets the error field and returns the actual value.
	 */
	private double convertOperand2() {
		// Fetch the string the user entered into the text field
		String temp = fldOperand2.getText();	// Get the text from the input field
		if (temp.length() == 0) {				// If there is nothing there,
			errOperand2.setText("");			// reset the error message
			fldResult.setText("");				// clear the result text field
			return 0;							// and return a zero value
		}
               
		// If the first character is a plus sign, ignore it.
		int start = 0;							// Start at character position zero
		boolean negative = false;				// Assume the value is not negative
		if (temp.charAt(start) == '+')			// See if the first character is '+'
			 start++;							// If so, skip it and ignore it
           
		// If the first character is a minus sign, skip over it, but remember it
		else if (temp.charAt(start) == '-'){	// See if the first character is '-'
			start++;							// if so, skip it
			negative = true;					// but do not ignore it
		}
		
		// Verify that the rest of characters in the input is numeric
		for (int i=start; i < temp.length(); ++i){					// Loop through the rest of the characters
                       if(temp.charAt(i)=='.'||temp.charAt(i)==','){
                                errOperand2.setText("Invalid digit"); 				// of the valid range, give an error
				operandError = false;
                                continue;}
			// Check each character to see if it is outside of the valid range for digits
			if (temp.charAt(i) < '0' || temp.charAt(i) > '9') {
                            
                            // If the character is outside
				errOperand2.setText("Invalid digit"); 				// of the valid range, give an error
				operandError = true;
                                
                                // signal the error for the operators
				return 0;
                                // The input contains an invalid character
			}
                        
                }
		// Convert the user-entered numeric string to a long integer value
		Scanner tempScanner = new Scanner(temp.substring(start));	// Create scanner for the digits
		fldResult.setText("");										// Clear the result field
		errOperand2.setText("");									// Reset the error message

		// Verify that the input has at least one character
		if (temp.substring(start).length()==0)						// If the digit string is empty
			return 0;												// return zero

		// Verify that the sequence of digits 
		else if (tempScanner.hasNextDouble())							// If the digit string is valid
			if (negative)											// return the proper value based
				return -tempScanner.nextDouble();						// on the state of the flag that
			else 													// says whether or not a '-' was
				return tempScanner.nextDouble();						// seen as the first character
		else {
			errOperand2.setText("Invalid value");					// If the digit string is not a valid
			operandError = true;									// value, signal the error and set
			return 0;												// an error message and
		}															// return a value of zero
	}

	/**********
	 * This listener is established to check the input after each and every change to either of this 
	 * input fields.  Each time a change is made, the corresponding operands are set.  The actual 
	 * computation of the result only takes place when one of the buttons is pressed.
	 */
	private void checkForErrors(){
		operandError = false;
		operand1 = convertOperand1();
		operand2 = convertOperand2();
	}

	/*********************************************************************************
	 * This portion of the class defines the computation that takes place when the
	 * various calculator buttons (add, subtract, multiply, and divide are pressed).
	 */

	/**********
	 * This is the add routine
	 * 
	 * @param ae	This routine ignores the ae parameter
	 */
	private void addOperands(ActionEvent ae){
		if (operandError)									// If there is an operand error just return
			return;
		errOperand2.setText("");							// Resets "divide by zero error"
		result = operand1 + operand2;
			msgResult.setText("Sum");
		fldResult.setText(new Double(result).toString());		// Converts the result to string
	}

	/**********
	 * This is the subtract routine
	 * 
	 * @param ae	This routine ignores the ae parameter
	 */
	private void subOperands(ActionEvent ae){
		if (operandError)									// If there is an operand error just return
			return;
		errOperand2.setText("");							// Resets "divide by zero error"
		result = operand1 - operand2;
                msgResult.setText("Difference");
		fldResult.setText(new Double(result).toString());		// Converts the result to string
	}

	/**********
	 * This is the multiply routine
	 * 
	 * @param ae	This routine ignores the ae parameter
	 */
	private void mpyOperands(ActionEvent ae){
		if (operandError)									// If there is an operand error just return
			return;
		errOperand2.setText("");							// Resets "divide by zero error"
		result = operand1 * operand2;
                msgResult.setText("Product");
		fldResult.setText(new Double(result).toString());		// Converts the result to string
	}

	/**********
	 * This is the divide routine.  If the divisor is zero, the divisor is declared to be invalid.
	 * 
	 * @param ae	This routine ignores the ae parameter
	 */
	private void divOperands(ActionEvent ae){
		if (operandError)										// If there is an operand error just return
			return;
		if (operand2 == 0) {									// Check to see if divide by zero
			errOperand2.setText("Divide by zero");				// If this is a divide by zero
			fldResult.setText("");								// display the error message
		} else {
			errOperand2.setText("");							// Resets "divide by zero error"
			result = operand1 / operand2;
                        msgResult.setText("Quotience");
			fldResult.setText(new Double(result).toString());		// Converts the result to string
		}
	}
}
