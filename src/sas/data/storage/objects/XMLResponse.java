/**
 * 
 */
package sas.data.storage.objects;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author VASUDEV
 *
 * Contains functionality to create XML response for HTTP resposne.
 */
public class XMLResponse {
	
	private Document document;
	private Element element;
	
	public XMLResponse()
	{
		try {
			
			this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			this.document.setXmlVersion("1.0");
			this.document.setXmlStandalone(true);
			this.element = this.document.createElement("response");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public XMLResponse(String responseTagName)
	{
		try {
			
			this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			this.element = this.document.createElement(responseTagName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void SetNodeValue(String response) 
	{
		try {

			this.element.appendChild(this.document.createTextNode(response));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String toString()
	{
		try {
			DOMSource domSource = new DOMSource(this.element);
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			StringWriter stringWriter = new StringWriter();
			
			transformer.setOutputProperty(OutputKeys.INDENT, "no");
			transformer.setOutputProperty(OutputKeys.STANDALONE, "yes"); 
			transformer.transform(domSource, new StreamResult(stringWriter));
			return stringWriter.toString();
		} catch (Exception e) {
		}
		return "";
	}	
}
