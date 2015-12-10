package XML_Build_UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Panel;
import java.awt.FlowLayout;

//@SuppressWarnings("unused")
public class xmlBuildUI {

	private JFrame frmDashTest;
	private JTextField textSuiteName;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFileName;
	private JLabel lblXMLFilePath;
	private JButton btnGenerateXmlFile;
	private JLabel lblFilename;
	private Panel panel;
	private JLabel lblxml;
	private JButton btnExit;
	private String[][]xmlParameters;
	
	private String browserSelected, testSuiteName, scenarioFilePath,testCaseID,testCaseName,objectFilePath;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					xmlBuildUI window = new xmlBuildUI();
					window.frmDashTest.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public xmlBuildUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDashTest = new JFrame();
		frmDashTest.setTitle("DASH Test - XML Builder");
		frmDashTest.setBounds(100, 100, 938, 596);
		frmDashTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDashTest.getContentPane().setLayout(null);
		
		//The labels and textboxes
		JLabel lblSuiteName = new JLabel("Suite Name :");
			lblSuiteName.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblSuiteName.setBounds(61, 45, 77, 14);
		frmDashTest.getContentPane().add(lblSuiteName);
		
		textSuiteName = new JTextField();
		textSuiteName.setToolTipText("Type in your Test Suite Name.\r\nThis will be the name of the Test Results directory");
		textSuiteName.setBounds(137, 42, 208, 20);
		frmDashTest.getContentPane().add(textSuiteName);
		textSuiteName.setColumns(10);
		
		//Browser Selection Radio Buttons Group
		JLabel lblSelectYourBrowser = new JLabel("Select Your Browser :");
		lblSelectYourBrowser.setBounds(61, 90, 119, 14);
		frmDashTest.getContentPane().add(lblSelectYourBrowser);
		lblSelectYourBrowser.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		final JRadioButton rdbtnGoogleChrome = new JRadioButton("Google Chrome");
			rdbtnGoogleChrome.setToolTipText("DASH will run tests on Google Chrome");
			rdbtnGoogleChrome.setSelected(true);
			rdbtnGoogleChrome.setBounds(190, 86, 99, 23);
		frmDashTest.getContentPane().add(rdbtnGoogleChrome);
		buttonGroup.add(rdbtnGoogleChrome);
		
		final JRadioButton rdbtnMozillaFirefox = new JRadioButton("Mozilla Firefox");
			rdbtnMozillaFirefox.setToolTipText("DASH will run tests on Mozilla Firefox");
			rdbtnMozillaFirefox.setBounds(190, 112, 93, 23);
		frmDashTest.getContentPane().add(rdbtnMozillaFirefox);
		buttonGroup.add(rdbtnMozillaFirefox);
		
		final JRadioButton rdbtnMicrosoftIE = new JRadioButton("Microsoft Internet Explorer");
			rdbtnMicrosoftIE.setToolTipText("DASH will run tests on Microsoft Internet Explorer");
			rdbtnMicrosoftIE.setBounds(190, 138, 155, 23);
		frmDashTest.getContentPane().add(rdbtnMicrosoftIE);
		buttonGroup.add(rdbtnMicrosoftIE);
		
		//Panel showing resulting filename and path
		panel = new Panel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panel.setBounds(220, 171, 527, 23);
			frmDashTest.getContentPane().add(panel);
			
			lblXMLFilePath = new JLabel("To execute type: ");
			panel.add(lblXMLFilePath);
		
		//Filename Label and TextField
		lblFilename = new JLabel("File Name:");
		lblFilename.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFilename.setBounds(488, 44, 64, 14);
		frmDashTest.getContentPane().add(lblFilename);
		
		textFileName = new JTextField();
		textFileName.setBounds(562, 42, 119, 20);
		frmDashTest.getContentPane().add(textFileName);
		textFileName.setColumns(10);
		
		lblxml = new JLabel(".xml");
		lblxml.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblxml.setBounds(683, 44, 32, 14);
		frmDashTest.getContentPane().add(lblxml);
		
		//The buttons
		btnGenerateXmlFile = new JButton("Generate XML File");
		btnGenerateXmlFile.setBounds(24, 171, 156, 23);
		frmDashTest.getContentPane().add(btnGenerateXmlFile);
			
		btnGenerateXmlFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!isValidName(textFileName.getText())){
					
					//Here is where parameters are integrated and call to create XML file is done
					testSuiteName = textSuiteName.getText();
					scenarioFilePath = textFileName.getText();
					testCaseID = "TS-001";
					testCaseName = "This is a test";
					objectFilePath = "loginScreens_objects.properties";
					
					if(rdbtnGoogleChrome.isSelected()){
						browserSelected = "CHROME";
					}
					if(rdbtnMozillaFirefox.isSelected()){
						browserSelected = "FIREFOX";
					}
					if(rdbtnMicrosoftIE.isSelected()){
						browserSelected = "IE";
					}
					
					//I want to see if I can make variable the size of array or fix it to max 10 rows.
					// however, it will always have 4 columns (scenarioFilePath,testCaseID,testCaseName,objectFilePath)
					xmlParameters = new String[1][4];
					
					xmlParameters[0][0]=scenarioFilePath;
					xmlParameters[0][1]=testCaseID;
					xmlParameters[0][2]=testCaseName;
					xmlParameters[0][3]=objectFilePath;
					
					generateXMLFile(testSuiteName,browserSelected,xmlParameters); ///<---- replace this with a call to CreateXMLFileJava
				}else{
					JOptionPane.showMessageDialog(frmDashTest, "Invalid File Name!!");	
				}
			}		
		});
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(823, 524, 89, 23);
		frmDashTest.getContentPane().add(btnExit);

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		//Add all components to the window
		frmDashTest.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblSuiteName, textSuiteName, lblSelectYourBrowser, rdbtnGoogleChrome, rdbtnMozillaFirefox, rdbtnMicrosoftIE, btnGenerateXmlFile, btnExit}));
	}
	
	private void generateXMLFile(String suiteName, String webBrowser, String [][]xmlParameters) {  ///<--- will pass parameters from an array
		JOptionPane.showMessageDialog(frmDashTest, "File \""+textFileName.getText()+".xml\" has been generated");
		lblXMLFilePath.setText("To execute type: dash "+textFileName.getText()+".xml");
		
		log("Suite Name: ",suiteName);
		log("Select Browser: ",webBrowser);
		log ("Scenario File Path: ",xmlParameters[0][0]);
		log ("Test Case ID: ",xmlParameters[0][1]);
		log ("Test Case Description: ",xmlParameters[0][2]);
		log ("Object File Path: ",xmlParameters[0][3]);
	}
	
	public static boolean isValidName(String text)
	{
	    Pattern pattern = Pattern.compile(
	    	"[^-_.A-Za-z0-9\\-\\.]",
	    		/*
	        "# Match a valid Windows filename (unspecified file system).          \n" +
	        "^                                # Anchor to start of string.        \n" +
	        "(?!                              # Assert filename is not: CON, PRN, \n" +
	        "  (?:                            # AUX, NUL, COM1, COM2, COM3, COM4, \n" +
	        "    CON|PRN|AUX|NUL|             # COM5, COM6, COM7, COM8, COM9,     \n" +
	        "    COM[1-9]|LPT[1-9]            # LPT1, LPT2, LPT3, LPT4, LPT5,     \n" +
	        "  )                              # LPT6, LPT7, LPT8, and LPT9...     \n" +
	        "  (?:\\.[^.]*)?                  # followed by optional extension    \n" +
	        "  $                              # and end of string                 \n" +
	        ")                                # End negative lookahead assertion. \n" +
	        "[^<>:\"/\\\\|?*\\x00-\\x1F]*     # Zero or more valid filename chars.\n" +
	        "[^<>:\"/\\\\|?*\\x00-\\x1F\\ .]  # Last char is not a space or dot.  \n" +
	        "$                                # Anchor to end of string.            ",
	        */
	        Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.COMMENTS);
	    Matcher matcher = pattern.matcher(text);
	    boolean isMatch = matcher.matches();
	    return isMatch;
	}
	
	public void log(String message, String value){
		System.out.println(message+" "+value);
	}
	
}
