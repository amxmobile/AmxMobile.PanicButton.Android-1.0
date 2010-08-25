package com.amxmobile.PanicButton.Runtime;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Helpers
{
	public static Document LoadXml(String xml) throws Exception
	{
		// create...
		ByteArrayInputStream stream = new ByteArrayInputStream(xml.getBytes());
		try
		{
			return LoadXml(stream);
		}
		finally
		{
			if (stream != null)
				stream.close();
		}
	}

	private static Document LoadXml(InputStream stream) throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);

		// builder...
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(stream);

		// return...
		return doc;
	}
	
	public static String GetHttpEntityContent(HttpEntity entity) throws Exception
	{
		// if we have a null entity, return null...
		if(entity == null)
			return null;
		
		// get...
		InputStreamReader stream = new InputStreamReader(entity.getContent());
		StringBuilder builder = new StringBuilder();
		try
		{
			BufferedReader reader = new BufferedReader(stream);
			while(true)
			{
				String buf = reader.readLine();
				if(buf == null)
					break;
				
				// add...
				builder.append(buf);
			}
		}
		finally
		{
			if(stream != null)
				stream.close();
		}
		
		// return...
		return builder.toString();
	}
	
	public static String GetStringValueForNode(Node item) throws Exception
	{
		if (item instanceof Element)
		{
			StringBuilder builder = new StringBuilder();
			NodeList children = item.getChildNodes();
			for (int index = 0; index < children.getLength(); index++)
				builder.append(children.item(index).getNodeValue());

			// return...
			return builder.toString();
		}
		else
			throw new Exception(String.format("Cannot handle '%s'.", item.getClass()));
	}
}
