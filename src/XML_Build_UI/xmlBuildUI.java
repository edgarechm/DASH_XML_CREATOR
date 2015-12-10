package XML_Build_UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class xmlBuildUI {

	private JFrame frmDashTest;
	private JTextField textSui;
	private final ButtonGroup buttonGroup = new ButtonGroup();

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
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(823, 524, 89, 23);
		frmDashTest.getContentPane().add(btnExit);
		
		JLabel lblSuiteName = new JLabel("Suite Name :");
			lblSuiteName.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblSuiteName.setBounds(61, 45, 77, 14);
		frmDashTest.getContentPane().add(lblSuiteName);
		
		textSui = new JTextField();
		textSui.setToolTipText("Type in your Test Suite Name.\r\nThis will be the name of the Test Results directory");
		textSui.setBounds(137, 42, 208, 20);
		frmDashTest.getContentPane().add(textSui);
		textSui.setColumns(10);
		
		JLabel lblSelectYourBrowser = new JLabel("Select Your Browser :");
		lblSelectYourBrowser.setBounds(61, 90, 119, 14);
		frmDashTest.getContentPane().add(lblSelectYourBrowser);
		lblSelectYourBrowser.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JRadioButton rdbtnGoogleChrome = new JRadioButton("Google Chrome");
			rdbtnGoogleChrome.setToolTipText("DASH will run tests on Google Chrome");
			rdbtnGoogleChrome.setSelected(true);
			rdbtnGoogleChrome.setBounds(190, 86, 99, 23);
		frmDashTest.getContentPane().add(rdbtnGoogleChrome);
		buttonGroup.add(rdbtnGoogleChrome);
		
		JRadioButton rdbtnMozillaFirefox = new JRadioButton("Mozilla Firefox");
			rdbtnMozillaFirefox.setToolTipText("DASH will run tests on Mozilla Firefox");
			rdbtnMozillaFirefox.setBounds(190, 112, 93, 23);
		frmDashTest.getContentPane().add(rdbtnMozillaFirefox);
		buttonGroup.add(rdbtnMozillaFirefox);
		
		JRadioButton rdbtnMicrosoftIE = new JRadioButton("Microsoft Internet Explorer");
			rdbtnMicrosoftIE.setToolTipText("DASH will run tests on Microsoft Internet Explorer");
			rdbtnMicrosoftIE.setBounds(190, 138, 155, 23);
		frmDashTest.getContentPane().add(rdbtnMicrosoftIE);
		buttonGroup.add(rdbtnMicrosoftIE);
		
		JButton btnGenerateXmlFile = new JButton("Generate XML File");
		btnGenerateXmlFile.setBounds(61, 524, 119, 23);
		frmDashTest.getContentPane().add(btnGenerateXmlFile);
		frmDashTest.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblSuiteName, textSui, lblSelectYourBrowser, rdbtnGoogleChrome, rdbtnMozillaFirefox, rdbtnMicrosoftIE, btnGenerateXmlFile, btnExit}));
	}
}
