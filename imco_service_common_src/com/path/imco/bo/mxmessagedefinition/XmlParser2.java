package com.path.imco.bo.mxmessagedefinition;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ibm.xml.parser.Parser;
import com.ibm.xml.parser.TXDocument;

public class XmlParser2 {

	public static void main(String[] args) {
		// Pick off the XML file name from the command-line arguments.
		
		File file  = new File("E:\\imall project data\\Payment Gateway\\pac8.xml");
	      try {
	        // Get the XML file name, e.g., "student.xml".
		FileReader fr = new FileReader (file);

	        // Create a parser instance.
		Parser p = new Parser(file.getName());

	        // These two will decide whether the parser will
	        // retain whitespace and comments from the original file.
		p.setKeepComment (false);
		p.setPreserveSpace (false);

	        // Now parse and create a document tree as a result.
	        // Note that we are passing a FileReader wrapped around
	        // the file.
		TXDocument doc = p.readStream(fr);

	        // Once we have the tree, extract the root.
		Element root = doc.getDocumentElement ();

	        // Now traverse the tree.
		System.out.println ("TRAVERSE ...");
		traverse (root);

	        // Alternatively, here is a scan by TAG name
	        // for the tag "NAME".
		System.out.println ("BY THE TAG 'NAME': ");
		NodeList nl = doc.getElementsByTagName ("NAME");
		int size = nl.getLength();
		for (int i=0; i<size; i++)
		  traverse (nl.item(i));
	      }
	      catch (IOException e) {
		System.out.println ("Usage: java xmltest <filename>");
	      }
	}
	
	// Traverse a subtree whose root is the parameter.

	  public static void traverse (Node n)
	  {
	    // Extract node info:
	    String nodename = n.getNodeName();
	    String valueStr = n.getNodeValue();
	    // Print and continue traversing.
	    System.out.println ("Node: " + n.getNodeName() + " value=[" + valueStr + "]");

	    // Now traverse the rest of the tree in depth-first order.
	    if (n.hasChildNodes()) {
	      // Get the children in a list.
	      NodeList nl = n.getChildNodes();
	      // How many of them?
	      int size = nl.getLength();
	      for (int i=0; i<size; i++)
	        // Recursively traverse each of the children.
		traverse (nl.item(i));
	    }
	  }

}
