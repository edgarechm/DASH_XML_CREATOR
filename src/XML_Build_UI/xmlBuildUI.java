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
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//@SuppressWarnings("unused")
public class xmlBuildUI {

	private JFrame frmDashTest;
	private JTextField textSuiteName,textFileName;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblXMLFilePath,lblFilename,lblxml;
	private JButton btnGenerateXmlFile, btnAddRowstests, btnExit;
	private Panel panel;
	private String[][]xmlParameters;
	
	private String browserSelected, testSuiteName, scenarioFilePath,testCaseID,testCaseName,objectFilePath;
	private JScrollPane scrollPane;
	private JTable table;

	
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
	@SuppressWarnings("serial")
	private void initialize() {
		frmDashTest = new JFrame();
		frmDashTest.setTitle("DASH Test - XML Builder");
		frmDashTest.setBounds(100, 100, 938, 596);
		frmDashTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//The labels and textboxes
		JLabel lblSuiteName = new JLabel("Suite Name :");
			lblSuiteName.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textSuiteName = new JTextField();
		textSuiteName.setToolTipText("Type in your Test Suite Name.\r\nThis will be the name of the Test Results directory");
		textSuiteName.setColumns(10);
		
		//Browser Selection Radio Buttons Group
		JLabel lblSelectYourBrowser = new JLabel("Select Your Browser :");
		lblSelectYourBrowser.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		final JRadioButton rdbtnGoogleChrome = new JRadioButton("Google Chrome");
			rdbtnGoogleChrome.setToolTipText("DASH will run tests on Google Chrome");
			rdbtnGoogleChrome.setSelected(true);
		buttonGroup.add(rdbtnGoogleChrome);
		
		final JRadioButton rdbtnMozillaFirefox = new JRadioButton("Mozilla Firefox");
			rdbtnMozillaFirefox.setToolTipText("DASH will run tests on Mozilla Firefox");
		buttonGroup.add(rdbtnMozillaFirefox);
		
		final JRadioButton rdbtnMicrosoftIE = new JRadioButton("Microsoft Internet Explorer");
			rdbtnMicrosoftIE.setToolTipText("DASH will run tests on Microsoft Internet Explorer");
		buttonGroup.add(rdbtnMicrosoftIE);
		
		//Panel showing resulting filename and path
		panel = new Panel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			
			lblXMLFilePath = new JLabel("To execute type: ");
			panel.add(lblXMLFilePath);
		
		//Filename Label and TextField
		lblFilename = new JLabel("File Name:");
		lblFilename.setFont(new Font("Dialog", Font.BOLD, 12));
		
		textFileName = new JTextField();
		textFileName.setColumns(10);
		
		lblxml = new JLabel(".xml");
		lblxml.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		
		
		//The buttons
		btnGenerateXmlFile = new JButton("Generate XML File");
		btnExit = new JButton("Exit");
		btnAddRowstests = new JButton("Add Rows (tests)");
		
		//Panel con la tabla de parametros
		scrollPane = new JScrollPane();
		
		
		GroupLayout groupLayout = new GroupLayout(frmDashTest.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(76)
									.addComponent(textSuiteName, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblSuiteName, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
							.addGap(143)
							.addComponent(lblFilename, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textFileName, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(lblxml, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAddRowstests)
							.addGap(686)
							.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 850, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnGenerateXmlFile, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 527, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSelectYourBrowser)
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnMozillaFirefox)
								.addComponent(rdbtnGoogleChrome)
								.addComponent(rdbtnMicrosoftIE))))
					.addGap(34))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textSuiteName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblSuiteName))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(lblFilename, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(textFileName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(lblxml, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)))
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblSelectYourBrowser))
						.addComponent(rdbtnGoogleChrome))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnMozillaFirefox)
					.addGap(4)
					.addComponent(rdbtnMicrosoftIE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGenerateXmlFile)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExit)
						.addComponent(btnAddRowstests))
					.addContainerGap())
		);
		
		//table = new JTable();
		DefaultTableModel modelo = new DefaultTableModel();
		table = new JTable(modelo);
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				//{null, null, null, null, null},
			},
			new String[] {
				"Item#", "Scenario File Path", "Test Case ID", "Test Case Description", "Object File Name"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(124);
		table.getColumnModel().getColumn(3).setPreferredWidth(209);
		table.getColumnModel().getColumn(4).setPreferredWidth(102);
		scrollPane.setViewportView(table);
		frmDashTest.getContentPane().setLayout(groupLayout);
		
		
		//Button Actions!!
		
		btnAddRowstests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numCols = table.getModel().getColumnCount();
				Object [] fila = new Object[numCols]; 
				fila[0] = "unal";
				fila[1] = "420";
				fila[2] = "mundo";
				fila[1] = "420";
				fila[2] = "mundo";
			}
		});
		
		
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
