/*
 * DASH_TEST: Test Accelerator
 * Developed by: Edgar Chavez 
 * 
 * Module name: xmlBuildUI
 * Purpose: XML Configuration file Generation User Interfase.
 * 		Use this simple app to generate your configuration files and call
 * 		DASH to execute your test scripts.
 * 
 * Language used: Java JavaSE-1.7
 * Project Start Date: 2013/04/10
 */
package XML_Build_UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.sun.glass.events.WindowEvent;

import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import XML_Builder.CreateXMLFileJava;

@SuppressWarnings({ "unchecked", "rawtypes","unused"})
public class xmlBuildUI {

	private JFrame frmDashTest;
	private static JTextField textSuiteName;
	private static JTextField textFileName;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final String osName = System.getProperty("os.name"); //(Windows, Linux, Mac, SunOS, FreeBSD)?
	private JLabel lblXMLFilePath,lblFilename,lblxml;
	private JButton btnGenerateXmlFile, btnAddRowstests,btnDeleteRowstests, btnExit;
	private String[][]xmlParameters;
	
	private static String browserSelected, testSuiteName, xmlFilePath,testCaseID,testCaseName,objectFilePath;
	private JScrollPane scrollPane;
	private static JTable parameterTable;
	private TableModelListener tableListener;

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

	public xmlBuildUI() {
		initialize();
	}

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
		textFileName.setToolTipText("Extension (.xml) not needed!");
		textFileName.setColumns(10);
		
		lblxml = new JLabel(".xml");
		lblxml.setFont(new Font("Dialog", Font.PLAIN, 12));
			
		//The buttons
		btnGenerateXmlFile = new JButton("Generate XML File");
		btnExit = new JButton("Exit");
		btnAddRowstests = new JButton("Add Rows (tests)");
		btnDeleteRowstests = new JButton("Delete Rows (tests)");
		//btnBrowseForPropertiesFile = new JButton("...");
		
		
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
		
		DefaultTableModel tableModel = new DefaultTableModel();
		parameterTable = new JTable(tableModel);
		
		parameterTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Test Name", "Scenario File Path", "Test Case ID", "Test Case Description", "Object File Name"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		parameterTable.getColumnModel().getColumn(0).setPreferredWidth(100); //item number
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
				((DefaultTableModel) parameterTable.getModel()).addRow(parameterRow);
			}
		});	
		//Delete Tests
		btnDeleteRowstests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numRows = parameterTable.getModel().getRowCount();
				if (numRows>0){
				//((DefaultTableModel) parameterTable.getModel()).removeRow(numRows-1);
					int currentRow = parameterTable.getSelectedRow();
					((DefaultTableModel) parameterTable.getModel()).removeRow(currentRow);
				}
			}
		});		
		//Actions on the Parameter table
		parameterTable.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (parameterTable.isColumnSelected(1)){
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
					        "Excel Files", "xls", "xlsx");
					chooser.setFileFilter(filter);
					if (osName.contains("Windows")){
						chooser.setCurrentDirectory(new java.io.File("..\\DASH_TEST_SRC\\automatedScripts"));
					}else{
						chooser.setCurrentDirectory(new java.io.File("../DASH_TEST_SRC/automatedScripts"));
					}
		            chooser.setDialogTitle("Select your Script File...");
		            chooser.setApproveButtonText("Select File");
		            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		            chooser.setAcceptAllFileFilterUsed(false);
		            String targetDir=null;
		            Path relative=null;
		            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		            	if (osName.contains("Windows")){
		            		targetDir = chooser.getSelectedFile().getPath().toString()+"\\";
		            	}else{
		            		targetDir = chooser.getSelectedFile().getPath().toString()+"/";
		            	}
		                relative = new File(System.getProperty("user.dir")).toPath().relativize(new File(targetDir).toPath());
		            } else {  }
		            int currentRow = parameterTable.getSelectedRow();
					parameterTable.setValueAt(relative, currentRow, 1);
		            //parameterTable.setValueAt(targetDir, currentRow, 1);//remove after testing
				}
				
				if (parameterTable.isColumnSelected(4)){
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
					        "Properties Files", "properties");
					chooser.setFileFilter(filter);
					if (osName.contains("Windows")){
						chooser.setCurrentDirectory(new java.io.File("..\\DASH_TEST_SRC\\object_repositories"));
					}else{
						chooser.setCurrentDirectory(new java.io.File("../DASH_TEST_SRC/object_repositories"));
					}
		            chooser.setDialogTitle("Select your Properties File...");
		            chooser.setApproveButtonText("Select File");
		            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		            chooser.setAcceptAllFileFilterUsed(false);
		            String targetDir=null;
		            Path relative=null;
		            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		            	if (osName.contains("Windows")){
		            		targetDir = chooser.getSelectedFile().getPath().toString()+"\\";
		            	}else{
		            		targetDir = chooser.getSelectedFile().getPath().toString()+"/";
		            	}
		                relative = new File(System.getProperty("user.dir")).toPath().relativize(new File(targetDir).toPath());
		            } else {  }
		            int currentRow = parameterTable.getSelectedRow();
					parameterTable.setValueAt(relative, currentRow, 4);
		            //parameterTable.setValueAt(targetDir, currentRow, 4);//remove after testing
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				//Auto-generated method stub	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				//Auto-generated method stub	
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//Auto-generated method stub	
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				//Auto-generated method stub	
			}
		});
		
		//Generate XML File
		btnGenerateXmlFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//check parameters
				if(parseParameters()){
					//Here is where parameters are integrated and call to create XML file is done
					testSuiteName = textSuiteName.getText();
					xmlFilePath = textFileName.getText();
					//testCaseID = "TS-001";
					//testCaseName = "This is a test";
					//objectFilePath = "loginScreens_objects.properties";
					
					if(rdbtnGoogleChrome.isSelected()){
						browserSelected = "CHROME";
					}
					if(rdbtnMozillaFirefox.isSelected()){
						browserSelected = "FIREFOX";
					}
					if(rdbtnMicrosoftIE.isSelected()){
						browserSelected = "IE";
					}
					int tblRows = parameterTable.getModel().getRowCount();
					int tblCols = parameterTable.getModel().getColumnCount();
					boolean exception_error = false;
					xmlParameters = new String[tblRows][tblCols];
					//Parse the parameterTable and store values into xmlParameters
					for(int i=0;i<tblRows;i++){
						for (int j=0;j<tblCols;j++){
							try{
								xmlParameters[i][j]=parameterTable.getModel().getValueAt(i,j).toString();
							}catch (Exception e){
								JOptionPane.showMessageDialog(frmDashTest, "Test Parameters cannot be empty!!!");
								exception_error = true;
							}
						}
					}
					if (!exception_error){
						//Select the directory where all be located						
						JFileChooser chooser = new JFileChooser();
						if (osName.contains("Windows")){
							chooser.setCurrentDirectory(new java.io.File("..\\DASH_TEST_SRC\\automatedScripts"));
						}else{
							chooser.setCurrentDirectory(new java.io.File("../DASH_TEST_SRC/automatedScripts"));
						}
			            
			            chooser.setDialogTitle("Select directory where XML will be saved...");
			            chooser.setApproveButtonText("Generate XML");
			            chooser.setApproveButtonMnemonic('g');
			            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			            chooser.setAcceptAllFileFilterUsed(false);
			            String targetDir=null;

			            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			            	if (osName.contains("Windows")){
			            		targetDir = chooser.getSelectedFile().getPath().toString()+"\\";
			            	}else{
			            		targetDir = chooser.getSelectedFile().getPath().toString()+"/";
			            	}
			                Path relative = new File(System.getProperty("user.dir")).toPath().relativize(new File(targetDir).toPath());			        
							//Creates the file
							CreateXMLFileJava.createXMLFile(testSuiteName,targetDir+xmlFilePath,browserSelected,xmlParameters);
							JOptionPane.showMessageDialog(frmDashTest, "File \""+targetDir+xmlFilePath+".xml\" has been generated");
							if (osName.contains("Windows")){
								lblXMLFilePath.setText("To execute type: dash "+relative+"\\"+xmlFilePath+".xml");
							}else{
								lblXMLFilePath.setText("To execute type: dash "+relative+"/"+xmlFilePath+".xml");
							}
			            } else {  }
					}
				}else{	}
			}		
		});
		
		//The Exit button
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		//In case Window is closed using x button
		frmDashTest.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDashTest.addWindowListener(new WindowAdapter() {
		    //@Override
		    public void windowClosed(WindowEvent e) {
		        PrintStream nullStream = new PrintStream(new OutputStream() {
		            public void write(int b) throws IOException {
		            }

		            public void write(byte b[]) throws IOException {
		            }

		            public void write(byte b[], int off, int len) throws IOException {
		            }
		        });
		        System.setErr(nullStream);
		        System.setOut(nullStream);
		        System.exit(0);
		    }
		});
		//Add all components to the window
		frmDashTest.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblSuiteName, textSuiteName, lblSelectYourBrowser, rdbtnGoogleChrome, rdbtnMozillaFirefox, rdbtnMicrosoftIE, btnGenerateXmlFile, btnExit}));
		frmDashTest.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textSuiteName, textFileName, rdbtnGoogleChrome, rdbtnMozillaFirefox, rdbtnMicrosoftIE, btnAddRowstests, btnDeleteRowstests, btnGenerateXmlFile, btnExit, frmDashTest.getContentPane(), lblSuiteName, lblFilename, lblxml, lblSelectYourBrowser, lblXMLFilePath, parameterTable, scrollPane}));
	}

	public static boolean parseParameters(){
		boolean parametersCheck = false;
		boolean blFileName=false, blSuiteName=false, blParameterTable=false;
		
		if (textFileName.getText().isEmpty()){
			blSuiteName = false;
			JOptionPane.showMessageDialog(null, "Missing File Name!!");
		}else{
			blFileName = !isValidName(textFileName.getText());
			if(!blFileName){
				JOptionPane.showMessageDialog(null, "Invalid FileName!!");
			}else if(textFileName.getText().endsWith(".xml")){
					blSuiteName = false;
					JOptionPane.showMessageDialog(null, "Extension is not Needed!!");
				}
		}
		
		if (!textSuiteName.getText().isEmpty()){
			blSuiteName = true;
		}else{
			JOptionPane.showMessageDialog(null, "Missing Suite Name!!");
		}
		if (parameterTable.getRowCount()>0){
			blParameterTable = true;
		}else{
			JOptionPane.showMessageDialog(null, "Missing Test Parameters!!");
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
