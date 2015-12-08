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

	public static void main(String argv[]) throws Exception {
		
		String localDir = System.getProperty("user.dir");
		String[] parameter_value={"CHROME","TS-LoginMyNe.xls","TS-LoginMyNe_Ed","Open Browser and Login","loginScreens_objects.properties"};
		String xmlFilePath = localDir+"\\xmltmp\\xmlfile.xml";
		DOMSource source = createXMLFile(parameter_value);
		
		prettyFormat(source, xmlFilePath);
	}
	
	
	public static DOMSource createXMLFile(String[] parameterValues){

		DOMSource source = null;
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMImplementation domImpl = null;
			Document doc = null;
			DocumentType doctype = null;
			Attr attr = null;
			Element suite, test, browser, parameter, classes, class_element;

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
				attr.setValue("test");
				suite.setAttributeNode(attr);

			// Add the test elements
			test = doc.createElement("test");
			suite.appendChild(test);

			// set attribute to test
			attr = doc.createAttribute("name");
				attr.setValue("Login to my.ne");
				test.setAttributeNode(attr);
			attr = doc.createAttribute("verbose");
				attr.setValue("2");
				test.setAttributeNode(attr);
			
				//add the first parameter element webBrowser
			browser = doc.createElement("parameter");
				attr = doc.createAttribute("name");
					attr.setValue(parameterNames[0]);
					browser.setAttributeNode(attr);
				attr = doc.createAttribute("value");
					attr.setValue(parameterValues[0]);
					browser.setAttributeNode(attr);
				test.appendChild(browser);
				
			//Add a Comment
				Comment commentElement = doc.createComment("Connection to my.ne");
					test.insertBefore(commentElement, browser);
					
			//insert the rest of the parameters
			for (int i=1;i<parameterNames.length;i++){
				parameter = doc.createElement("parameter");
				attr = doc.createAttribute("name");
					attr.setValue(parameterNames[i]);
					parameter.setAttributeNode(attr);
				attr = doc.createAttribute("value");
					attr.setValue(parameterValues[i]);
					parameter.setAttributeNode(attr);
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


			// write the content into xml file
						
				source = new DOMSource(doc);
			

		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
		return source;

	}
	
	public static void prettyFormat(DOMSource source, String xmlFilePath) throws Exception {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		transformerFactory.setAttribute("indent-number", 2);
		Transformer transformer = transformerFactory.newTransformer();
	    try {
	    	StreamResult result = new StreamResult(new File(xmlFilePath));
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);

			System.out.println("File saved!");
	    } catch (Exception e) {
	        throw new RuntimeException(e); // simple exception handling, please review it
	    }
	}
	
	
}