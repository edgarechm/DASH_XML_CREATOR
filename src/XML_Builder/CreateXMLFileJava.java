/*
 * DASH_TEST: Test Accelerator
 * Developed by: Edgar Chavez 
 * 
 * Module name: CreateXMLFileJava
 * Purpose: XML Configuration file Generation application.
 * 		This application will generate an XML Configuration file
 * 		that matches DASH requirements to execute your testscripts.
 * 
 * Language used: Java JavaSE-1.7
 * Project Start Date: 2013/04/10
 */
package XML_Builder;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

public class CreateXMLFileJava {

	final static String[] parameterNames ={"webBrowser","scenarioFilePath","testCaseID","testCaseName","objectFilePath"};
	
	/// Call will need to be changed for this---> createXMLFile(String suiteName, String xmlFilePath, String webBrowser, String [][]xmlParameters)
	public static void createXMLFile(String suiteName, String xmlFilePath, String webBrowser, String [][]xmlParameters){

		DOMSource source = null;
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			transformerFactory.setAttribute("indent-number", 2);
			Transformer transformer = transformerFactory.newTransformer();
			DOMImplementation domImpl = null;
			Document doc = null;
			DocumentType doctype = null;
			Attr attr = null;
			Element suite, test, parameter, classes, class_element;

			// Create a new Document
			doc = docBuilder.newDocument();
			
			//Add document type statement 
			domImpl = doc.getImplementation();
			doctype = domImpl.createDocumentType("doctype", "suite", "http://testng.org/testng-1.0.dtd");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
			
			
			//Add the suite root element
			suite = doc.createElement("suite");
			doc.appendChild(suite);
				attr = doc.createAttribute("name");
				attr.setValue(suiteName);
				suite.setAttributeNode(attr);
			// Add the test elements
			test = null; //doc.createElement("test");	
			for (int i=0;i<xmlParameters.length;i++){
				// Add the test elements
				test = doc.createElement("test");
				suite.appendChild(test);
	
				// set attribute to test
				attr = doc.createAttribute("name");
					attr.setValue(xmlParameters[i][0]);
					test.setAttributeNode(attr);
				attr = doc.createAttribute("verbose");
					attr.setValue("2");
					test.setAttributeNode(attr);
				//Add a Comment
				Comment commentElement = doc.createComment(xmlParameters[i][3]);
					suite.insertBefore(commentElement, test);
				//Add Browser Parameter
				if (i==0){//Only the first matters
					parameter = doc.createElement("parameter");
					attr = doc.createAttribute("name");
						attr.setValue("webBrowser");
						parameter.setAttributeNode(attr);
					attr = doc.createAttribute("value");
						attr.setValue(webBrowser);
						parameter.setAttributeNode(attr);
					//Here I have to skip the first parameter only in the first run, webBrowser.
						test.appendChild(parameter);
				}else{ //The rest are the "SAME" browser
					parameter = doc.createElement("parameter");
					attr = doc.createAttribute("name");
						attr.setValue("webBrowser");
						parameter.setAttributeNode(attr);
					attr = doc.createAttribute("value");
						attr.setValue("SAME");
						parameter.setAttributeNode(attr);
					//Here I have to skip the first parameter only in the first run, webBrowser.
						test.appendChild(parameter);
				}
				
				//insert the rest of the parameters
					for (int j=1;j<5;j++){
					parameter = doc.createElement("parameter");
					attr = doc.createAttribute("name");
						attr.setValue(parameterNames[j]);
						parameter.setAttributeNode(attr);
					attr = doc.createAttribute("value");
						attr.setValue(xmlParameters[i][j]);
						parameter.setAttributeNode(attr);
					//Here I have to skip the first parameter only in the first run, webBrowser.
						test.appendChild(parameter);
				}
			
				// Call to DASH Main class
				classes = doc.createElement("classes");
				test.appendChild(classes);
				class_element = doc.createElement("class");
					attr = doc.createAttribute("name");
					attr.setValue("DASH_Main.DASH");
					class_element.setAttributeNode(attr);
					classes.appendChild(class_element);
			}

			// write the content into xml file			
				source = new DOMSource(doc);
			    try {
			    	StreamResult result = new StreamResult(new File(xmlFilePath+".xml"));
					transformer.setOutputProperty(OutputKeys.INDENT, "yes");
					transformer.transform(source, result);

					System.out.println("File saved!");
			    } catch (Exception e) {
			        throw new RuntimeException(e); // simple exception handling, please review it
			    }
			

		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
		//return true;
	} 
	
}