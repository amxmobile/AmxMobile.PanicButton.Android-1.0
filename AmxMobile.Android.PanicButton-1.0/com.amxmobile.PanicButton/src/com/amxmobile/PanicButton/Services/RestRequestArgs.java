package com.amxmobile.PanicButton.Services;

import java.io.StringWriter;
import java.util.Dictionary;
import java.util.Hashtable;

import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

public class RestRequestArgs extends Hashtable<String, String> 
{
	public RestRequestArgs(String operation)
	{
		this.put("operation", operation);
	}

	public String ToXml() throws Exception
	{
		XmlSerializer xml = Xml.newSerializer();
		
		// output...
		StringWriter writer = new StringWriter();
		try
		{
			xml.setOutput(writer);
			
			// start...
			xml.startTag(null, "RestArgs");
			
			// values...
			for(String name : keySet()) 
			{
				xml.startTag(null, name);
				xml.text(get(name));
				xml.endTag(null, name);
			}
			
			// end...
			xml.endTag(null, "RestArgs");
			
			// flush and return...
			xml.flush();
			writer.flush();
			return writer.toString();
		}
		finally
		{
			if(writer != null)
				writer.close();
		}
	}
}
