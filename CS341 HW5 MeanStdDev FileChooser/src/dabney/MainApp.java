package dabney;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;

/**
 * Represents the Main GUI App.
 * 
 * @author Devon Dabney
 * @version 1.0
 * 
 */
public class MainApp {

	private JFrame frame;
	private JButton chooseBtn;
	
	/**
	 * Handles the output for the computation or errors.
	 */
	public JTextArea resultsTextArea;

	/**
	 * Launch the application.
	 * 
	 * @param args	Collection of string arguments.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp window = new MainApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("File Math App");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(160, 10, 128, 43);
		frame.getContentPane().add(lblNewLabel);
		
		JTextArea AppDescription = new JTextArea();
		AppDescription.setBackground(SystemColor.window);
		AppDescription.setText("This app allows you to input your own text file of whole numbers and will \r\ncompute the mean and standard deviation from the set of numbers! Make\r\nsure each number is on a separate line for proper computation***");
		AppDescription.setBounds(21, 50, 405, 58);
		frame.getContentPane().add(AppDescription);
		
		chooseBtn = new JButton("Choose File");
		chooseBtn.setBounds(160, 127, 117, 29);
		frame.getContentPane().add(chooseBtn);
		
		resultsTextArea = new JTextArea();
		resultsTextArea.setForeground(new Color(255, 255, 255));
		resultsTextArea.setBackground(new Color(0, 0, 0));
		resultsTextArea.setBounds(21, 179, 405, 56);
		frame.getContentPane().add(resultsTextArea);
		
		// Add a listener event to the chooseBtn JButton.
		chooseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFile();
			}
		});
	}
	
	/**
	 * Method using JFileChooser to choose a text file. The chosen file will have
	 * a method imposed on it to store the values in a LinkedList (if text file 
	 * meets requirements). Once the list is created the computation for mean and
	 * standard deviation will take place and be displayed via JTextArea.
	 * 
	 */
	public void chooseFile() {
		// Part 1: Create an input file and file scanner
		File inputFile;
		Scanner fileInputScan = null;
		
		// Part 2 Use a try-catch for file processing
		try {
			// TASK 1: USE A JFileChooser TO SELECT AN INPUT FILE
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int returnValue = jfc.showOpenDialog(null);
			if(returnValue == JFileChooser.APPROVE_OPTION) {
				inputFile = jfc.getSelectedFile();
				
				// TASK 2: OPEN THE CODE FILE WITH A SCANNER
				fileInputScan = new Scanner(inputFile);
				
				// TASK 3: COLLECT THE NUMBERS AND STORE THEM IN A LinkedList
				LinkedList numList = collectNums(fileInputScan);
				
				// TASK 4: DO THE COMPUTATION FOR MEAN AND STD DEV
				double mean = 0;
				for(int i = 0; i < numList.size; i++) {
					mean += numList.getVal(i); 
				}
				// Test to see if getVal() method works
				// resultsTextArea.setText("" + sum);
				mean /= numList.size;
				
				double stdDev = 0;
				for(int i = 0; i < numList.size; i++) {
					stdDev += Math.pow(numList.getVal(i) - mean, 2); 
				}
				
				stdDev = Math.sqrt(stdDev / numList.size);
				
				// TASK 5: DISPLAY THE MEAN AND STDDEV TO FINISH
				resultsTextArea.setText("Mean: " + mean + "\nStandard Deviation: " + stdDev);
				
			}
			
		} catch(FileNotFoundException e) {
			System.out.println("Error - This file could not be found.");
		} finally {
			if(fileInputScan != null)
				fileInputScan.close();
		}
	}
	
	/**
	 * Method to create a LinkedList of the real number values from an input file.
	 *
	 * @param fileInputScan	The scanner of the input file
	 * @return A newly created LinkedList of all the resulting values.
	 */
	public LinkedList collectNums(Scanner fileInputScan) {
		// TASK 1: CREATE A LinkedList FOR THE RESULTING VALUES.
		LinkedList myList = new LinkedList();
	
		// TASK 2: USE A LOOP TO SCAN ONE CODE LINE AT A TIME AND COLLECT VALUES
		double value;
		String codeLine = "";
		
		// TEST SUMMARY CASES
		// Case 1: Test for an empty file
		if(!fileInputScan.hasNextLine()) {
			resultsTextArea.setText("ERROR: the text file chosen is empty! Try another file.");
			return myList;
		}
		
		while(fileInputScan.hasNextLine()) {
			codeLine = fileInputScan.nextLine();
			/* Case 4: For files containing empty lines in the middle, there are no issues
			 * since the trimming will take care of any whitespace.
			 */
			codeLine.trim();	
			
			if(isCode(codeLine)) {
				// Case 2: Test for valid input. Use try/catch to attempt parsing to double.
				try {
					value = Double.parseDouble(codeLine);
					myList.add(value);
				}catch (NumberFormatException ex) {
					resultsTextArea.setText("ERROR: input in text file is NOT a real number. "
							+ "Try another file");
				}
			}
		}
		return myList;
	}
	
	/**
	 * Method to check if a given string is long enough to be considered code.
	 * 
	 * @param str	String to be tested.
	 * @return True if length of string is greater than 0, false otherwise.
	 */
	public static boolean isCode(String str) {
		return str.length() > 0;
	}
}
