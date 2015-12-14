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
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import XML_Builder.CreateXMLFileJava;

//@SuppressWarnings("unused")
@SuppressWarnings({ "unchecked", "rawtypes","unused"})
public class xmlBuildUI {

	private JFrame frmDashTest;
	private static JTextField textSuiteName;
	private static JTextField textFileName;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblXMLFilePath,lblFilename,lblxml;
	private JButton btnGenerateXmlFile, btnAddRowstests,btnDeleteRowstests, btnExit;
	private String[][]xmlParameters;
	
	private static String browserSelected, testSuiteName, scenarioFilePath,testCaseID,testCaseName,objectFilePath;
	private JScrollPane scrollPane;
	private static JTable parameterTable;

	
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
		frmDashTest.setResizable(false);
		frmDashTest.setTitle("DASH Test - XML Builder");
		frmDashTest.setBounds(20, 100, 1320, 500);
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
		btnDeleteRowstests = new JButton("Delete Rows (tests)");
		
		
		//Panel with the table of parameters
		scrollPane = new JScrollPane();		
		lblXMLFilePath = new JLabel("To execute type: ");
		
		//Main Panel containing all components
		GroupLayout groupLayout = new GroupLayout(frmDashTest.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(btnAddRowstests, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDeleteRowstests))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblSuiteName, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textSuiteName, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(lblFilename, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textFileName, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblxml, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblSelectYourBrowser)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnMozillaFirefox)
								.addComponent(rdbtnGoogleChrome)
								.addComponent(rdbtnMicrosoftIE)))
						.addComponent(btnGenerateXmlFile, Alignment.LEADING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblXMLFilePath)
							.addPreferredGap(ComponentPlacement.RELATED, 737, Short.MAX_VALUE)
							.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblSuiteName)
							.addComponent(textSuiteName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(textFileName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblFilename, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblxml, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectYourBrowser)
						.addComponent(rdbtnGoogleChrome))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtnMozillaFirefox)
					.addGap(1)
					.addComponent(rdbtnMicrosoftIE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddRowstests)
						.addComponent(btnDeleteRowstests))
					.addGap(18)
					.addComponent(btnGenerateXmlFile)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblXMLFilePath)
						.addComponent(btnExit))
					.addGap(21))
		);
		
		DefaultTableModel modelo = new DefaultTableModel();
		//table = new JTable();
		parameterTable = new JTable(modelo);
		
		parameterTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"#", "Scenario File Path", "Test Case ID", "Test Case Description", "Object File Name"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		parameterTable.getColumnModel().getColumn(0).setPreferredWidth(2);   //item number
		parameterTable.getColumnModel().getColumn(1).setPreferredWidth(400); //SFP
		parameterTable.getColumnModel().getColumn(2).setPreferredWidth(100); //TCID
		parameterTable.getColumnModel().getColumn(3).setPreferredWidth(400); //TCD
		parameterTable.getColumnModel().getColumn(4).setPreferredWidth(300); //OFN
		scrollPane.setViewportView(parameterTable);
		frmDashTest.getContentPane().setLayout(groupLayout);
		
		
		//Button Actions!!
		//Add Tests
		btnAddRowstests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numCols = parameterTable.getModel().getColumnCount();
				int numRows = parameterTable.getModel().getRowCount();
				Object [] parameterRow = new Object[numCols];
				parameterRow[0]=numRows+1;
				((DefaultTableModel) parameterTable.getModel()).addRow(parameterRow);
			}
		});
		//Delete Tests
		btnDeleteRowstests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numRows = parameterTable.getModel().getRowCount();
				if (numRows>0){
				((DefaultTableModel) parameterTable.getModel()).removeRow(numRows-1);
				}
			}
		});
		
		//Generate XML File
		btnGenerateXmlFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//check parameters
				
				
				//if(!isValidName(textFileName.getText())){
				if(parseParameters()){
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
					
					// It will always have 4 columns (scenarioFilePath,testCaseID,testCaseName,objectFilePath), !skip the first column=>item#!
					int tblRows = parameterTable.getModel().getRowCount();
					int tblCols = parameterTable.getModel().getColumnCount();
					xmlParameters = new String[tblRows][tblCols];
					log("Number of Rows in Table: ", String.valueOf(tblRows));
					log("Number of Columns in Table: ", String.valueOf(tblCols));
					//Parse the parameterTable and store values into xmlParameters
					for(int i=0;i<tblRows;i++){
						for (int j=0;j<tblCols;j++){
						xmlParameters[i][j]=parameterTable.getModel().getValueAt(i,j).toString();
						log("Value Stored: ", xmlParameters[i][j]);
						}
					}
					
					/*xmlParameters[0][0]=scenarioFilePath;
					xmlParameters[0][1]=testCaseID;
					xmlParameters[0][2]=testCaseName;
					xmlParameters[0][3]=objectFilePath;*/
					
					generateXMLFile(testSuiteName,browserSelected,xmlParameters); ///<---- replace this with a call to CreateXMLFileJava
				}else{
					JOptionPane.showMessageDialog(frmDashTest, "Invalid Parameter(s)!!");	
				}
			}		
		});
		
		//The Exit button
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
	
	public static boolean parseParameters(){
		boolean parametersCheck = false;
		boolean blFileName=false, blSuiteName=false, blParameterTable=false;
		
		if (textFileName.getText().isEmpty()){
			blSuiteName = false;
		}else{
			blFileName = !isValidName(textFileName.getText());
		}
		
		if (!textSuiteName.getText().isEmpty()){
			blSuiteName = true;
		}
		if (parameterTable.getRowCount()>0){
			blParameterTable = true;
		}
		if (blFileName&&blSuiteName&&blParameterTable){
			parametersCheck=true;
		}
		return parametersCheck;
	}
	
	public static boolean isValidName(String text)
	{
	    Pattern pattern = Pattern.compile(
	    	"[^-_.A-Za-z0-9\\-\\.]",
	        Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.COMMENTS);
	    Matcher matcher = pattern.matcher(text);
	    boolean isMatch = matcher.matches();
	    return isMatch;
	}
	
	public void log(String message, String value){
		System.out.println(message+" "+value);
	}
}
