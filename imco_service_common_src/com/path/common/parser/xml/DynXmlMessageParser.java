package com.path.common.parser.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.path.dbmaps.vo.IMCO_DYN_FILE_TAGVO;
import com.path.imco.bo.dynamicfiles.DynamicFileStructureConstants;
import com.path.imco.vo.dynamicfiles.DynamicFileStructureCO;

public class DynXmlMessageParser 
{
	public static List<DynamicFileStructureCO> dynamicFileStructureCOs;
	private static StringBuilder parentList = null;
	
	public static List<DynamicFileStructureCO> parseMessage(String message, List<?> tagList, File file) throws FileNotFoundException, Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		dynamicFileStructureCOs = new ArrayList<>();

		Document document = builder.parse(file);
		NodeList nodeList = document.getElementsByTagName(message);
		Node node = null;
		IMCO_DYN_FILE_TAGVO tagsvo;
		DynamicFileStructureCO tagco = null;
		
		for (int i = 0; i < nodeList.getLength(); i++)
		{
			node = nodeList.item(i);
			for (int j = 0; j < tagList.size(); j++)
			{
				tagco = (DynamicFileStructureCO) tagList.get(j);
				tagsvo = tagco.getDyn_FILE_TAGVO();
				parseFileTagValues(node, tagsvo, message);
			}
			System.out.println("\n======================================================");
		}
		return dynamicFileStructureCOs;
	}

	private static void parseFileTagValues(Node node, IMCO_DYN_FILE_TAGVO tagsvo, String message)
	{
		NodeList childNodeList = null;
		Node childNode = null;
		childNodeList = node.getChildNodes();
		String parentPath = null;
		if(node instanceof Element)
		{
			 Element docElement = (Element) node;
			 childNodeList = docElement.getElementsByTagName(tagsvo.getTAG_NAME());
			 List<String> lstValue = null;
			 //If Tag is marked as List Create list to add All values
			 if((DynamicFileStructureConstants.STR_ONE).equals(tagsvo.getIS_MULTIPLE_YN()))
			 {
				 lstValue = new ArrayList<String>();
				 for (int i = 0; i < childNodeList.getLength(); i++)
				 {
					 childNode = childNodeList.item(i);
					 parentList = new StringBuilder();
					 allParentList(childNode, message);
					 parentPath = parentList.toString()+message;
					 if (tagsvo.getTAG_NAME().equalsIgnoreCase(childNode.getNodeName()) && tagsvo.getPARENT_PATH().equals(parentPath))
					 {
						 lstValue.add(childNode.getTextContent());
					 }
				 }
				 System.out.println("\n"+tagsvo.getPARENT_PATH()+" ==> "+tagsvo.getTAG_NAME()+" : "+lstValue);
			 }
			 //Else If Tag is an XML attribute parse it
			 else if((DynamicFileStructureConstants.STR_ONE).equals(tagsvo.getIS_ATTR_YN()))
			 {
				 childNodeList = docElement.getElementsByTagName(tagsvo.getPARENT_PATH().split(" > ")[0]);
				 if(childNodeList.getLength() > 0)
				 {
					 for (int i = 0; i < childNodeList.getLength(); i++)
					 {
						 childNode = childNodeList.item(i);
						 NamedNodeMap attributes = childNode.getAttributes();
						 Attr attr = (Attr) attributes.getNamedItem(tagsvo.getTAG_NAME());
						 System.out.println(tagsvo.getTAG_NAME()+" : " + attr.getNodeValue());
					 }
				 }
				 else
				 {
					 NamedNodeMap attributes = docElement.getAttributes();
					 Attr attr = (Attr) attributes.getNamedItem(tagsvo.getTAG_NAME());
					 System.out.println(tagsvo.getTAG_NAME()+" : " + attr.getNodeValue());
				 }
			 }
			 // else parse all as normal single valued tag
			 else
			 {
				 for (int i = 0; i < childNodeList.getLength(); i++)
				 {
					 childNode = childNodeList.item(i);
					 parentList = new StringBuilder();
					 allParentList(childNode, message);
					 parentPath = parentList.toString()+message;
					 if (tagsvo.getTAG_NAME().equalsIgnoreCase(childNode.getNodeName()) && tagsvo.getPARENT_PATH().equals(parentPath) &&
						childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getChildNodes().getLength() == 1)
					 {
						System.out.println(tagsvo.getTAG_NAME()+" : " + childNode.getTextContent()+"\t");
					}//end if checking Parents
				 }//End Loop
			 }//End else
		}//end if 
	}//end recursive method
	
	/*method to construct parent path*/
	private static void allParentList(Node node, String message)
	{
		if(node.getParentNode() != null && !message.equals(node.getParentNode().getNodeName()))
		{
			parentList.append(node.getParentNode().getNodeName()+" > ");
			allParentList(node.getParentNode(), message);
		}
	}
}
